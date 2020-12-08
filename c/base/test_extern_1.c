#include <stdio.h>

int A;
// gcc test_extern_1.c test_extern_2.c -o test_extern
// ./extern
int main(){
    void print_A();

    A=10;
    print_A();
    return 0;
}