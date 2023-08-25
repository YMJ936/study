'''
Created on 2015. 7. 1.
@author: user
'''
print('변수선언시 대소문자 구별')
print('들여쓰기 오류조심') #블럭처리 대용
A=1;a=2

print(id(A),id(a))
#변수의 이름은 숫자로 시작하면 안됨
#파이썬의 예약어로 변수선언 안됨
#모듈->외부(함수로 작성)
import keyword #import 모듈명
print('키워드목록:',keyword.kwlist)
'''
키워드목록: ['False', 'None', 'True', 'and', 'as', 'assert', 'break', 'class', 'continue', 'def', 'del', 'elif', 'else', 'except', 'finally', 'for', 'from', 'global', 'if', 'import', 'in', 'is', 'lambda', 'nonlocal', 'not', 'or', 'pass', 'raise', 'return', 'try', 'while', 'with', 'yield']

'''
print('\n') #줄바꿈
#oct(숫자)->8진수, hex(숫자),bin(숫자)
print(10,oct(10),hex(10),bin(10))
print(10,0o12, 0xa, 0b1010)

print('파이썬의 자료형')
#type(값)->자료형
print(7,type(7)) #7 <class 'int'> int,long->int
print(7.2,type(7.2))  #float
print(7+4j,type(7+4j))#복소수->complex
print(True,type(True))#bool->참,거짓
print('a',type('a')) #<class 'str'>
print("a",type("a"))

print('list,set,dic')
print((1,),type((1,)))#<class 'tuple'>
print([1],type([1]))#[1] <class 'list'>
print({1},type({1}))#{1} <class 'set'>
print({'key':1},type({'key':1}))
#<class 'dict'>


