public class LinkedListDeque<T> {
  private class Node {
    T item;
    Node prevNode;
    Node nextNode;

    // Default constructor
    public Node() {
      prevNode = null;
      nextNode = null;
    }

    // Custom constructor
    public Node(T itemValue) {
      item = itemValue;
      prevNode = null;
      nextNode = null;
    }
  }

  public Node sentinelHead;
  public Node sentinelTail;
  int listSize = 0;

  // Creates an empty linked list deque
  public LinkedListDeque() {
    sentinelHead = new Node();
    sentinelTail = new Node();
    sentinelHead.nextNode = sentinelTail;
    sentinelTail.prevNode = sentinelHead;
    listSize = 0;
  }

  // Creates a deep copy of other
  public LinkedListDeque(LinkedListDeque<T> other) {
    sentinelHead = new Node();
    sentinelTail = new Node();
    sentinelHead.nextNode = sentinelTail;
    sentinelTail.prevNode = sentinelHead;
    listSize = 0;

    for (int i = 0; i < other.size(); ++i) {
      addLast(other.get(i));
    }
  }

  // Returns the previous node of the selected node
  public Node getPrevNode(Node selectedNode) {
    if (selectedNode == null) {
      return null;
    }
    return selectedNode.prevNode;
  }

  // Returns the next node of the selected node
  public Node getNextNode(Node selectedNode) {
    if (selectedNode == null) {
      return null;
    }
    return selectedNode.nextNode;
  }

  // Add an item of type T to the front of the deque
  public void addFirst(T item) {
    // Create a newNode
    Node newNode = new Node(item);
    // Link newNode to sentinelHead
    newNode.prevNode = sentinelHead;
    // Link newNode to node after sentinelHead
    newNode.nextNode = sentinelHead.nextNode;
    // Link node after sentinelHead to newNode
    sentinelHead.nextNode.prevNode = newNode;
    // Link sentinelHead to newNode
    sentinelHead.nextNode = newNode;
    listSize += 1;
  }

  // Add an item of type T to the end of the deque
  public void addLast(T item) {
    // Create a newNode
    Node newNode = new Node(item);
    // Link newNode to sentinelTail
    newNode.nextNode = sentinelTail;
    // Link newNode to the previous node of sentinelTail
    newNode.prevNode = sentinelTail.prevNode;
    // Link the previous node of sentinelTail to newNode
    sentinelTail.prevNode.nextNode = newNode;
    // Link sentinelTail to newNode
    sentinelTail.prevNode = newNode;
    listSize += 1;
  }

  // Return true if deque is empty, false otherwise
  public boolean isEmpty() {
    return listSize == 0;
  }

  // Return the number of items in the deque
  public int size() {
    return listSize;
  }

  // Return the list size
  public int getSize() {
    int listSize = 0;
    Node p = sentinelHead.nextNode;
    while (p != sentinelTail) {
      listSize += 1;
      p = p.nextNode;
    }
    return listSize;
  }

  // Print the items in the deque from first to last, separated by a space. Once all the items have been printed, print out a new line
  public void printDeque() {
    Node p = sentinelHead.nextNode;
    while (p != sentinelTail) {
      System.out.print(p.item + " ");
      p = p.nextNode;
    }
    System.out.println();
  }

  // Remove and return the item at the front of the deque. If no such item exists, returns null.
  public T removeFirst() {
    if (isEmpty()) {
      return null;
    }

    // Creates a new node points to the first node
    Node p = sentinelHead.nextNode;
    // Link sentinelHead to the second node to the front
    sentinelHead.nextNode = p.nextNode;
    // Link the second node to the front to sentinelHead
    p.nextNode.prevNode = sentinelHead;
    // Unlink selected node with sentinelHead
    p.prevNode = null;
    // Unlink selected node with the node after it
    p.nextNode = null;

    listSize -= 1;
    return p.item;
  }

  // Removes and returns the item at the back of the deque. If no such item exists, returns null
  public T removeLast() {
    if (isEmpty()) {
      return null;
    }

    // Creates a new node points to the last node
    Node p = sentinelTail.prevNode;
    // Link sentinelTail to the second node to the last
    sentinelTail.prevNode = p.prevNode;
    // Link the second node to the last to sentinelTail
    p.prevNode.nextNode = sentinelTail;
    // Unlink selected node with sentinelTail
    p.nextNode = null;
    // Unlink selected node with the node before it
    p.prevNode = null;

    listSize -= 1;

    return p.item;
  }

  //Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. if no such item exits, returns null. Must not alter the deque!
  public T get(int index) {
    // If list is empty
    if (isEmpty()) {
      return null;
    }

    // If index is out of the range
    if (index > listSize || index < 0) {
      return null;
    }

    int tempIndex = 0;
    Node p = sentinelHead.nextNode;
    while (tempIndex != index) {
      p = p.nextNode;
      tempIndex += 1;
    }

    return p.item;
  }

  // Helper method of getRecursive
  public T getRecursiveHelper(int index, Node n) {
    if (index == 0) {
      return n.item;
    }

    return getRecursiveHelper(index - 1, n.nextNode);
  }

  // Same get but using  recursion
  public T getRecursive(int index) {
    // if list is empty
    if (isEmpty()) {
      return null;
    }

    // if index is out of range
    if (index > listSize || index < 0) {
      return null;
    }

    return getRecursiveHelper(index, sentinelHead.nextNode);
  }

  public static void main(String[] args) {
    LinkedListDeque<Integer> l1 = new LinkedListDeque<Integer>();

    l1.addFirst(5);
    l1.addFirst(10);
    System.out.println(l1.getSize());                                                            // 2
    l1.addFirst(15);
    l1.addLast(25);
    l1.addLast(30);
    l1.addLast(35);

    System.out.println(l1.getSize());                                                           // 6
    l1.printDeque();

    System.out.println("l1.get(0): " + l1.get(0));                                              // 15
    System.out.println("l1.get(2): " + l1.get(1));                                              // 5
    System.out.println("l1.get(3): " + l1.get(2));                                              // 25
    System.out.println("l1.getRecursive(3): " + l1.getRecursive(3));                      // 25
    System.out.println("l1.getRecursive(3): " + l1.getRecursive(4));                      // 25
    System.out.println("l1.getRecursive(3): " + l1.getRecursive(5));                      // 25
    System.out.println("l1.removeFirst(): " + l1.removeFirst());                                // 15
    System.out.println(l1.getSize());                                                           // 5
    System.out.println("l1.removeLast(): " + l1.removeLast());                                  // 35
    System.out.println(l1.getSize());                                                           // 4

    LinkedListDeque<Integer> l2 = new LinkedListDeque<Integer>(l1);
    System.out.print("l2.printDeque(): ");
    l2.printDeque();






  }
}
