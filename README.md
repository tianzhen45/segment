## 启动
修改application-dev.yml中 spring.datasource.url 将路径修改为本地自定义路径

运行SegmentApplication

## 初始化数据库
项目使用嵌入式数据库h2，伴随Spring应用一同启动,连接地址使用自定义文件路径存储数据

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
右键docker-compose.yml，运行docker-compose.yml完成启动


## Docker部署（前后端一同部署）
### 本地保存镜像到tar文件，上传至服务器
此步骤需先完成前后端项目的本地docker运行构建，生成镜像后进行保存、上传
> docker save -o portal.tar segment-portal

> docker save -o segment.tar segment-segment

### 服务器上加载镜像
服务器需完成安装docker及docker compose环境，参考网上教程即可。

将上一步骤中生成的tar包上传至服务器中，并执行加载镜像
> docker load -i segment.tar

> docker load -i portal.tar

### 创建docker-compose.yml
在服务器任意目录下创建docker-compose.yml文件
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
### 启动应用
默认使用当前目录下的docker-compose.yml文件
> docker compose up -d


## 非Docker后端单独部署
前端部署参考segment-portal项目中README(非Docker部署) 

服务器需安装java环境
### 上传字典文件配置
复制config/user.dict 文件到 application.prod myapp:dict-path中指定目录中/app/config/user.dict
### 打包并上传包，运行后端Spring应用 
> java -jar segment-1.0-SNAPSHOT.jar --spring.profiles.active=prod