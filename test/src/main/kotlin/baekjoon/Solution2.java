package baekjoon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Solution2 {
    public static void main(String[] args) {
        int a = 10;

        // 처음
        for(int i=1; i<a; i++) {
            System.out.print(" ");
        }
        System.out.println("*");

        // 중간
        for(int i=1; i<a-1; i++) {
            for(int j=a-1; j>i; j--) {
                System.out.print(" ");
            }
            System.out.print("*");

            for(int j=0; j<i*2-1; j++) {
                System.out.print(" ");
            }
            System.out.println("*");
        }

        // 마지막
        if(a != 1) {
            for (int i = 1; i < a * 2; i++) {
                System.out.print("*");
            }
        }

    }
}