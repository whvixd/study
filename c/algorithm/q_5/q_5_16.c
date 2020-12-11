#include <stdio.h>

// 两个乒乓球队进行比赛，各出3人。甲队为A，B，C 3人，乙队为X,Y,Z 3人。已抽签决定比赛名单。有人向队员打听比赛的名单，
// A说他不和X比，C说他不和X,Z比，请编程决定比赛名单。
void print_answer();
int contains(char array[],char c);
int main(){

    char jia_team[3]={'A','B','C'};
    char yi_team[3]={'X','Y','Z'};
    char done_person[3];
    int z=0;

    for(int i=0;i<3;i++){
        char jia=jia_team[i];
        for(int j=0;j<3;j++){
            char yi=yi_team[j];
            if((jia=='A'&&yi=='X')
                    ||
                ((jia=='C'&&yi=='X')||(jia=='C'&&yi=='Z'))) continue;

            if(contains(done_person,jia)) continue;
            printf("jia_team:%c vs yi_team:%c\n",jia,yi);
            done_person[z++]=jia;
        }
    }
    print_answer();
    return 0;
}

int contains(char array[],char c){
    char *p=array;
    for(int i=0;i<3;i++){
        char t=*(p+i);
        if(t==c){
            return 1;
        }
    }
    return 0;
}


void print_answer(){
    char i,j,k; /*是 a 的对手;j 是 b 的对手;k 是 c 的对手*/
    for (i='x';i<='z';i++)
        for (j='x';j<='z';j++)
            if (i!=j)
                for (k='x';k<='z';k++)
                    if (i!=k && j!=k)
                        if (i!='x' && k!='x' && k!='z')
                            printf("A--%c\nB--%c\nC--%c\n",i,j,k);
}