
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
    public void resize() {
        if(size == item.length){
            addSpace();
        }
        if (size <= item.length*0.25 && item.length > 8){
            deleteSpace();
        }
    }
    public void resizeHelper(int memory){
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
    
    public void addSpace(){
        resizeHelper(size*2);
    }
    
    public void deleteSpace(){
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
        return item[index + (nextFirst + 1)% item.length];
    }
    public static void main(String[] args) {
        ArrayDeque<Integer> aq = new ArrayDeque<Integer>();
        for (int i = 0; i < 100; i++) {
            aq.addLast(i);
        }
        //aq.printDeque();
        for (int i = 0; i < 98; i++) {
            aq.removeFirst();
        }
        aq.printDeque();
        System.out.println(aq.get(0));
        
    }
}