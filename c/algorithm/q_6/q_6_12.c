#include <stdio.h>
char transfer_origin_char(char secret);

// 有一行电文，已按下面规律译成密码：A->Z a->z B->Y b->y C->X c->x
// 即第一个字母变成第26个字母，第i个字母变成第（26-i+1）个字母，非字母符不变。要求编程序将密码译回原文，并输出密码和原文。
int main(){

    char s[50];
//    scanf("%s",s);
    gets(s);
    for(int i=0;i<50;i++){
        char c=s[i];
        if(c=='\0'){
            break;
        }
        printf("%c -> %c\n",c,transfer_origin_char(c));
    }

    return 0;
}


char transfer_origin_char(char secret){
    int s_n=(int)secret;

    // A-Z
    if(s_n>=65&&s_n<91){
        return (char)(155-s_n);
    // a-z
    }else if(s_n>=97&&s_n<123){
        return (char)(219-s_n);
    }

    return secret;
}