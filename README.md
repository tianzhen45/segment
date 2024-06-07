## 启动
修改application-dev.yml中 spring.datasource.url 将路径修改为本地自定义路径

运行SegmentApplication

## 初始化数据库
项目使用嵌入式数据库h2,连接地址使用自定义文件路径存储数据

(例如：jdbc:h2:file:D:\DevSpace\DevelopSpace\segment\h2db)

### 进入数据库管理页面
http://localhost:9999/seg/h2-console

- 数据库连接信息
> jdbc:h2:file:D:\DevSpace\DevelopSpace\segment\h2db (按照配置application.yml - spring.datasource.url)

> sa

> password

### 复制数据库初始化脚本内容，到管理页面中运行
- src/main/resource/init_schema.sql
- src/main/resource/init_data_sql


## 本地Docker构建运行
完成前后端项目打包后，修改docker-compose.yml
```
version: '3'
services:
  segment:
    build:
      context: .
      dockerfile: Dockerfile
    ports:
      - "9999:9999"
    container_name: segment
  portal:
    build:
      context: ../../DevLab/segment-portal  #修改为前端项目路径
      dockerfile: Dockerfile
    ports:
      - "80:80"
    links:
      - segment
    container_name: portal
    depends_on: [segment]
```
右键docker-compose.yml，运行docker-compose.yml


## Docker部署
### 本地保存镜像到tar文件，上传至服务器
> docker save -o portal.tar segment-portal

> docker save -o segment.tar segment-segment

### 服务器上加载镜像
> docker load -i segment.tar

> docker load -i portal.tar

### 创建docker-compose.yml
```
services:
  segment:
    image: segment-segment:latest
    ports:
      - "9999:9999"
    container_name: segment  
  portal:
    image: segment-portal:latest
    ports:
      - "80:80"
    links:
      - segment
    container_name: portal
    depends_on: [segment]
```
运行应用
> docker compose up -d