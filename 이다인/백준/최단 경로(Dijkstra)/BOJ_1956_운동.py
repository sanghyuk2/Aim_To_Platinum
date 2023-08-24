'''
1. 문제요약
- V개의 마을과 E개의 도로로 구성되어 있는 도시
- 사이클을 이루는 도로의 길이가 최소가 되는 경로

2. 아이디어(문제접근법)
- 플로이드 워셜 알고리즘 사용

3. 어려움 및 해결방식
- 시작점과 끝점이 같은 사이클을 찾아야 하므로, 거리 값을 저장할 graph 배열을 초기화 할 때
- 두 개의 인덱스가 같은 경우에도 0으로 초기화 해주지 않고 INF로 초기화
'''

import sys
input = sys.stdin.readline

INF = int(1e9)
v, e = map(int, input().split())
graph = [[INF] * (v + 1) for _ in range(v + 1)]

for _ in range(e):
    a, b, c = map(int, input().split())
    graph[a][b] = c

for k in range(1, v + 1):
    for a in range(1, v + 1):
        for b in range(1, v + 1):
            graph[a][b] = min(graph[a][b], graph[a][k] + graph[k][b])

min_cycle = INF
for i in range(1, v + 1):
    min_cycle = min(min_cycle, graph[i][i])

if min_cycle == INF:
    print(-1)
else:
    print(min_cycle)