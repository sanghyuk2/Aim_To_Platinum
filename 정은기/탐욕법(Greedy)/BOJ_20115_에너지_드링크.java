package aim_to_platinum.week05_greedy.b20115;
/*
1. 문제 요약
- 갖고있는 에너지 드링크의 수 N 이 주어진다 (2 <= N <= 10^5)
- 각 에너지 드링크의 양이 주어진다 (1 <= xi <= 10^9)
- 아래 과정을 에너지 드링크가 하나 남을 때까지 반복한다
    1. 두 에너지 드링크 A, B 를 고른다
    2. A+B/2 혹은 A/2+B 용량이 되도록 하나로 합친다
       이 때 절반으로 줄어드는 에너지 드링크는 붓는 에너지 드링크이다
    3. 부은 에너지 드링크는 합칠 에너지 드링크 목록에서 제외된다
- 위 과정의 최종 결과값이 최대가 되게 하면 된다

2. 아이디어 (문제 접근법)
[아이디어-1]
- 에너지 드링크 용량 배열 int[] energy 를 입력받는다
- Arrays.sort(energy) 로 오름차순 정렬한다
- 가장 큰 값 (energy[N-1]) 외의 나머지 값들에 오름차순으로 위 1~3 과정을 반복한다

3. 어려움 및 해결
[아이디어-1] -> 가장 큰 용량의 음료는 줄이면 안된다는 것은 알았지만
                병이 두 개만 있는게 아닌데 더 생각하지 않고 이전의 합을 나누다가 이후
                가장 용량이 큰 곳에 붓기를 반복하면 된다는 것을 깨닫고 바로 고쳤다
                이전의 합을 나눌 경우 더 앞의 용량들은 /2^(N-i) 가 된다..
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_20115_에너지_드링크 {

    static int N;

    static int[] energy;

    static double greedy(){
        Arrays.sort(energy);
        double max = energy[N-1];

        for(int i=0; i<N-1; i++){
            max += (double)energy[i]/2;
        }
        return max;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        N = Integer.parseInt(br.readLine());
        energy = new int[N];

        stk = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            energy[i] = Integer.parseInt(stk.nextToken());
        }

        System.out.println(greedy());
    }
}












