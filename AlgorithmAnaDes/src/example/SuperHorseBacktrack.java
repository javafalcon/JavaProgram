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
public class SuperHorseBacktrack {

    private class Node {

        int x, y;
        int pos;//棋子可走的方向

        public Node() {
            pos = 1;
        }

        public Node(int xx, int yy) {
            x = xx;
            y = yy;
        }

        public Node(int xx, int yy, int pp) {
            x = xx;
            y = yy;
            pos = pp;
        }

        public Node next() {
            int r=3, c=4;
            Node qq = new Node();
            switch (pos) {
                case 1:
                    qq.x = x + r;
                    qq.y = y + c;
                    break;
                case 2:
                    qq.x = x + r;
                    qq.y = y - c;
                    break;
                case 3:
                    qq.x = x - r;
                    qq.y = y + c;
                    break;
                case 4:
                    qq.x = x - r;
                    qq.y = y - c;
                    break;
                case 5:
                    qq.x = x + c;
                    qq.y = y + r;
                    break;
                case 6:
                    qq.x = x + c;
                    qq.y = y - r;
                    break;
                case 7:
                    qq.x = x - c;
                    qq.y = y + r;
                    break;
                case 8:
                    qq.x = x - c;
                    qq.y = y - r;
                    break;
            }
            pos += 1;
            return qq;
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
            pos = i;
            return qq;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }

    int n;//棋盘大小n-by-n
    int pan[][];//棋盘，初始为每个位置都为0，已走过则为1
    
    public SuperHorseBacktrack(int nn) {
        n = nn;
        pan = new int[nn][nn];
        for (int i = 0; i < nn; i++) {
            for (int j = 0; j < nn; j++) {
                pan[i][j] = 0;
            }
        }
    }

    private void init(int x, int y) {
        pan[x][y] = 0;
    }

    private void search(Node N, int t) {
        for (int i = 1; i <= 8; i++) {
                Node E = N.next(i);
                boolean flag = (E.x >= 0 && E.x < n) && (E.y >= 0 && E.y < n);
                if (flag){
                    if(pan[E.x][E.y] == 0){
                        pan[E.x][E.y] = t;
                        if( t == n*n - 1){
                            output();
                            break;
                        }
                        else
                            search(E, t + 1);
                    pan[E.x][E.y] = 0;
                }
            }
        }
    }

    public void search(){
        init(0,0);
        search(new Node(0,0),1);
    }
    public void search2() {
        ArrayList<Node> a = new ArrayList<>();
        int top = 0;
        Node N = new Node(0, 0, 1);
        a.add(top, N);
        Node E;
        while (top < n * n && !a.isEmpty()) {
            E = a.get(top);
            
            if (E.pos <= 8) {//如果E还有下一个节点可达（最多共有8个节点）
                Node T = E.next();//E下一个可达的节点
                if ((T.x < n && T.x >= 0) && (T.y < n && T.y >= 0) && pan[T.x][T.y] == 0){
                    a.add(T);//T的位置合法且还未遍历过，则入栈
                    pan[T.x][T.y] = 1;//棋盘上标记T的位置已遍历
                    top++;
                    //System.out.println("in stact: " + top);
                }
            }else{//如果E已经没有下一个节点可去
                E = a.remove(top);//E出栈
                pan[E.x][E.y] = 0;//标记棋盘上E的位置未遍历
                //System.out.println("out stact: " + top);
                top--;
            }
        }
        if(a.isEmpty())
            System.out.println("NO Resolve");
        else{
            top--;
            int j = 0;
            while( !a.isEmpty()){
                N = a.remove(top);
                top--;
                
                System.out.print(N + " ");
                j++;
                if( j%6 == 0)
                    System.out.print("\n");
            }
        }
    }

    //计算节点n的下一步可达节点个数
    private int nextNodeNum(Node n){
        int k = 0;
        for(int i = 0; i < 8; i++){
            Node E = n.next();
            if ( E.x < 0 || E.x >= 8 || E.y < 0 || E.y >=8 || pan[E.x][E.y] == 1){

            }else{
                k++;
            }
        }
        return k;
    }
    
    public void output(){
        Node a[] = new Node[n*n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++)
                a[pan[i][j]] = new Node(i,j);
        }
        for(int i = 0; i < n*n; i++){
            if( i%8==0) 
                System.out.print("\n");
            System.out.println(a[i] + " ");
        }
            
    }

    public static void main(String[] args) {
        SuperHorseBacktrack sh = new SuperHorseBacktrack(12);
        //sh.search();
        //sh.output();
        sh.search2();
    }
}
