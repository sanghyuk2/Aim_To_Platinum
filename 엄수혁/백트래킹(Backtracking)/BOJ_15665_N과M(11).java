/*
* N개의 숫자를 입력받아 길이가 m인 수열을 만들어라
*
* 전제 조건
*   1. 각 숫자는 띄어쓰기를 통해 간격을 제어한다.
*   2. 공통된 숫자는 이용 불가능하다
*
* 문제 해결 방법
*   1. 숫자 크기의 순서대로 나타내야하기 때문에 Sorting
*   2. LinkedSet<Integer> 자료구조를 통한 공통된 숫자 제어 및 정렬된 상태 유지
*  * */

public class Main {
    static List<Integer> list = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();
    static int N,M;
    static Integer[] values;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        values = new Integer[N];

        for (int i = 0; i < N; i++) {
            values[i] = Integer.parseInt(st.nextToken());
        }
        list = Arrays.stream(values).sorted().distinct().collect(toList());


        dfs(0,"");

        System.out.println(sb.toString());

        br.close();

    }

    public static void dfs(int depth, String sequence) {
        if (depth == M) {
            sb.append(sequence).append("\n");
            return;
        }

        for (Integer value : list) {
            dfs(depth + 1, sequence + value + " ");
        }
    }
}