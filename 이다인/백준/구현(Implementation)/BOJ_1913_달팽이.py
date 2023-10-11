'''
1. 문제요약
- NxN의 표의 중간부터 달팽이 모양으로 숫자를 채운 후 출력
- 주어진 자연수에 해당하는 칸의 좌표 출력

2. 아이디어(문제접근법)
- NxN 표의 중간부터 위 - 오른쪽 - 아래 - 왼쪽 순서대로 이동하고,
- 각 순서에서 이동하는 칸의 개수가 1칸 이동이 2번, 2칸 이동이 2번 ... 과 같이 늘어남
- 반복문을 통해 배열을 채우고, 범위를 벗어날 경우 출력 후 프로그램 종료
'''

n = int(input())
m = int(input())

array = [[0] * n for _ in range(n)]
dx = [-1, 0, 1, 0]
dy = [0, 1, 0, -1]

x = n // 2
y = n // 2
result_x = 0
result_y = 0
count = 1
direction = 0

array[x][y] = 1
if m == 1:
    result_x = x + 1
    result_y = y + 1

while True:
    for _ in range(2):
        for i in range(count):
            nx = x + dx[direction]
            ny = y + dy[direction]

            if nx < 0 or nx >= n or ny < 0 or ny >= n:
                for a in array:
                    print(*a)
                print(result_x, result_y)
                exit(0)
            
            array[nx][ny] = array[x][y] + 1
            if array[nx][ny] == m:
                result_x = nx + 1
                result_y = ny + 1

            x = nx
            y = ny
        direction = (direction + 1) % 4
    count += 1
