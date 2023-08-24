'''
1. 문제요약
- 시작도시 A, 도착도시 B , 버스를 타고 이동하는데 걸리는 시간 C가 주어질 때 1번 도시에서 출발해서 나머지 도시로 가는 가장 빠른 시간
- C = 0이면 순간 이동
- C < 0면 타임머신으로 시간을 되돌아가는 경우

2. 아이디어(문제접근법)
- 가중치가 음수면서, 음수 사이클이 발생할 수 있으므로 벨만-포드 알고리즘 활용

3. 어려움 및 해결방식
- 벨만-포드 알고리즘 학습 후 문제 풀이
'''

import sys

input = sys.stdin.readline
INF = int(1e9)

def bellmanford(start):
    time[start] = 0
    for i in range(n):
        for cur, next, cost in edges:
            if time[cur] != INF and time[next] > time[cur] + cost:
                time[next] = time[cur] + cost
                if i == n - 1:
                    return True
    return False

n, m = map(int, input().split())
time = [INF] * (n + 1)
edges = []

for _ in range(m):
    a, b, c = map(int, input().split())
    edges.append((a, b, c))

cycle = bellmanford(1)

if cycle:
    print(-1)
else:
    for i in range(2, n + 1):
        if time[i] == INF:
            print(-1)
        else:
            print(time[i])