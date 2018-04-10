package example;

import myUtil.MinHeap;

public class Chapter4_7 {

    static class JobNode implements Comparable {
        int id, time;

        JobNode(int i, int tt) {
            id = i;
            time = tt;
        }

        public int compareTo(Object x) {
            int xt = ((JobNode) x).time;
            if (time < xt) return -1;
            if (time == xt) return 0;
            return 1;
        }
    }

    static class MachineNode implements Comparable {
        int id, avail;

        MachineNode(int i, int a) {
            id = i;
            avail = a;
        }

        public int compareTo(Object x) {
            int xa = ((MachineNode) x).avail;
            if (avail < xa) return -1;
            if (avail == xa) return 0;
            return 1;
        }
    }

    public static int greedy(int [] a, int m){
        int n = a.length;
        int sum = 0;
        if( n <= m){
            for( int i = 0; i < n; i++)
                sum += a[i];
            System.out.println("为每个作业分配一台机器");
            return sum;
        }
        return sum;
/*
        JobNode [] d = new JobNode[n];
        for( int i = 0; i < n; i++) {
            d[i] = new JobNode(i + 1, a[i + 1]);
        }
        java.util.Arrays.sort(d);

        MinHeap H = new MinHeap(m);

        for( int i = 1; i <= m; i++){
            MachineNode x = new MachineNode(i,0);
            H.put(x);
        }*/
    }
}