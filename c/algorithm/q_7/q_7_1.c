#include <stdio.h>

int common_divisor(int n1,int n2);
int common_multiple(int n1,int n2);

// 最大公约数与最小公倍数 @see q_5_3
int main(){
    int n1,n2;
    printf("input n1:");
    scanf("%d",&n1);
    printf("input n2:");
    scanf("%d",&n2);
    int d=common_divisor(n1,n2);
    int c=common_multiple(n1,n2);
    printf("d:%d,c:%d\n",d,c);
    return 0;
}

// 最大公约数,两者都能整除的最大数
int common_divisor(int n1,int n2){
    int min=n1<n2?n1:n2,j=-1;

    for(int i=2;i<min;i++){
        if(n1%i==0&&n2%i==0){
            j=i;
        }
    }
    return j;
}

int common_multiple(int n1,int n2){
    int divisor=common_divisor(n1,n2);
    return n1*n2/divisor;
}