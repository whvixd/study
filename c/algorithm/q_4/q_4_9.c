#include <stdio.h>

void sort_num(){
    int num,num_tmp;
    scanf("%d",&num);
    num_tmp=num;

    int w=0;// 位数
    int b=1;// 除数
    while(num_tmp){
        num_tmp=num_tmp/b;
        w++;
        if(num_tmp){
           b=b*10;
        }
    }

    printf("数位:%d\n",w);
    int c=0;
    for(int i=w;i>0;i--){
        c=num/b;//1=123/100
        num%=b;//23
        printf("%d   ",c);
        b/=10;//10
    }
}

void inverse_num(){
    int num,num_tmp;
    scanf("%d",&num);
    num_tmp=num;

    while(num_tmp){
        int d= num_tmp%10;
        printf("%d",d);
        num_tmp/=10;
    }

}

int main(void)
{
    inverse_num();
}