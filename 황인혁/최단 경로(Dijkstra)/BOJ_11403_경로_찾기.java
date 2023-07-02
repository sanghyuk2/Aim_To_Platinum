import java.util.Scanner;
/*
1. 문제이해
정점의 개수 N을 입력받고 i에서 j로 가는 길이 있는 경우에는 1, 없는 경우에는 0을 입력받은 후 i부터 j로 정점들을 통해 갈수 있는 지를 1과 0으로 표현해서 출력한다.
조건 : 주어진 값은 방향이 있다.
2. 아이디어
주어진 값을 map으로 구현해 놓고 통하는 길이 있는지 찾는다.
정점을 거쳐서 갈수있는 경우를 for문을 통해 구현한다. 갈수있는 길이 있는 경우 map[i][j]를 1로 초기화 해준다.
3. 어려움 및 해결방식
 */
public class BOJ_11403_경로_찾기 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][k] == 1 && map[k][j] == 1) {
                        map[i][j] = 1;
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}