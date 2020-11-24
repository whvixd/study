#include <stdio.h>

// Q 求100之内的素数
int main(){
    for(int i=1;i<100;i++){
        int j=2;
        for(;j<i;j++){
            if(i%j==0){
                break;
            }
        }
//        printf("%d,%d",j,i);
        if (j==i) printf("%d\n",i);
    }
}