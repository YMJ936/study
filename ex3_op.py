'''
연산자(치환->값을 바꾸어서 저장)
int a=4; int b=10;
System.out.println(a+","+b)
v1=5;v2=5;v3=5
'''
v1=3
v1=v2=v3=5 #변수명=변수명2,=값
print(v1,v2,v3)
#출력1,출력2: 출력2...
#print('출력할값',end=구분자->',' or
#end=':'
print('출력1',end=':')
print('출력2')

#치환2
'''
변수1,변수2=값1,값2
'''
v1,v2=10,20
print(v1,v2)
v1,v2=v2,v1
print('서로바뀐값:',v1,v2)
print('값 할당packing')
v1,*v2=[1,2,3,4,5]#배열
#v1=1, v2->[2,3,4,5]
print(v1)
print(v2)
#v1=>[1,2,3,4],v2=5
*v1,v2=[1,2,3,4,5]

print(v1)
print(v2)

#v1->[1,2,3], v2=4,v3=5
*v1,v2,v3=[1,2,3,4,5]
print(v1)
print(v2)
print(v3)