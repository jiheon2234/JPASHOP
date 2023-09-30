FROM mariadb:latest
COPY docker.cnf  /etc/mysql/conf.d/docker.cnf
COPY init.sql /docker-entrypoint-initdb.d/

# ENV MYSQL_ROOT_PASSWORD=0000
ENV MYSQL_DATABASE=springjpa
ENV MYSQL_ALLOW_EMPTY_PASSWORD=yes



CMD ["--character-set-server=utf8mb4", "--collation-server=utf8mb4_unicode_ci"]
