public class ThreeInOne {
  private int[] stackSizes;
  private int[] arrayOfStacks;
  private int capacity;
  private final int NUMBER_OF_STACKS = 3;

  public ThreeInOne(int capacity) {
    stackSizes = new int[NUMBER_OF_STACKS];
    arrayOfStacks = new int[NUMBER_OF_STACKS*capacity];
    this.capacity = capacity;
  }

  public void push(final int data, final int stackNumber) {
    if (isFull(stackNumber)) {
      throw new Exception("Error: Stack " + stackNumber + " is full");
    }
    int N = getTopOfStack(stackNumber);
    arrayOfStacks[N] = data;
    stackSizes[stackNumber]++;
  }

  public int pop(final int stackNumber) {
    if (isEmpty(stackNumber)) {
      return null;
    }
    int N = getTopOfStack(stackNumber);
    final int data = arrayOfStacks[--N];
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

  public int peek(final int stackNumber) {
    int N = getTopOfStack(stackNumber);
    return arrayOfStacks[--N];
  }

  private int getTopOfStack(int stackNumber) {
    int offset = (stackNumber * NUMBER_OF_STACKS) + stackSizes[stackNumber];
    return offset;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder("[ ");
    for (int i = 0; i < arrayOfStacks.length; i++) {
      builder.append(arrayOfStacks[i] + (i == arrayOfStacks.length-1 ? "", ", "));
    }
    builder.append(" ]");
    return builder.toString();
  }

}
