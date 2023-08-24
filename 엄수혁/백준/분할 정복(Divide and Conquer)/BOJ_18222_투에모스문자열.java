/*
 * X는 맨 처음에 "0"으로 시작한다.
 * X에서 0을 1로, 1을 0으로 뒤바꾼 문자열 X'을 만든다.
 * X의 뒤에 X'를 붙인 문자열을 X로 다시 정의한다.
 * 2~3의 과정을 무한히 반복한다.
 *
 * 0 -> 01 -> 0110 -> 01101001 -> 0110100110010110
 * 1은 반드시 0이다.
 * N번째 자리수 인덱스가 0부터 시작이 아닌 1부터 시작이므로
 * (N-1)을 바이트 코드로 변환 후 1인 비트의 수를 % 2 했을 때의 결과가 해당 자리 숫자
 * */
public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(in.readLine());

        System.out.print(Long.bitCount(N-1)%2);
    }
}
