'''
1. 문제요약
- 같은 색 뿌요가 4개 이상 상하좌우로 연결되어 있으면 한꺼번에 없어짐
- 여러 그룹이 있으면 동시에 터져야 하고, 여러 그룹이 터지더라도 1 연쇄
- 뿌요들이 없어지고 나서 위에 다른 뿌요들이 있다면, 중력의 영향을 받아 차례대로 아래로 떨어지고 연쇄 시작
- 연쇄가 몇 번 연속으로 일어나는지 구하기

2. 아이디어(문제접근법)
- graph를 탐색하면서 뿌요가 있는 경우 bfs로 탐색
    - temp 배열에 같은 색의 뿌요 위치 저장
- temp에 저장된 뿌요가 4개 이상이라면, clear 함수로 뿌요를 제거하고 check = True로 변경
- temp 집합을 초기화하고 다시 graph 탐색
- graph를 모두 탐색한 후 check = True면 연쇄가 일어났다는 뜻
    - count += 1
    - check = False로 초기화
    - gravity로 뿌요들을 아래로 떨어뜨림
        - graph를 가장 밑에서 부터 돌면서 빈칸이 있으면 해당 열을 아래부터 위로 탐색
        - 같은 열에 뿌요가 있으면 빈공간으로 떨어뜨림
- check = False면 연쇄가 일어나지 않았다는 뜻이므로, while문을 탈출하고 count 출력

3. 어려움 및 해결방식
- clear 후 temp 집합을 초기화해주지 않아서 오류가 발생했었음
- bfs로 탐색할 때 이미 temp에 있으면 방문한 곳이므로 temp와 queue에 삽입하면 안됨
- 따라서 temp를 리스트에서 집합으로 변경
'''

from collections import deque

graph = [list(input()) for _ in range(12)]

dx = [1, 0, -1, 0]
dy = [0, 1, 0, -1]

temp = set()
check = False
count = 0

def bfs(x, y):
    color = graph[x][y]
    queue = deque([(x, y)])
    temp.add((x, y))
    while queue:
        x, y = queue.popleft()
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            if (nx, ny) in temp:
                continue
            if nx < 0 or nx >= 12 or ny < 0 or ny >= 6:
                continue
            if graph[nx][ny] != color:
                continue
            queue.append((nx, ny))
            temp.add((nx, ny))


def clear():
    for x, y in temp:
        graph[x][y] = '.'


def gravity():
    for i in reversed(range(12)):
        for j in range(6):
            if graph[i][j] == '.':
                for k in reversed(range(0, i)):
                    if graph[k][j] != '.':
                        graph[i][j] = graph[k][j]
                        graph[k][j] = '.'
                        break


while(True):
    for i in range(12):
        for j in range(6):
            if graph[i][j] != '.':
                bfs(i, j)
                if len(temp) >= 4:
                    clear()
                    check = True
                temp.clear()
    
    if check:
        count += 1
        check = False
        gravity()
    else:
        break

print(count)