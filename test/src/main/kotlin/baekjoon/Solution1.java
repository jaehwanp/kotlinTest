package baekjoon;

import java.util.Arrays;

public class Solution1 {
    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();
        String[] bans = {"gqk", "kdn", "jxj", "jxi", "fug", "jxg", "ewq", "len", "bhc"};
        Arrays.sort(bans, (o1, o2) -> o1.length() - o2.length());

        //System.out.println(Arrays.toString(bans));

        System.out.println(toBase26(7388));
        // jxd
        // again~
        //solution1.solution(7388, bans);
    }

    private static String solution(long n, String[] bans) {
        String answer = "";
        System.out.println(toBase26(n));

        int compareNum = 0; // toBase26(n).length();

        for(String s : bans) {
            if(n+compareNum > toDemical(s)) {
                compareNum++;
            } else {

            }
        }
        answer = toBase26(n+compareNum);

        return answer;
    }

    private static final char[] BASE26_CHARS = {
            'a','b','c','d','e','f','g','h','i','j','k','l','m',
            'n','o','p','q','r','s','t','u','v','w','x','y','z'
    };

    public static String toBase26(long n) {

        StringBuilder result = new StringBuilder();

        if(n<=0) {
            throw new IllegalArgumentException("0 일 수 는 없 다");
        }

        while (n > 0) {
            n--;
            long remainder = n % 26;
            int remain = Long.valueOf(remainder).intValue();
            result.insert(0, BASE26_CHARS[remain]);
            n /= 26;
        }

        return result.toString();
    }

    private static long toDemical(String base26) {
        long result = 0;

        for (int i = 0; i < base26.length(); i++) {
            char c = base26.charAt(i);
            int value = c - 'a' + 1;
            result = result * 26 + value;
        }

        return result;
    }
}
