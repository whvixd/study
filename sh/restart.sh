#!/bin/sh

_pid=`jps | grep xxx | head -n1 | awk '{print $1}'`

kill -9 ${_pid}
echo "kill pid:${_pid} success"

/usr/local/jdk1.8.0_65/bin/java -DcarreraConsumer.enabled=false -jar xxx.jar -Xms512M -Xmx512M -Xmn128M -XX:MetaspaceSize=128M -XX:MaxMetaspaceSize=128M -XX:SurvivorRatio=8 -XX:+UseConcMarkSweepGC -XX:+UseParNewGC -verbose:gc -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xloggc:/xxx/logs/gc.log -XX:CMSInitiatingOccupancyFraction=40 -XX:+UseCMSInitiatingOccupancyOnly -XX:+CMSClassUnloadingEnabled -XX:+DisableExplicitGC -XX:+PrintPromotionFailure -XX:+UseCMSCompactAtFullCollection -XX:+UseCompressedOops -XX:ParallelGCThreads=4 -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/xxx/logs/oom.dump &