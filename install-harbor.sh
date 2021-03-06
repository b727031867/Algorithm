#!/usr/bin/env bash
## test
set -e
# -a 是否需要配置ssh无密码连接 1为是 0为否
# -b 域名  例如 yourdomain.com
# -c A机IP  默认是 192.168.174.130
# -d B机IP  默认是 192.168.174.131
isNeedSSH="0"
domain=tartarus.com
A_IP=192.168.174.130
B_IP=192.168.174.131
#镜像仓库要读取的网卡名称
INTERNET_NAME=eth0
#镜像仓库的IP(默认获取本机IP)
REGISTER_IP=`ip a show dev ${INTERNET_NAME} | grep -w "inet" | awk '{print $2}' | awk -F '/' '{print $1}'`
harborOfflineInstaller="harbor-offline-installer-v2.2.0.tgz"
while getopts ":a:b:c:d:e:f:" optname
do
   case "$optname" in
     "a")
       isNeedSSH=$OPTARG
       echo "create ssh connection param is $OPTARG";;
     "b")
       domain=$OPTARG
       echo "domain value is $OPTARG";;
     "c")
       A_IP=$OPTARG
       echo "A ip is $OPTARG";;
     "d")
       B_IP=$OPTARG
       echo "B ip is $OPTARG";;
     "e")
       INTERNET_NAME=$OPTARG
       echo "current network card name is $OPTARG";;
     "f")
       REGISTER_IP=$OPTARG
       echo "current mirror warehouse ip is $OPTARG";;
     ":")
       echo "No argument value for option $OPTARG"
       exit 1;;
     "?")
       echo "Unknown option $OPTARG"
       exit 1;;
     *)
       echo "Unknown error while processing options"
       exit 1;;
   esac
done
if [[ isNeedSSH -eq "1" ]];
then
   ssh-keygen
   ssh-copy-id -i .ssh/id_rsa.pub  root@${A_IP}
   ssh-copy-id -i .ssh/id_rsa.pub  root@${B_IP}
fi
echo "start Generate a Certificate Authority Certificate"

openssl genrsa -out ca.key 4096
openssl req -x509 -new -nodes -sha512 -days 36500 \
-subj "/C=CN/ST=Beijing/L=Beijing/O=example/OU=Personal/CN=${domain}" \
-key ca.key \
-out ca.crt
echo "Generate down"
echo "start Generate a Server Certificate"
openssl genrsa -out ${domain}.key 4096
openssl req -sha512 -new \
   -subj "/C=CN/ST=Beijing/L=Beijing/O=example/OU=Personal/CN=${domain}" \
   -key ${domain}.key \
   -out ${domain}.csr
cat > v3.ext <<-EOF
authorityKeyIdentifier=keyid,issuer
basicConstraints=CA:FALSE
keyUsage = digitalSignature, nonRepudiation, keyEncipherment, dataEncipherment
extendedKeyUsage = serverAuth
subjectAltName = @alt_names

[alt_names]
DNS.1=${domain}
EOF
openssl x509 -req -sha512 -days 3650 \
   -extfile v3.ext \
   -CA ca.crt -CAkey ca.key -CAcreateserial \
   -in ${domain}.csr \
   -out ${domain}.crt
echo "Generate Server Certificate down"
echo "Start Provide the Certificates to Harbor and Docker"
mkdir -p /data/cert/
cp ${domain}.crt /data/cert/
cp ${domain}.key /data/cert/
openssl x509 -inform PEM -in ${domain}.crt -out ${domain}.cert
mkdir -p /etc/docker/certs.d/${domain}
cp ${domain}.cert /etc/docker/certs.d/${domain}/
cp ${domain}.key /etc/docker/certs.d/${domain}/
cp ca.crt /etc/docker/certs.d/${domain}/
systemctl restart docker
#配置系统信任证书
cp ${domain}.crt /etc/pki/ca-trust/source/anchors/${domain}.crt
update-ca-trust
#配置本机host文件
cat >> /etc/hosts << EOF
${REGISTER_IP} ${domain}
EOF
echo "End Provide the Certificates to Harbor and Docker"
echo "Start Provide the Certificates to Harbor and Docker at remote IP ${A_IP}"
#设置130机器信任此证书
ssh root@${A_IP} > /dev/null 2>&1 << eeooff
mkdir -p /etc/docker/certs.d/${domain}
cat >> /etc/hosts << EOF
${REGISTER_IP} ${domain}
EOF
exit
eeooff
scp ${domain}.cert root@${A_IP}:/etc/docker/certs.d/${domain}/
scp ${domain}.key root@${A_IP}:/etc/docker/certs.d/${domain}/
scp ca.crt root@${A_IP}:/etc/docker/certs.d/${domain}/
scp ${domain}.crt root@${A_IP}:/etc/pki/ca-trust/source/anchors/${domain}.crt
ssh root@${A_IP} > /dev/null 2>&1 << eeooff
systemctl restart docker
update-ca-trust
exit
eeooff
echo "END Start Provide the Certificates to Harbor and Docker at remote IP ${A_IP}"

#设置131机器信任此证书
ssh root@${B_IP} > /dev/null 2>&1 << eeooff
mkdir -p /etc/docker/certs.d/${domain}
cat >> /etc/hosts << EOF
${REGISTER_IP} ${domain}
EOF
exit
eeooff
scp ${domain}.cert root@${B_IP}:/etc/docker/certs.d/${domain}/
scp ${domain}.key root@${B_IP}:/etc/docker/certs.d/${domain}/
scp ca.crt root@${B_IP}:/etc/docker/certs.d/${domain}/
scp ${domain}.crt root@${B_IP}:/etc/pki/ca-trust/source/anchors/${domain}.crt
ssh root@${B_IP} > /dev/null 2>&1 << eeooff
systemctl restart docker
update-ca-trust
exit
eeooff
echo "END Start Provide the Certificates to Harbor and Docker at remote IP ${B_IP}"

echo "Start install harbor"

if [[ ! -f "${harborOfflineInstaller}" ]];
then
    echo "The ${harborOfflineInstaller} is not Exist!"
    exit 1
fi
firewall-cmd --zone=public --add-port=443/tcp --permanent
firewall-cmd --zone=public --add-port=4443/tcp --permanent
firewall-cmd --zone=public --add-port=80/tcp --permanent
firewall-cmd --reload
tar xvf ${harborOfflineInstaller} && cd harbor && touch harbor.yml
cat > harbor.yml <<EOF
# Configuration file of Harbor

# The IP address or hostname to access admin UI and registry service.
# DO NOT use localhost or 127.0.0.1, because Harbor needs to be accessed by external clients.
hostname: ${domain}

# http related config
http:
  # port for http, default is 80. If https enabled, this port will redirect to https port
  port: 80

# https related config
https:
  # https port for harbor, default is 443
  port: 443
  # The path of cert and key files for nginx
  certificate: /etc/docker/certs.d/${domain}/${domain}.cert
  private_key: /etc/docker/certs.d/${domain}/${domain}.key

# # Uncomment following will enable tls communication between all harbor components
# internal_tls:
#   # set enabled to true means internal tls is enabled
#   enabled: true
#   # put your cert and key files on dir
#   dir: /etc/harbor/tls/internal

# Uncomment external_url if you want to enable external proxy
# And when it enabled the hostname will no longer used
# external_url: https://reg.mydomain.com:8433

# The initial password of Harbor admin
# It only works in first time to install harbor
# Remember Change the admin password from UI after launching Harbor.
harbor_admin_password: Harbor12345

# Harbor DB configuration
database:
  # The password for the root user of Harbor DB. Change this before any production use.
  password: root123
  # The maximum number of connections in the idle connection pool. If it <=0, no idle connections are retained.
  max_idle_conns: 50
  # The maximum number of open connections to the database. If it <= 0, then there is no limit on the number of open connections.
  # Note: the default number of connections is 1024 for postgres of harbor.
  max_open_conns: 1000

# The default data volume
data_volume: /data

# Harbor Storage settings by default is using /data dir on local filesystem
# Uncomment storage_service setting If you want to using external storage
# storage_service:
#   # ca_bundle is the path to the custom root ca certificate, which will be injected into the truststore
#   # of registry's and chart repository's containers.  This is usually needed when the user hosts a internal storage with self signed certificate.
#   ca_bundle:

#   # storage backend, default is filesystem, options include filesystem, azure, gcs, s3, swift and oss
#   # for more info about this configuration please refer https://docs.docker.com/registry/configuration/
#   filesystem:
#     maxthreads: 100
#   # set disable to true when you want to disable registry redirect
#   redirect:
#     disabled: false

# Trivy configuration
#
# Trivy DB contains vulnerability information from NVD, Red Hat, and many other upstream vulnerability databases.
# It is downloaded by Trivy from the GitHub release page https://github.com/aquasecurity/trivy-db/releases and cached
# in the local file system. In addition, the database contains the update timestamp so Trivy can detect whether it
# should download a newer version from the Internet or use the cached one. Currently, the database is updated every
# 12 hours and published as a new release to GitHub.
trivy:
  # ignoreUnfixed The flag to display only fixed vulnerabilities
  ignore_unfixed: false
  # skipUpdate The flag to enable or disable Trivy DB downloads from GitHub
  #
  # You might want to enable this flag in test or CI/CD environments to avoid GitHub rate limiting issues.
  # If the flag is enabled you have to download the `trivy-offline.tar.gz` archive manually, extract `trivy.db` and
  # `metadata.json` files and mount them in the `/home/scanner/.cache/trivy/db` path.
  skip_update: false
  #
  # insecure The flag to skip verifying registry certificate
  insecure: false
  # github_token The GitHub access token to download Trivy DB
  #
  # Anonymous downloads from GitHub are subject to the limit of 60 requests per hour. Normally such rate limit is enough
  # for production operations. If, for any reason, it's not enough, you could increase the rate limit to 5000
  # requests per hour by specifying the GitHub access token. For more details on GitHub rate limiting please consult
  # https://developer.github.com/v3/#rate-limiting
  #
  # You can create a GitHub token by following the instructions in
  # https://help.github.com/en/github/authenticating-to-github/creating-a-personal-access-token-for-the-command-line
  #
  # github_token: xxx

jobservice:
  # Maximum number of job workers in job service
  max_job_workers: 10

notification:
  # Maximum retry count for webhook job
  webhook_job_max_retry: 10

chart:
  # Change the value of absolute_url to enabled can enable absolute url in chart
  absolute_url: disabled

# Log configurations
log:
  # options are debug, info, warning, error, fatal
  level: info
  # configs for logs in local storage
  local:
    # Log files are rotated log_rotate_count times before being removed. If count is 0, old versions are removed rather than rotated.
    rotate_count: 50
    # Log files are rotated only if they grow bigger than log_rotate_size bytes. If size is followed by k, the size is assumed to be in kilobytes.
    # If the M is used, the size is in megabytes, and if G is used, the size is in gigabytes. So size 100, size 100k, size 100M and size 100G
    # are all valid.
    rotate_size: 200M
    # The directory on your host that store log
    location: /var/log/harbor

  # Uncomment following lines to enable external syslog endpoint.
  # external_endpoint:
  #   # protocol used to transmit log to external endpoint, options is tcp or udp
  #   protocol: tcp
  #   # The host of external endpoint
  #   host: localhost
  #   # Port of external endpoint
  #   port: 5140

#This attribute is for migrator to detect the version of the .cfg file, DO NOT MODIFY!
_version: 2.2.0

# Uncomment external_database if using external database.
# external_database:
#   harbor:
#     host: harbor_db_host
#     port: harbor_db_port
#     db_name: harbor_db_name
#     username: harbor_db_username
#     password: harbor_db_password
#     ssl_mode: disable
#     max_idle_conns: 2
#     max_open_conns: 0
#   notary_signer:
#     host: notary_signer_db_host
#     port: notary_signer_db_port
#     db_name: notary_signer_db_name
#     username: notary_signer_db_username
#     password: notary_signer_db_password
#     ssl_mode: disable
#   notary_server:
#     host: notary_server_db_host
#     port: notary_server_db_port
#     db_name: notary_server_db_name
#     username: notary_server_db_username
#     password: notary_server_db_password
#     ssl_mode: disable

# Uncomment external_redis if using external Redis server
# external_redis:
#   # support redis, redis+sentinel
#   # host for redis: <host_redis>:<port_redis>
#   # host for redis+sentinel:
#   #  <host_sentinel1>:<port_sentinel1>,<host_sentinel2>:<port_sentinel2>,<host_sentinel3>:<port_sentinel3>
#   host: redis:6379
#   password:
#   # sentinel_master_set must be set to support redis+sentinel
#   #sentinel_master_set:
#   # db_index 0 is for core, it's unchangeable
#   registry_db_index: 1
#   jobservice_db_index: 2
#   chartmuseum_db_index: 3
#   trivy_db_index: 5
#   idle_timeout_seconds: 30

# Uncomment uaa for trusting the certificate of uaa instance that is hosted via self-signed cert.
# uaa:
#   ca_file: /path/to/ca

# Global proxy
# Config http proxy for components, e.g. http://my.proxy.com:3128
# Components doesn't need to connect to each others via http proxy.
# Remove component from `components` array if want disable proxy
# for it. If you want use proxy for replication, MUST enable proxy
# for core and jobservice, and set `http_proxy` and `https_proxy`.
# Add domain to the `no_proxy` field, when you want disable proxy
# for some special registry.
proxy:
  http_proxy:
  https_proxy:
  no_proxy:
  components:
    - core
    - jobservice
    - trivy

# metric:
#   enabled: false
#   port: 9090
#   path: /metrics
EOF
sh install.sh

