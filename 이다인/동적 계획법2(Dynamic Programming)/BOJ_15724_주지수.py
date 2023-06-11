'''
1. 문제요약
- NxM 크기의 영토의 각 구역 내에 살고 있는 사람 수가 주어질 때, 주어진 직사각형 범위 내에 사는 사람의 수

2. 아이디어(문제접근법)
- 각 행 별로 누적 합을 계산해서 territory 배열에 저장
- x1 ~ x2 범위에서 각 행 별로 y2에 저장된 누적 합 - (y1 - 1)에 저장된 누적 합 계산
- 배열의 index가 0부터 시작하기 때문에 각 범위에 알맞게 index 조정

3. 어려움 및 해결방식
- 1 1 3 2 가 주어질 때, (1, 1) ~ (3, 2) 범위가 아닌 (1, 1)부터 3x2 직사각형 범위를 구하는 것으로 잘못 이해했었음
'''

import sys


def sum(x1, y1, x2, y2, territory):
    result = 0
    for i in range(x1 - 1, x2):
        result += territory[i][y2 - 1]
        if (y1 != 1):
            result -= territory[i][y1 - 2]
    print(result)


input = sys.stdin.readline
n, m = map(int, input().split())
territory = []

for i in range(n):
    territory.append(list(map(int, input().split())))
    for j in range(1, m):
        territory[i][j] += territory[i][j - 1]

k = int(input())

for _ in range(k):
    x1, y1, x2, y2 = map(int, input().split())
    sum(x1, y1, x2, y2, territory)