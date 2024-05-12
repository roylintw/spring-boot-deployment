# 使用 Java 17 版本
FROM openjdk:17-oracle

# 將工作目錄設置為 /app
WORKDIR /app

# 將打包後的 Spring Boot 應用程序 JAR 文件複製到 /app 目錄
COPY target/spring-boot-deployment-0.0.1-SNAPSHOT.jar .

# 定義容器啟動時運行的命令
CMD ["java", "-jar", "your-application.jar"] > /dev/stdout