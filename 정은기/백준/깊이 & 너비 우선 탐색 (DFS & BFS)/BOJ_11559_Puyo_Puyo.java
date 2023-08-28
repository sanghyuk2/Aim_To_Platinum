package aim_to_platinum.week10_DFS_BFS.b11559;

/*
1. 문제 요약
- 12행 6열의 필드에 R, G, B, P, Y 다섯가지 색의 뿌요가 들어가 주어진다.
- 현재 주어진 상황에, 몇번의 '연쇄' 가 일어나는지 알아내면 된다.
    - 같은 색의 뿌요가 4개 이상 상하좌우로 연결되어 있다면 같은 색 뿌요들이 한 번에 없어진다.
        [이 경우 연쇄가 1 증가한 것으로 본다]
    - 한 번에 다른 색깔인 여러 그룹의 뿌요가 터지더라도 연쇄는 1 증가한다
        [즉, 터진 그룹의 수가 아니라 각 타이밍마다 터진 횟수로 센다]
    - 뿌요가 생기고 빈 공간이 있으면 위의 뿌요들이 아래로 떨어진다


2. 아이디어 (문제 접근법)
[아이디어-1]
- 필드 (board) 의 크기가 12행 6열로 정해져 있으니 각 좌표에 대한 반벅은 무시하기로 한다 (~ x 72)
- Character[][] board = new Character[12][6] 를 입력받는다
- LinkedList<LinkedList<Integer>> puyoList 를 생성한다
    BFS 를 활용해 알아낸 같은 색상의 뿌요의 좌표 모음이다
    puyoList 의 크기가 4 미만이라면 아무일도 일어나지 않는다
    puyoList 의 크기가 4 이상이라면 모든 좌표의 값들을 null 로 바꾼다
- 이제 [0, 0] 에서 시작해 [11,5] 까지 아래를 반복한다

[터뜨리기]
boolean isPopped = false
- 방문한 좌표라면 다음 좌표로 넘어간다
- 방문하지 않은 좌표라면 값을 확인한다
    - 값이 ' . ' 라면 다음 좌표로 넘어간다
    - 값이 R, G, B, P, Y 중 하나라면 puyoList 를 초기화한 후, puyoList 에 좌표를 담고 BFS 를 진행한다
        BFS 를 진행하며 같은 색상이라면 puyoList 에 좌표를 담고 방문 표시를 하고 계속 BFS 를 진행한다
        - puyoList 의 크기가 4 이상이라면 puyoList 에 저장된 좌표의 값들을 전부 ' . ' 로 바꾸고 isPopped = true
        - puyoList 의 크기가 4 미만이라면 다음 좌표로 넘어간다
[뿌요 아래로 떨어뜨리기]
- 각 열의 밑에서부터 ' . '이 아닌 값들을 queue 에 담아준다
- 이후 밑에서부터 다시 queue 의 값들을 채워주고, queue 가 비었다면 ' . ' 을 채워준다
[터졌는지 여부 확인]
- 위 과정이 끝날 때 isPopped = true 라면 연쇄 횟수 + 1


3. 어려움 및 해결
- 초기화 부분
    puyoList = new LinkedList<>();
    isVisited = new boolean[12][6];
을 조건문 if (board[i][j] != '.') 의 내부에 넣었다가 무한루프에 갇혔었다
*/

import java.io.*;
import java.util.*;

public class BOJ_11559_Puyo_Puyo {

    static Character[][] board = new Character[12][6];
    static LinkedList<int[]> puyoList = new LinkedList<>();
    static boolean[][] isVisited = new boolean[12][6];
    static int answer = 0;
    static int[] nextX = {1, -1, 0, 0};
    static int[] nextY = {0, 0, 1, -1};

    static void gravity(){
        Queue<Character> queue = new LinkedList<>();
        for(int i = 0; i < 6; i++){
            for(int j = 11; j >= 0; j--){
                if(board[j][i] != '.'){
                    queue.offer(board[j][i]);
                }
            }
            for(int j = 11 ; j >= 0 ;j--){
                if(queue.isEmpty()){
                    board[j][i] = '.';
                }else{
                    board[j][i] = queue.poll();
                }
            }
        }
    }
    static void DFS(int x, int y, char c){
        puyoList.add(new int[]{x, y});
        isVisited[x][y] = true;

        for(int i = 0; i < 4; i++){
            int nx = x + nextX[i];
            int ny = y + nextY[i];

            if(nx < 0 || nx > 11 || ny < 0 || ny > 5){
                continue;
            }

            if(isVisited[nx][ny] || board[nx][ny] != c){
                continue;
            }
            DFS(nx, ny, c);
        }
    }
    static void BFS(){

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < 12; i++){
            String s = br.readLine();
            for(int j = 0; j < 6; j++){
                board[i][j] = s.charAt(j);
            }
        }

        while(true) {

            boolean flag = false;

            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {

                    puyoList = new LinkedList<>();
                    isVisited = new boolean[12][6];

                    if (board[i][j] != '.') {
                        DFS(i, j, board[i][j]);
                    }

                    if(puyoList.size() >= 4){
                        flag = true;

                        for(int[] arr : puyoList){
                            int x = arr[0];
                            int y = arr[1];
                            board[x][y] = '.';
                        }
                    }
                }
            }

            if(!flag) {
                break;
            }
            gravity();
            answer++;
        }

        sb.append(answer);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
