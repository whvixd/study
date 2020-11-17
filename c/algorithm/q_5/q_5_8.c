#include <stdio.h>


// Q:水仙花数，三位数，各位数字立方和等于本身
int main()
{
    for(int i=100;i<=999;i++){
        int a=i%10;//个位
        int b=i%100/10;//十位
        int c=i/100;//百位
//        printf("i:%d,a:%d,b:%d,c:%d\n",i,a,b,c);
        if((a*a*a+b*b*b+c*c*c)==i){
            printf("it is %d\n",i);
        }
    }
    return 0;
}