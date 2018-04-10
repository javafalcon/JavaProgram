package myUtil;

public class Element implements Comparable{
    float w;
    int i;

    public Element(float ww, int ii){
        w = ww; i = ii;
    }

    @Override
    public int compareTo(Object x) {
        float xw = ((Element) x).w;
        if( w < xw) return -1;
        if( w == xw) return 0;
        return 1;
    }
}
