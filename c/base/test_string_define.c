#include <stdio.h>
#include <string.h>
/*
/////////// typedef 与 #define区别：///////////
1. typedef 仅限于为类型定义符号名称，#define 不仅可以为类型定义别名，也能为数值定义别名，比如您可以定义 1 为 ONE。
2. typedef 是由编译器执行解释的，#define 语句是由预编译器进行处理的。
*/
// Object-like宏
#define MAX_SIZE 1000

// Function-like宏
// 字符串化指的是，可以在宏的参数前面加入#，使入参变成字符串。
#define str(expr) printf("%s\r\n",#expr)

// 在宏中，可以使用两个#将两个符号连接成一个符号。
#define P1 printf("this is P1\n")
#define PT(ARG) P##ARG

#define printArg(...) fprintf (stderr, __VA_ARGS__)

// typedef 定义别名
/*
struct StrClass str; 若不加typedef，声明变量
Str str; 添加 typedef，只能这样声明  struct StrClass 等于 Str
*/
typedef struct StrClass{
    struct Str *str;
    int length;
}Str;

void str_init_value()
{
    // 剩余的以 '\0' 填充，也是字符的结束标识
    char a[10];
    a[0]='1';
    a[1]='2';

    char b[10]={'3','4'};

    char c[10]={"45"};

    char d[]="67";

    printf("%s %s %s %s\n",a,b,c,d);

}

void str_mem(void){
    const char src_str[10]="src";
    char dest[10];
    memcpy(dest,src_str,strlen(src_str)+1);
    printf("dest:%s\n",dest);
}

int main()
{
    char c1[15];
    char c2[15];

    strcpy(c1,"12345");
    strcpy(c2,"56789");

    int result = strcmp(c1,c2);
    printf("strcmp result:%d",result);

    str(abc);
    str(qwe);

    PT(1);
    printArg("123\n");

    // 标准的预定义宏都是用双下划线开头和结尾
    printf("FILE:%s,LINE:%d\r\n",__FILE__, __LINE__);

// 是否被c++编译器编译
#ifdef __cplusplus
    printf("cplusplus:%d\r\n", __cplusplus);
#else
    printf("complied by c\r\n");
#endif

    // 字符串初始化
    str_init_value();

    // memcpy()
    str_mem();
    return 0;
}