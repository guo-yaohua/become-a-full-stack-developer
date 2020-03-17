#include<stdio.h>

int jinWei(int a,int b){
	int i,jw,num;
	jw = 0;
	num = 0;
	while(a!=0 && b !=0){
		if((a%10+b%10+jw)/10){ //如果相加存在进位，记录
			jw = 1;
			num++;
		}
		else{
			jw = 0;
		}
		a = a/10;
		b = b/10;
	}
	return num;
}

int diJian(int a){
	int i,j,k,n,num;
	num = 0;
	while(a){
		n = a;
		k = 1;
		while(n){
			i = n%10;
			j = (n/10)%10;
			if(i<j){
				n = n/10;
				k *= 10;
			}
			else{
				break;
			}
		}
		num = num > a%k?num:a%k;
		a = a/10;
	}
	return num;
}

int main(int argc, char const *argv[])
{
	int a,b,n;
	while(1){
		printf("输入序号，选择功能：\n1. 相加进位 2. 求最大递减数\n");
	    scanf("%d",&n);
	    if(n == 1){
		    printf("输入两个十进制整数：\n");
		    scanf("%d %d",&a,&b);
		    printf("%d\n", jinWei(a,b));
	    }
	    else if (n == 2)
	    {
		    printf("输入一个正整数：\n");
		    scanf("%d",&a);
		    printf("%d\n", diJian(a));
	    }
	    else{
	    	break;
	    }
	}
	return 0;
}