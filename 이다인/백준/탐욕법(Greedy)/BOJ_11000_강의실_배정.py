'''
1. 문제요약
- N개 수업의 시작 시간과 종료 시간이 주어질 때, 강의실의 최소 개수

2. 아이디어(문제접근법)
- 수업을 시작 시간 기준으로 오름차순 정렬
- 우선순위 큐에 첫 수업의 종료 시간 push
- classes 배열을 돌면서, 현재 수업의 시작 시간이 우선순위 큐 맨 앞에 있는 수업(종료 시간이 가장 빠른 수업)의 종료 시간보다 느리면
- 해당 강의실에서 현재 수업을 들을 수 있다는 뜻이므로 맨 앞에 있는 수업을 pop, 현재 수업의 종료 시간을 push
- 그렇지 않다면 강의실을 추가해야 하므로 현재 수업의 종료 시간을 push
- 우선순위 큐의 길이가 강의실의 최소 개수

3. 어려움 및 해결방식
- 배열에 현재 사용하는 강의실들의 끝나는 시간을 저장한 후, 반복적으로 확인하는 방법으로 접근한 결과 시간초과 발생
- 우선순위 큐를 활용해서 각 강의실이 끝나는 시간을 기준으로 오름차순 정렬될 수 있도록 수정
'''

import sys
import heapq

input = sys.stdin.readline
n = int(input())
classes = [tuple(map(int, input().split())) for _ in range(n)]

classes.sort()
room = []
heapq.heappush(room, classes[0][1])

for class_ in classes[1:]:
    if (class_[0] >= room[0]):
        heapq.heappop(room)
        heapq.heappush(room, class_[1])
    else:
        heapq.heappush(room, class_[1])

print(len(room))

'''
import sys

input = sys.stdin.readline
n = int(input())
classes = [list(map(int, input().split())) for _ in range(n)]

if (n == 1):
    print(1)
    exit()

classes.sort()
result = []
result.append(classes[0][1])

check = False
for class_ in classes[1:]:
    for i in range(len(result)):
        if (class_[0] >= result[i]):
            result[i] = class_[1]
            check = True
            break
    if (not check):
        result.append(class_[1])
    check = False

print(len(result))
'''