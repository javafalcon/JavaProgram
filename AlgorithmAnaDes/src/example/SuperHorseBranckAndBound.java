/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example;

import java.util.ArrayList;
import java.util.ArrayDeque;

/**
 * @author weizhong
 */
public class SuperHorseBranckAndBound {

    public static int sx[] = {0, 1, 1, -1, -1, 2, 2, -2, -2};
    public static int sy[] = {0, 2, -2, 2, -2, 1, -1, 1, -1};
    //public static int sx[] = {0, 3, 3, -3, -3, 4, 4, -4, -4};
    // public static int sy[] = {0, 4, -4, 4, -4, 3, -3, 3, -3};

    private class Node {

        private int x, y;//棋子位置
        private int rute[];//到达目前位置所走过的路径�

        Node() {
            rute = new int[1];
            rute[0] = -1;//根节点�
        }

        Node(int xx, int yy) {
            x = xx;
            y = yy;
            rute = new int[1];
            rute[0] = -1;
        }

        Node(int xx, int yy, int[] r) {
            x = xx;
            y = yy;
            rute = r;
        }

        boolean inRute(int xx, int yx) {
            boolean f = false;
            int len = rute.length;
            for (int i = 0; i < len; i++) {
                if (xx * 8 + yx == rute[i]) {
                    f = true;//在路径中�
                    break;
                }
            }
            return f;
        }

        public Node next(int i) {
            int xx = x + sx[i];
            int yy = y + sy[i];
            int len = rute.length + 1;

            Node qq = new Node(xx, yy, new int[len]);
            for (int j = 0; j < len - 1; j++) {
                qq.rute[j] = rute[j];
            }
            qq.rute[len - 1] = x * 8 + y;//添加本节点到子节点的路径中

            return qq;
        }

        @Override
        public String toString() {
            int xx, yy;
            String str = new String();
            for (int i = 1; i < rute.length; i++) {
                xx = rute[i] / 8;
                yy = rute[i] % 8;
                str += "(" + xx + "," + yy + ") --> ";
                if (i % 8 == 0) {
                    str += "\n";
                }
            }
            return str + "(" + x + "," + y + ") ->END";
        }

    }

    private int n;//棋盘大小

    public SuperHorseBranckAndBound(int nn) {
        n = nn;
    }

    //计算节点合法子节点个数
    private int outEdgeNum(Node N) {
        int xx, yy, k = 0;
        for (int i = 1; i <= 8; i++) {
            xx = N.x + sx[i];
            yy = N.y + sy[i];
            if (legal(xx, yy) && !N.inRute(xx, yy)) {
                k++;
            }
        }
        return k;
    }

    /**
     * 优先级队列式分支限界法。用一个数组模拟优先级队列
     * @param N 棋子的起始位置
     */
    private void search(Node N) {
        int Max = n * n * n * n;
        ArrayList<Node> queue = new ArrayList<>(Max);
        queue.add(N);
        int head = 0;

        while (true) {
            Node E = queue.remove(head);
            if (E == null) {
                break;
            }
            if (E.rute.length == n * n) {
                System.out.println(E);
                break;
            }

            //扩展当前节点的所有子节点 
            for (int i = 1; i <= 8; i++) {
                Node child = E.next(i);
                if (legal(child.x, child.y)) {
                    if (!E.inRute(child.x, child.y)) {
                        queue.add(child);
                    }
                }
            }
            //找队列中第一个具有最少子节点的节点出队
            int min = outEdgeNum(queue.get(0));
            int t;
            head = 0;
            for (int i = 0; i < queue.size(); i++) {
                t = outEdgeNum(queue.get(i));
                if (t < min) {
                    min = t;
                    head = i;//出队的位置
                }
            }//end 查找最优节点

        }
    }

    public boolean legal(int x, int y) {
        boolean f = true;
        if (x < 0 || x >= n || y < 0 || y >= n) {
            f = false;
        }
        return f;
    }

    /**
     * 从(x,y)遍历棋盘
     *
     * @param x
     * @param y
     */
    public void traverse(int x, int y) {
        Node N = new Node(x, y);
        search(N);

    }

    public static void main(String[] args) {
        SuperHorseBranckAndBound horse = new SuperHorseBranckAndBound(8);
        horse.traverse(0, 0);
    }
}
