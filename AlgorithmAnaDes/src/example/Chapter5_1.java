package example;

public class Chapter5_1 {
    private int n;
    private int[] x;
    private int step;
    public Chapter5_1(int n){
        step = 1;
        this.n = n;
        x = new int[n+1];
        for( int i = 1; i <= n; i++){
            x[i] = i;
        }
    }
    public void backtrack( int t){
        if( t > n)
            output(x);
        else
            for( int i = t; i <= n; i++){
                swap(x, t, i);
                if( t>0) backtrack(t+1);
                swap(x, i, t);
            }
    }

    private void output(int [] x){
        for ( int i = 1; i <= n; i++)
            System.out.print(x[i] + " ");
        System.out.print("\n");
    }

    private void swap(int[] x, int i, int j){
        System.out.println("swap " + x[i] + "和" + x[j] );
        int temp;
        temp = x[i]; x[i] = x[j]; x[j] = temp;
    }

    public static void main(String args[]){
        Chapter5_1 chp = new Chapter5_1(4);
        chp.backtrack(1);
    }
}
