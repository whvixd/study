#include <stdio.h>
#define SIZE 100

// Q:写一个函数，使输入的一个字符串按反序存放，在主函数中输入输出字符串。
int main(){
    void reverse(char s[SIZE]);

    char s[SIZE];
    printf("input s:");
    gets(s);
    reverse(s);
    printf("reverse s:%s\n",s);
    return 1;
}


void reverse(char s[SIZE]){

    char t;
    int l=0;

    while(s[l++]!='\0');
    l-=2;
    for(int i=0;i<l;i++){
        if(i>l/2){
            return;
        }
        t=s[i];
        s[i]=s[l-i];
        s[l-i]=t;
    }

}