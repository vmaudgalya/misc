/***
* @author Varun Maudgalya
* Binary Search Tree implementation
* Operations are worst case O(n) due to lack of balancing 
***/

import java.util.Deque;
import java.util.ArrayDeque;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class BST<Key extends Comparable<Key>, Value> implements Iterable<Key> {

  private class Node {
    private Key key;
    private Value value;
    private Node left;
    private Node right;
    private int count;

    public Node(final Key key, final Value value, final int count) {
      this.key = key;
      this.value = value;
      this.count = count;
    }
  }

  private Node root;

  public int size() {
    return size(root);
  }

  private int size(final Node x) {
    if (x == null) {
      return 0;
    }
    return x.count;
  }

  public boolean isEmpty() {
    return root == null;
  }

  public Value get(final Key key) {
    if (key == null) {
      throw new NullPointerException("Key cannot be null");
    }
    return r_get(root, key); // Recursive implementation
    // return i_get(key); // Iterative implementation
  }

  private Value r_get(final Node x, final Key key) {
    if (x == null || key == null) {
      return null;
    }
    final int cmp = key.compareTo(x.key);
    if (cmp < 0) {
      return r_get(x.left, key);
    } else if (cmp > 0) {
      return r_get(x.right, key);
    } else {
      return x.value;
    }
  }

  @SuppressWarnings("unused")
  private Value i_get(final Key key) {
    Node current = root;
    while (current != null) {
      int cmp = key.compareTo(current.key);
      if (cmp < 0) {
        current = current.left;
      } else if (cmp > 0) {
        current = current.right;
      } else {
        return current.value;
      }
    }
    return null;
  }

  public void put(final Key key, final Value value) {
    if (key == null || value == null) {
      throw new NullPointerException((key == null ? "Key" : "Value") + " cannot be null");
    }
    root = r_put(root, key, value);
    i_put(key, value);
  }

  private Node r_put(final Node x, final Key key, final Value value) {
    if (x == null) {
      return new Node(key, value, 1);
    }
    int cmp = key.compareTo(x.key);
    if (cmp < 0) {
      x.left = r_put(x.left, key, value);
    } else if (cmp > 0) {
      x.right = r_put(x.right, key, value);
    } else {
      x.value = value;
      return x; // not necessary (?)
    }
    x.count = size(x.left) + size(x.right) + 1;
    return x;
  }

  private void i_put(final Key key, final Value value) {
    Node current = root;
    while (current != null) {
      int cmp = key.compareTo(current.key);
      if (cmp < 0) {
        if (current.left == null) {
          current.left = new Node(key, value, 1);
          // adjustCounts();
          return;
        }
        current = current.left;
      } else if (cmp > 0) {
        if (current.right == null) {
          current.right = new Node(key, value, 1);
          // adjustCounts(); // TODO Implement adjustCounts()
          return;
        }
        current = current.right;
      } else {
        current.value = value;
        return;
      }
    }
  }

  public Value remove(final Key key) {
    if (key == null) {
      throw new NullPointerException("Null keys are not permitted");
    }
    Node deleted = r_remove(root, key);
    return deleted.value;
    // return i_remove(key);
  }

  @SuppressWarnings("unused")
  private Node r_remove(Node root, final Key key) {
    if (root == null) {
      return root;
    } else if (key.compareTo(root.key) < 0) {
      root.left = r_remove(root.left, key);
    } else if (key.compareTo(root.key) > 0) {
      root.right = r_remove(root.right, key);
    } else {
      if (root.left == null && root.right == null) { // Case: 0 children
        root = null;
      } else if (root.left == null) { // Case: 1 child
        Node victim = root;
        root = root.right;
        victim = null;
      } else if (root.right == null) {
        Node victim = root;
        root = root.left;
        victim = null;
      } else { // Case: 2 children
        Node successor = getMinimum(root.right);
        root.key = successor.key;
        root.value = successor.value;
        root.right = r_remove(root.right, successor.key);
      }
    }
    return root;
  }

  private Node getMinimum(Node root) {
    Node current = root;
    while (current.left != null) {
      current = current.left;
    }
    return current;
  }

  public boolean containsKey(final Key key) {
    if (key == null) {
      throw new NullPointerException("Null keys are not allowed");
    }
    return get(key) != null;
  }


  public boolean containsValue(final Value value) {
    return r_containsValue(root, value);
    // return i_containsValue(value);
  }

  private boolean r_containsValue(final Node x, final Value value) {
    if (x == null) {
      return false;
    }
    return (value == x.value || r_containsValue(x.left, value) || r_containsValue(x.right, value));
  }

  @SuppressWarnings("unused")
  private boolean i_containsValue(final Value value) {
    Deque<Node> queue = new ArrayDeque<Node>();
    Node current = root;
    queue.addLast(root);
    while (!queue.isEmpty()) {
      current = queue.pollFirst();
      if (current.value == value) {
        return true;
      }
      if (current.left != null) {
        queue.addLast(current.left);
      }
      if (current.right != null) {
        queue.addLast(current.right);
      }
    }
    return false;
  }

  public List<Value> values() {
    return i_values();
    // return r_values(root, new ArrayList<Value>());
  }

  private List<Value> i_values() {
    Deque<Node> queue = new ArrayDeque<Node>();
    List<Value> values = new ArrayList<Value>();
    Node current = root;
    queue.addLast(root);
    while (!queue.isEmpty()) {
      current = queue.pollFirst();
      values.add(current.value);
      if (current.left != null) {
        queue.addLast(current.left);
      }
      if (current.right != null) {
        queue.addLast(current.right);
      }
    }
    return values;
  }

  @SuppressWarnings("unused")
  private List<Value> r_values(final Node root, final List<Value> values) {
    if (root == null) {
      return values;
    }
    values.add(root.value);
    List<Value> leftValues = r_values(root.left, values);
    return r_values(root.right, leftValues);
  }

  public Iterator<Key> iterator() {
    return new BSTKeyIterator();
  }

  public Iterator<Value> iterator_values() {
    return new BSTValueIterator();
  }

  public String toString() {
    if (isEmpty()) {
      return "";
    }
    StringBuilder builder = new StringBuilder();
    Deque<Node> currentLevel = new ArrayDeque<Node>();
    Deque<Node> nextLevel = new ArrayDeque<Node>();
    currentLevel.addLast(root);
    while (!currentLevel.isEmpty()) {
      for (Node x : currentLevel) {
        if (x.left != null) {
          nextLevel.addLast(x.left);
        }
        if (x.right != null) {
          nextLevel.addLast(x.right);
        }
        builder.append("[key:" + x.key + " value:" + x.value + "] ");
      }
      builder.append("\n");
      currentLevel = nextLevel;
      nextLevel = new ArrayDeque<Node>();
    }
    return builder.toString();
  }

  /* Breadth-First Iteration over the keys in the BST */
  private class BSTKeyIterator implements Iterator<Key> {
    private Node current;
    private Deque<Node> queue;

    public BSTKeyIterator() {
      current = root;
      queue = new ArrayDeque<Node>();
      queue.addLast(current);
    }

    public boolean hasNext() {
      return !queue.isEmpty();
    }

    public Key next() {
      current = queue.pollFirst();
      if (current.left != null) {
        queue.addLast(current.left);
      }
      if (current.right != null) {
        queue.addLast(current.right);
      }
      return current.key;
    }

    public void remove() {
      throw new UnsupportedOperationException("This method is not supported");
    }

  }

  /* Breadth-First Iteration over the keys in the BST */
  private class BSTValueIterator implements Iterator<Value> {
    private Node current;
    private Deque<Node> queue;

    public BSTValueIterator() {
      current = root;
      queue = new ArrayDeque<Node>();
      queue.addLast(current);
    }

    public boolean hasNext() {
      return !queue.isEmpty();
    }

    public Value next() {
      current = queue.pollFirst();
      if (current.left != null) {
        queue.addLast(current.left);
      }
      if (current.right != null) {
        queue.addLast(current.right);
      }
      return current.value;
    }

    public void remove() {
      throw new UnsupportedOperationException("This method is not supported");
    }

  }

  public static void main(String[] args) {
    BST<Integer, String> tree = new BST<Integer, String>();
    System.out.println("Empty tree: " + tree);
    tree.put(44, "apples");
    System.out.println("After adding apples:\n" + tree);
    tree.put(23, "pears");
    System.out.println("After adding pears:\n" + tree);
    tree.put(15, "bananas");
    System.out.println("After adding bananas:\n" + tree);
    tree.put(70, "tangerines");
    System.out.println("After adding tangerines:\n" + tree);
    tree.put(24, "oranges");
    System.out.println("After adding oranges:\n" + tree);
    tree.put(66, "pineapples");
    System.out.println("After adding pineapples:\n" + tree);
    tree.remove(44);
    System.out.println("Removed apples:\n" + tree);
    System.out.println("Inorder traversal:");
    for (int key : tree) {
      System.out.println(key);
    }
    System.out.println("In order values: " + tree.values());
    System.out.println("contains oranges? " + tree.containsValue("oranges"));
    System.out.println("contains 15? " + tree.containsKey(15));

  }

}
