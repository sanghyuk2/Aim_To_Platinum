'''
1. 문제요약
- 1 ~ N번 까지 들어야 하는 과목의 수와 선수과목 조건이 주어질 때 각 과목을 몇 학기에 이수할 수 있는지 출력

2. 아이디어(문제접근법)
- 위상정렬 활용
- graph[a] = a가 선수과목인 과목들
- indegree[b] = b를 듣기 위해 먼저 들어야 할 선수과목 수
- indegree가 0인 과목들을 큐에 넣고, 하나씩 꺼내서 graph에 있는 과목들의 indegree -= 1
- indegree = 0이면 선수과목을 모두 들었다는 뜻이므로 큐에 추가
'''

import sys
from collections import deque

input = sys.stdin.readline
n, m = map(int, input().split())
graph = [[] for _ in range(n + 1)]
indegree = [0] * (n + 1)

for _ in range(m):
    a, b = map(int, input().split())
    graph[a].append(b)
    indegree[b] += 1


def topology_sort():
    queue = deque()
    result = [1] * (n + 1)
    for i in range(0, n + 1):
        if not indegree[i]:
            queue.append(i)
    
    while (queue):
        a = queue.popleft()
        for i in graph[a]:
            indegree[i] -= 1
            if not indegree[i]:
                result[i] = result[a] + 1
                queue.append(i)
    return result

print(*topology_sort()[1:])
