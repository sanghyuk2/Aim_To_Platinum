'''
1. 문제요약
- 각 물건의 무게 w와 가치 v가 주어지고, 최대 k만큼의 무게만큼 물건을 넣을 수 있을 때 물건들의 가치의 최댓값

2. 아이디어(문제접근법)
- knapsack 배열에 1 ~ 무게 k까지 각 물건을 넣었을 때와 뺐을 때를 모두 고려하여 가치의 최댓값 저장
- knapsack[아이템 index][무게] = 가능한 최대 가치
- 현재 탐색하는 무게 < 물건의 무게일 경우 물건을 담지 못하므로, 이전 index 아이템의 가치를 그대로 가져옴
- 현재 탐색하는 무게 > 물건의 무게일 경우 최대 가치 = 물건의 가치 + (현재 탐색하는 무게 - 물건의 무게)일 때 최대 가치 / 이전 index 아이템의 가치 중 큰 값

3. 어려움 및 해결방식
- knapsack 배열을 갱신할 때 v + knapsack[i][j - w]로 계산해서 같은 물건이 중복으로 들어가는 오류가 발생
- v + knapsack[i - 1][j - w]로 수정
'''

import sys

input = sys.stdin.readline
n, k = map(int, input().split())
knapsack = [[0] * (k + 1) for _ in range(n + 1)]

for i in range(1, n + 1):
    w, v = map(int, input().split())
    for j in range(1, k + 1):
        if j < w:
            knapsack[i][j] = knapsack[i - 1][j]
        else:
            knapsack[i][j] = max(v + knapsack[i - 1][j - w], knapsack[i - 1][j])

print(knapsack[n][k])