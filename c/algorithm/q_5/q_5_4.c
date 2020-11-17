#include <stdio.h>
#define MAX_SIZE 1024

// Q: 输入一行字符，分别统计出其中英文字母、空格、其他字符的个数
int main()
{
    char str[MAX_SIZE];
    printf("input string:");
    scanf("%s:",str);

    int char_num,blank_num,num_count,other_char_num;

    char *p=str;
    for(int i=0;*(p+i)!='\0';i++){
//        printf("%c",*(p+i));

        char c=*(p+i);
        if(c>='A'&&c<='a'){
            char_num++;
        }else if(c>='0'&&c<='9'){
            num_count++;
        }else if(c==' '){
            blank_num++;
        }else{
            other_char_num++;
        }
    }

    printf("char_num:%d,blank_num:%d,num_count:%d,other_char_num:%d\n",char_num,blank_num,num_count,other_char_num);
}