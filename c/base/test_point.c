#include <stdio.h>
/*
type *var-name;// type数据类型

所有实际数据类型，不管是整型、浮点型、字符型，还是其他的数据类型，对应指针的值的类型都是一样的，都是一个代表内存地址的长的十六进制数。
不同数据类型的指针之间唯一的不同是，指针所指向的变量或常量的数据类型不同。

*/
int main()
{
    int a;
    printf("a init value:%d\n",a);
    int *ap;
    ap=&a;
    // %p 打印ap指向的内存地址
    printf("$a:%p \n",ap);

    a=10;
    printf("$a:%p \n",&a);

    printf("a:%d \n",*ap);

    ////////////////////
    // NULL：空指针 =.=! //
    // 0x0 内存0地址，系统保留地址，程序不可访问
    int *null_point=NULL;
    printf("NULL:%p\n",null_point);
    if(!null_point) printf("空\n");
    if(null_point) printf("非空\n");

}