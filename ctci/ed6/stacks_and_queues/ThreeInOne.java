
public class ThreeInOne<E> {

  private int[] stackSizes;
  private E[] arrayOfStacks;
  private int capacity;
  private final int NUMBER_OF_STACKS = 3;

  @SuppressWarnings("unchecked")
  public ThreeInOne(int capacity) {
    stackSizes = new int[NUMBER_OF_STACKS];
    arrayOfStacks = (E[]) new Object[NUMBER_OF_STACKS*capacity];
    this.capacity = capacity;
  }

  public void push(final int stackNumber, final E data) throws Exception {
    if (isFull(stackNumber)) {
      throw new Exception("Error: Stack " + stackNumber + " is full");
    }
    int N = getTopOfStack(stackNumber);
    arrayOfStacks[N] = data;
    stackSizes[stackNumber]++;
  }

  public E pop(final int stackNumber) {
    if (isEmpty(stackNumber)) {
      return null;
    }
    int N = getTopOfStack(stackNumber);
    final E data = arrayOfStacks[--N];
    arrayOfStacks[N] = null;
    stackSizes[stackNumber]--;
    return data;
  }

  public boolean isFull(final int stackNumber) {
    return getTopOfStack(stackNumber) == (stackNumber * NUMBER_OF_STACKS) + capacity;
  }

  public boolean isEmpty(final int stackNumber) {
    return stackSizes[stackNumber] == 0;
  }

  public E peek(final int stackNumber) {
    int N = getTopOfStack(stackNumber);
    return arrayOfStacks[--N];
  }

  private int getTopOfStack(int stackNumber) {
    int offset = (stackNumber * capacity) + stackSizes[stackNumber];
    return offset;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder("[ ");
    for (int i = 0; i < arrayOfStacks.length; i++) {
      builder.append(arrayOfStacks[i] + (i == arrayOfStacks.length-1 ? "" : ", "));
    }
    builder.append(" ]");
    return builder.toString();
  }

}
