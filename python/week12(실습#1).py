import csv

max_diff = 0
diff_date = ''

min_temp = 999
min_date = ''

max_temp = -999
max_date = ''

f = open('seoul.csv')
data = csv.reader(f)
header = next(data)

for row in data :
    if row[-1] == ' ' :
        continue
    if row[-2] == ' ':
        continue
    row[-1] = float(row[-1])
    row[-2] = float(row[-2])
    if max_diff < row[-1]-row[-2] :
        diff_date = row[0]
        max_diff = row[-1]-row[-2]

print("일교차가 가장 큰 날")
print(diff_date)
f.close()

f = open('seoul.csv')
data = csv.reader(f)
header = next(data)

for row in data :
    if row[-2] == ' ' :
        row[-2] = 999
    row[-2] = float(row[-2])
    if min_temp > row[-2] :
        min_date = row[0]
        min_temp = row[-2]
        
print("최저기온이 가장 낮은 날")
print(min_date)
f.close()

f = open('seoul.csv')
data = csv.reader(f)
header = next(data)

for row in data :
    if row[-1] == ' ' :
        row[-1] = -999
    row[-1] = float(row[-1])
    if max_temp < row[-1] :
        max_date = row[0]
        max_temp = row[-1]

print("최고기온이 가장 높은 날")
print(max_date)
f.close()
