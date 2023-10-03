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
    
    public void delete(T key){
        if(head == null)
            return;
        if(head.data == key){
            head = head.next;
            size--;
            return;
         }
        Node current = head;
        while(current.next != null){
            if(current.next.data == key){
                current.next = current.next.next;
                size--;
                return;
            }
            current = current.next;
        }
    }
    
    public int size(){
        return size;
    }
}
