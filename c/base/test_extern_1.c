#include <stdio.h> // 原型

//  添加 static，则函数为内部函数，不允许外部文件调用
static test_static(){
}

// 添加extern，则函数为外部函数，可被本文件及外部文件调用，不加默认是外部函数
extern test_extern(){}

// static int A; 只允许本文件使用，即使外部文件使用了 extern也不能使用
int A;
// gcc test_extern_1.c test_extern_2.c -o test_extern
// ./extern
int main(){
    void print_A();

    A=10;
    print_A();
    return 0;
}