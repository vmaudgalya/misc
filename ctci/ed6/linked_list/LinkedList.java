/*
** @author Varun Maudgalya
** @brief Singly Linked List Implementation for CTCI
** @date 11/14/2015
*/
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<E extends Comparable<E>> implements Iterable<E> {

  public class Node { // exposed for the sake of doing the questions
    E data;
    Node next;
    public Node(E data) {
      this.data = data;
    }
  }

  public Node head; // exposed for the sake of doing the questions
  private int size;

  public int size() {
    return this.size;
  }

  public boolean isEmpty() {
    return this.head == null;
  }

  public boolean contains(final E data) {
    if (this.isEmpty() || data == null) {
      return false;
    }
    Node current = this.head;
    while (current != null) {
      if (data.compareTo(current.data) == 0) {
        return true;
      }
      current = current.next;
    }
    return false;
  }

  private Node getLast(Node current) {
    if (current == null) {
      return null;
    }
    while (current.next != null) {
      current = current.next;
    }
    return current;
  }

  public void addLast(final E data) {
    if (data == null) {
      return;
    }
    if (this.isEmpty()) {
      this.head = new Node(data);
    } else {
      Node current = this.getLast(this.head);
      current.next = new Node(data);
    }
    this.size++;
  }

  public E remove(final E data) {
    if (this.isEmpty() || data == null) {
      return null;
    }
    Node current = this.head;
    if (data.compareTo(current.data) == 0) {
      this.head = this.head.next;
      this.size--;
      return data;
    }
    while (current != null) {
      if (data.compareTo(current.next.data) == 0) {
        current.next = current.next.next;
        this.size--;
        return data;
      }
      current = current.next;
    }
    return null;
  }

  public Iterator<E> iterator() {
    return new ListIterator();
  }

  private class ListIterator implements Iterator<E> {

    private Node current;

    public ListIterator() {
      this.current = head;
    }

    @Override
    public boolean hasNext() {
      return this.current != null;
    }

    @Override
    public E next() {
      if (this.current == null) {
        throw new NoSuchElementException("Error: No more elements to iterate over");
      }
      E data = this.current.data;
      this.current = this.current.next;
      return data;
    }

    @Override
    public void remove() {
      /* Modification of a list during iteration is not recommended. */
      throw new UnsupportedOperationException("remove() is not supported by this API");
    }
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder("[");
    Node current = this.head;
    while (current != null) {
      builder.append( (current.next == null) ? current.data : current.data + ", ");
      current = current.next;
    }
    builder.append("]");
    return builder.toString();
  }

}
