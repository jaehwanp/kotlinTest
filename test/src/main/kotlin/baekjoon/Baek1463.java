package baekjoon;

public class Baek1463 {
    private static int[] d;

    public static void main(String[] args) {
        int n = 10;
        d = new int[n+1];
        System.out.println(go(n));
    }

    private static int go(int n){
        if (n == 1) {
            return 0;
        }
        if(d[n] > 0) {
            return d[n];
        }
        d[n] = go(n-1)+1;
        if(n%2 == 0) {
            int temp = go(n/2)+1;
            if (d[n] > temp) {
                d[n] = temp;
            }
        }
        if(n%3 == 0) {
            int temp = go(n/3)+1;
            if(d[n] > temp) {
                d[n] = temp;
            }
        }
        return d[n];
    }

}
