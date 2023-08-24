'''
1. 문제요약
- 모든 정점 (i, j)에 대해서 i에서 j로 가는 길이가 양수인 경로가 있는지 구하기

2. 아이디어(문제접근법)
- 모든 정점에서 다른 모든 정점까지의 경로를 구해야 하므로 플로이드 워셜 알고리즘 사용
'''

n = int(input())
graph = [list(map(int, input().split())) for _ in range(n)]

for k in range(n):
    for i in range(n):
        for j in range(n):
            if (graph[i][k] and graph[k][j]):
                graph[i][j] = 1

for g in graph:
    print(*g)