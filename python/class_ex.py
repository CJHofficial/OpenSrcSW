class Calc :
    def sum(self, a, b):
        result = int(a) + int(b)
        print(format(a) + " + " + format(b) +" = " + format(result) + " 입니다.")
    def sub(self, a, b):
        result = int(a) - int(b)
        print(format(a) + " - " + format(b) +" = " + format(result) + " 입니다.")
    def multi(self, a, b):
        result = int(a) * int(b)
        print(format(a) + " * " + format(b) +" = " + format(result) + " 입니다.")
    def divi(self, a, b):
        result = int(a) / int(b)
        print(format(a) + " / " + format(b) +" = " + format(result) + " 입니다.")
