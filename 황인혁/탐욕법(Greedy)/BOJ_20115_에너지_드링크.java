/*
1. 문제이해
주어진 에너지드링크 용량을 보고 최대로 만들 수 있는 용량의 에너지 드링크를 만드는 문제
입력 : N(에너지 드링크의 개수)
          N개의 에너지 드링크 용량
출력 : 최대 에너지 드링크의 용량
조건 : 따르는 에너지 드링크의 용량의 절반밖에 추가하지 못한다.
2. 아이디어
배열에 용량 저장후 정렬해서
제일 용량이 많은 에너지 드링크에 다른 에너지 드링크의 용량을 추가한다.
3. 어려움 및 해결방식 X
 */


import java.util.Arrays;
import java.util.Scanner;

public class  BOJ_20115_에너지_드링크{

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        double[] drink = new double[N];
        for(int i = 0; i < N; i++) {
            drink[i] = sc.nextInt();
        }

        Arrays.sort(drink);

        double energy = drink[N-1];

        for(int i = 0; i < N -1; i++) {
            double num = drink[i]/2;
            energy += num;
        }

        System.out.println(energy);

    }

}