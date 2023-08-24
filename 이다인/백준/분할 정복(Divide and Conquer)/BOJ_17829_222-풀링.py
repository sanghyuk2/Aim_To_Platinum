import sys
input = sys.stdin.readline

n = int(input())
matrix = [list(map(int, input().split())) for _ in range(n)]

def pooling(i, j, length):
    if length == 2:
        temp = sorted(sum([row[j:j+length] for row in matrix[i:i+length]], []))
        return temp[2]

    temp = [0] * 4
    length //= 2
    
    temp[0] = pooling(i, j, length)
    temp[1] = pooling(i + length, j, length)
    temp[2] = pooling(i, j + length, length)
    temp[3] = pooling(i + length, j + length, length)
    
    temp.sort()
    return temp[2]

print(pooling(0, 0, n))