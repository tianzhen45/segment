FROM openjdk:12-alpine

# 设置工作目录
WORKDIR /app

# 将你的项目的.jar文件复制到容器的/app目录下
COPY target/segment-1.0-SNAPSHOT.jar /app
COPY config/* /app/config/

# 暴露应用程序的端口
EXPOSE 9876

# 运行你的应用程序
CMD ["java", "-jar", "segment-1.0-SNAPSHOT.jar","--spring.profiles.active=prod"]