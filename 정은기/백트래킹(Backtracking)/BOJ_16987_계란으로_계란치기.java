/*
시간제한 2초로 충분하다고 생각
브루트 포스 방식으로 모든 경우의 수들 DFS 방식으로 건드린 다음
maxCount 를 출력하면 된다.
내구도와 무게 필드를 갖는 Egg 클래스를 만든다.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


class Egg{
    int durability;
    int weight;
    public Egg(int durability, int weight){
        this.durability = durability;
        this.weight = weight;
    }
}
public class BOJ_16987_계란으로_계란치기 {
    static void solution(int idx, int count){

        //제일 오른쪽 계란일 때 maxCount 업데이트, return
        if(idx == N){
            maxCount = Math.max(maxCount, count);
            return;
        }
        //현재 계란 빼고 전부 깨져있다면 maxCount 업데이트, return
        if(count == N-1){
            maxCount = Math.max(maxCount, count);
            return;
        }

        Egg nowEgg = eggArr[idx]; //현재의 계란 (DFS 롤백용)

        //현재 계란이 깨져있다면 다음 계란으로
        if(nowEgg.durability <= 0){
            solution(++idx, count);
            return;
        }

        //현재 계란이 멀쩡하다면 다른 계란 치기


        for(int i=0; i<N; i++){

            //스스로를 치려 한다면 continue
            if(i==idx) continue;
            //다른 계란이 깨져있다면 continue
            if(eggArr[i].durability <= 0) continue;

            //다른 계란이 멀쩡하다면 치기
            //갱신
            eggArr[idx].durability -= eggArr[i].weight;
            eggArr[i].durability -= eggArr[idx].weight;

            //현재 계란이 깨진다면 다음 계란으로, count++
            if(eggArr[idx].durability <= 0) count++;

            //다른 계란이 깨진다면 count++
            if(eggArr[i].durability <= 0) count++;

            solution(++idx, count);

            eggArr[idx].durability = nowEgg.durability;
        }

    }

    static int N;

    static int maxCount = Integer.MIN_VALUE;
    static boolean[] isVisited;
    static Egg[] eggArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = null;

        N = Integer.parseInt(br.readLine());
        eggArr = new Egg[N];

        for(int i=0; i<N; i++){
            stk = new StringTokenizer(br.readLine());
            int durability = Integer.parseInt(stk.nextToken());
            int weight = Integer.parseInt(stk.nextToken());
            eggArr[i] = new Egg(durability, weight);
        }

        //가장 왼쪽의 계란부터 시작하므로 eggArr[0]으로 시작하면 된다
        solution(0, 0);

        System.out.println(maxCount);
    }
}
