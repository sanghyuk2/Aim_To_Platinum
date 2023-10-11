'''
1. 문제요약
- 주어진 문자열이 ZOAC 일 때, A -> AC -> OAC -> ZOAC과 같이
- 아직 보여주지 않은 문자 중 추가했을 때의 문자열이 사전 순으로 가장 앞에 오도록 하는 문자를 보여주는 방식으로 출력

2. 아이디어(문제접근법)
- 전체 문자열에서 사전 순으로 가장 앞에 있는 문자를 찾고, visited 배열의 값을 true로 변경
- visited값이 true인 문자만 출력 후
- 재귀를 통해 현재 사전 순으로 가장 앞에 있는 문자의 뒤쪽에서 같은 과정을 반복

3. 어려움 및 해결방식
- 문자열 내에 같은 문자가 있을 때 string.find로 알맞은 인덱스를 찾지 못함
- 재귀 함수에 현재 문자의 인덱스 값을 함께 넘겨(min) find 함수에서 방금 탐색한 문자 뒤에서만 찾도록 범위를 지정해줌
'''

string = input()
visited = [False] * len(string)

def func(temp, min):
    array = list(temp)
    array.sort()

    for s in array:
        index = string.find(s, min + 1)
        if visited[index]:
            continue

        visited[index] = True
        result = ''
        for i in range(len(string)):
            if visited[i]:
                result += string[i]
        print(result)
        
        func(string[index+1:], index)

func(string, -1)