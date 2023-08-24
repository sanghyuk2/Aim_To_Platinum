'''
1. 문제요약
- 주어진 배열을 M개 이하의 구간으로 나눌 때 구간의 점수의 최댓값 중 최솟값

2. 아이디어(문제접근법)
- 구간의 점수의 최댓값 중 최솟값을 이분탐색
- 반복문을 돌며 현재 수와 현재 구간의 최솟값, 최댓값을 비교해서 max_num과 min_num에 저장한 후,
- max_num - min_num이 현재 탐색하고 있는 구간의 점수의 최댓값의 최솟값보다 크면 구간 수 + 1
- M개 이하의 구간이므로 구간 수가 m보다 작거나 같을 때 result에 mid 값 저장

3. 어려움 및 해결방식
- 구간을 나눈 후, 현재 탐색하고 있는 수부터 다음 구간에 포함되므로, min_num과 max_num을 현재 탐색하고 있는 수로 초기화해야 함
'''

n, m = map(int, input().split())
numbers = list(map(int, input().split()))

start = 0
end = max(numbers)
result = 0

while start <= end:
    mid = (start + end) // 2
    min_num = max_num = numbers[0]
    count = 1

    for i in range(1, n):
        max_num = max(max_num, numbers[i])
        min_num = min(min_num, numbers[i])
        if max_num - min_num > mid:
            count += 1
            max_num = min_num = numbers[i]
    
    if count > m:
        start = mid + 1
    else:
        end = mid - 1
        result = mid

print(result)