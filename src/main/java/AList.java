//an attempt to construct my own SLList for generics
import java.util.ArrayDeque;
import java.util.Scanner;

/*Main data structure, an Array-"double-ended-queue" or "deck" allows access at both ends*/
public class AList<E> {
    private E[] items;
    private int size;

    /*Empty constructor (I will refer to ArrayDeque as AD)*/
    public AList() {
        items = (E []) new Object[50];
        size = 0;
    }

    /*Constructor with input*/
    private AList(E first) {
        items = (E []) new Object[50];
        items[0] = first;
        size = 1;
    }

    /*returns true if the AD is empty*/
    public boolean isEmpty() {
        if (size == 0)
            return true;
        return false;
    }

    /*Retrieves item at index*/
    public E get(int index) {
        return items[index];
    }

    public E getLast(){
        return items[size-1];
    }

    /*Creates a "deep copy" of the AD*/
    public AList<E> copy() {
        AList<E> newList = new AList<E>();
        System.arraycopy(items, 0, newList.items, 0, size);

        return newList;
    }

    /*Returns the size of the AD*/
    public int size() {
        return size;
    }

    public static void printValsandSize(AList theList)
    {
        for(int i = 0; i < theList.size; i++){
            System.out.print(theList.items[i]);
            if(i < theList.size - 1)
                System.out.print(", ");
        }
        System.out.println("\nSize: " + theList.size + "\n");
    }

    public void printDeque() {
        for(int i = 0; i < size; i++){
            System.out.print(items[i]);
            if(i < size - 1)
                System.out.print(", ");
        }
    }

    /*This appends to the front of the vNode contained within*/
    public void addFirst(E itemToAdd) {
        if (size == items.length) {
            resize(size * 2);
        }
        System.arraycopy(items, 0, items, 1, size);
        items[0] = itemToAdd;
        size += 1;
    }

    /*Removes the front vNode*/
    public void removeFirst() {
        if (isEmpty()) {
            System.out.println("The list is empty");
            return;
        }
        System.arraycopy(items, 1, items, 0, size);
        size -= 1;
    }

    //Resizes the underlying array
    private void resize(int capacity) {
        E[] a = (E []) new Object[capacity];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
    }

    /*This appends to the back of the vNode contained within*/
    public void addLast(E itemToAdd) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[size] = itemToAdd;
        size += 1;
    }

    /*This deletes the prev vNode*/
    public void removeLast() {
        if (isEmpty()) {
            System.out.println("The list is empty");
            return;
        }
        System.arraycopy(items, 0, items, 0, size-1);
        size -= 1;
    }

    public static void main(String[] args) {
        boolean on = true;
        AList<String> instanceVarListJava = new AList<>();
        int n = instanceVarListJava.size();

        while(on) {
            Scanner s = new Scanner(System.in);  //Used to take user input
            System.out.println("Enter choice: ");
            String c = s.nextLine();
            System.out.println("Choice: "+ c);

            switch(c) {
                case "addFirst":
                    System.out.println("Enter what you want to add: ");
                    String start = s.nextLine();
                    instanceVarListJava.addFirst(start);

                    printValsandSize(instanceVarListJava);//prints the state of the LLD
                    break;

                case "addLast":
                    System.out.println("Enter what you want to add: ");
                    String end = s.nextLine();
                    instanceVarListJava.addLast(end);

                    printValsandSize(instanceVarListJava);
                    break;

                /*Retrieve by index*/
                case "get":
                    System.out.println("What index?: ");
                    int index = Integer.parseInt(s.nextLine());

                    System.out.println(instanceVarListJava.get(index));
                    break;

                case "removeFirst":
                    instanceVarListJava.removeFirst();

                    printValsandSize(instanceVarListJava);
                    break;

                case "removeLast":
                    instanceVarListJava.removeLast();

                    printValsandSize(instanceVarListJava);
                    break;

                case "print":
                    instanceVarListJava.printDeque();
                    break;

                case "quit":
                    System.exit(0);

                default:
                    AList<String> instanceVarList = new AList<>();

                    instanceVarList.addLast("Check1!");
                    instanceVarList.addLast("Check2!");

                    System.out.println(instanceVarList.items[0]);
                    System.out.println(instanceVarList.items[1]);

                    instanceVarList.addFirst("Stay away from the summoner!");

                    System.out.println(instanceVarList.items[instanceVarList.size-1]);
                    System.out.println(instanceVarList.items[instanceVarList.size]);

                    AList<String> newList = instanceVarList.copy();

                    System.out.println(newList.items[0]);

            }
        }
    }
}
