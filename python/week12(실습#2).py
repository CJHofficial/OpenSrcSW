import csv

max_num1 = [0] * 3
max_name1 = [''] * 3
min_num1 = [999999999999] * 3
min_name1 = [''] * 3
templine = ''

f = open('subwayfee.csv', encoding = 'UTF-8')
data = csv.reader(f)
next(data)

for row in data :
    row[-1] = row[-1].replace(",","")
    row[-2] = row[-2].replace(",","")
    row[-1] = int(row[-1])
    row[-2] = int(row[-2])
    sum1 = row[-1] + row[-2]
    if templine == row[1]:
        for i in range (0,3) :
            if max_num1[i] < sum1:
                max_num1.insert(i, sum1)
                max_name1.insert(i,row[-3])
                break
        for i in range (0,3) :
            if min_num1[i] > sum1:
                min_num1.insert(i, sum1)
                min_name1.insert(i, row[-3])
                break
    elif templine == '' :
        templine = row[1]
        row[-1] = int(row[-1])
        row[-2] = int(row[-2])
        for i in range (0,3) :
            if max_num1[i] < (row[-1]+row[-2]):
                max_num1.insert(i, row[-1]+row[-2])
                max_name1.insert(i,row[-3])
                break
        min_num1.insert(0, row[-1]+row[-2])
        min_name1.insert(0, row[-3])
    else :
        print("")
        print(templine)
        print("")
        print("이용객이 가장 많은 역순위")
        for k in range(0,3):
            print( format(k+1)+"."+format(max_name1[k])+"("+format(max_num1[k])+")")
        print("")
        print("이용객이 가장 적은 역순위")
        for m in range(0,3):
            print( format(m+1)+"."+format(min_name1[m])+"("+format(min_num1[m])+")")
        templine = row[1]
        max_num1 = [0] * 3
        max_name1 = [''] * 3
        min_num1 = [999999999999] * 3
        min_name1 = [''] * 3
        for i in range (0,3) :
            if max_num1[i] < (sum1):
                max_num1.insert(i, sum1)
                max_name1.insert(i,row[-3])
                break
        min_num1.insert(0, sum1)
        min_name1.insert(0, row[-3])
f.close()

