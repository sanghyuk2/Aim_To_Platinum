import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static String[] map;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        Integer N = Integer.parseInt(st.nextToken());

        map = new String[N];
        map[0] = "  *  ";
        map[1] = " * * ";
        map[2] = "*****";

        for (int i = 1; 3 * (int) Math.pow(2, i) <= N; i++) {
            draw(i);
        }

        for (String s : map) {
            System.out.println(s);
        }
    }

    public static void draw(int k) {
        int height = 3 * (int) Math.pow(2, k);
        int middle = height / 2;

        for (int i = middle; i < height; i++) {
            map[i] = map[i - middle] + " " + map[i - middle];
        }

        String space = "";
        while(space.length() < middle){
            space += " ";
        }

        for(int i = 0; i < middle; i++){
            map[i] = space + map[i] + space;
        }
    }
}
