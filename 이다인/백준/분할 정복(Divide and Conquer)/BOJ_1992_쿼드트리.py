'''
1. 아이디어
- 0과 1이 섞여있으면 4등분으로 나누어 각각의 영상을 확인

2. 구현
- 재귀적으로 구현
- 다른 색이 있으면 결과 문자열에 '(' 추가, 4등분으로 나누어 각각 zip 함수 호출
- 4개의 영상이 모두 압축되었다면 결과 문자열에 ')' 추가
- 모두 같은 색으로 되어 있으면 결과 문자열에 해당 색 추가
'''

n = int(input())
image = [list(map(int, input())) for _ in range(n)]
result = ''

def zip(image):
    global result
    half = len(image) // 2
    dot = image[0][0]
    for i in image:
        for j in i:
            if j != dot:
                result += '('
                zip([row[:half] for row in image[:half]])
                zip([row[half:] for row in image[:half]])
                zip([row[:half] for row in image[half:]])
                zip([row[half:] for row in image[half:]])
                result += ')'
                return
    result += str(dot)

zip(image)
print(result)