package aim_to_platinum.week11_implementation.b20546;
/*
1. 문제 요약
- 준현과 성민 각각의 투자 전략을 구현하고 14일 누가 더 수익률이 높은지 구한다

- 준현의 전략
    - 매일매일 살 수 있는 최대한의 주식을 산다
    - 한 번 산 주식은 절대 팔지 않는다
- 성민의 전략
    - 3일 연속 주가 상승 시 전량 매도한다
    - 3일 연속 주가 하락 시 전량 매수한다


2. 아이디어 (문제 접근법)
[아이디어-1]
- Investor 클래스를 만들어 인스턴스 준현과 성민을 만들어 풀이한다
- BNP 와 TIMING 메서드를 만들어 매일 각자의 전략대로 작동하게 만든다


3. 어려움 및 해결
-
*/


import java.io.*;
import java.util.StringTokenizer;

public class BOJ_20546_기적의_매매법 {

    public static class Investor{
        int money;
        int stock;

        Investor(int money, int stock){
            this.money = money;
            this.stock = stock;
        }
    }

    static int DATE = 14;
    static int[] stockPriceArr = new int[DATE];
    static int dayCount = -1;
    static int stockPrice;

    static Investor BNP(Investor joonhyeon){
        int newStock = joonhyeon.money / stockPrice;
        joonhyeon.stock += newStock;
        joonhyeon.money -= newStock * stockPrice;
        return joonhyeon;
    }
    static Investor TIMING(Investor sungmin){
        if(dayCount >= 3){
            if(stockPriceArr[dayCount] > stockPriceArr[dayCount - 1] && stockPriceArr[dayCount - 1] > stockPriceArr[dayCount - 2] && stockPriceArr[dayCount - 2] > stockPriceArr[dayCount - 3]){
                sungmin.money += sungmin.stock * stockPrice;
                sungmin.stock = 0;
            }else if(stockPriceArr[dayCount] < stockPriceArr[dayCount - 1] && stockPriceArr[dayCount - 1] < stockPriceArr[dayCount - 2] && stockPriceArr[dayCount - 2] < stockPriceArr[dayCount - 3]){
                int newStock = sungmin.money / stockPrice;
                sungmin.stock += newStock;
                sungmin.money -= newStock * stockPrice;
            }
        }
        return sungmin;
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();

        int budget = Integer.parseInt(br.readLine());
        stk = new StringTokenizer(br.readLine());
        for(int i = 0; i < DATE; i++){
            stockPriceArr[i] = Integer.parseInt(stk.nextToken());
        }

        Investor joonhyeon = new Investor(budget, 0);
        Investor sungmin = new Investor(budget, 0);

        while(DATE-- > 0){
            dayCount++;
            stockPrice = stockPriceArr[dayCount];

            joonhyeon = BNP(joonhyeon);
            sungmin = TIMING(sungmin);
        }

        int joonhyeonMoney = joonhyeon.stock * stockPrice + joonhyeon.money;
        int sungminMoney = sungmin.stock * stockPrice + sungmin.money;

        if(joonhyeonMoney == sungminMoney){
            System.out.println("SAMESAME");
        }else{
            String answer = joonhyeonMoney > sungminMoney ? "BNP" : "TIMING";
            System.out.println(answer);
        }
    }
}
