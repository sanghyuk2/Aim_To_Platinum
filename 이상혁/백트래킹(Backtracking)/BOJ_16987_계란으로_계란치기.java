import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static Egg[] eggs;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        eggs = new Egg[N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            eggs[i] = new Egg(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        eggsFight(0, 0);

        System.out.println(max);
    }

    static void eggsFight(int idx, int cnt) {
        if(idx == N) {
            max = Math.max(max, cnt);
            return;
        }

        if(eggs[idx].isBroken() || cnt == N-1) {
            eggsFight(idx + 1, cnt);
            return;
        }

        int nCnt = cnt;
        for(int i=0; i<N; i++) {
            if(i == idx) {
                continue;
            }

            if(eggs[i].isBroken()) {
                continue;
            }

            eggs[idx].hitEgg(eggs[i]);

            if(eggs[idx].isBroken()) {
                cnt++;
            }

            if(eggs[i].isBroken()) {
                cnt++;
            }

            eggsFight(idx + 1, cnt);

            eggs[idx].recoveryEgg(eggs[i]);
            cnt = nCnt;
        }
    }
}

class Egg {
    private int durability;
    private int weight;

    public Egg(int durability, int weight) {
        this.durability = durability;
        this.weight = weight;
    }

    public boolean isBroken() {
        return this.durability <= 0;
    }

    public void hitEgg(Egg target) {
        this.durability -= target.weight;
        target.durability -= this.weight;
    }

    public void recoveryEgg(Egg target) {
        this.durability += target.weight;
        target.durability += this.weight;
    }
}