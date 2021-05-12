
print("실습 #2: 최대값 구하기")
numstring = input("Numbers? : ")
a = numstring.split()
Max = int(a[0])
for i in range(len(a)):
    if Max <= int(a[i]):
        Max = int(a[i])

print("Max = ",Max)

print("실습 #3: 암호화/복호화")
print("암호화")
string1 = input("input string : ")
b = string1.split()
b.reverse()
pstr =""
for i in range(len(b)):
    pstr += b[i]+" "
p=0
newpstr=""
for k in range(len(pstr)):
    if pstr[k] != " ":
        p = ord(pstr[k])
        newpstr += chr(p + 1);
    else:
        newpstr += pstr[k]
print(newpstr)

print("복호화")
string1 = input("input string : ")
b = string1.split()
b.reverse()
pstr =""
for i in range(len(b)):
    pstr += b[i]+" "
p=0
newpstr=""
for k in range(len(pstr)):
    if pstr[k] != " ":
        p = ord(pstr[k])
        newpstr += chr(p - 1);
    else:
        newpstr += pstr[k]
print(newpstr)

print("실습 #4: 전화번호부")
a = {}
for i in range(10):
    name = input("이름 : ")
    pnum = input("전화번호 : ")
    a[name]=pnum
print("========menu=======")
print("1. 이름 찾기")
print("2. 전화번호 찾기")
check = 0
select = input("메뉴 선택 : ")
if select == "1":
    findname = input("찾는 이름의 전화번호 : ")
    for key, value in a.items():
        if value == findname:
            print(key)
            check = 1
            break 
    if check ==0:
        print("찾으시는 이름의 전화번호가 존재하지 않습니다.")
        
elif select == "2":
    findnum = input("찾는 전화번호의 name : ")
    for key, value in a.items():
        if key == findnum:
            print(value)
            check = 1
            break
    if check ==0:
        print("찾으시는 이름의 전화번호가 존재하지 않습니다.")
else:
    print("잘못 입력하셨습니다.")

