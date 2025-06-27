package baekjoon;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
// 백준 1158
public class Solution3 {
    public static void main(String[] args) {
        Queue<Integer> jsps = new LinkedList<>();
        int n = 7;
        int k = 3;
        ArrayList<Integer> answer = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            jsps.add(i);
        }

        while (jsps.size() > 0) {
            for (int i = 0; i < k-1; i++) {
                jsps.add(jsps.poll());
            }
            answer.add(jsps.poll());
        }

        System.out.println(answer.toString());

    }
}
