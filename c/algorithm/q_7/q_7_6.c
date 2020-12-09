#include <stdio.h>
#define SIZE 100
void str_cat(const char s1[SIZE],const char s2[SIZE]);

char s[SIZE];
// Q:写一个函数，将两个字符串连接
int main(){
    char s1[SIZE],s2[SIZE];
    printf("input s1:");
    gets(s1);
    printf("input s2:");
    gets(s2);
    str_cat(s1,s2);
    puts(s);
    return 1;
}

void str_cat(const char s1[SIZE],const char s2[SIZE]){
    int l1=0,l2=0,i=0;
    while((s[i++]=s1[l1++])!='\0');
    i--;
    while((s[i++]=s2[l2++])!='\0');
}