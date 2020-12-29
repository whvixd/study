#!/usr/bin/env bash

name="aaa.c--.c"

echo ${name/.c/} # / 将 .c 替换成空格 ，匹配一次
echo ${name//.c/} #// 将 .c 替换成空格 ，匹配所有
echo ${name/#a/b} #/# 以什么开头，替换
echo ${name/%.c/} # /% 以什么结尾，替换

# -d 文件存在 -f 文本存在 -n 参数是否有值 -x 是否可执行文件
if [[ -d /Users/xx/Documents/workspace/idea/study/shell ]]
then
    echo "文件存在"
fi

if [[ -f /Users/xx/Documents/workspace/idea/study/shell/print_good.c ]]
then
    echo "文本存在"
fi

##################################文件表达式##################################
#        -e filename 如果 filename存在，则为真
#        -d filename 如果 filename为目录，则为真
#        -f filename 如果 filename为常规文件，则为真
#        -L filename 如果 filename为符号链接，则为真
#        -r filename 如果 filename可读，则为真
#        -w filename 如果 filename可写，则为真
#        -x filename 如果 filename可执行，则为真
#        -s filename 如果文件长度不为0，则为真
#        -h filename 如果文件是软链接，则为真
#        filename1 -nt filename2 如果 filename1比 filename2新，则为真。
#        filename1 -ot filename2 如果 filename1比 filename2旧，则为真。
#        *linux 下使用man test指令查看
#
#        整数条件表达式，大于，小于，shell里没有> 和< ,会被当作尖括号，只有-ge,-gt,-le,lt
#        -eq  -ne  -lt  -nt只能用于整数，不适用于字符串，字符串等于用赋值号=
#
#        整数变量表达式
#        -eq 等于
#        -ne 不等于
#        -gt 大于
#        -ge 大于等于
#        -lt 小于
#        -le 小于等于
#
#        字符串变量表达式
#        If  [ $a = $b ]                 如果string1等于string2，则为真, 字符串允许使用赋值号做等号
#        if  [ $string1 !=  $string2 ]   如果string1不等于string2，则为真
#        if  [ -n $string  ]             如果string 非空(非0），返回0(true)
#        if  [ -z $string  ]             如果string 为空，则为真
#        if  [ $sting ]                  如果string 非空，返回0 (和-n类似)
#
#        逻辑非 !                  	条件表达式的相反
#        if [ ! 表达式 ]
#        if [ ! -d $num ]            如果不存在目录$num
#
#        逻辑与 –a                   条件表达式的并列
#        if [ 表达式1  –a  表达式2 ]
#
#        逻辑或 -o                   条件表达式的或
#        if [ 表达式1  –o 表达式2 ]
#           逻辑表达式
#
#        表达式与前面的=  != -d –f –x -ne -eq -lt等合用
#        逻辑符号就正常的接其他表达式，没有任何括号（ ），就是并列
#        if [ -z "$JHHOME" -a -d $HOME/$num ]
#        注意逻辑与-a与逻辑或-o很容易和其他字符串或文件的运算符号搞混了
###############################################################################

##################################常用简单语句##################################
#        && 如果是“前面”，则“后面”
#        [ -f /var/run/dhcpd.pid ] && rm /var/run/dhcpd.pid    检查 文件是否存在，如果存在就删掉
#           ||   如果不是“前面”，则后面
#        [ -f /usr/sbin/dhcpd ] || exit 0    检验文件是否存在，如果存在就退出
#
#
#         用简化 if 和$1,$2,$3来检测参数，不合理就调用help
#        [ -z "$1" ] && help                如果第一个参数不存在（-z  字符串长度为0 ）
#        [ "$1" = "-h" ] && help            如果第一个参数是-h,就显示help
#
#        if [ -f file ] 如果文件存在
#        if [ -d … ] 如果目录存在
#        if [ -s file ] 如果文件存在且非空
#        if [ -r file ] 如果文件存在且可读
#        if [ -w file ] 如果文件存在且可写
#        if [ -x file ] 如果文件存在且可执行
#
#        整数变量表达式
#        if [ int1 -eq int2 ] 如果int1等于int2
#        if [ int1 -ne int2 ] 如果不等于
#        if [ int1 -ge int2 ] 如果>=
#        if [ int1 -gt int2 ] 如果>
#        if [ int1 -le int2 ] 如果<=
#        if [ int1 -lt int2 ] 如果<
#
#        字符串变量表达式
#        If [ $a = $b ] 如果string1等于string2
#        字符串允许使用赋值号做等号
#        if [ $string1 != $string2 ] 如果string1不等于string2
#        if [ -n $string ] 如果string 非空(非0），返回0(true)
#        if [ -z $string ] 如果string 为空
#        if [ $sting ] 如果string 非空，返回0 (和-n类似)
#
#        s​h​e​l​l​中​条​件​判​断​i​f​中​的​-​z​到​-​d​的​意​思:
#        [ -a FILE ] 如果 FILE 存在则为真。
#        [ -b FILE ] 如果 FILE 存在且是一个块特殊文件则为真。
#        [ -c FILE ] 如果 FILE 存在且是一个字特殊文件则为真。
#        [ -d FILE ] 如果 FILE 存在且是一个目录则为真。
#        [ -e FILE ] 如果 FILE 存在则为真。
#        [ -f FILE ] 如果 FILE 存在且是一个普通文件则为真。
#        [ -g FILE ] 如果 FILE 存在且已经设置了SGID则为真。
#        [ -h FILE ] 如果 FILE 存在且是一个符号连接则为真。
#        [ -k FILE ] 如果 FILE 存在且已经设置了粘制位则为真。
#        [ -p FILE ] 如果 FILE 存在且是一个名字管道(F如果O)则为真。
#        [ -r FILE ] 如果 FILE 存在且是可读的则为真。
#        [ -s FILE ] 如果 FILE 存在且大小不为0则为真。
#
#        [ -t FD ] 如果文件描述符 FD 打开且指向一个终端则为真。
#        [ -u FILE ] 如果 FILE 存在且设置了SUID (set user ID)则为真。
#        [ -w FILE ] 如果 FILE 如果 FILE 存在且是可写的则为真。
#        [ -x FILE ] 如果 FILE 存在且是可执行的则为真。
#        [ -O FILE ] 如果 FILE 存在且属有效用户ID则为真。
#        [ -G FILE ] 如果 FILE 存在且属有效用户组则为真。 [ -L FILE ] 如果 FILE 存在且是一个符号连接则为真。
#        [ -N FILE ] 如果 FILE 存在 and has been mod如果ied since it was last read则为真。
#        [ -S FILE ] 如果 FILE 存在且是一个套接字则为真。
#        [ FILE1 -nt FILE2 ] 如果 FILE1 has been changed more recently than FILE2,or 如果 FILE1 exists and FILE2 does not则为真。
#        [ FILE1 -ot FILE2 ] 如果 FILE1 比 FILE2 要老, 或者 FILE2 存在且 FILE1 不存在则为真。
#        [ FILE1 -ef FILE2 ] 如果 FILE1 和 FILE2 指向相同的设备和节点号则为真。
#        [ -o OPTIONNAME ] 如果 shell选项 “OPTIONNAME” 开启则为真。
#
#
#        EQ=Equal =
#        //等运算符，如果运算符两边相同则返回真，否则返回假；
#
#        NE（Not Equal to）
#        //不等运算符，如果运算符两边不等则返回真，否则返回假；
#
#        GE（Greater than or equal to）
#        //大于等于运算符，如果运算符两边左边大于等于右边则返回真，否则返回假；
#
#        GT（Greater than）
#        //大于运算符，如果运算符两边左边大于右边则返回真，否则返回假；
#
#        LE（Less than or equal to）
#        //小于等于运算符，如果运算符两边左边小于等于右边则返回真，否则返回假；
#
#        LT（Less than）
#        //小于运算符，如果运算符两边左边大于右边则返回真，否则返回假；
#
#        运算符：
#        算术运算符 + 、 - 、 * 、 / （或 div ）和 % （或 mod ）
#        关系运算符 == （或 eq ）、 != （或 ne ）、 < （或 lt ）、 > （或 gt ）、 <= （或 le ）和 >= （或 ge ）
#        逻辑运算符 && （或 and ）、 || （或 or ）和 ! （或 not ）
#        验证运算符 empty
###############################################################################