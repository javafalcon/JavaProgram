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

    private class Node{

        int x, y;
        int[] r;
        
        public Node() {
        }

        public Node(int xx, int yy) {
            x = xx;
            y = yy;
            r = null;
        }

        public Node(int xx, int yy, int[] rr){
            x = xx;
            y = yy;
            int len = rr.length;
            r = new int[len];
            for( int i=0; i < len; i++)
                r[i] = rr[i];
        }
        public Node next(int i) {
            int xx=0, yy=0, k=0;
            switch (i) {
                case 1:
                    xx = x + 3;
                    yy = y + 4;
                    break;
                case 2:
                    xx = x + 3;
                    yy = y - 4;
                    break;
                case 3:
                    xx = x - 3;
                    yy = y + 4;
                    break;
                case 4:
                    xx = x - 3;
                    yy = y - 4;
                    break;
                case 5:
                    xx = x + 4;
                    yy = y + 3;
                    break;
                case 6:
                    xx = x + 4;
                    yy = y - 3;
                    break;
                case 7:
                    xx = x - 4;
                    yy = y + 3;
                    break;
                case 8:
                    xx = x - 4;
                    yy = y - 3;
                    break;
            }
            Node qq = new Node(xx,yy,new int[r.length+1]);
            
            for(k=0; k < r.length; k++)
                qq.r[k] = r[k];
            qq.r[k] = x*n + y;
            return qq;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y  + ")";
        }

     }
    
    private int n;//棋盘大小n-by-n
    
    private int[] bestr;

    private Node[] pos;//棋盘，初始为每个位置都为0，已走过则为1
    
    private Queue<Node> queue;

    public SuperHorse(int nn) {
        n = nn;
        bestr = new int[n*n];
        
        pos = new Node[nn*nn];
        for (int i = 0; i < nn; i++) {
            for (int j = 0; j < nn; j++) {
                pos[i*n +j] = new Node(i,j);
            }
        }
    
        queue = new LinkedList<>();
    }

    private boolean legal(Node N){
        boolean flag;
        flag = true;
        if ((N.x >= 0 && N.x < n) && (N.y >= 0 && N.y < n)) {
            
        } else {
            return false;
        }
        
        //从后往前追查N节点是否已经访问
        int j = N.r.length-1;
        Node E;
        while( N.r[j] >= 0){
            E = pos[N.r[j]];
            if( N.x == E.x && N.y == E.y){//该位置已在路径中
                flag = false;
                break;
            }
            j--;
        }
        return flag;
    }

    public void search() {
        Node E = new Node(0,0,new int[1]);
        E.r[0] = -1;
         
        do {
            if (E.r.length == n * n) {//E是叶节点
                break;
            }else {
                for (int j = 1; j <= 8; j++) {
                    Node N = E.next(j);
                    
                    if (legal(N)) {
                        queue.add(N);
                    }
                }
            }
            if (queue.isEmpty()) {
                break;
            } else {
                E = queue.remove();
            }
        }while(true);
        
        System.arraycopy(E.r, 0, bestr, 0, n*n);
    }

    public void output(){
        for( int i = 0; i < n*n; i++){
            System.out.println(pos[bestr[i]]);
            i++;
        }
    }
            
    
    public static void main(String[] args) {
        SuperHorse sh = new SuperHorse(12);
        sh.search();
        sh.output();
        
    }
}
