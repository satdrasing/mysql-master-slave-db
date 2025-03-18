https://javamana.com/2021/10/20211004065230987I.html
https://stackoverflow.com/questions/68012475/how-to-do-database-routing-in-read-only-and-read-write-with-spring


### Docker
```
docker network create mysql-network

docker container run --name mysql-master --network mysql-network --privileged=true -v /home/satendra/docker-data/mysql-master:/var/lib/mysql -p 3307:3306 -e MYSQL_ROOT_PASSWORD=654321 -d mysql:latest

docker container run --name mysql-slave  --network mysql-network --privileged=true -v /home/satendra/docker-data/mysql-slave:/var/lib/mysql -p 3308:3306  -e MYSQL_ROOT_PASSWORD=654321 -d mysql:latest

```
### master
```
docker container exec -it mysql-master /bin/bash

microdnf install -y vim
vim  /etc/mysql/my.cnf

[mysqld]
server-id=1
log-bin=mysql-bin
mysql_native_password=ON

docker container restart mysql-master

docker container exec -it mysql-master /bin/bash
mysql -uroot -p654321

CREATE USER 'slaveaccount'@'%'  IDENTIFIED BY '654321';
GRANT REPLICATION SLAVE ON *.* TO  'slaveaccount'@'%' ;

ALTER USER 'slaveaccount'@'%' IDENTIFIED WITH mysql_native_password BY '654321';

flush privileges;
show master status;
select host, user from mysql.user;
```

### Slave
```
docker container exec -it mysql-slave /bin/bash

microdnf install -y vim
vim /etc/mysql/my.cnf

[mysqld]
server-id=2
log-bin=mysql-bin
mysql_native_password=ON

docker container restart mysql-slave

docker container exec -it mysql-slave /bin/bash
mysql -uroot -p654321

change master to master_host='172.18.0.2', master_user='slaveaccount', master_password='654321', master_port=3306, master_log_file='mysql-bin.000002', master_log_pos=10223, master_connect_retry=30;

start slave; //stop  slave;
show slave status \G
```
