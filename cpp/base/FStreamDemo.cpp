#include <iostream>
#include <fstream>
#include <cstdio>

using namespace std;

int main(){
    char str[100];

    cout<<"input str:";
    cin.getline(str,100);

    ofstream outfile;
    outfile.open("test.txt");

    // 写入文件
    outfile << str << endl;
    outfile.close();

    cout<<"write success"<<endl;

//######################

    ifstream infile;
    infile.open("test.txt");

    // 读到文件中
    infile >> str;

    cout<<"read str:";
    cout<<str<<endl;
    infile.close();

    cout<<"read success"<<endl;

    if(remove("test.txt")==0){
        cout<<"remove success"<<endl;
    }else{
        cout<<"remove fail"<<endl;
    }

    return 0;
}