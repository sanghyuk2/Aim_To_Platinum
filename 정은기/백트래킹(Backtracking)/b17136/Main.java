package aim_to_platinum.backtracking.b17136;

/*
백트래킹, 브루트 포스 문제.
한 변의 길이가 1, 2, 3, 4, 5 인 정사각형 모양의 색종이를
한 변의 길이가 10 인 색종이 안에 붙이되, 다음 조건을 만족해야 한다.
1. 0이 적힌 칸에는 색종이가 있어서는 안된다.
2. 종이의 경계 밖을 나가서는 안된다.
3. 붙이는 종이들끼리 겹치면 안된다.
이 때 1이 적힌 모든 칸들을 붙이는데 필요한 색종이의 최소 개수를 구하면 된다.
- 현재의 위치와 사용된 종이의 총 개수를 인자로 넘겨주며 DFS 함수를 재귀호출하면 될 것 같다.

- 남아있는 종이의 개수를 나타내는 int[] paperArr
- DFS (x, y, 현재 사용한 종이 장수)
    - 만약 끝에 도착했으면 최소 개수를 최신화한다.
    - 현재 개수가 최소 개수보다 많으면 더 볼 필요가 없기 때문에 return.
    - 오른쪽 끝이면 (y==9) 다음줄로 넘어간다. (y=0, x++)

    - 종이를 순차탐색하던 중 1을 만나면
        - 해당 좌표를 꼭지점으로, 가장 큰 종이(5x5) 부터 가장 작은 종이(1x1)까지 붙이기를 시작한다.
            - 종이의 잔여량이 충분한지 확인한다.
            - 붙일 수 있는지 확인한다.
                - 종이 밖으로 나가는지 확인한다.
                - 0과 닿는지 확인한다.
        - 색종이를 붙이면 해당 종이의 남은 종이 장수--
        - DFS (x, y+1, 현재 사용한 종이 장수 + 1)
        - DFS 가 끝나면 해당 종이의 남은 종이 장수++
    - 1을 만난게 아니면 오른쪽으로 한 칸 간다. (y++)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int answer = Integer.MAX_VALUE;
    static int[] paperArr = {5, 5, 5, 5, 5};
    static int[][] board;

    public static void attach(int x, int y, int size){
        for(int i=x; i<(x+size); i++){
            for(int j=y; j<y+size; j++){
                board[i][j] = 0;
            }
        }
    }

    public static void detach(int x, int y, int size) {
        for (int i=x; i<(x+size); i++) {
            for (int j = y; j < y + size; j++) {
                board[i][j] = 1;
            }
        }
    }

    public static boolean isAttachable(int x, int y, int size){
        for(int i=x; i<(x+size); i++){
            for(int j=y; j<(y+size); j++){
                if(i<0 || j<0 || i>9 || j>9){
                    return false;
                }
                if(board[i][j] != 1) return false;
            }
        }
        return true;
    }

    public static void DFS(int x, int y, int count){
        if(x>=9 && y>9){
            answer = Math.min(count, answer);
            return;
        }
        if(count>=answer) return;
        if(y>9){
            DFS(x + 1, 0, count);
            return;
        }

        if(board[x][y] == 1){
            for(int i=5; i>=1; i--){
                if(paperArr[i-1]>0 && isAttachable(x, y, i)){
                    attach(x, y, i);
                    paperArr[i-1]--;
                    DFS(x, y+1, count+1);
                    detach(x, y, i);
                    paperArr[i-1]++;
                }
            }
        }else{
            DFS(x, y+1, count);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        board = new int[10][10];
        
        StringTokenizer stk;
        for(int x=0; x<10; x++){
             stk = new StringTokenizer(br.readLine());
            for(int y=0; y<10; y++){
                board[x][y] = Integer.parseInt(stk.nextToken());
            }
        }

        DFS(0, 0, 0);

        if (answer == Integer.MAX_VALUE) answer = -1;

        System.out.println(answer);
    }
}
