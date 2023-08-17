'''
1. 문제요약
- 절단기의 높이보다 큰 나무의 윗 부분을 잘라서 가져갈 때,
- 적어도 M미터의 나무를 집에 가져가기 위해서 절단기에 설정할 수 있는 높이의 최댓값

2. 아이디어(문제접근법)
- 절단기의 높이는 가장 큰 나무의 높이 보다 높을 수 없으므로, 초기 end값은 max(trees)
- 절단기의 높이를 가져갈 수 있는 나무의 길이와 비교하며 이분탐색
'''
import sys

input = sys.stdin.readline
n, m = map(int, input().split())
trees = list(map(int, input().split()))

start = 1
end = max(trees)

result = 0
while start <= end:
    total = 0
    mid = (start + end) // 2
    for i in trees:
        if i > mid:
            total += i - mid
    if total < m:
        end = mid - 1 
    else:
        result = mid
        start = mid + 1

print(result)
