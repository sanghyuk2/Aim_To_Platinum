package aim_to_platinum.week11_implementation.b20164;


/*
1. 문제 요약
- 숫자 N(1 ≤ N ≤ 10^9-1)이 주어졌을 때
    다음 과정 중 볼 수 있는 홀수의 개수를 기록한다
    [1] - 한 자리라면 : 짝수거나 홀수이므로 여기서 끝
    [2] - 두 자리라면 : 2개로 나눠서 합을 구해서 새로운 수로 생각
    [3] - 세 자리 이상 : 임의의 위치에서 쪼개 3개의 수로 만든다
                   -> 3개를 더한 수를 새로운 수로 생각한다
                   -> 위의 1 or 2 or 3 반복


2. 아이디어 (문제 접근법)
[아이디어-1]
- 우선 전부 해보는 방법 (브루트 포스)
- 숫자를 입력받고 다음의 cut 메서드에 입력한다
    - 홀수의 개수를 세는 getOdd() 메서드 실행, 홀수 만날 때마다 count++
    - point1 과 point2 가 있다고 하자
        이 때 각 point 가 가리키는 곳은 쪼개는 위치가 된다
        point1 은 for( 1 ~ K-2 ) 범위
            point2 는 for( point1 + 1 ~ K-1 ) 범위
    - 쪼갠 숫자를 합쳐서 새로운 cut 에 넣는다
- 값을 비교하는 메서드를 사용해 최대값과 최소값을 업데이트 한다


3. 어려움 및 해결
- 홀수를 세는 작업을 String 으로 처리하려는 실수를 했다
- 전
        System.out.println("[getOdd]");
        for(char c : value.toCharArray()){
            if(c - '0' % 2 == 1){
                count++;
            }
        }
- 후
        int intValue = Integer.parseInt(value);
        while(intValue > 0){
            if(intValue % 10 % 2 == 1){
                count++;
            }
            intValue /= 10;
        }
*/


import java.io.*;

public class BOJ_20164_홀수_홀릭_호석 {
    static int[] answers = {Integer.MAX_VALUE, Integer.MIN_VALUE};
    public static void update(int count){
//        System.out.println("[update]");
//        System.out.println("전 : " + answers[0] + " " + answers[1]);
        answers[0] = Math.min(count, answers[0]);
        answers[1] = Math.max(count, answers[1]);
//        System.out.println("후 : " + answers[0] + " " + answers[1]);

    }
    public static int getOdd(String value, int count){

        int intValue = Integer.parseInt(value);
        while(intValue > 0){
            if(intValue % 10 % 2 == 1){
                count++;
            }
            intValue /= 10;
        }

        return count;
    }
    public static void cut(String now, int count){

        //현재 숫자에서 홀수 체크, 업데이트
        int newCount = getOdd(now, count);

        //한 자리 숫자면 최소값, 최대값 업데이트
        if(Integer.parseInt(now) < 10){
            update(newCount);
        }
        //두 자리 숫자면 각 자리 더해서 cut
        else if(Integer.parseInt(now) < 100){
            int a1 = Integer.parseInt(now) / 10;
            int a2 = Integer.parseInt(now) % 10;
            int newValue = (Integer.parseInt(now) / 10)  + (Integer.parseInt(now) % 10);
            cut(Integer.toString(newValue), newCount);
        }
        //세 자리 이상이면 세 부분으로 나눠서 더한 후, cut
        else{
            int[] arr = new int[3];
            for(int i = 1; i <= now.length() - 2; i++){
                for(int j = i + 1; j <= now.length() - 1; j++){
                    arr[0] = Integer.parseInt(now.substring(0, i)); // 0 ~ i - 1
                    arr[1] = Integer.parseInt(now.substring(i, j)); //i ~ j - 1
                    arr[2] = Integer.parseInt(now.substring(j)); //j ~ length()

                    int newValue = 0;
                    for(int value : arr){
                        newValue += value;
                    }
                    cut(Integer.toString(newValue), newCount);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        String first = br.readLine();

        cut(first, 0);

        sb.append(answers[0]).append(" ").append(answers[1]);
        bw.write(sb.toString());
        bw.flush();

        bw.close();
        br.close();
    }
}
