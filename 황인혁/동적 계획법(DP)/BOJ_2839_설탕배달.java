/*
1. 문제요약
입력 : 상근이가 배달하는 설탕의 그램수
출력 : 상근이가 배달하는 설탕봉지의 최소개수
조건 : 봉지는 3킬로와 5키로그램이 있다. 정확하게 봉지안에 담을수 없다면 -1을 출력한다.
2. 아이디어(문제접근법)
입력받은 값을 먼저 5로 나눈다. 5로 전부 나눠지는 것이 가장 작은 값이기 때문에 5로 나눈후 나눠지지 않는다면 3키로 봉지의 값을 늘려가며 값을 찾는다.
3. 어려움 및 해결방식
더 간단하게 풀 수 있을것 같은데 방법을 찾지 못했다.
 */

import java.util.Scanner;

public class BOJ_2839_성탕배달 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int threeBag = 0;
        while (true) {
            if(N%5==0){
                int fiveBag = threeBag + N/5;
                System.out.println(fiveBag);
                break;
            } else if(N >= 0){
                N -= 3;
                threeBag++;
            } else {
                System.out.println(-1);
                break;
            }
        }
    }
}