package ctci;

public class MyQueue<E> {

  private class Stack {

    private class Node {
      E data;
      Node next;
      public Node(final E data) {
        this.data = data;
      }
    }

    private Node head;
    private int size;

    public int size() {
      return size;
    }

    public boolean isEmpty() {
      return head == null;
    }

    public void push(final E data) {
      if (data == null) {
        throw new NullPointerException("Null elements are not permitted");
      }
      Node top = new Node(data);
      if (isEmpty()) {
        head = top;
      } else {
        top.next = head;
        head = top;
      }
      size++;
    }

    public E pop() {
      if (isEmpty()) {
        return null;
      }
      final E data = head.data;
      head = head.next;
      return data;
    }

    public void printInReverse() {
      Node current = head;
      printInReverseHelper(current);
    }

    private void printInReverseHelper(Node current) {
      if (current == null) {
        return;
      }
      printInReverseHelper(current.next);
      System.out.print(current.data);
    }

    @Override
    public String toString() {
      StringBuilder builder = new StringBuilder("[");
      for (Node current = head; current != null; current = current.next) {
        builder.append(current.data + (current.next == null ? "" : ", "));
      }
      builder.append("]");
      return builder.toString();
    }
  }

  private Stack a;
  private Stack b;

  public MyQueue() {
    a = new Stack(); //a for adding
    b = new Stack(); // b for removing
  }

  public int size() {
    return a.size() + b.size();
  }

  public boolean isEmpty() {
    return a.isEmpty() && b.isEmpty();
  }

  public void enqueue(final E data) {
    if (data == null) {
      throw new NullPointerException("Null elements are not permitted");
    }
    while (!b.isEmpty()) {
      a.push(b.pop());
    }
    a.push(data);
  }

  public E dequeue() {
    if (isEmpty()) {
      return null;
    }
    while (!a.isEmpty()) {
      b.push(a.pop());
    }
    return b.pop();
  }

  @Override
  public String toString() {
    if (a.isEmpty() && b.isEmpty()) {
      return "[]";
    } else if (a.isEmpty()) {
      return b.toString();
    } else if (b.isEmpty()) {
      a.printInReverse();
      return "";
    }
    return null;
  }

}
