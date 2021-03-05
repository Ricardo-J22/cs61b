
/**
 * ArrayDeque
 */
public class ArrayDeque <T>{
    private int size;
    private T item[];
    private int nextFirst;
    private int nextLast;
    public ArrayDeque(){
        item = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }
    private void resize() {
        if(size == item.length){
            addSpace();
        }
        if (size < item.length*0.25 && item.length > 8){
            deleteSpace();
        }
    }
    private void resizeHelper(int memory){
        T[] temp = item;
        int begin = (nextFirst + 1) % item.length;
        int end = (nextLast + item.length -1 )% item.length;
        item = (T[]) new Object[memory];
        nextFirst = 0; 
        nextLast = 1;
        for (int i = begin; i != end; i = (i + 1)%temp.length) {
            item[nextLast] = temp[i];
            nextLast = (nextLast+1)%item.length;
        }
        item[nextLast] = temp[end];
        nextLast = (nextLast+1)%item.length;
    }
    
    private void addSpace(){
        resizeHelper(size*2);
    }
    
    private void deleteSpace(){
        resizeHelper(size);
    }

    public void addFirst(T item){
        resize();
        this.item[nextFirst] = item;
        nextFirst = (nextFirst + (this.item.length - 1)) % this.item.length;
        size++;
    }

    public void addLast(T item){
        resize();
        this.item[nextLast] = item;
        nextLast = (nextLast + 1 ) % this.item.length;
        size ++;
    }

    public boolean isEmpty(){
        if (size == 0){
            return true;
        }
        return false;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        int begin = (nextFirst + 1)/item.length;
        int end = (nextLast + item.length -1 )% item.length;
        for (int i = begin; i != end; i = (i + 1)%item.length) {
           System.out.print(item[i]+ " ");
        }
        System.out.println(item[end]);
    }

    public T removeFirst(){
        resize();
        if (isEmpty()){
            return null;
        }
        nextFirst = (nextFirst + 1)% item.length;
        T temp = item[nextFirst];
        item[nextFirst] = null;
        size --;
        return temp;
    }

    public T removeLast(){
        resize();
        if (isEmpty()){
            return null;
        }
        nextLast = (nextLast + item.length - 1)% item.length;
        T temp = item[nextLast];
        item[nextLast] = null;
        size --;
        return temp;
    }

    public T get(int index){
        if(index > size || index < 0){
            return null;
        }
        return item[(index + nextFirst + 1)% item.length];
    }
    // public static void main(String[] args) {
    //     ArrayDeque<Integer> aq = new ArrayDeque<Integer>();
    //     aq.addFirst(0);
    //     int c = aq.removeFirst();
    //     aq.addFirst(2);
    //     aq.addFirst(3);
    //     aq.addFirst(4);
    //     int a = aq.get(0);
    //     aq.addFirst(6);
    //     int b = aq.removeLast();
    //     int l = aq.get(1);
    //     aq.addFirst(9);
    //     aq.addLast(10);
    //     aq.addLast(11);
    //     aq.get(5);
    //     aq.addFirst(13);
    //     aq.addFirst(14);
    //     int d = aq.removeLast();
    //     int e = aq.removeFirst();
    //     int f = aq.removeFirst();
    //     int g = aq.removeLast();
    //     aq.addFirst(19);
    //     int h = aq.removeLast();
    //     aq.printDeque();
    // }
}