package ctci;

import java.util.ArrayList;

public class SetOfStacks<E> {

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
      size--;
      return data;
    }

    public int size() {
      return size;
    }

    @Override
    public String toString() {
      StringBuilder builder = new StringBuilder("TOP [");
      for (Node current = head; current != null; current = current.next) {
        builder.append(current.data + (current.next == null ? "" : ", "));
      }
      builder.append("] BOTTOM");
      return builder.toString();
    }
  }

  private int size;
  private ArrayList<Stack> stacks;
  private final int THRESHOLD;
  private Stack currentStack;

  public SetOfStacks(final int threshold) {
    THRESHOLD = threshold;
    stacks = new ArrayList<Stack>();
    currentStack = new Stack();
  }

  public void push(final E data) {
    if (data == null) {
      throw new NullPointerException("Null elements are not permitted");
    }
    if (currentStack.size() == THRESHOLD) {
      stacks.add(currentStack);
      currentStack = new Stack();
    }
    currentStack.push(data);
    size++;
  }

  public E pop() {
    if (stacks.isEmpty()) {
      return null;
    } else if (currentStack.isEmpty()) {
      currentStack = stacks.get(stacks.size() - 1);
      stacks.remove(currentStack);
    }
    size--;
    return currentStack.pop();
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return stacks.isEmpty();
  }

  public E popAt(final int index) {
    if (index < 0 || index >= stacks.size()) {
      throw new IndexOutOfBoundsException("Invalid index: " + index);
    }
    Stack current = stacks.get(index);
    final E data = current.pop();
    stacks.set(index, current);
    size--;
    return data;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder("BOTTOM { ");
    for (int i = 0; i < stacks.size(); i++) {
      builder.append(stacks.get(i).toString() + (i == stacks.size()-1 && currentStack.isEmpty() ? "" : ", "));
    }
    if (!currentStack.isEmpty()) {
      builder.append(currentStack.toString());
    }
    builder.append(" } TOP");
    return builder.toString();
  }

}
