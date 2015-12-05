import java.util.Deque;
import java.util.LinkedList;

public class SortStack<T extends Comparable<T>> {

  public Deque<T> sortStack(final Deque<T> stack) {
    if (stack == null || stack.isEmpty()) {
      return stack;
    }
    Deque<T> tmpStack = new LinkedList<T>();
    while (!stack.isEmpty()) {
      T max = findMax(stack);
      int counter = 0;
      while (stack.peek().compareTo(max) != 0) {
        tmpStack.push(stack.pop());
        counter++;
      }
      stack.pop();
      while(counter-- > 0) {
        stack.push(tmpStack.pop());
      }
      tmpStack.push(max);
    }
    return tmpStack;
  }

  private T findMax(final Deque<T> stack) {
    T max = stack.peek();
    for (T item : stack) {
      max = (item.compareTo(max) > 0 ? item : max);
    }
    return max;
  }

  public Deque<T> sortStack(Deque<T> stack) {
    if (stack == null || stack.isEmpty()) {
      return stack;
    }
    Deque<T> tmpStack = new LinkedList<T>();
    while (!stack.isEmpty()) {
      T current = stack.pop();
      int count = 0;
      while ((!tmpStack.isEmpty()) && (current.compareTo(tmpStack.peek()) > 0)) {
        stack.push(tmpStack.pop());
        count++;
      }
      tmpStack.push(current);
      while (count-- > 0) {
        tmpStack.push(stack.pop());
      }
    }
    return tmpStack;
  }

}
