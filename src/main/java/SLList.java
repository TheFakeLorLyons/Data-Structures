import java.util.Scanner;

/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\
 * This is a practice SINGLY LINKED list
 *  It contains a nested "pNode" which is of generic type.                                               |
 *  Each pNode points to the next one and its own item.
 *  The outside list tracks the first and last nodes as well as the size of the list.                    |
 *   There is still a getlast() method to practice iteration through the list that way,
 *     even though there is a tail pointer.                                                              |
 *
 *  =====================================================================================================|
 *
 * This list has the following functions                                                                 |
 *
 *  C ---------------------------------------------------------------------------------------------------|
 *    -Empty and single node constructors
 *                                                                                                       |
 *    -addFirst/Last():           Inserts at the beginning or end of the list respectively.
 *    -insert(<T>item,int index): Inserts a node at the specified index:                                 |
 *                                  (insert) Ex.->           insert(6,2)  = [5, 2 ,3] -> [5, 2, 6, 3]
 *                                   appends extra to end -> insert(6,10) = [5 ,2 ,3] -> [5, 2, 3, 6]    |
 *    -copy():                    Returns a new copy of the list.
 *    -reverseRecur():            Iteratively/ Nondestructively and iteratively reverses the list,       |
 *                                  -> reverseRecursive destructively and recursively does this.
 *                                     -> reverse() is actually an 'update' function, since it returns   |
 *                                         the same list provided, but with updated values
 *  R ---------------------------------------------------------------------------------------------------|
 *    -size():                    returns the size.
 *    -isEmpty():                 T/F based on whether it is empty or not.                               |
 *    -printDeque():              Prints the contents of the list.
 *                                                                                                       |
 *    -get(index):                Gets the NODE at the index (not the value therein).
 *    -getLastIterative():        Returns the last object by iterating though the list.                  |
 *    -getRecursive(int index):   Recursively returns the NODE at the index. (not the value therein).
 *                                                                                                       |
 *
 *  U ---------------------------------------------------------------------------------------------------|
 *    -updateAtIndex(item,index): Updates the item stored at an index (TBD) in a List.
 *    -reverse():                 Reverses the SLList and returns the same list rather than creating     |
 *                                    a new one
 *  D ---------------------------------------------------------------------------------------------------|
 *    -removeAtIndex(index):      Removes the nodes at the selected index.
 *    -removeFirst/Last():        Removes the first and last nodes of the list respectively.             |
 *
 * ------------------------------------------------------------------------------------------------------|
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
public class SLList<T>{
    public class pNode<T> {
        T item;
        pNode<T> next;

        public pNode(T item, pNode<T> next) {
            this.item = item;
            this.next = next;
        }
    }

    private pNode<T> head;
    private int size;
    private pNode<T> tail;

//Create operations

    /*Empty and single Node constructors*/
    public SLList() {
        head = null;
        tail = null;
        size = 0;
    }
    public SLList(pNode<T> first) {
        if (first == null) {
            head = null;
            tail = null;
            size = 0;
        } else {
            head = first;
            tail = first;
            size = 1;
        }
    }

    /*Should work - adds a node to the beginning of the list*/
    public void addFirst (T item) {
        head = new pNode<>(item, head);
        if (size == 0) {
            tail = head;
        }
        size += 1;
    }

    public void addLast(T item) {
        if (isEmpty()) {
            addFirst(item); //If the list is empty, addFirst will handle it
        } else {
            pNode<T> newNode = new pNode<>(item, null);
            tail.next = newNode;
            tail = newNode;
            size++;
        }
    }

    //Insert
    public void insert(T item, int position) {
        if(head == null || position == 0 || position < 0) {
            addFirst(item);
            return;
        }
        if(position == size || position > size) {
            addLast(item);
            return;
        }
        pNode<T> nodeToSwap = getIndex(position-1);
        nodeToSwap.next = new pNode<>(item, nodeToSwap.next);
    }

    /*Creates a "deep copy" of the PracticeNode*/
    public SLList<T> copy() {
        SLList<T> newList = new SLList<>();
        pNode<T> current = head;

        while (current != null) {
            System.out.println("I swear I am a copy: " + current.item);
            newList.addLast(current.item); // Use addLast to preserve order
            current = current.next;
        }
        return newList;
    }

    /*This is a non destructive reversal using iteration*/
    public SLList<T> reverse() {
        SLList<T> newList = new SLList<>();
        pNode<T> current = head;
        newList.tail = newList.head;
        while (current != null) { //or   "for (int i = 0; i < size; i++) {"
            newList.addFirst(current.item);
            current = current.next;
        }
        return newList;
    }

//Reading operations

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void printDeque(){
        pNode<T> tmp = head;
        if (head == null) {
            System.out.println("The List is empty.");
            return;
        }
        while (tmp != null){
            System.out.print(tmp.item + " ");
            tmp = tmp.next;
        }
        System.out.println("\n Head: " + head.item);
        System.out.println(" Tail: " + tail.item + "\n");
    }

    /*Returns null for empty, or calls itself until the index reaches 0*/
    private pNode<T> getHelper(pNode<T> n, int index) {
        if (n == null) {
            return null;
        }
        if (index == 0 || n.next == null) {
            return n;
        }
        return getHelper(n.next, index - 1);
    }
    /*This method uses the getHelper method to recursively search the list for the index*/
    public pNode<T> getRecursive (int index) {
        return getHelper(head, index);
    }

    public pNode<T> getLastIterative () {
        if (size == 0) {
            return null;
        }
        while (head.next != null) {
            head = head.next;
        }
        return head;
    }

    public pNode<T> getIndex (int index) {
        if (size == 0) {
            return null;
        }
        pNode<T> current = head;
        while (index > 0) {
            if (current.next == null) {
                return current;
            }
            current = current.next;
            index--;
        }
        return current;
    }

//Update operations

    public void updateAtIndex(T item, int position) {
        if(head == null)
            throw new NullPointerException("The list is empty still");
        if(position < 0 || position >= size)
            throw new IndexOutOfBoundsException();
        pNode<T> nodeToChange = getIndex(position);
        nodeToChange.item = item;
        System.out.println("Item: " + position + " Changed to: " +nodeToChange.item + " \n");
    }

    /*This is a destructive reversal using recursion*/
    public pNode<T> reverseHelper(pNode<T> first, boolean isFirst) {
        if (first == null || first.next == null)
            return first;
        else {
            pNode<T> endOfReversal = first.next;
            pNode<T> reversed = reverseHelper(first.next, false);
            endOfReversal.next = first;
            first.next = null;
            if (isFirst) {
                tail = first;  // Set the new tail
            }
            return reversed;
        }
    }
    public void reverseRecur() {
        head = reverseHelper(head, true);
    }

//Deletion operations

    private void removeHeadHelper () {
        System.out.println("Removed " + head.item + " ,this list is now empty.");
        head = null;
        tail = null;
    }

    public void removeLast () {
        if (size == 0) {
            System.out.println("The list is empty");
            return;
        }
        if (size == 1) {
            removeHeadHelper();
        } else {
            pNode<T> nodeToDelete = getIndex(size - 2);
            System.out.println("Removed " + nodeToDelete.next.item);
            nodeToDelete.next = null;
            tail = nodeToDelete;
        }
        size--;
    }

    public void removeFirst () {
        if (size == 0) {
            System.out.println("The list is empty");
            return;
        }
        if (size == 1) {
            removeHeadHelper();
        } else {
            pNode<T> tmp = head.next;  //test this to see if I need the tmp node or if I can just say head = head.next;
            System.out.println("Removed " + head.item);
            head = tmp;
            if (size == 2) {
                tail = head;
            }
        }
        size--;
    }

    //Null head is checked in remove first, same with null tail.
    public void removeAtIndex (int position) {
        if(position >= size || position < 0)
            throw new IndexOutOfBoundsException();
        else if(position == 0)
            removeFirst();
        else if (position >= size - 1)
            removeLast();//update tail in removeLast
        else {
            pNode<T> nodeToDelete = getIndex(position - 1);
            nodeToDelete.next = nodeToDelete.next.next;
            size--;
        }
    }

//Main, play around with the data structure.

    public static void main(String[] args) {
        boolean on = true;
        SLList<String> instanceVarListJava = new SLList<>();

        while (on) {

            Scanner s = new Scanner(System.in);
            System.out.println("Enter choice: ");
            String c = s.nextLine();
            System.out.println("Choice: " + c);

            switch (c) {

                //Creation operations

                case "addFirst":
                    System.out.println("Enter what you want to add: ");
                    String start = s.nextLine();
                    instanceVarListJava.addFirst(start);
                    instanceVarListJava.printDeque();
                    break;

                case "addLast":
                    System.out.println("Enter what you want to add: ");
                    String end = s.nextLine();
                    instanceVarListJava.addLast(end);
                    instanceVarListJava.printDeque();
                    break;

                case "insert":
                    System.out.println("What index?: ");
                    int index = Integer.parseInt(s.nextLine());
                    System.out.println("What Value?: ");
                    String item = s.nextLine();
                    instanceVarListJava.insert(item, index);
                    instanceVarListJava.printDeque();
                    break;

                case "copy":
                    SLList<String> newList = instanceVarListJava.copy();
                    newList.printDeque();
                    break;

                case "reverse":
                    newList = instanceVarListJava.reverse();
                    newList.printDeque();
                    break;

                //Reading operations

                case "print":
                    instanceVarListJava.printDeque();
                    break;

                case "get":
                    System.out.println("What index?: ");
                    index = Integer.parseInt(s.nextLine());
                    System.out.println(instanceVarListJava.getIndex(index).item);
                    break;

                case "getr":
                    System.out.println("What index?: ");
                    index = Integer.parseInt(s.nextLine());
                    System.out.println(instanceVarListJava.getRecursive(index).item);
                    break;

                case "getl":
                    System.out.println(instanceVarListJava.getLastIterative().item);
                    break;

                //Update operations

                case "update":
                    System.out.println("What index?: ");
                    index = Integer.parseInt(s.nextLine());
                    System.out.println("What Value?: ");
                    item = s.nextLine();
                    instanceVarListJava.updateAtIndex(item, index);
                    instanceVarListJava.printDeque();
                    break;

                case "reverseRecur":
                    instanceVarListJava.reverseRecur();
                    instanceVarListJava.printDeque();
                    break;

                //Deletion operations

                case "removeFirst":
                    instanceVarListJava.removeFirst();
                    instanceVarListJava.printDeque();
                    break;

                case "removeLast":
                    instanceVarListJava.removeLast();
                    instanceVarListJava.printDeque();
                    break;

                case "removeIndex":
                    System.out.println("What index?: ");
                    index = Integer.parseInt(s.nextLine());
                    instanceVarListJava.removeAtIndex(index);
                    instanceVarListJava.printDeque();
                    break;

                //Default Case && Quit Application

                case "q":
                    System.exit(0);

                default:
                    System.out.println("In theory this shouldn't happen.");
            }
        }
    }
}
