public class Main {
    static int[][] map;
    static boolean[] visited;
    static long minCost = Integer.MAX_VALUE;
    static int vertexCnt = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        vertexCnt = Integer.parseInt(br.readLine());
        map = new int[vertexCnt][vertexCnt];
        visited = new boolean[vertexCnt];

        for (int i = 0; i < map.length; i++) {
            String[] row = br.readLine().split(" ");
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = Integer.parseInt(row[j]);
            }
        }

        visited[0] = true;
        dfs( 0, 1, 0);
        System.out.println(minCost);
    }


    public static void dfs(int cur, int cnt, long cost) {
        if (cnt == vertexCnt) {
            minCost = Math.min(cost + map[cur][0], minCost);
            return;
        }

        for (int i = 0; i < vertexCnt; i++) {
            if (!visited[i] && map[cur][i] != 0) {
                visited[i] = true;
                dfs(i, cnt + 1, cost + map[cur][i]);
                visited[i] = false;
            }
        }
    }
}