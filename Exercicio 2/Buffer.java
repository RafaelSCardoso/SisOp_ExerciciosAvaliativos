public class Buffer {

    class Node {
        Node next;
        Node prev;
        boolean value;
        public Node(){ value = false; prev = next = null;} 
    }

    private Node head;
    private Node tail;
    private Node cursor;

    Buffer(int length){
        head = tail = null;
        for(int i = 0; i < length; i++) add();
        cursor = head;
    }

    private void add(){
        if(head == null){
            head = tail = new Node();
        } else if (head == tail){
            head.next = head.prev = tail = new Node();
            tail.next = tail.prev = head;
        } else {
            tail = tail.next = new Node();
            tail.prev = head.prev;
            head.prev = tail;
            tail.next = head;
        }
    }

    public void insere() {
        if(cursor.next.value == true) throw new ArrayStoreException("Buffer cheio");
        cursor.next.value = true;
        cursor = cursor.next;
    }
    public void retira() {
        if(cursor.value == false) throw new NullPointerException("Buffer vazio");
        cursor.value = false;
        cursor = cursor.prev;
    }

    public boolean isEmpty(){ return cursor.value == false; }
    public boolean isFull() { return cursor.next.value == true; }

    @Override
    public String toString(){
        String response = "| ";
        Node aux = head.next;

        int i =0;
        response += i +":"+head.value+ " |";
        while(aux != head){
            i++;
            response += " " + i +":"+aux.value+ " |";
            aux = aux.next;
        }

        // System.out.println(head + " -> " + head.next + " -> " + head.next.next);
        // System.out.println(head.prev.prev + " <- " + head.prev + " <- " + head);

        return response;
    }
}
