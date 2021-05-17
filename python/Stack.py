class stack:
    def __init__(self):
        self.top = []
    def __len__(self):
        return len(self.top)
    def isEmpty(self):
        return len(self.top) == 0
    def push(self, item):
        self.top.append(item)
    def pop(self):
        if not self.isEmpty():
            return self.top.pop(-1)
        else:
            print("빈 스택입니니다.")
            exit()
    def peek(self):
        if not self.isEmpty():
            return self.top[-1]
        else:
            print("빈 스택입니다.")
    def size(self):
        return len(self.top)
    
