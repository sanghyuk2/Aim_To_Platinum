'''
1. 문제 요약
- 상담을 완료하는데 걸리는 기간과 상담을 했을 때 받을 수 있는 금액이 주어질 때, n일 동안 얻을 수 있는 최대 수익
- 상담을 이미 진행중이면 다른 상담을 진행할 수 없음
- n + 1일째에 퇴사하기 때문에 n일 이후에 끝나는 상담은 할 수 없음

2. 아이디어
- profit 배열에 해당 날짜까지 받을 수 있는 최대 수익 저장
- 해당 날짜의 상담을 할 경우와 하지 않을 경우의 수익 중 큰 값을 저장
- 해당 날짜의 상담이 퇴사일을 넘어가면 이전 날짜의 최대 수익을 그대로

3. 어려움 및 해결방식
- 1일차부터 시작해서 각 상담이 끝나는 날 기준으로 최대 수익을 계산하려고 했지만, 인덱스 계산이 복잡해서
- 7일차부터 순차적으로 채워나가는 방식으로 해결
'''

import sys

input = sys.stdin.readline
n = int(input())

plans = [list(map(int, input().split())) for _ in range(n)]
profit = [0] * (n + 1)

for i in reversed(range(n)):
    if (i + plans[i][0] > n):
        profit[i] = profit[i + 1]
    else:
        profit[i] = max(plans[i][1] + profit[i + plans[i][0]], profit[i + 1])

print(profit[0])
