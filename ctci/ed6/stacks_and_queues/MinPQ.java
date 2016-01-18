/**
* @author Varun Maudgalya
* Priority Queue Implementation
*
* Item with the lowest priority will be at the root
* meaining that the item with the lowest priority will be
* removed first.
***/
import java.util.Iterator;
public class MinPQ<Priority extends Comparable<Priority>, Item> implements Iterable<Item> {

  private class Node {
    Priority priority;
    Item item;

    public Node(final Priority priority, final Item item) {
      this.priority = priority;
      this.item = item;
    }

    @Override
    public String toString() {
      return item.toString();
    }
  }

  private Node[] queue;
  private int size;

  public MinPQ() {
    queue = (Node[]) new MinPQ.Node[2]; // because we're leaving index 0 empty
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  private void resize(final int resizedLength) {
    Node[] resizedQueue = (Node[]) new MinPQ.Node[resizedLength+1];
    for (int i = 1; i <= size; i++) {
      resizedQueue[i] = queue[i];
    }
    queue = resizedQueue;
  }

  private void swap(final int i, final int j) {
    Node node = queue[i];
    queue[i] = queue[j];
    queue[j] = node;
  }

  private boolean less(final int first, final int second) {
    return queue[first].priority.compareTo(queue[second].priority) < 0;
  }

  /* Swim an item up the heap if it has lower priority than its parent */
  private void swim(int index) {
    while ((index > 1) && (less(index, index/2))) {
      swap(index/2, index);
      index /= 2;
    }
  }

  /* Sink an item until its priority is greater than that of its parent */
  private void sink(int index) {
    while (index <= size) {
      int child = index * 2;
      if ((child < (size)) && less(child+1, child)) {
        child++;
      }
      if (child > size || less(index, child)) {
        break;
      }
      swap(index, child);
      index = child;
    }
  }

  /*
  * Add an item and its respective priority to the queue
  */
  public void insert(final Item item, final Priority priority) {
    int insertionIndex = size + 1;
    Node n = new Node(priority, item);
    queue[insertionIndex] = n;
    swim(insertionIndex);
    size++;
    if (queue.length-1 == size) {
      resize(queue.length*2);
    }
  }

  /*
  * Change priority of all occurrences of a particular item
  */
  public void changePriority(final Item item, final Priority priority) {
    int itemIndex = findIndex(item);
    if (itemIndex == -1) {
      throw new java.util.NoSuchElementException("Item not in queue!");
    }
    queue[itemIndex].priority = priority;
    swim(itemIndex);
    sink(itemIndex);
  }

  private int findIndex(final Item item) {
    for (int i = 1; i <= size; i++) {
      if (queue[i].item.equals(item)) {
        return i;
      }
    }
    return -1;
  }

  /*
  * Return true if a particular item exists in the queue
  */
  public boolean contains(final Item item) {
    return findIndex(item) > -1;
  }

  /*
  * Delete the item from the queue
  */
  public void delete(final Item item) {
    int index = findIndex(item);
    if (index == -1) {
      throw new java.util.NoSuchElementException("Item " + item + " not in queue");
    }
    swap(index, size+1);
    queue[size+1] = null;
    size--;
    sink(index);
    if (size < queue.length/4) {
      resize(queue.length/2);
    }
  }

  /*
  * Delete the minimum element from the queue
  */
  public Item deleteMin() {
    if (isEmpty()) {
      throw new java.util.NoSuchElementException("No minimum in queue");
    }
    Item min = queue[1].item;
    swap(1, size);
    queue[size] = null;
    size--;
    sink(1);
    if (size < queue.length/4) {
      resize(queue.length/2);
    }
    return min;
  }

  /*
  * Get (but do not remove) the minimum element in the queue
  */
  public Item getMin() {
    if (isEmpty()) {
      throw new java.util.NoSuchElementException("No minimum in queue");
    }
    return queue[1].item;
  }

  public Iterator<Item> iterator() {
    return new MinPQIterator();
  }

  public String toString() {
    StringBuilder builder = new StringBuilder();
    int maxNodes = 1;
    int seenNodes = 0;
    for (int i = 1; i <= size; i++) {
      builder.append(queue[i].item + " ");
      seenNodes++;
      if (seenNodes == maxNodes) {
        builder.append("\n");
        seenNodes = 0;
        maxNodes *= 2;
      }
    }
    return builder.toString();
  }

  // public static void main(String[] args) {
  //   MinPQ<Integer, Integer> q = new MinPQ<Integer, Integer>();
  //   java.util.Random generator = new java.util.Random();
  //   for (int i = 0; i < 100; i++) {
  //     int number = generator.nextInt(1000);
  //     System.out.println("Inserting " + number);
  //     q.insert(number, number);
  //     System.out.println(q);
  //   }
  //   for (int i = 0; i < 10; i++) {
  //     System.out.println("Removing " + q.deleteMin());
  //   }
  // }

  private class MinPQIterator implements Iterator<Item> {

    private int current;

    @Override
    public void remove() {
      throw new UnsupportedOperationException("remove() operation not supported");
    }

    @Override
    public boolean hasNext() {
      return current <= size;
    }

    @Override
    public Item next() {
      Item item = queue[current++].item;
      return item;
    }

  }

}
