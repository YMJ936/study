'''
Created on 2015. 7. 1.

'''
#print('환영합니다. python')
#한줄 주석  ctrl+f11
'''
 여러줄 주석표시
  변수명앞에 자료형은 안쓴다
  (자동 인식(동적)
'''
print("환영합니다. python");
#String var1="안녕 파이썬";
var1='안녕 파이썬'
print(var1)
var1=5; print(var1)
#각줄에 한개이상 명령어를 쓸때는 ;

a=10
b=20.5 #float
c=b #b값을 c에 대입
#print(값1,값2,,,,)
print(a,b,c)
#id(값)->주소값 출력됨
print('주소출력:',id(a),id(b),id(c))
#1874252384 1455344 1455344
#주소비교->is (==)
print(a is b,a==b)
print(b is c,b==c)#같은 주소 공유

