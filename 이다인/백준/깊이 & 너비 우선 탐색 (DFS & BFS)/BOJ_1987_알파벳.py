'''
1. 문제요약
- 좌측 상단 칸에 놓여있는 말이 인접한 네 칸 중 한 칸으로 이동할 수 있을 때,
- 지금까지 지나온 칸과 같은 알파벳이 적힌 칸으로는 이동할 수 없는 경우 최대 이동할 수 있는 칸 수 구하기

2. 아이디어(문제접근법)
- dfs로 상하좌우 칸을 탐색하며 보드 안에 있고, 지금까지 지나온 칸과 다른 알파벳이 적힌 경우 이동
- alphabets 집합에 지금까지 지나온 알파벳 저장
- count 변수는 지금까지 지나온 칸 수, result는 현재 칸에서 상하좌우를 탐색했을 때 최대한 지날 수 있는 칸 수

3. 어려움 및 해결방식
- alphabets 집합을 그대로 넘겼을 때, 기존 집합이 그대로 공유되어 재귀가 끝나고 돌아와도 복구되지 않는 문제가 생김
- alphabets.copy()로 복사해서 넘겼더니 시간초과 발생
- 재귀가 끝난 후 return 하기 전에 alphabets 집합에서 현재 방문한 칸을 제거해서 복구시키는 방법으로 해결
'''

r, c = map(int, input().split())
graph = [list(input()) for _ in range(r)]

count = 0
alphabets = set()

dx = [-1, 0, 1, 0]
dy = [0, 1, 0, -1]

def dfs(x, y, alphabets, count):
    alphabets.add(graph[x][y])
    count += 1
    result = count
    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]
        if nx < 0 or nx >= r or ny < 0 or ny >= c:
            continue
        if graph[nx][ny] in alphabets:
            continue

        result = max(dfs(nx, ny, alphabets, count), result)
    alphabets.remove(graph[x][y])
    return result

print(dfs(0, 0, alphabets, 0))