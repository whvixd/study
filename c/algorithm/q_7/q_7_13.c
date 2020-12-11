#include <stdio.h>

/*
 Q:用递归方法求n阶勒让德多项式的值，递归公式为：

 当n=0,Pn(x)=1;
 当n=1,Pn(x)=x;
 当n>=1,Pn(x)=((2n-1)x - Pn-1(x) - (n-1)Pn-2(x))/n
 */

 static int count;

int main(){
    double polya(int n,int x);
    int n,x;
    printf("input n,x:");
    scanf("%d,%d",&n,&x);
    double p=polya(n,x);
    printf("count:%d\n",count);
    printf("n=%d,x=%d -> polya=%f\n",n,x,p);
    return 1;
}

double polya(int n,int x){
    double p;
    if(n==0){
        return 1;
    }else if(n==1){
        return x;
    }else if(n>=1){
        p=((2*n-1)*x-polya(n-1,x)-(n-1)*polya(n-2,x))/n;
    }
    count++;
    if(count%100==0){
        printf("count:%d\n",count);
    }
    return p;
}