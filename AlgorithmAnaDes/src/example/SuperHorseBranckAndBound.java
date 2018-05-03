/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

/**
 *
 * @author weizhong
 */
public class SuperHorseBranckAndBound {

    class Node extends SuperHorseNode {

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

    /**
     * 分支限界+贪心
     *
     * @param N
     */
    public void search3(Node N) throws InterruptedException {
        int Max = n * n * n;
        ArrayBlockingQueue<Node> queue = new ArrayBlockingQueue<>(Max);
        queue.put(N);
        int dep = 1;
        Node E, T;
        while( true){
            if( dep == n*n)
                break;
            E = queue.poll();
            if( E == null)
                break;
            for( int i = 1; i <= 8; i++){
                T = E.next(i);
            }
        }

    }
    
    /**
     * 寻找N的最少出边的合法子节点
     * @param N
     * @return 
     */
    public int[] minOutEdgeNode(Node N) {
        int edgeIndx[] = new int[8];
        HashTable<int,Node> ht = new Hash
        Node bestE = null;
        int x, y, bestx = -1, besty = -1;
        int t, k = 0, bt = 9;
        for (int i = 1; i <= 8; i++) {
            
                x = N.x + Node.sx[i];
                y = N.y + Node.sy[i];
                if (legal(x, y)) {//N在方向i上的子节点(x,y)是合法节点
                    t = outEdgeNum(N, x, y);//(x,y)的出边数
                    for(k=0; k <8; k++){
                        if( t)
                    }
                }
            }
        
        if (k > 0) {//找到N的最优子节点为方向k的子节点
            N.p[k] = 1;
            bestE = new SuperHorseNode(bestx, besty);
        }
        return bestE;
    }

    //计算节点N的子节点(x,y)的出边数
    public int outEdgeNum(Node N, int x, int y) {
        int xx, yy, k = 0;
        for (int i = 1; i <= 8; i++) {
            xx = x + Node.sx[i];
            yy = y + Node.sy[i];
            if (legal(xx, yy) && !N.inRute(x, y)) {
                k++;
            }
        }
        return k;
    }

    public boolean legal(int x, int y){
        boolean f = true;
        if( x < 0 || x >= n || y < 0 || y >= n)
            f = false;
        return f;
    }

}
