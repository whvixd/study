#!/usr/bin/env bash

#--- run_c test.c  直接编译运行 ---#

# 获取目录
path=`pwd`

c_filename=$1 # c文件
c_target=${c_filename} # c编译文件

# 判断输入是否以 .c 结尾，补充.c
if [[ !c_filename =~ '.c'$ ]]
then
    c_target=$1
    c_filename="${c_filename}.c"
else
    c_target=${c_target/%.c/}
fi

# echo "c_filename:${c_filename}"
# echo "c_target:${c_target}"

# 判断是否存在
if [[ ! -f ${path}/${c_filename} ]]
then
    echo "${path}/${c_filename} does not exist"
    exit
fi

# 判断debug文件是否存在
if [[ ! -d ${path}/../debug ]]
then
#    echo ${path}/../debug" does not exist"
#    exit
#    2020-11-12 修改为若debug文件没有，则创建
    mkdir ${path}/../debug
fi

# 编译到 ../debug 路径中
`gcc -o ${path}/../debug/${c_target} ${path}/${c_filename}`

# 执行C程序
${path}/../debug/${c_target}