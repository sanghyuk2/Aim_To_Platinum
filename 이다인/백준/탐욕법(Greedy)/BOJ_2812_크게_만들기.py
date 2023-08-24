'''
1. 문제요약
- 주어진 N자리 숫자에서 숫자 K개를 지워서 얻을 수 있는 가장 큰 수

2. 아이디어(문제접근법)
- stack에 앞자리 수부터 넣으면서 비교
- 현재 수가 stack의 마지막 수보다 크면 작은 수가 나올때까지 pop, k를 1씩 감소시킨 후 push
- k가 0이면 지워야 하는 숫자 개수만큼 지운 것이므로 주어진 수의 남은 부분을 stack에 추가한 후 break
- for문을 다 돌았는데도 k가 0이 아니면 뒤에서부터 숫자 k개 제거

3. 어려움 및 해결방식
- 범위 내의 최댓값을 구하는 방식으로 접근헸을 때 시간초과 발생
- stack을 활용해서 해결
'''

n, k = map(int, input().split())
num_arr  = list(map(int, input()))
stack = []
stack.append(num_arr[0])

for i, num in enumerate(num_arr[1:]):
    if k == 0:
        stack.extend(num_arr[i + 1:])
        break

    while num > stack[-1]:
        stack.pop()
        k -= 1
        if not k:
            break
        if not len(stack):
            break
    stack.append(num)

if k != 0:
    del stack[-k:]

print(''.join(map(str, stack)))