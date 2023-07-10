'''
1. 문제요약
- 이진 트리를 입력받아 전위 순회, 중위 순회, 후위 순회한 결과를 출력

2. 아이디어(문제접근법)
- 각각의 함수를 재귀적으로 호출하면서 탐색
- 현재 노드를 출력하는 위치를 조정함으로써 전위순회, 중위순회, 후위순회 실행

3. 어려움 및 해결방식
- 딕셔너리에 key는 노드, value[0]은 왼쪽 자식 노드, value[1]은 오른쪽 자식 노드 저장
'''

n = int(input())
first = ''
tree = {}

def preorder(node):
    print(node, end='')
    if tree[node][0]:
        preorder(tree[node][0])
    if tree[node][1]:
        preorder(tree[node][1])

def inorder(node):
    if tree[node][0]:
        inorder(tree[node][0])
    print(node, end='')
    if tree[node][1]:
        inorder(tree[node][1])

def postorder(node):
    if tree[node][0]:
        postorder(tree[node][0])
    if tree[node][1]:
        postorder(tree[node][1])
    print(node, end='')


for i in range(1, n + 1):
    node, left, right = input().split()
    
    if i == 1:
        first = node
    if left == '.':
        left = 0
    if right == '.':
        right = 0

    tree[node] = [left, right]

preorder(first)
print()
inorder(first)
print()
postorder(first)