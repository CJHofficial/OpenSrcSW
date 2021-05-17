

print("실습 #6: copy")
userinput = input("명령을 입력하세요 : ")
input1 = userinput.split(' ')

if input1[0] == 'cp':
    f=open(input1[1]+'.txt', 'r')
    data = f.read()
    f.close()
    f=open(input1[2]+'.txt', 'w')
    f.write(data)
    f.close()

print("실습 #7: wc")
userinput = input("명령을 입력하세요 : ")
input2 = userinput.split(' ')
linenum = 0
wordnum = 0
if input2[0] == 'wc':
    f=open(input2[1]+'.txt', 'r')
    while 1:
        line = f.readline()
        if not line: break
        linenum = linenum + 1
        a = line.split(' ')
        wordnum = wordnum + len(a)
    f.close()
print("#of lines : ",format(linenum))
print("#of words : ",format(wordnum))

