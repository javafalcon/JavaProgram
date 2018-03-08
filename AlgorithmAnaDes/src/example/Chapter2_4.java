/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example;

import myUtil.MyMath;
/**
 *
 * @author weizhong
 */
public class Chapter2_4 {
    public static void perm(Object[] list, int k ,int m){
        if( k== m){
            for(int i=0; i<=m; i++){
                System.out.print(list[i]);
            }
            System.out.println();
        }
        else
            for( int i = k; i<=m; i++){
                MyMath.swap(list, k, i);
                perm(list,k+1,m);
            }
    }
    
    public static void main(String args[]){
        String list[] = {"a","b","c","d"};
        Chapter2_4.perm(list,0,3);
    }
}
