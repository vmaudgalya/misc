/***
* @author Varun Maudgalya
* Stack Implementation using resizing array
* O(1) constant amortized push/pop
***/

import java.util.Iterator;

public class Stack<E> implements Iterable<E> {

  private E[] stack;
  private int N; // Points to next insertion point

  @SuppressWarnings("unchecked")
  public Stack() {
    this.stack = (E[]) new Object[1];
  }

  public int size() {
    return this.N;
  }

  public boolean isEmpty() {
    return this.N == 0;
  }

  @SuppressWarnings("unchecked")
  private void resize(final int newLength) {
    E[] resizedStack = (E[]) new Object[newLength];
    for (int i = 0; i < N; i++) {
      resizedStack[i] = this.stack[i];
    }
    this.stack = resizedStack;
  }

  public void push(final E data) {
    if (data == null) {
      throw new NullPointerException("Null elements not permitted");
    }
    stack[this.N++] = data;
    if (this.N >= this.stack.length) {
      this.resize(2*this.stack.length);
    }
  }

  public E peek() {
    if (this.isEmpty()) {
      return null; // Error
    }
    return stack[N-1];
  }

  public E pop() {
    if (this.isEmpty()) {
      return null; // Error
    }
    final E data = this.stack[--this.N];
    this.stack[this.N] = null; // Avoid data loitering
    if (N < stack.length/4) {
      resize(stack.length/2);
    }
    return data;
  }

  public Iterator<E> iterator() {
    return new StackIterator();
  }

  private class StackIterator implements Iterator<E> {


    private int index;

    public StackIterator() {
      this.index = 0;
    }

    @Override
    public boolean hasNext() {
      return this.index < N;
    }

    @Override
    public E next() {
      if (this.hasNext()) {
        return stack[index++];
      }
      return null; // Error
    }

    @Override
    public void remove() {
      /* Modification of a list during iteration is not recommended. */
      throw new UnsupportedOperationException("remove() is not supported by this API");
    }

  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder("---TOP---\n");
    for (int i = N-1; i >= 0; i--) {
      builder.append(stack[i] + "\n");
    }
    builder.append("--BOTTOM--\n");
    return builder.toString();
  }

}
