'''
1. 문제 요약
- 홍보할 수 있는 도시의 개수, 각 도시별 홍보하는 데 드는 비용과 그 비용으로 얻을 수 있는 고객의 수가 주어질 때
- 고객을 적어도 c명 늘이기 위해 투자해야 하는 돈의 최솟값

2. 아이디어
- dp 배열에 각 고객수 별 돈의 최솟값 저장
- 적어도 c명 이므로 c + customer - 1 까지 최솟값 계산, 고객수가 c를 넘어가면 dp[c]와 비교하여 최솟값 저장

3. 어려움 및 해결방식
- dp 배열의 초기값을 잘못 설정
- 돈의 최댓값이 나오는 경우는 c = 1000, cost = 100, customer = 1일 때 이므로 초기값을 1000*100+1 = 100001로 수정
'''

c, n = map(int, input().split())
dp = [100001] * (c + 1)
dp[0] = 0

for _ in range(n):
    cost, customer = map(int, input().split())
    for i in range(customer, c + customer):
        if (i > c):
            dp[c] = min(dp[c], dp[i - customer] + cost)
        else:
            dp[i] = min(dp[i], dp[i - customer] + cost)

print(dp[c])