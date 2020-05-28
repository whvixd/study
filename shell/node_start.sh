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
npm run dev