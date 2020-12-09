#include <stdio.h>
#define SIZE 100

int char_c,num_c,b_c,o_c;
// Q:编写一个函数，由实参传来一个字符串，统计此字符串中字母、数字、空格和其它字符的个数，在主函数中输入字符串以及输出上述的结果
int main(){
    void count(char str[SIZE]);

    char str[SIZE];
    printf("input str:");
    scanf("%s",str);
    char_c=0,num_c=0,b_c=0,o_c=0;
    count(str);
    printf("char_c:%d,num_c:%d,b_c:%d,o_c:%d\n",char_c,num_c,b_c,o_c);
    return 1;
}

void count(char str[SIZE]){
    int i;
    char c;
    for(i=0;i<SIZE;i++){
        if(str[i]=='\0'){
            return;
        }
        c=str[i];
        if((c>='a'&&c<='z')||(c>='A'&&c<='Z')){
            char_c++;
        }else if(c>='0'&&c<='9'){
            num_c++;
        }else if(c==32){
            b_c++;
        }else{
            o_c++;
        }
    }
}