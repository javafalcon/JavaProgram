/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example;

import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

/**
 *
 * @author weizhong
 */
public class SuperHorseGreed {

    int n;//棋盘大小n-by-n
    int pan[][];//棋盘，初始为每个位置都为0，已走过则为1

    public SuperHorseGreed(int nn) {
        n = nn;
        pan = new int[nn][nn];
        for (int i = 0; i < nn; i++) {
            for (int j = 0; j < nn; j++) {
                pan[i][j] = 0;
            }
        }
    }

    /**
     *
     * @param N
     * @return 返回一个节点，是N的所有可达节点中出边的次数（>0)最少的
     */
    public SuperHorseNode minOutEdgeNode(SuperHorseNode N) {

        SuperHorseNode bestE = null;
        int x, y, bestx=-1, besty=-1;
        int t, k = 0, bt = 9;
        for (int i = 1; i <= 8; i++) {
            if (N.p[i] == 0) {//方向i未访问
                x = N.x + SuperHorseNode.sx[i];
                y = N.y + SuperHorseNode.sy[i];
                if (legal(x, y)) {//N在方向i上的子节点(x,y)是合法节点
                    t = outEdgeNum(x, y);//(x,y)的出边数
                    if (t > 0 && t < bt) {
                        bestx = x;
                        besty = y;
                        bt = t;
                        k = i;
                    }
                }
            }
        }
        if (k > 0) {//找到N的最优子节点为方向k的子节点
            N.p[k] = 1;
            bestE = new SuperHorseNode(bestx, besty);
        }
        return bestE;
    }

    //计算节点(x,y)的出边数
    public int outEdgeNum(int x, int y) {
        int xx, yy, k = 0;
        for (int i = 1; i <= 8; i++) {
            xx = x + SuperHorseNode.sx[i];
            yy = y + SuperHorseNode.sy[i];
            if (legal(xx, yy)) {
                k++;
            }
        }
        return k;
    }

    public boolean legal(SuperHorseNode N) {
        boolean f = true;
        if (N.x < 0 || N.x >= n || N.y < 0 || N.y >= n || pan[N.x][N.y] > 0) {
            f = false;
        }
        return f;
    }

    public boolean legal(int x, int y) {
        boolean f = true;
        if (x < 0 || x >= n || y < 0 || y >= n || pan[x][y] > 0) {
            f = false;
        }
        return f;
    }

    //递归回溯+贪心
    private void search(SuperHorseNode E, int t) {
        System.out.println("(" + E.x + "," + E.y + ")" + t);
        pan[E.x][E.y] = t;
        if (t == n * n ) {
            output();
        } else {
            SuperHorseNode node = this.minOutEdgeNode(E);
            if (node != null) {
                search(node, t + 1);
                pan[E.x][E.y] = 0;
            }
        }

    }

    //非递归的回溯+贪心
    public void search2() {
        ArrayList<SuperHorseNode> a = new ArrayList<>();
        int top = 0;
        SuperHorseNode N = new SuperHorseNode(0, 0);
        pan[0][0] = 1;
        a.add(top, N);
        SuperHorseNode E;
        while (top < n * n -2 && !a.isEmpty()) {
            E = a.get(top);
            SuperHorseNode T = minOutEdgeNode(E);
            if (T != null) {
                a.add(T);//T的位置合法且还未遍历过，则入栈
                pan[T.x][T.y] = 1;//棋盘上标记T的位置已遍历
                top++;
                System.out.println("in stact: " + top + "(" + T.x + "," + T.y + ")");
            } else {//如果E已经没有下一个节点可去
                E = a.remove(top);//E出栈
                pan[E.x][E.y] = 0;//标记棋盘上E的位置未遍历
                top--;
            }
        }
        if (a.isEmpty()) {
            System.out.println("NO Resolve");
        } else {
            top--;
            int j = 0;
            while (!a.isEmpty()) {
                N = a.remove(top);
                top--;

                System.out.print(N + " ");
                j++;
                if (j % 6 == 0) {
                    System.out.print("\n");
                }
            }
        }
    }

    //输出结果
    public void output() {
        SuperHorseNode a[] = new SuperHorseNode[n * n ];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[pan[i][j]-1] = new SuperHorseNode(i, j);
            }
        }
        for (int i = 1; i <= n * n; i++) {
            if (i % 8 == 0) {
                System.out.print("\n");
            }
            System.out.println(a[i] + " ");
        }

    }

    public static void main(String[] args) {
        SuperHorseGreed sg = new SuperHorseGreed(8);
        sg.search2();
        //sg.search(new SuperHorseNode(0, 0), 1);
    }
}
