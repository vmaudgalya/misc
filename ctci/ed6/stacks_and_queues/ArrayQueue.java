/***
* @author Varun Maudgalya
* Queue implemented as a circular resizing array
***/

import java.util.Iterator;

public class ArrayQueue<E> implements Iterable<E> {

  private E[] queue;
  private int front;
  private int rear;
  private int size;

  @SuppressWarnings("unchecked")
  public ArrayQueue() {
    this.queue = (E[]) new Object[1];
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public int size() {
    return size;
  }

  public void enqueue(final E data) {
    if (data == null) {
      throw new NullPointerException("Null elements not permitted");
    }
    queue[this.rear] = data;
    this.size++;
    if (((this.rear + 1) % this.queue.length) == this.front) {
      this.resize(2*this.queue.length);
    }
    this.rear++;
  }

  public E dequeue() {
    if (this.isEmpty()) {
      return null;
    }
    final E data = this.queue[this.front];
    this.queue[this.front] = null; // Avoid loitering
    this.front = (this.front + 1) % this.queue.length;
    size--;
    return data;
  }

  @SuppressWarnings("unchecked")
  private void resize(int resizedLength) {
    E[] resizedQueue = (E[]) new Object[resizedLength];
    for (int i = this.front; i < this.front+this.size; i++) {
      resizedQueue[i] = queue[i % this.queue.length];
    }
    this.queue = resizedQueue;
  }

  public Iterator<E> iterator() {
    return new ArrayQueueIterator();
  }

  private class ArrayQueueIterator implements Iterator<E> {

    private int current;

    public ArrayQueueIterator() {
      this.current = front;
    }

    @Override
    public boolean hasNext() {
      return (queue[this.current] != null) && (this.current != rear);
    }

    @Override
    public E next() {
      if (isEmpty()) {
        return null;
      }
      final E data = queue[this.current];
      this.current = (this.current + 1) % queue.length;
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
    StringBuilder builder = new StringBuilder("FRONT [");
    for (int i = this.front; i < this.front+this.size; i++) {
      builder.append(queue[i % queue.length] + (i == this.front+this.size-1 ? "" : ", "));
    }
    builder.append("] REAR");
    return builder.toString();
  }

}
