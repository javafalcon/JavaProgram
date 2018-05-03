/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author weizhong
 */
public class SuperHorseBranckAndBound {

    private class Node extends SuperHorseNode {

        int rute[];

        Node() {
            super();
            rute = new int[1];
            rute[0] = -1;//根节点
        }

        Node(int x, int y) {
            super(x, y);
            rute = new int[1];
            rute[0] = -1;//根节点
        }

        Node(int x, int y, int[] r) {
            super(x, y);
            rute = r;
        }

        boolean inRute(int x, int y) {
            boolean f = false;
            int len = rute.length;
            for (int i = 0; i < len; i++) {
                if (x * 8 + y == rute[i]) {
                    f = true;//（x,y)已在路径中
                    break;
                }
            }

            return f;
        }

        @Override
        public Node next(int i) {
            int xx = x + sx[i];
            int yy = y + sy[i];
            int len = rute.length + 1;
            Node qq = new Node(xx, yy, new int[len]);
            p[i] = 1;
            for (int j = 0; j < len - 1; j++) {
                qq.rute[j] = rute[j];
            }
            qq.rute[len - 1] = x * 8 + y;//到达此节点的最后一个父节点

            return qq;

        }
    }

    private int n;//棋盘大小

    public SuperHorseBranckAndBound(int nn){
        n = nn;
    }
    /**
     * 分支限界+贪心
     *
     * @param N
     */
    private void search3(Node N) throws InterruptedException {
        int Max = n * n * n;
        ArrayBlockingQueue<Node> queue = new ArrayBlockingQueue<>(Max);
        queue.put(N);
        Node E, T;
        while (true) {
            E = queue.poll();
            //System.out.println(E);
            if (E == null)
                break;
            if (E.rute.length == n * n) {
                outputRute(E);
                break;
            }

            for (int i = 1; i <= 8; i++) {
                T = E.next(i);
                if( legal(T.x, T.y) && !E.inRute(T.x,T.y)){
                    queue.put(T);
                }
            }
        }

    }

    public boolean legal(int x, int y) {
        boolean f = true;
        if (x < 0 || x >= n || y < 0 || y >= n)
            f = false;
        return f;
    }

    /**
     * 输出到达节点N的遍历路径
     */
    public void outputRute(Node N){
        int len = N.rute.length;
        int k,x,y;
        System.out.print(N);
        for(int i = len-1; i > 0; i--){
            k = N.rute[i];
            if( k != -1){//根节点
                y = k%8;
                x = k/8;
                System.out.println("(" + x + "," + y + ") " );
            }
        }
    }
    /**
     * 从(x,y)出发遍历棋盘
     * @param x
     * @param y
     */
    public void traverse(int x, int y){
        Node N = new Node(x,y);
        try {
            search3(N);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        SuperHorseBranckAndBound horse = new SuperHorseBranckAndBound(8);
        horse.traverse(0,0);
    }
}
