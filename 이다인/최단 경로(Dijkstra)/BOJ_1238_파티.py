import heapq
import sys
input = sys.stdin.readline
INF = int(1e9)

def dijkstra(start, time, graph):
    q = []
    heapq.heappush(q, (0, start))
    time[start] = 0

    while q:
        t, now = heapq.heappop(q)
        if time[now] < t:
            continue

        for i in graph[now]:
            cost = t + i[1]
            if cost < time[i[0]]:
                time[i[0]] = cost
                heapq.heappush(q, (cost, i[0]))

n, m, x = map(int, input().split())
graph = [[] for _ in range(n + 1)]
graph_reversed = [[] for _ in range(n + 1)]

for _ in range(m):
    start, end, t = map(int, input().split())
    graph[start].append((end, t))
    graph_reversed[end].append((start, t))

x_to_start = [INF] * (n + 1)
dijkstra(x, x_to_start, graph)
start_to_x = [INF] * (n + 1)
dijkstra(x, start_to_x, graph_reversed)

result = 0
for i in range(1, n + 1):
    if x_to_start[i] + start_to_x[i] > result:
        result = x_to_start[i] + start_to_x[i]

print(result)