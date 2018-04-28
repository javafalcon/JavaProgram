/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example;

import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;

/**
 *
 * @author weizhong
 */

public class SuperHorse {

    private class Node implements Comparable{

        int x, y;
        int parent;
        
        public Node() {
            parent = -1;
        }

        public Node(int xx, int yy) {
            x = xx;
            y = yy;
            parent = -1;
        }

        public Node next(int i) {
            Node qq = new Node();
            switch (i) {
                case 1:
                    qq.x = x + 3;
                    qq.y = y + 4;
                    break;
                case 2:
                    qq.x = x + 3;
                    qq.y = y - 4;
                    break;
                case 3:
                    qq.x = x - 3;
                    qq.y = y + 4;
                    break;
                case 4:
                    qq.x = x - 3;
                    qq.y = y - 4;
                    break;
                case 5:
                    qq.x = x + 4;
                    qq.y = y + 3;
                    break;
                case 6:
                    qq.x = x + 4;
                    qq.y = y - 3;
                    break;
                case 7:
                    qq.x = x - 4;
                    qq.y = y + 3;
                    break;
                case 8:
                    qq.x = x - 4;
                    qq.y = y - 3;
                    break;
            }
            return qq;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + "," + parent + ")";
        }

        @Override
        public int compareTo(Object o) {
            int p = ((Node)o).parent;
            if(parent < p) return 1;
            if(parent == p) return 0;
            return -1;
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
    private int n;//棋盘大小n-by-n

    private int pan[][];//棋盘，初始为每个位置都为0，已走过则为1
    
    private Node[] r;//路径
    private Queue<Node> queue;

    public SuperHorse(int nn) {
        pan = new int[nn][nn];
        for (int i = 0; i < nn; i++) {
            for (int j = 0; j < nn; j++) {
                pan[i][j] = 0;
            }
        }
        n = nn;
        r = new Node[n*n];
        queue = new LinkedList<>();
    }


    public void search() {
        Node E = new Node(0,0);
        pan[0][0] = 1;
        int dep = 0;
        r[dep] = E;
        //Node br[] = new Node[n*n];
        boolean flag;
        while (true) {
            if (dep == n * n - 1) {
                Arrays.sort(r);
                return;
            }else {
                for (int j = 1; j <= 8; j++) {
                    Node N = E.next(j);
                    N.parent = dep;
                    flag = (N.x >= 0 && N.x < n) && (N.y >= 0 && N.y < n);
                    if (flag && pan[N.x][N.y] == 0) {
                        queue.add(N);
                        pan[N.x][N.y] = 1;
                    }
                }
            }
            if (queue.isEmpty()) {
                break;
            } else {
                E = queue.remove();
                //pan[E.x][E.y] = 1;
                dep ++;
                r[dep] = E;
                
            }
            
        }
        
    }

    public void output(){
        int i = 0;
        for( Node node: r){
            System.out.println(i + ":" + node + " ");
            i++;
        }
    }
    public static void main(String[] args) {
        SuperHorse sh = new SuperHorse(12);
        sh.search();
        sh.output();
    }
}
