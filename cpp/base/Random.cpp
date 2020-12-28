#include <iostream>
#include <ctime>
#include <cstdlib>
using namespace std;

int main(){
    int i,j;
    srand((unsigned)time(NULL));
    for(i=0;i<5;i++){
        j=rand();
        cout << "random:"<< j << endl;
    }
    return 0;
}