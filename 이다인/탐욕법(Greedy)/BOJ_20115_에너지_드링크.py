n = int(input())
drinks = list(map(int, input().split()))
drinks.append(max(drinks))
print(sum(drinks)/2)