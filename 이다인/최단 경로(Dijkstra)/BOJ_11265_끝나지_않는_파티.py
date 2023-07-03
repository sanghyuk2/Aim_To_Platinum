'''
1. 문제요약
- A 파티장에서 B 파티장으로 직접 연결된 일방통행 도로를 만들었지만, 다른 파티장을 경유해서 더 빨리 갈 수 있는 경우가 있을 수 있음
- B 파티장에서 C 시간 후에 다음 파티가 열릴 때, A 파티장에서 시간내에 도착할 수 있는지 없는지 알아봐주는 서비스

2. 아이디어(문제접근법)
- 서비스를 요청한 손님이 최대 10,000명이므로 모든 파티장에서 다른 모든 파티장으로의 이동 시간을 먼저 구함
- 플로이드 워셜 알고리즘을 활용해서 계산한 후 graph 배열에 저장
- graph[a][b]가 c보다 작거나 같으면 시간내에 도착할 수 있으므로 "Enjoy other party" 출력, 아니면 "Stay here" 출력
'''

import sys

input = sys.stdin.readline
n, m = map(int, input().split())
graph = [[0] * (n + 1)]

for _ in range(n):
    graph.append([0] + list(map(int, input().split())))

for k in range(1, n + 1):
    for a in range(1, n + 1):
        for b in range(1, n + 1):
            graph[a][b] = min(graph[a][k] + graph[k][b], graph[a][b])

for _ in range(m):
    a, b, c = map(int, input().split())
    if graph[a][b] <= c:
        print("Enjoy other party")
    else:
        print("Stay here")