'''
1. 문제요약
- 트리의 지름이란, 트리에 존재하는 모든 경로들 중에서 가장 긴 것의 길이
- 루트가 있는 트리를 가중치가 있는 간선들로 줄 때, 트리의 지름 구하기

2. 아이디어(문제접근법)
- 임의의 노드에서 가장 거리가 먼 노드를 구한 후, 그 노드에서 가장 거리가 먼 노드를 구하면
- 두 노드가 트리의 지름의 끝 점이 됨
- 하나의 정점에서 가장 멀리 있는 정점은 원의 지름 부분에 해당하는 정점이기 때문

3. 어려움 및 해결방식
- 처음엔 모든 리프 노드에서 가장 먼 노드를 찾아서 max값을 출력하려고 시도
- 블로그 참조
    - https://lmcoa15.tistory.com/56
    - https://blog.myungwoo.kr/112
- 무방향 그래프 이므로 graph 배열에 부모와 자식을 모두 추가해주어야 함
'''

import sys
sys.setrecursionlimit(10 ** 6)
input = sys.stdin.readline

n = int(input())
graph = [[] for _ in range(n + 1)]
distance = [-1] * (n + 1)

def dfs(node, dist):
    for next, cost in graph[node]:
        if distance[next] == -1:
            distance[next] = dist + cost
            dfs(next, dist + cost)


for _ in range(n - 1):
    a, b, c = map(int, input().split())
    graph[a].append([b, c])
    graph[b].append([a, c])


distance[1] = 0
dfs(1, 0)

start = distance.index(max(distance))
distance = [-1] * (n + 1)
distance[start] = 0
dfs(start, 0)

print(max(distance))