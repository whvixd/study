// 预处理指令 #include <...> #define ...
# include <stdio.h>

// 主函数
int main(){
	int max_number(int x,int y);
	// 全局参数
	int a,b,c;
	scanf("%d,%d",&a,&b);
	c=max_number(a,b);
	printf("max_number:%d between a:%d and b:%d\n",c,a,b);
	return 0;
}

// 函数首部: 函数类型+函数名+函数参数类型+函数参数名
int max_number(int x,int y){
	// 函数体: 声明部分+执行部分
	return x>y?x:y;
}