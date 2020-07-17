#!/bin/bash

# git add -all ; git commit -am "优化" ; git push 合成一个命令

add_flag=`git status | grep 'Untracked'`
if [[ ${add_flag}!="" ]]
then
	git add --all
fi

commit_log=$1

if [ ! -n "${commit_log}" ]; then
	commit_log="优化"
fi

git commit -am "${commit_log}"

git push
