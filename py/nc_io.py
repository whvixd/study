#!/usr/bin/env python3
from netCDF4 import Dataset
import numpy as np
import os
# todo 修改下python解释器版本
path = "D:\\PycharmProjects\\DataProcess\\MeteData\\2018010203_WS_TEM_SP_BLH2.nc"
dst = Dataset(path, mode='r', format="netCDF4")

# 查看nc文件中包含了什么
print(dst)
print('---------------------------------------------------------')
# 查看nc文件有哪些变量
print(dst.variables.keys())
print('--------------------------------------------------------')
# 查看nc文件中变量的属性名称
print(dst.variables.keys())
for i in dst.variables.keys():
    print('%s: %s' % (i, dst.variables[i].ncattrs()))
print('--------------------------------------------------------')
# 查看nc文件中变量的属性的具体信息
print(dst.variables.keys())
for i in dst.variables.keys():
    print(dst.variables[i])
    print('\n')
print('-------------------------------------------------------')

# 获取维度的sizes信息，获得索引范围
for i in dst.dimensions.keys():
    print('%s_sizes: %s' % (i, dst.dimensions[i].size))
print('------------------------------------------------------')
# 循环提取数据
# for i in dst.variables.keys():
#     if i not in dst.dimensions.keys():