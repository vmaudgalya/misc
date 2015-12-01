/***
* @author Varun Maudgalya
* Queue implementation using nodes & links
* O(1) constant time operations
***/

import java.util.NoSuchElementException;
import java.util.Iterator;

public class Queue<E> implements Iterable<E> {

  private class Node {
    E data;
    Node next;
    public Node(final E data) {
      this.data = data;
    }
  }

  private int size;
  private Node head; // Front of queue
  private Node tail; // Rear of queue

  public int size() {
    return this.size;
  }

  public boolean isEmpty() {
    return head == null;
  }

  public void enqueue(final E data) {
    if (data == null) {
      throw new NullPointerException("Null elements not permitted");
    }
    if (this.isEmpty()) {
      this.tail = new Node(data);
      this.head = this.tail;
    } else {
      this.tail.next = new Node(data);
      this.tail = this.tail.next;
    }
    this.size++;
  }

  public E dequeue() {
    if (this.isEmpty()) {
      return null; // Error
    }
    final E data = this.head.data;
    this.head = this.head.next;
    this.size--;
    return data;
  }

  public E peek() {
    if (this.isEmpty()) {
      return null; // Error
    }
    return this.head.data;
  }

  public Iterator<E> iterator() {
    return new QueueIterator();
  }

  private class QueueIterator implements Iterator<E> {

    private Node current;

    public QueueIterator() {
      this.current = head;
    }

    @Override
    public boolean hasNext() {
      return this.current != null;
    }

    @Override
    public E next() {
      if (this.hasNext()) {
        final E data = this.current.data;
        this.current = this.current.next;
        return data;
      }
      throw new NoSuchElementException("No more items in queue!");
    }

    @Override
    public void remove() {
      /* Modification of a list during iteration is not recommended. */
      throw new UnsupportedOperationException("remove() is not supported by this API");
    }
  }

  @Override
  public String toString() {
    if (this.isEmpty()) {
      return "REAR[ ]FRONT";
    }
    StringBuilder builder = new StringBuilder("REAR[");
    for (Node current = head; current != null; current = current.next) {
      builder.append(current.data + (current.next == null ? "]FRONT" : ", "));
    }
    return builder.toString();
  }

}
