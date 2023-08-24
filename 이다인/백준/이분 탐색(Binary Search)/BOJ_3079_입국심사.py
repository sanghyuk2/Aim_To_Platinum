'''
1. 문제요약
- N개의 입국심사대의 입국심사관이 심사를 하는데 걸리는 시간 T가 각각 주어질 때, M명이 모두 심사를 받는데 걸리는 시간의 최솟값

2. 아이디어(문제접근법)
- 모두가 심사를 받는데 걸리는 시간을 이분탐색
- end 값은 가장 오래 걸리는 심사대의 심사시간 * 인원 수
- 주어진 시간들을 오름차순으로 정렬한 후, 반복문을 돌며 주어진 시간에 각 심사대에서 몇 명을 심사할 수 있는지 count
- count가 인원 수 보다 크거나 같을 때 result에 mid 값 저장

3. 어려움 및 해결방식
- 현재 비어있는 심사대에서 심사를 받는 것과, 몇 초 더 기다린 후 다른 심사대에서 심사를 받는 경우의 수를 고려하는 것이 어려웠음
- 단순하게 주어진 시간동안 각 심사대에서 받을 수 있는 인원의 수의 합만 고려
'''

n, m = map(int, input().split())
t_k = [int(input()) for _ in range(n)]
t_k.sort()

start = t_k[0]
end = t_k[-1] * m
result = 0

while start <= end:
    mid = (start + end) // 2
    count = 0

    for t in t_k:
        count += mid // t
    
    if count < m:
        start = mid + 1
    else:
        result = mid
        end = mid - 1

print(result)