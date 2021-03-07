#!/usr/bin/env bash
#Version to install
mysql_version="8.0.13";
log_expired_days="15";
mysql_access_port="3306";
character="utf8mb4";
mysql_root_password="root";
#example 192.168.174.129:5000;
registryIp=192.168.174.129:5000;
echo "start install docker mysql:${mysql_version}"
echo ${registryIp}\/mysql:${mysql_version}
docker pull ${registryIp}/mysql:${mysql_version} && docker images && echo "mysql:${mysql_version} image pull success"
mkdir -p /opt/mysql${mysql_version} && mkdir -p /opt/mysql/conf && mkdir -p /opt/mysql/logs && mkdir -p /opt/mysql/data
rm /opt/mysql/my.cnf
touch /opt/mysql/my.cnf
#Mysql configs
cat > /opt/mysql/my.cnf << EOF
[mysqld]
user=mysql
character-set-server = ${character}
default_authentication_plugin=mysql_native_password
secure_file_priv=/var/lib/mysql
expire_logs_days=${log_expired_days}
sql_mode=STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION
max_connections=1000
collation-server = ${character}_unicode_ci
#All init connection will use ${character} character set
init_connect='SET NAMES ${character}'
#Ignore client character set
skip-character-set-client-handshake = true

[client]
default-character-set=${character}

[mysql]
default-character-set=${character}
EOF
#open firewall port
echo "open port ${mysql_access_port}"
firewall-cmd --zone=public --permanent --add-port=${mysql_access_port}/tcp && echo `firewall-cmd --reload`
#start mysql
echo "starting mysql docker ...."
docker run --restart=always --privileged=true \
-v /opt/mysql/data/:/var/lib/mysql \
-v /opt/mysql/logs/:/var/log/mysql \
-v /opt/mysql/conf/:/etc/mysql \
-v /opt/mysql/my.cnf:/etc/mysql/my.cnf \
-p ${mysql_access_port}:3306 --name mysql${mysql_version} \
-e MYSQL_ROOT_PASSWORD=${mysql_root_password} -d ${registryIp}/mysql:${mysql_version}


