/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example;

import java.util.Scanner;
/**
 *
 * @author weizhong
 */
public class Chapter2_5 {
    public static int q(int n, int m){
        if((n < 1) || (m < 1)) return 0;
        if((n==1) || (m == 1)) return 1;
        if( n < m) return q(n,n);
        if( n == m) return 1 + q(n, m-1);
        return q(n, m-1) + q(n - m, m);
    }
    public static void main(String[] args){
        int n;
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        System.out.println("q(" + n + "," + n +")=" + Chapter2_5.q(n, n));
    }
}
