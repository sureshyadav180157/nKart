# Use the official MySQL image as a base
FROM mysql:8.0.30

# Set environment variables for MySQL
ENV MYSQL_ROOT_PASSWORD=root
ENV MYSQL_DATABASE=nKart
ENV MYSQL_USER=mysqluser
ENV MYSQL_PASSWORD=mysqlpass

# Copy the initialization script into the /docker-entrypoint-initdb.d directory
COPY init.sql /docker-entrypoint-initdb.d/

# Expose the MySQL port
EXPOSE 3306