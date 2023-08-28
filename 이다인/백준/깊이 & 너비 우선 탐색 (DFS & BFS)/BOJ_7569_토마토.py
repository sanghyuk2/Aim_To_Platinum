'''
1. 문제요약
- 보관 후 하루가 지나면 익은 토마토들의 인접한 곳에 있는 익지 않은 토마토들이 익게 될 때,
- 창고에 보관된 토마토들이 다 익게 되는 최소 일수

2. 아이디어(문제접근법)
- 큐에 이미 익은 토마토의 위치를 추가한 후,
- 큐가 빌 때까지 인접한 6방향에 있는 토마토가 아직 익지 않은 토마토면 큐에 추가하고,
- graph에 현재 토마토의 값 + 1을 저장
- 탐색이 끝나면 전체 graph를 돌며 아직 익지 않은 토마토가 있으면 -1 출력, 없으면 가장 큰 수 - 1 출력

3. 어려움 및 해결방식
- 0일차에 익어있는 토마토의 값이 1부터 시작했기 때문에 최소 일수는 graph에서 가장 큰 수 - 1
'''

from collections import deque
import sys
input = sys.stdin.readline

dx = [-1, 1, 0, 0, 0, 0]
dy = [0, 0, -1, 1, 0, 0]
dz = [0, 0, 0, 0, -1, 1]

def bfs(graph):
    queue = deque()
    for i in range(h):
        for j in range(n):
            for k in range(m):
                if graph[i][j][k] == 1:
                    queue.append((i, j, k))

    while queue:
        z, x, y = queue.popleft()
        for i in range(6):
            nx = x + dx[i]
            ny = y + dy[i]
            nz = z + dz[i]
            if 0 <= nx < n and 0 <= ny < m and 0 <= nz < h:
                if graph[nz][nx][ny] == 0:
                    graph[nz][nx][ny] = graph[z][x][y] + 1
                    queue.append((nz, nx, ny))

m, n, h = map(int, input().split())
graph = []
result = 0

for _ in range(h):
    box = [list(map(int, input().split())) for _ in range(n)]
    graph.append(box)

bfs(graph)

for i in range(h):
    for j in range(n):
        for k in range(m):
            if graph[i][j][k] == 0:
                print(-1)
                exit(0)
            result = max(result, graph[i][j][k])

print(result - 1)