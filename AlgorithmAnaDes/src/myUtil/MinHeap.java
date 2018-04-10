package myUtil;

public class MinHeap<T> {
    private T[] Heap;
    private int maxsize;
    private int size;

/*
    public MinHeap(int max) {
        maxsize = max;
        Heap = new T[maxsize];
        size = 0;
        Heap[0] = T.MIN_VALUE;
    }


    private int leftchild(int pos) {
        return 2 * pos;
    }


    private int rightchild(int pos) {
        return 2 * pos + 1;
    }


    private int parent(int pos) {
        return pos / 2;
    }


    private boolean isleaf(int pos) {
        return ((pos > size / 2) && (pos <= size));
    }


    private void swap(int pos1, int pos2) {
        T tmp;
        tmp = Heap[pos1];
        Heap[pos1] = Heap[pos2];
        Heap[pos2] = tmp;
    }


    public void put(T elem) {
        size++;
        Heap[size] = elem;
        int current = size;
        while (Heap[current] < Heap[parent(current)]) {
            swap(current, parent(current));
            current = parent(current);
        }
    }


    public void print() {
        int i;
        for (i = 1; i <= size; i++)
            System.out.print(Heap[i] + " ");
        System.out.println();
    }


    public int removeMin() {
        swap(1, size);
        size--;
        if (size != 0)
            pushdown(1);
        return Heap[size + 1];
    }


    private void pushdown(int position) {
        int smallestchild;
        while (!isleaf(position)) {
            smallestchild = leftchild(position);
            if ((smallestchild < size)
                    && (Heap[smallestchild] > Heap[smallestchild + 1]))
                smallestchild = smallestchild + 1;
            if (Heap[position] <= Heap[smallestchild])
                return;
            swap(position, smallestchild);
            position = smallestchild;
        }
    }
*/
}