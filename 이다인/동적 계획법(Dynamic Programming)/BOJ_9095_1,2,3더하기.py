'''
1. 문제 요약
- 정수 n을 1, 2, 3의 합으로 나타내는 방법의 수
- 순서 고려

2. 아이디어
- i일 때 방법의 수 = i-1을 만드는 모든 경우 뒤에 + 1, i-2를 만드는 모든 경우 뒤에 + 2, i-3을 만드는 모든 경우 뒤에 + 3을 더하는 모든 경우의 수의 합
- dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3]

3. 어려움 및 해결방식
- 점화식을 세우기 까지 오래 걸림
- 1부터 5까지 직접 써보면서 규칙 파악
'''

import sys

input = sys.stdin.readline
t = int(input())
for _ in range(t):
    n = int(input())
    dp = [0] * 12
    dp[1] = 1
    dp[2] = 2
    dp[3] = 4
    for i in range(4, n + 1):
        dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3]
    print(dp[n])