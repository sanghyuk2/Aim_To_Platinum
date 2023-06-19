'''
1. 문제요약
- 2를 곱하기 / 1을 수의 가장 오른쪽에 추가하기
- 두 가지 연산이 가능할 때, 정수 A를 B로 바꾸는데 필요한 연산의 최솟값

2. 아이디어(문제접근법)
- bfs 활용
- queue에 (숫자, 연산 횟수)를 추가해서 pop한 결과가 b면 count 반환
- queue가 빌 때까지 b를 만들지 못하면 -1 반환
'''

from collections import deque


def bfs(a, b):
    queue = deque()
    queue.append((a, 1))

    while queue:
        now, count = queue.popleft()
        if now == b:
            return count
        elif now > b:
            continue

        queue.append((2 * now, count + 1))
        queue.append((int(str(now) + '1'), count + 1))
    return -1

a, b = map(int, input().split())

print(bfs(a, b))