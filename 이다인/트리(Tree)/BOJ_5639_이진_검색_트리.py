'''
1. 문제요약
- 이진 검색 트리의 전위 순회 결과가 주어졌을 때, 이 트리를 후위 순회한 결과 구하기

2. 아이디어(문제접근법)
- post_order 함수를 재귀적으로 호출
- pre[i] > pre[start]면 i부터 start의 오른쪽 서브트리이므로, mid = i
- start + 1부터 mid - 1까지 왼쪽 서브트리 후위 순회
- mid부터 end까지 오른쪽 서브트리 후위 순회
- 현재 노드 출력

3. 어려움 및 해결방식
- 입력의 수가 주어지지 않았으므로, try-except를 활용해서 입력이 끝나면 다음으로 넘어갈 수 있게 함
- 오른쪽 서브트리가 없을 수 있으므로, mid를 end + 1로 초기화해서 하단의 post_order 중 첫번째는 왼쪽 서브트리 탐색, 두번째는 start > end이므로 바로 종료
'''

import sys
sys.setrecursionlimit(10 ** 6)
input = sys.stdin.readline

pre = []
while True:
    try:
        pre.append(int(input()))
    except:
        break

def post_order(start, end):
    if start > end:
        return

    mid = end + 1
    for i in range(start + 1, end + 1):
        if pre[i] > pre[start]:
            mid = i
            break
    
    post_order(start + 1, mid - 1)
    post_order(mid, end)
    print(pre[start])

post_order(0, len(pre) - 1)