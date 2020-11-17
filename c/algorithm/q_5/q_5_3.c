#include <stdio.h>
#include "./q_5_3.h"

/*
@Q:输入两个正整数m和n，求其最大公约数和最小公约数
*/
int main()
{
    int m,n;
    scanf("%d,%d",&m,&n);
    common_divisor(m,n);

}

int common_multiple(int m,int n)
{
    int r=1;
    for(int i=2;i<(m>n?m:n);i++){
        if(m%i==0&&n%i==0){
            r=r*i;
        }
    }
    return r;
}

void common_divisor(int m,int n)
{
    int min=m>n?n:m;
    int min_v=0,max_v=0;
    int j=0;
    for(int i=2;i<min;i++){
        if((m%i==0)&&(n%i==0)){
            j++;
            if(j==1){
                min_v=i;
            }
            max_v=i;
        }
    }
    printf("min_v:%d,max_v:%d",min_v,max_v);
}
