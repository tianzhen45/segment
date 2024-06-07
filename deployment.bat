chcp 65001

@echo off

echo maven packing...
"C:\Program Files\Java\jdk-11.0.15.1\bin\java.exe" -Dmaven.multiModuleProjectDirectory=D:\DevSpace\DevelopSpace\segment -Dmaven.home=D:\DevSoft\apache-maven-3.3.9 -Dclassworlds.conf=D:\DevSoft\apache-maven-3.3.9\bin\m2.conf "-Dmaven.ext.class.path=D:\Program Files\JetBrains\IntelliJ IDEA 2023.3.5\plugins\maven\lib\maven-event-listener.jar" "-javaagent:D:\Program Files\JetBrains\IntelliJ IDEA 2023.3.5\lib\idea_rt.jar=3928:D:\Program Files\JetBrains\IntelliJ IDEA 2023.3.5\bin" -Dfile.encoding=UTF-8 -classpath D:\DevSoft\apache-maven-3.3.9\boot\plexus-classworlds-2.5.2.jar org.codehaus.classworlds.Launcher -Didea.version=2023.3.5 -s D:\DevSoft\apache-maven-3.3.9\conf\settings_primeton.xml -DskipTests=true package

echo portal packing...
call cd ../../Devlab/segment-portal && call npm run build

echo deleting old images...
docker rmi -f segment-segment
docker rmi -f segment-portal

echo running Docker Compose...
cd ../../DevelopSpace/segment && docker-compose up -d

echo finished!
pause