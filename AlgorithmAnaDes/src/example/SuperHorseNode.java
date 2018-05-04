/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example;

/**
 *
 * @author weizhong
 */
public class SuperHorseNode {
    //public static int sx[] = {0, 3, 3, -3, -3, 4, 4, -4, -4};
   // public static int sy[] = {0, 4, -4, 4, -4, 3, -3, 3, -3};
    public static int sx[] = {0, 1, 1, -1, -1, 2, 2, -2, -2};
    public static int sy[] = {0, 2, -2, 2, -2, 1, -1, 1, -1};

    public int x, y;//棋盘上的位置坐标

    public int[] p;//8个方向的节点是否已访问的标记，p[i]=0(1<=i<=8)表示未访问

    public SuperHorseNode() {
        p = new int[9];
        for(int i = 1; i <= 8; i++)
            p[i] = 0;
    }

    public SuperHorseNode(int xx, int yy) {
        x = xx;
        y = yy;
        p = new int[9];
        for(int i = 1; i <= 8; i++)
            p[i] = 0;
    }

    public SuperHorseNode next(int i) {
        SuperHorseNode qq = new SuperHorseNode();
        qq.x = x + sx[i];
        qq.y = y + sy[i];
        p[i] = 1;
        return qq;
    }
    
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
