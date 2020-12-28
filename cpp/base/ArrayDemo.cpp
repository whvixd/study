#include <iostream>
using namespace std;

#include <iomanip>
using std::setw;

int main(){
    int n[10];

    for(int i=0;i<10;i++){
        n[i]=100+i;
    }

    cout << "Ele" << setw(13) << "Val" << endl;//setw(x) x空格

    for(int j=0;j<10;j++){
        cout << setw(3) << j << setw(13) << n[j] << endl;
    }

    return 0;
}