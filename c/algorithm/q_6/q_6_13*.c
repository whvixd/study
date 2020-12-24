#include <stdio.h>
void strappend(char *str1,char *str2);
void strappend1(char *str1,char *str2);

// Q: 编写一个程序，将两个字符串连接起来，不要用strcat
int main(){
    char s[100];
    char c[100];
    printf("input str1:");
    scanf("%s",s);
    printf("input str2:");
    scanf("%s",c);

    strappend1(s,c);

    printf("%s\n",s);
    return 0;
}

void strappend(char *str1,char *str2){
    char *t=str1;
    while(*t!='\0'){
        t++;
    }
    while(*str2!='\0'){
        *t++=*str2++;
    }
    *t='\0';
}

void strappend1(char str1[],char str2[]){
    int i=0,j=0;
    while(str1[i]!='\0') i++;
    while(str2[j]!='\0'){
        str1[i]=str2[j];
        i++;
        j++;
    }
    str1[i]='\0';
}