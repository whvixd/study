#include <stdio.h>

// 编写一个程序，将字符数组s2中的全部字符复制到字符数组s1中。不用strcpy,截止到'\0'，'\0'也要复制
int main(){
    char s1[100],s2[100];
    printf("input str:");
    gets(s2);
    while(*s2!='\0'){
        *s1++=*s2++;
    }

    puts(s1);
    return 0;
}