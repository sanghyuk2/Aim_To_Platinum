/*
1. 문제 이해
주어진 꿀통의 수와 각 꿀통의 양이 주어질 때, 두 마리의 벌이 선택한 꿀통 사이에서 가질 수 있는 최대의 꿀의 양을 구하는 문제입니다.
입력: N(꿀통의 수)
         N개의 꿀통의 양
출력: 최대 꿀의 양
조건
- 두 마리의 벌이 시작한 위치의 꿀통은 채취할 수 없습니다.
- 꿀통의 위치(종료 위치)는 채취 가능합니다.
2. 아이디어
Prefix Sum을 활용하여 각 위치에서 가능한 꿀의 양을 빠르게 계산합니다.
이후, 두 벌이 선택한 꿀통 사이에서의 꿀의 양을 구하는 과정에서 Prefix Sum을 활용하여 시간을 단축시킵니다.
경우의 수를 생각한다.
꿀통이 중간에 위치할 때(꿀통의 위치가 변경 : 꿀벌은 양쪽 끝에 위치)
꿀통이 오른쪽 끝에 위치할 때(벌 한마리의 위치가 변경 : 꿀통과 벌 한마리는 고정)
꿀통이 왼쪽 끝에 위치할 때(벌 한마리의 위치가 변경 : 꿀통과 벌 한마리는 고정)
3. 어려움 및 해결 방식.
최대값의 값을 찾는 부분에서 idx값의 범위를 N까지로 설정해서 필요없는 값이 추가적으로 들어가서 통과할 수 없었다.
꿀통이 왼쪽에 있을 때는 오른쪽 끝에 꿀벌이 있다고 가정하기 때문에 범위를 N-1까지로 설정해준다.
 */


import java.util.Scanner;

public class  BOJ_21758_꿀_따기{

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[] honey = new int[N];
        for(int i = 0; i < N; i++) {
            honey[i] = sc.nextInt();
        }

        int[] sumHoney = new int[N];
        sumHoney[0] = honey[0];
        for(int i = 1; i < N; i++) {
            sumHoney[i] = sumHoney[i-1] + honey[i];
        }

        int result = 0;
        for(int i = 1; i < N-1; i++) {
            result = Math.max(result, (sumHoney[i] - honey[0]) +
                (sumHoney[N-2] - sumHoney[i-1]));
            result = Math.max(result, (sumHoney[N-1] - honey[0] - honey[i]) +
                (sumHoney[N-1] - sumHoney[i]));
            result = Math.max(result, (sumHoney[N-2] - honey[i]) + sumHoney[i-1]);
        }

        System.out.println(result);
    }

}