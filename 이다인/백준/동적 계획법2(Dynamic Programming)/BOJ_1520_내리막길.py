'''
1. 문제요약
- 지도의 각 칸에 그 지점의 높이가 쓰여 있을 때, 항상 높이가 더 낮은 지점으로만 이동하여 제일 왼쪽 위 칸에서 제일 오른쪽 아래 칸으로 가는 경로의 개수 h

2. 아이디어(문제접근법)
- dfs로 높이가 더 낮은 칸들을 찾아 경로 탐색
- dp[x][y] = (x, y)에서 목표 지점에 도달할 수 있는 경로의 개수
- -1 = 도달한 적 없는 칸, 0 = 목표 지점에 도달할 수 있는 경로가 없는 칸
- dfs로 경로 탐색을 하면서 해당 칸의 dp 배열 값이 0이면 더 탐색하지 않고 continue, 양수면 dp 배열의 값을 h에 더하고 continue

3. 어려움 및 해결방식
- dfs만 활용했을 때 시간초과 발생
- dp 배열을 활용해서 같은 경로를 반복적으로 탐색하지 않도록 수정
'''

import sys
sys.setrecursionlimit(10**6)

input = sys.stdin.readline
m, n = map(int, input().split())
graph = [list(map(int, input().split())) for _ in range(m)]
dp = [[-1] * n for _ in range(m)]
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]
h = 0


def dfs(x, y):
    global h
    dp[x][y] = 0
    if x == (m - 1) and y == (n - 1):
        h += 1
        if dp[x][y] > 0:
            dp[x][y] += 1
        else:
            dp[x][y] = 1
        return dp[x][y]

    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]
        
        if nx < 0 or nx >= m or ny < 0 or ny >= n:
            continue
        if graph[x][y] <= graph[nx][ny]:
            continue
        if not dp[nx][ny]:
            continue
        if dp[nx][ny] > 0:
            h += dp[nx][ny]
            dp[x][y] += dp[nx][ny]
            continue

        dp[x][y] += dfs(nx, ny)

    return dp[x][y]

dfs(0, 0)
print(h)