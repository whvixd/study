#!/usr/bin/env bash

name="aaa.c--.c"

echo ${name/.c/} # / 将 .c 替换成空格 ，匹配一次
echo ${name//.c/} #// 将 .c 替换成空格 ，匹配所有
echo ${name/#a/b} #/# 以什么开头，替换
echo ${name/%.c/} # /% 以什么结尾，替换

# -d 文件存在 -f 文本存在 -n 参数是否有值 -x 是否可执行文件
if [[ -d /Users/didi/Documents/workspace/idea/study/shell ]]
then
    echo "文件存在"
fi

if [[ -f /Users/didi/Documents/workspace/idea/study/shell/print_good.c ]]
then
    echo "文本存在"
fi