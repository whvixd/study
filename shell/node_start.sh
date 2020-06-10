#!/bin/bash
cd /Users/xxx/Documents/workspace/webstorm/web_project

#  yarn 镜像配置
yarn_registry=`yarn config get registry`
if test ${yarn_registry} != "http://registry.npm.taobao.org/"
then
	`yarn config set registry http://registry.npm.taobao.org/`
	echo "switch registry to http://registry.npm.taobao.org/ success"
fi

# nvm 环境配置
export NVM_DIR="/Users/xxx/.nvm"
[ -s "$NVM_DIR/nvm.sh" ] && \. "$NVM_DIR/nvm.sh"  # This loads nvm

# 切换node版本
node_version=`nvm current`
if test ${node_version} != "v12.16.3"
then
	nvm use v12.16.3
fi
# 启动项目
yarn install

# 另启线程监测node是否启动，启动后打开首页
{
while true
do
# 获取9090端口的进程号
pid=`lsof -i:9090|awk '{print $2}'|grep 'PID'`
echo ${pid}
# 如果进程号不为空，则打开浏览器
# =~ 不包含，默认数字转字符
if   [[ ${pid} != "" ]]
then
open -a "Google Chrome.app" http://baidu.com
break
else
sleep 5
fi
done
} &

npm run dev