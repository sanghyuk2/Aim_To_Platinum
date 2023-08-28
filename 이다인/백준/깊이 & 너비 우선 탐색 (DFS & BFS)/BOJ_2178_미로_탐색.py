'''
1. 문제요약
- 미로에서 1은 이동할 수 있는 칸을 나타내고, 0은 이동할 수 없는 칸을 나타낼 때
- (1, 1)에서 출발하여 (N, M)의 위치로 이동할 때 지나야 하는 최소의 칸 수 구하기

2. 아이디어(문제접근법)
- 네 방향을 미리 정의한 후, bfs로 미로를 탐색하며 인접한 칸이 미로 안에 있으며 이동할 수 있는 칸이면 큐에 삽입
- 해당 칸에 도달할 때까지 지나온 칸의 수를 graph에 저장
'''

from collections import deque

n, m = map(int, input().split())
graph = [list(map(int, input())) for _ in range(n)]

dx = [0, 1, -1, 0]
dy = [1, 0, 0, -1]

def bfs(x, y):
    queue = deque([(x, y)])

    while queue:
        x, y = queue.popleft()
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            if nx < 0 or nx >= n or ny < 0 or ny >= m:
                continue
            if graph[nx][ny] == 0:
                continue
            if graph[nx][ny] == 1:
                graph[nx][ny] = graph[x][y] + 1
                if nx == n - 1 and ny == m - 1:
                    return graph[nx][ny]
                queue.append((nx, ny))

print(bfs(0, 0))