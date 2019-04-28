package cll;

import java.util.NoSuchElementException;

public class CircularLinkedList<T extends Comparable>{
    private Node head;

    public CircularLinkedList(){
        head = new Node("HEAD");
    }

    public CircularLinkedList(T data){
        if(data == null){
            throw new NullPointerException("Error. Trying to create a new node with null pointer");
        }
        head = new Node("HEAD");
        head.next = new Node(data);
    }

    public void insert(T data){
        if(head.next == null){
            head.next = new Node(data);
            Node start = head.next;
            start.next = start;
            start.prev = start;
        } else {
            Node start = head.next;
            Node node = new Node(data);
            if(start.prev == start){
                start.prev = node;
                start.next = node;
                node.next = start;
                node.prev = start;
            } else {
                Node end = start.prev;
                start.prev = node;
                end.next = node;
                node.prev = end;
                node.next = start;
            }
        }
    }

    public void delete(T data){
        if(isEmpty()){
            throw new NoSuchElementException("Error. The circular linked list is empty.");
        } else {
            if(!deleteRecursive(head.next, data, false)){
                throw new NoSuchElementException("Error. The circular linked list doesn't contain the element " + data.toString());
            }
        }
    }

    private boolean deleteRecursive(Node node, T data, boolean circleDone){
        if(!circleDone){
            circleDone = node.next == head.next;
            if(equals(node.data, data)){
                Node start = head.next;
                if(node == start){
                    Node end = start.prev;
                    if(node.next == node){
                        head.next = null;
                    } else {
                        head.next = start.next;
                        head.next.prev = end;
                        end.next = head.next;
                    }
                } else {
                    Node prev = node.prev;
                    Node next = node.next;
                    prev.next = next;
                    next.prev = prev;
                }
                return true;
            } else { return deleteRecursive(node.next, data, circleDone); }
        } else { return false; }
    }

    private boolean equals(T first, T second){
        return first.compareTo(second) == 0;
    }

    public boolean isEmpty(){
        return head.next == null;
    }

    @Override
    public String toString() {
        StringBuilder cll = new StringBuilder(head.toString());
        cll.append("->");
        return displayCLL(head.next, cll);
    }

    private String displayCLL(Node node, StringBuilder cll){
        if(node != null){
            cll
                    .append("(")
                    .append(node.data)
                    .append(")")
                    .append("<->");
            if(node.next != head.next){
                return displayCLL(node.next, cll);
            } else {
                return cll.toString().substring(0, cll.length() - 3);
            }
        } else {
            return cll.toString();
        }
    }

    private class Node{
        private T data;
        private Node prev = null;
        private Node next = null;

        public Node(T data){
            this.data = data;
        }

        public Node(String data){
            this.data = (T) data;
        }

        @Override
        public String toString() {
            return this.data.toString();
        }
    }
}
