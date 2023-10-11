'''
1. 문제요약
- 주어진 html 코드를 조건에 맞게 파싱하여 출력

2. 아이디어(문제접근법)
- main 태그 -> div 태그 -> p 태그 순으로 파싱 후 p 태그 안에 있는 문장의 모든 태그 제거 및 연속적인 공백 치환

3. 어려움 및 해결방식
- 파이썬의 정규표현식 라이브러리 re 활용
- findall로 태그 안의 내용을 파싱
- sub로 조건에 맞게 문자열 가공
'''

import re

html = input()

# . : \n을 제외한 모든 문자
# * : 바로 앞에 있는 문자가 0부터 무한대까지 반복될 수 있다는 의미
# (.*) : 그룹 지정, <main>과 </main> 사이의 모든 내용
# 결과 : '<div title="title_name_1"><p>paragraph 1</p><p>paragraph 2 <i>Italic Tag</i> <br > </p><p>paragraph 3 <b>Bold Tag</b> end.</p></div><div title="title_name_2"><p>paragraph 4</p><p>paragraph 5 <i>Italic Tag 2</i> <br > end.</p></div>'
main = re.findall('<main>(.*)</main>', html)[0]

# (.*?) : div 태그 안에 있는 문자열을 최소 일치로 만듦 -> (.*)는 최대 일치로, <div title="title_name_1"> 부터 맨 끝의 </div>까지 하나로 인식
# 결과 : [('title_name_1', '<p>paragraph 1</p><p>paragraph 2 <i>Italic Tag</i> <br > </p><p>paragraph 3 <b>Bold Tag</b> end.</p>'), ('title_name_2', '<p>paragraph 4</p><p>paragraph 5 <i>Italic Tag 2</i> <br > end.</p>')]
div_list = re.findall('<div title="(.*?)">(.*?)</div>', main)

for title, paragraph in div_list:
    print('title :', title)
    p_list = re.findall('<p>(.*?)</p>', paragraph)
    for p in p_list:
        # p 태그 안에 있는 문장에서 모든 태그를 지움
        p = re.sub('(<.*?>)', '', p)
        # \s+ : 공백문자가 하나 이상 연속해서 나타날 경우
        # p.strip으로 문장 시작과 끝의 공백을 지운 후, 연속적인 공백을 하나의 공백으로 바꿈
        p = re.sub('\s+', ' ', p.strip())
        print(p)