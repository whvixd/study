#include <stdio.h>

// Q:写一个函数，输入一个4位数字，要求输出这4个数字字符，但每两个数字间空一个空格。如输入1990，应输出"1 9 9 0"
int main(){
    void insert_black(char n[7]);

    char n[7];
    printf("input four digit:");
    scanf("%s",n);
    insert_black(n);
    printf("result:%s\n",n);
    return 1;
}

void insert_black(char n[7]){
    int i;
    for(i=4;i>0;i--){
        n[2*i]=n[i];
        n[2*i-1]=' ';
    }
}