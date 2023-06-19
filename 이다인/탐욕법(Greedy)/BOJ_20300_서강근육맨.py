'''
1. 문제요약
- N개의 운동 기구를 되도록 하루에 두 개씩 선택할 때, 근손실 정도가 M을 넘지 않도록 선택
- M의 최솟값

2. 아이디어(문제접근법)
- n이 홀수인 경우 m의 초기값은 근손실 정도가 가장 큰 마지막 운동기구
- temp 변수에 양 끝 값의 근손실 정도를 더하면서 현재 m보다 클 경우 m값 갱신

3. 어려움 및 해결방식
- 근손실 정도가 오름차순으로 주어진다는 조건이 없기 때문에 입력값들을 정렬해줘야 함
'''

n = int(input())
list = list(map(int, input().split()))
list.sort()
m = 0

if n % 2 == 1:
    m = list[n - 1]
    n -= 1

for i in range(n):
    temp = list[i] + list[n - i - 1]
    if temp > m:
        m = temp

print(m)