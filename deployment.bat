@echo off

REM 第一步：使用Maven打包应用
echo 正在使用Maven打包应用...
"C:\Program Files\Java\jdk-11.0.15.1\bin\java.exe" -Dmaven.multiModuleProjectDirectory=E:\DevelopSpace\segment -Dmaven.home=D:\DevSoft\apache-maven-3.3.9 -Dclassworlds.conf=D:\DevSoft\apache-maven-3.3.9\bin\m2.conf "-Dmaven.ext.class.path=D:\Program Files\JetBrains\IntelliJ IDEA 2023.1\plugins\maven\lib\maven-event-listener.jar" "-javaagent:D:\Program Files\JetBrains\IntelliJ IDEA 2023.1\lib\idea_rt.jar=40396:D:\Program Files\JetBrains\IntelliJ IDEA 2023.1\bin" -Dfile.encoding=UTF-8 -classpath D:\DevSoft\apache-maven-3.3.9\boot\plexus-classworlds-2.5.2.jar org.codehaus.classworlds.Launcher -Didea.version=2023.1 -s D:\DevSoft\apache-maven-3.3.9\conf\settings.xml -Dmaven.repo.local=D:\primeton\repository_primeton -DskipTests=true package

REM 第二步：删除Docker镜像
echo 正在删除Docker镜像...
docker rmi -f segment-seg_app

REM 第三步：运行Docker Compose
echo 正在运行Docker Compose...
docker-compose up -d

echo 完成！
pause