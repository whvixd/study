#!/usr/bin/env bash

# 获取输入
commit_log=$1

# 输入是否为空判断
if [ ! -n "$commit_log" ]; then
    # 赋值
    commit_log="default"
    # 打印
    echo "NULL"
else
    echo "NOT NULL"
fi

echo ${commit_log}