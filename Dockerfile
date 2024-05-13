# 使用官方 Maven 映像作為基礎映像
FROM maven:3.8.4-openjdk-17 AS build

# 將工作目錄設置為 /app
WORKDIR /app

# 複製整個專案到容器中
COPY . .

# 使用 Maven 打包應用程序
RUN mvn clean package -DskipTests

# 使用 Java 17 版本
FROM openjdk:17-oracle

# 將打包後的 Spring Boot 應用程序 JAR 文件複製到 /app 目錄
COPY target/spring-boot-deployment-0.0.1-SNAPSHOT.jar .

# 定義容器啟動時運行的命令
CMD ["java", "-jar", "spring-boot-deployment-0.0.1-SNAPSHOT.jar"] > /dev/stdout