#!/usr/bin/env bash
set -x
set -e
sudo yum remove docker \
                  docker-client \
                  docker-client-latest \
                  docker-common \
                  docker-latest \
                  docker-latest-logrotate \
                  docker-logrotate \
                  docker-engine
sudo yum install -y yum-utils
sudo yum-config-manager \
    --add-repo \
    https://download.docker.com/linux/centos/docker-ce.repo
sudo yum install docker-ce-19.03.15 docker-ce-cli-19.03.15 containerd.io
touch /etc/docker/daemon.json
cat > /etc/docker/daemon.json << EOF
{
"registry-mirror": [
"https://hub-mirror.c.163.com",
"https://mirror.baidubce.com"
],
"insecure-registries": [
"192.168.174.129:5000"
]
}
EOF
sudo systemctl restart docker
