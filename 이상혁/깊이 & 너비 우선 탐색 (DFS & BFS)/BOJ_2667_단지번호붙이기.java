/*
1. 문제요약

정사각형 모양의 지도가 있다. 1은 집이 있는 곳을, 0은 집이 없는 곳을 나타낸다. 철수는 이 지도를 가지고 연결된 집의 모임인 단지를 정의하고, 단지에 번호를 붙이려 한다. 여기서 연결되었다는 것은 어떤 집이 좌우, 혹은 아래위로 다른 집이 있는 경우를 말한다. 대각선상에 집이 있는 경우는 연결된 것이 아니다.

지도를 입력하여 단지수를 출력하고, 각 단지에 속하는 집의 수를 오름차순으로 정렬하여 출력하는 프로그램을 작성하시오.

2. 아이디어(문제접근법)

특정 위치에서부터 주변 값이 1인 단지 내 모든 집의 수를 끝까지 세는 문제이기에 DFS로 풀고자 하였다

특정 위치의 상, 하, 좌, 우의 값이 1일 때 0으로 바꾸면서 단지에 속하는 집의 수를 세자

단지에 속하는 집의 수를 세다 주변의 모든 값이 0일 때, 단지에 속하는 집의 수를 모두 세었다는 의미이니, 단지 수를 셀 수 있다

3. 어려움 및 해결 방식
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    static int N;
    static int[][] graph;
    static int cnt = 0;
    static List<Integer> resultList = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        graph = new int[N][N];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                graph[i][j] = str.charAt(j) - '0';
            }
        }

        int total = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                cnt = 0;
                if (dfs(i, j)) {
                    resultList.add(cnt);
                    total++;
                }
            }
        }

        Collections.sort(resultList);

        System.out.println(total);
        for (int result : resultList) {
            System.out.println(result);
        }
    }

    private static boolean dfs(int x, int y) {
        if (x < 0 || x >= N || y < 0 || y >= N) {
            return false;
        }

        if (graph[x][y] == 1) {
            cnt++;
            graph[x][y] = 0;

            dfs(x - 1, y);
            dfs(x + 1, y);
            dfs(x, y - 1);
            dfs(x, y + 1);
            return true;
        }
        return false;
    }
}