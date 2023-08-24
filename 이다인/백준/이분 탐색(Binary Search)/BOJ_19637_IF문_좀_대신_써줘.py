'''
1. 문제요약
- 칭호의 전투력 상한값이 주어질 때, 각 캐릭터의 전투력에 맞는 칭호 출력

2. 아이디어(문제접근법)
- 칭호의 전투력 상한값을 캐릭터의 전투력과 비교하며 이분탐색
'''
import sys

input = sys.stdin.readline
n, m = map(int, input().split())

titles = []
for _ in range(n):
    title, limit = input().split()
    titles.append((title, int(limit)))

for _ in range(m):
    power = int(input())
    start = 0
    end = len(titles)

    while start <= end:
        mid = (start + end) // 2
        if power > titles[mid][1]:
            start = mid + 1
        else:
            title = titles[mid][0]
            end = mid - 1
    print(title)