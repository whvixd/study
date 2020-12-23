#include <stdio.h>

// Q:一个数如果因子之和等于这个数，这个数称完数，如6=1+2+3
// 找出1000之内所有完数
int main()
{
    int m,s,i;
    for(m=2;m<1000;m++){
        s=0;
        for(i=1;i<m;i++){
            if(m%i==0){
                s+=i; //1+2+3 都能被6整除
            }

            if(s==m){
                // 再次输出负载因子
                printf("%d,its factors are ",m);
                for(i=1;i<m;i++){
                    if(m%i==0){
                        printf("%d ",i);
                    }
                }
                printf("\n");

            }

        }
    }
    return 0;
}