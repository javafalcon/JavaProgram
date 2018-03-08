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
public class Chapter2_6 {
    private int board[][];
    private int size;
    int tile;
    public Chapter2_6(int size){
        tile = 0;
        this.size =size;
        int k = (int)(Math.pow(2, size));
        board = new int[k][k];
    }
    public void chessBoard(int tr, int tc, int dr, int dc, int size){
        if( size == 1)
            return;
        int t = tile++;
        int s;
        s = size/2;
        if(dr < tr+s && dc < tc+s)
            chessBoard(tr,tc,dr,dc,s);
        else{
            board[tr+s-1][tc+s-1] = t;
            chessBoard(tr, tc, tr+s-1, tc+s-1,s);
        }
        if( dr < tr+s && dc >= tc+s)
            chessBoard(tr,tc+s,dr,dc,s);
        else{
            board[tr+s-1][tc+s] = t;
            chessBoard(tr,tc+s,tr+s-1,tc+s,s);
        }
        if( dr >= tr+s && dc < tc+s)
            chessBoard(tr+s,tc,dr,dc,s);
        else{
            board[tr+s][tc+s-1] = t;
            chessBoard(tr+s,tc,tr+s,tc+s-1,s);
        }
        if( dr >= tr+s && dc >= tc+s)
            chessBoard(tr+s,tc+s,dr,dc,s);
        else{
            board[tr+s][tc+s] = t;
            chessBoard(tr+s,tc+s,tr+s,tc+s,s);
        }
    }
    
    /**
     * 1~4号骨牌形状分别为 ++++  ++++  ++      ++
     *                   ++      ++  ++      ++
     *                   ++      ++  ++++  ++++
     * @param tr
     * @param tc
     * @param dr
     * @param dc
     * @param size 
     */
    public void chessBoard2(int tr, int tc, int dr, int dc, int size){
        if(size == 1) return;
        int s = size/2, f=0;
        int t = ++tile;
        int dx[] = {tr+s,tr+s,tr+s-1,tr+s-1};//
        int dy[] = {tc+s,tc+s-1,tc+s,tc+s-1};
        int tx[] = {tr+s, tr+s, tr, tr};
        int ty[] = {tc+s, tc, tc+s, tc};
        //特殊方格在左上角
        if(dr < tr+s && dc < tc+s){
            f = 4;
        }
        //特殊方格在右上角
        if(dr < tr+s && dc >= tc+s)
        {
            f = 3;
        }
        //特殊方格在左下角
        if(dr >= tr+s && dc < tc+s)
        {
            f = 2;
        }
        //特殊方格在右下角
        if(dr >= tr+s && dc >= tc+s)
        {
            f = 1;
        }
        //用编号为f的骨牌覆盖小棋盘的分割处
        for(int i = 0; i <= 3; i++){
            if( i+1 != f){
                board[dx[i]][dy[i]] = t;
                chessBoard2(tx[i],ty[i],dx[i],dy[i],s);
            }else{
                chessBoard2(tx[i],ty[i],dr,dc,s);
            }
        }
 
    }
    public static void main(String args[]){
        int size = 3;
        Chapter2_6 chess = new Chapter2_6(size);
        int k = (int)(Math.pow(2, chess.getSize()));
        chess.chessBoard2(0, 0, 0, 1, k);
        int chessBoard[][] = chess.getBoard();
        for(int i = 0; i < k; i++){
            for(int j = 0; j < k; j++)
                System.out.format("%4d",chessBoard[i][j]);
            System.out.println();
        }
    }

    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * @return the board
     */
    public int[][] getBoard() {
        return board;
    }
}
