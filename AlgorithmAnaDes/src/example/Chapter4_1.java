package example;

public class Chapter4_1 {
    public static int greedySelector(int [] s, int [] f, boolean [] a){
        int n = s.length - 1;
        a[1] = true;
        int j = 1;
        int count = 1;
        for(int i = 2; i <= n; i++){
            if(s[i] >= f[j]){
                a[i] = true;
                j = i;
                count++;
            }
            else
                a[i] = false;
        }
        return count;
    }

    public static void main(String[] args){
        int s[] = {1,3,0,5,3,5,6,8,8,2,12};
        int f[] = {4,5,6,7,8,9,10,11,12,13,14};
        boolean a[] = new boolean[11];
        Chapter4_1.greedySelector(s,f,a);
        for(int i = 0; i < 11; i++){
            System.out.print(a[i] + " ");
        }
    }
}
