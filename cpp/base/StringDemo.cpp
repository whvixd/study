#include <iostream>
using namespace std;

#include <string>

int main(){
    string str1="123";
    string str2="qwe";
    int len;
    str2=str1;
    cout << "str2:" << str2 << endl;

    string str3=str1+str2;
    cout << "str3:" << str3 << endl;

    len=str3.size();

    cout << "len:" << len << endl;
    return 0;
}