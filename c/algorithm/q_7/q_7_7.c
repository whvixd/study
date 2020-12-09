#include <stdio.h>
#define SIZE 100

int vowel_flag(char);
void copy_vowel(const char src[SIZE],char dest[SIZE]);

// Q:写一个函数，将一个字符串中的元音字母复制到另一字符串，然后输出。
int main(){
    char src[SIZE],dest[SIZE];
    printf("input src:");
    gets(src);
    copy_vowel(src,dest);
    printf("dest:%s\n",dest);
    return 1;
}

void copy_vowel(const char src[SIZE],char dest[SIZE]){
    int i;
    int j=0;
    for(i=0;i<SIZE;i++){
        if(src[i]=='\0'){
            dest[j]='\0';
            return;
        }
        if(vowel_flag(src[i])){
            dest[j++]=src[i];
        }

    }
}

int vowel_flag(char c){
    return (c=='a'||c=='o'||c=='i'||c=='u'||c=='e');
}