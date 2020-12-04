#include <stdio.h>

// 编写一个程序，将字符数组s2中的全部字符复制到字符数组s1中。不用strcpy,截止到'\0'，'\0'也要复制
int main(){
    char s1[100],s2[100];
    printf("input str:");
    gets(s2);
    // 不能用*s1++
    for(int i=0;i<100;i++){
        if(s2[i]!='\0'){
            s1[i]=s2[i];
        }else{
            s1[i]=s2[i];
            break;
        }
    }

    printf("s1:%s\n",s1);
    return 0;
}