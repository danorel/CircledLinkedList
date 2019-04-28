import cll.CircularLinkedList;

public class App {
    public static void main(String[] args) {
        CircularLinkedList<Integer> list = new CircularLinkedList<>();
        list.insert(5);
        list.insert(11);
        list.insert(15);
        System.out.println(list);
        list.delete(15);
        System.out.println(list);
        list.delete(5);
        System.out.println(list);

    }
}
