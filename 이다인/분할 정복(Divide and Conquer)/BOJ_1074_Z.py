'''
1. 아이디어
- 주어진 (r, c)가 몇 사분면에 있는지 판단하여 해당 사분면을 다시 4등분


2. 구현
- length : 한 변의 길이
- caculate 함수
    - 한 변의 길이가 2가 될 때 까지 find_quadrant 함수를 호출해서 결과 계산
    - result에 이전 사분면에서 방문한 칸의 수를 더함
    - 한 변의 길이를 반으로 줄여가며 반복
- find_quadrant 함수
    - (r, c)가 몇 사분면에 있는지 찾는 함수
    - 주어진 (r, c)를 1사분면으로 옮긴 좌표와 몇 사분면인지 반환
'''

n, r, c = map(int, input().split())
length = 2**n

def find_quadrant(r, c, length):
    half = length // 2
    if 0 <= r < half and 0 <= c < half:
        return r, c, 1
    elif 0 <= r < half and half <= c < length:
        return r, c-half, 2
    elif half <= r < length and 0 <= c < half:
        return r-half, c, 3
    elif half <= r < length and half <= c < length:
        return r-half, c-half, 4

def calculate(r, c, length):
    result = 0
    while (length >= 2):
        r, c, quadrant = find_quadrant(r, c, length)
        result += (length // 2)**2 * (quadrant - 1)
        length //= 2
    return result

print(calculate(r, c, length))
