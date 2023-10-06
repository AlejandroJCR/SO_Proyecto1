package proyecto1;

public class LinkedList<T> {
    Node<T> head;
    int size = 0;
     
    public class Node<T> {
        T data;
        Node<T> next;

        public Node(T data){
            this.data = data;
            this.next = null;
        }
    }

    public void append(T data){
        Node<T> newNode = new Node<>(data);
        
        if(head == null){
            head = newNode;
        } else {
            Node<T> current = head;
            while(current.next != null)
                current = current.next;
            current.next = newNode;
        }
        
        size++;
    }

    public T get(int index){
        if(head == null)
            return null;
        if(index == 0)
            return head.data;
        
        int i = 1;
        Node<T> current = head;
        while(current.next != null){
            if(i == index)
                return current.next.data;
            current = current.next;
            i++;
        }
        return null;
    }
    
    public T pop() {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            Node<T> node = head;
            head = null;
            size--;
            return node.data;
        } else {
            Node<T> current = head;
            while (current.next.next != null) {
                current = current.next;
            }
            Node<T> node = current.next;
            current.next = null;
            size--;
            return node.data;
        }
    }
    
    public int size(){
        return size;
    }
}
