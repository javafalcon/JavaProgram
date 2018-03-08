/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myUtil;
/**
 *
 * @author weizhong
 */
public class MyMath {
    public static void swap(Object[] list, int i, int j){
        Object temp = list[i];
        list[i] = list[j];
        list[j] = temp;
    }
}
