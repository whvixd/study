#include <iostream>

using namespace std;

class Box{
    public:
        int len;
        int bre;
        int hei;

        // 成员函数声明
        int get(void);
        void set(int l,int b,int h);
};

int Box::get(){
    return len*bre*hei;
}

void Box::set(int l,int b,int h){
    len=l;
    bre=b;
    hei=h;
}

class F{
    public:
        void setCommon(int c){
            common=c;
        }
    protected:
        int common;
};

class M{
    public:
        void setNumber(int n){
            number=n;
        }
    protected:
        int number;
};

// 多继承
class C:public F,public M{
    public:
        int getCommon(){
            return common;
        }
        int getNumber(){
            return number;
        }
};

int main(){
    Box b;
    b.set(1,2,3);
    int v=b.get();

    cout<<"Box:"<<v<<endl;

    C c;
    c.setCommon(4);
    c.setNumber(5);
    cout<<"common:"<<c.getCommon()<<endl;
    cout<<"number:"<<c.getNumber()<<endl;
    return 0;
}