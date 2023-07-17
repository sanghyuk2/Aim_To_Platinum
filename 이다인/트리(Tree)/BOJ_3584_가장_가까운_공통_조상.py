'''
1. 문제요약
- 루트가 있는 트리 상의 두 노드가 주어질 때, 그 두 노드의 가장 가까운 공통 조상 찾기

2. 아이디어(문제접근법)
- tree 딕셔너리에 key 값은 노드, value 값은 그 노드의 부모 노드를 저장
- 첫번째 노드의 조상들을 parents set에 저장
- 반복문을 통해 두번째 노드의 조상들을 돌며 parents 배열에 있으면 출력하고 리턴

3. 어려움 및 해결방식
- set의 탐색 시간복잡도가 O(1)이기 때문에 배열 대신 set 사용
'''

import sys
input = sys.stdin.readline

t = int(input())
tree = {}

for _ in range(t):
    n = int(input())
    tree = {}
    parents = set()

    for _ in range(n - 1):
        a, b = map(int, input().split())
        tree[b] = a

    a, b = map(int, input().split())

    while a in tree:
        parents.add(a)
        a = tree[a]
    parents.add(a)

    while True:
        if b in parents:
            print(b)
            break
        else:
            b = tree[b]