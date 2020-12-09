#include <stdio.h>
#define SIZE 100


// Q:写一个函数，输入一行字符，将此字符串中最长的单词输出
int main(){
    void long_word(char str[SIZE]);

    char str[SIZE];
    printf("input str:");
    scanf("%s",str);

    long_word(str);

    return 1;
}

void long_word(char str[SIZE]){
    char long_word[SIZE],c;
    int max=0,max_start=0,j=0,j_start=0,i;
    for(i=0;i<SIZE;i++){
        c=str[i];
        if(c=='\0'){
            // 校验最长
            if(j>max){
                max=j;
                max_start=j_start;
            }
            j=0;
            break;
        }
        // 单词
        if((c>='a'&&c<='z')||(c>='A'&&c<='Z')){
            if(j==0){
                j_start=i;
            }
            j++;
        }else{
            // 校验最长
            if(j>max){
                max=j;
                max_start=j_start;
            }
            j=0;
        }
    }

    // 赋值
    for(int i=0;i<max;i++){
        long_word[i]=str[i+max_start];
    }

    printf("long word:%s\n",long_word);
}