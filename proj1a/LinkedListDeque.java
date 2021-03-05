public class LinkedListDeque <T>{
    private  class TNode{
        public T item;
        public TNode prev;
        public TNode next;
        public TNode(T item, TNode next, TNode prev ){
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }
    private TNode sentinel;
    private int size;
    public LinkedListDeque(){
        sentinel = new TNode((T)null, null, null );
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }
    public void addFirst(T item){
        sentinel.next = new TNode(item, sentinel.next, sentinel);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }
    public void addLast(T item){
        sentinel.prev = new TNode(item , sentinel, sentinel.prev);
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;
    }
    public T removeFirst(){
        if (isEmpty()){
            return null;
        }
        T value = sentinel.next.item;
        sentinel.next.next.prev = sentinel;  
        sentinel.next = sentinel.next.next;
        size --;
        return value;

    }
    public T removeLast(){
        if (isEmpty()){
            return null;
        }
        T value = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size --;
        return value;
    }
    public int size(){

        return size;
    }
    public boolean isEmpty(){
        if(size == 0){
            return true;
        }
        return false;
    }
    public void printDeque(){
        TNode ptr = new TNode(null, sentinel.next, null);
        while(ptr.next != sentinel){
            ptr.next = ptr.next.next;
            System.out.print( ptr.next.item + " " );

        }
        
    }
    public T get(int index){
        int num = 0;
        TNode find = new TNode((T)null, sentinel.next, null);
        while( find.next != sentinel){
            if (num == index){
                break;
            }
            find.next = find.next.next;
            num ++ ;
            
        }
        return find.next.item;
    }
  
    private T getRecursiveHelper(int index, int count, TNode ptr) {
        if (index == count) {
            return ptr.item;
        }
        return getRecursiveHelper(index, count+1, ptr.next);
    }

    public T getRecursive(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        int count = 0;
        TNode ptr = sentinel.next;
        return getRecursiveHelper(index, count, ptr);
    }
public static void main(String[] args) {
    LinkedListDeque<Integer> linked= new LinkedListDeque<>();
    linked.addLast(0);
    linked.addFirst(1);
    System.out.println(linked.removeLast()); 
}

}
