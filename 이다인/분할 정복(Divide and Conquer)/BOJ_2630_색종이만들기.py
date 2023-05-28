'''
1. 아이디어
- 주어진 색종이가 모두 같은 색으로 칠해져 있지 않다면, 4등분으로 나누어 각각의 종이를 확인

2. 구현
- 재귀적으로 구현
- 첫번째 칸부터 순차적으로 확인하여 다른색이 있으면 4등분으로 나눔 -> 각각 divide_paper 함수 호출
- 모두 같은 색으로 칠해져 있으면 해당 색 개수 + 1
'''

n = int(input())
paper = [list(map(int, input().split())) for _ in range(n)]

white = 0
blue = 0

def divide_paper(paper):
    global white, blue
    color = paper[0][0]
    for i in paper:
        for j in i:
            if j != color:
                half = len(paper)//2
                divide_paper([row[:half] for row in paper[:half]])
                divide_paper([row[half:] for row in paper[:half]])
                divide_paper([row[:half] for row in paper[half:]])
                divide_paper([row[half:] for row in paper[half:]])
                return
    if color == 0:
        white += 1
    else:
        blue += 1

divide_paper(paper)

print(white)
print(blue)