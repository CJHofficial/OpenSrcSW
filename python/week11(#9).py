from Stack import stack

nums = stack()
operators = stack()

userinput = input("계산식 입력 : ")
a = userinput.split(' ')

check = 0
for i in a:
    print(i, end='')
    if (check%2) ==0:
        nums.push(i)
    elif(check%2)==1:
        if(not operators.isEmpty()):
            if (i == '*') or (i == '/') :
                operators.push(i)
            else:
                while 1:
                    n2 = nums.pop()
                    n1 = nums.pop()
                    o = operators.pop()
                    if(o == '*'):
                        result = int(n1)*int(n2)
                    elif(o == '/'):
                        result = int(n1)/int(n2)
                    elif(o == '+'):
                        result = int(n1)+int(n2)
                    elif(o == '-'):
                        result = int(n1)-int(n2)
                    nums.push(result)
                    if(operators.isEmpty()):
                        operators.push(i)
                        break
        else:
            operators.push(i)
    check +=1
if(check == len(a)):
        while 1:
                    n2 = nums.pop()
                    n1 = nums.pop()
                    o = operators.pop()
                    if(o == '*'):
                        result = int(n1)*int(n2)
                    elif(o == '/'):
                        result = int(n1)/int(n2)
                    elif(o == '+'):
                        result = int(n1)+int(n2)
                    elif(o == '-'):
                        result = int(n1)-int(n2)
                    nums.push(result)
                    if(operators.isEmpty()):
                        operators.push(i)
                        break
        print("="+format(nums.pop()))    
    

