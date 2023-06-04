'''
1. 아이디어
- n = 6일때는 n = 3의 결과를 가로로 두 번 출력
- 이전 결과 활용

2. 구현
- stars 배열에 n = 3일 때의 결과를 각 행 별로 저장 (양쪽의 공백은 고려 x)
- i = 3, 6, 12, 24, 48 ...
- 이전 결과의 각 행 사이에 알맞은 공백을 추가하여 stars 배열에 추가
- 현재 행의 길이는 직전 행의 길이 + 2 이므로, 공백은 직전 행의 길이 + 2에서 별 길이 * 2를 뺀 값
- stars 배열을 한 줄 씩 출력, center 함수를 활용하여 가운데 정렬
'''

n = int(input())

stars = [''] * n
stars[0] = "*"
stars[1] = "* *"
stars[2] = "*****"

i = 3
while (i < n):
    for j in range(i):
        stars[i+j] = stars[j] + " " * (len(stars[i+j- 1]) + 2 - len(stars[j] * 2)) + stars[j]
    i += i

for star in stars:
    print(star.center(len(stars[n-1])))