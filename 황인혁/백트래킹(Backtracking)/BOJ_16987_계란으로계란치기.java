/*
1. 문제이해
계란의 내구도와 무게가 주어지고 왼쪽에 있는 계란부터 들고 오른쪽에 있는 계란부터
차례대로 치며 깰수 있는 계란의 최대값을 구한다.
입력 : 계란의 수(N), 아래줄부터 int[N][2](계란의 내구도, 무게)
출력 : 최대한 많이 깰수 있는 계란의 수
조건
- 가장 왼쪽의 계란을 든다.
- 계란은 한번만 친다(깨질때 까지 치기X)
- 손에 든 게란이 깨지면 바로 오른쪽에 있는 계란을 든다.
- 손에 든 계란이 가장 오른쪽 계란이면 종료
- *꼭 바로 오른쪽에 있는 계란을 깰 필요는 없음
2. 아이디어
모든 계란을 들고 친 경우 return
if 현재 계란이 깨진 경우 ⇒
    결과값(ans)에 cnt값과 비교하여 최대값을 저장
    다음 계란으로 dfs
else 현재 계란이 안깨진 경우
        한번씩 치기
            부딧힐 경우(
             egg[손에 든 계란 내구도] -= egg[맞은 계란 무게]
             egg[맞은 계란 내구도] -= egg[손에 든 계란 무게]
             )

            자기자신을 치는 경우와 이미 칠 계란이 깨진 경우는 제외

           계란을 치며 cnt에 깨진 계란을 ++(친 계란이 깨진것과 맞은 계란이
           깨진 경우 고려)
           이후 원상복구
3. 사용변수
int N : 계란의 개수
int[][] eggs : 계란 정보 저장
int max : 결과값
boolean isBroken : 깨졌는지 확인
4. 자료구조
dfs( 계란 index : int idx)
5. 문제점/해결책
-처음에 문제를 제대로 이해하지 못했다. 가장 왼쪽에 계란부터 시작해서 바로 오른쪽에 있는 계란부터 순차적으로 깨는 줄 알았지만
바로 오른쪽에 있는 계란부터 시작하지 않아도 되어서 여러 경우의 수를 탐색해서 최대로 깰수 있는 계란의 수를 찾아냈다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16987_계란으로계란치기 {
    static int N;
    static int[][] eggs;
    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        eggs = new int[N][2];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            eggs[i][0] = Integer.parseInt(st.nextToken());
            eggs[i][1] = Integer.parseInt(st.nextToken());
        }

        dfs(0);

        System.out.println(max);
    }

    public static void dfs(int idx) {
        if (idx == N) {
            int cnt = 0;

            for (int i = 0; i < N; i++) {
                if (eggs[i][0] <= 0) {
                    cnt++;
                }
            }
            max = Math.max(max, cnt);
            return;
        }

        if (eggs[idx][0] <= 0) {
            dfs(idx + 1);
            return;
        }

        boolean isBroken = false;
        for (int i = 0; i < N; i++) {
            if (idx == i || eggs[i][0] <= 0) {
                continue;
            }
            isBroken = true;
            eggs[idx][0] -= eggs[i][1];
            eggs[i][0] -= eggs[idx][1];

            dfs(idx + 1);

            eggs[idx][0] += eggs[i][1];
            eggs[i][0] += eggs[idx][1];
        }

        if (!isBroken) {
            dfs(idx + 1);
        }
    }
}