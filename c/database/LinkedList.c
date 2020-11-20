#include <stdio.h>
#include <stdlib.h>

typedef struct NodeStruct{
    int value;
    struct NodeStruct *next;
}Node;


int length;
Node *head=NULL;

void init(){
    head=(Node *)malloc(sizeof(Node));
    length=0;
    head->next=NULL;
    head->value=0;
}

// 头插
int insert_node(int value){
    if(head==NULL) return 0;
    Node *new_node=(Node *)malloc(sizeof(Node));
    new_node->value=value;
    new_node->next=NULL;
    length++;

    Node *p=head;
    if(p->next!=NULL){
        new_node->next=p->next;
    }
    head->next=new_node;
    return 1;
}

int print_list(){
    if(head==NULL) return 0;
    Node *p=head->next;
    printf("head -> ");
    while(p!=NULL){
        printf("%d",p->value);
        if(p->next!=NULL){
            printf(" -> ");
        }
        p=p->next;
    }
    printf("\n");
    return 1;
}

int main(){
    init();
    insert_node(2);
    insert_node(3);
    print_list();

}




