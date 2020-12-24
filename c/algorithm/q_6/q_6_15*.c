#include <stdio.h>

// 编写一个程序，将字符数组s2中的全部字符复制到字符数组s1中。不用strcpy,截止到'\0'，'\0'也要复制
int main(){
    void strcopy(char *str1,const char *str2);
    void strcopy1(char *str1,const char *str2);

    char s1[100],s2[100];
    printf("input str:");
    gets(s2);
    // 不能用*s1++
//    for(int i=0;i<100;i++){
//        if(s2[i]!='\0'){
//            s1[i]=s2[i];
//        }else{
//            s1[i]=s2[i];
//            break;
//        }
//    }
    strcopy(s1,s2);

    printf("s1:%s\n",s1);
    return 0;
}

void strcopy(char *str1,const char *str2){
    while((*str1++=*str2++)!='\0');
}

void strcopy1(char str1[],const char str2[]){
    int i=0;
    while(str2[i]!='\0'){
        str1[i]=str2[i];
        i++;
    }
    str1[i]='\0';
}