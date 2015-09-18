/********************************************************************************
* Binary Search Tree API
* Unbalanced version
* By default, methods are recursively implemented for the sake of logical clarity
* However, iterative solutions are more efficient and are preferred. Hence
* inclusion of iterative versions of the method, which are prefixed with 'i'.
* Null keys not supported, however null values are supported.
*
* Compilation: javac BinaryTree.java
* Run: java BinaryTree
*
* @author Varun Maudgalya
*********************************************************************************/

public class BinaryTree<Key extends Comparable<Key>, Value> {
  private class Node {
    Node left, right;
    int size; // Keep track of the size of each subtree
    Key key;
    Value value;

    public Node(Key key, Value value, int size) {
      nullCheck(key);
      this.key = key;
      this.value = value;
      this.size = size;
    }
  }

  private Node root;

  public boolean isEmpty() {
    return root == null;
  }

  public int size() {
    return size(root);
  }

  private int size(Node current) {
    if (current == null) {
      return 0;
    } else {
      return size(current.left) + size(current.right) + 1;
    }
  }

  /***************************************************************
  * Recursive version
  * Given a Key, returns the value associated with the key
  * If key does not exist in tree, throws NoSuchElementException()
  * Time: O(n)
  * Space: O(1);
  ****************************************************************/
  public Value get(Key key) {
    nullCheck(key);
    return get(key, root);
  }
  // Helper method
  private Value get(Key key, Node current) {
    if (current == null) {
      return null;
    }
    int cmp = key.compareTo(current.key);
    if (cmp > 0) {
      return get(key, current.right);
    } else if (cmp < 0) {
      return get(key, current.left);
    } else {
      return current.value;
    }
  }

  /***************************************************************
  * Iterative version
  * Given a Key, returns the value associated with the key
  * If key does not exist in tree, throws NoSuchElementException()
  * Time: O(n)
  * Space: O(1);
  ****************************************************************/
  public Value iget(Key key) {
    nullCheck(key);
    return iget(key, root);
  }
  // Helper method
  private Value iget(Key key, Node current) {
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

  public void put(Key key, Value value) {
    nullCheck(key);
    root = put(key, value, root);
  }

  private Node put(Key key, Value value, Node current) {
    if (current == null) {
      return new Node(key, value, 1);
    }
    int cmp = key.compareTo(current.key);
    if (cmp < 0) {
      current.left = put(key, value, current.left);
    } else if (cmp > 0) {
      current.right = put(key, value, current.right);
    } else {
      current.value = value;
    }
    current.size = size(current.left) + size(current.right) + 1;  
    return current;
  }

  public void iput(Key key, Value value) {
    nullCheck(key);
    if (isEmpty()) {
      root = new Node(key, value, 1);
      return;
    }
    Node current = root;
    Node parent = null;
    int cmp = 0;
    while (current != null) {
      cmp = key.compareTo(current.key);
      parent = current;  
      if (cmp < 0) {
        current = current.left;
      } else if (cmp > 0) {
        current = current.right;
      } else {
        current.value = value;
        return;
      }
    }
    if (cmp < 0) {
      parent.left = new Node(key, value, 1);
    } else {
      parent.right = new Node(key, value, 1);
    }
  }

  public boolean contains(Key key) {
    return iget(key) != null;
  }

  // General Helpers
  private void nullCheck(Key key) {
    if (key == null) { 
      throw new NullPointerException("Null keys not allowed");
    }
  }

  /* For testing purposes */
  public static void main(String[] args) {
    BinaryTree<Integer, String> tree = new BinaryTree<Integer, String>();
    System.out.println("Tree size " + tree.size());
    System.out.println("Adding 1 : \"parrot\" to tree");
    tree.put(1, "parrot");
    System.out.println("Tree size " + tree.size());
    System.out.println("Adding 0 : \"dog\" to tree");
    tree.put(0, "dog");
    System.out.println("Tree size " + tree.size());
    System.out.println("Adding 2 : \"cat\" to tree");
    tree.put(2, "cat");
    System.out.println("Tree size " + tree.size());
    System.out.println(tree.iget(2) + " is the value of getting(2)");
    System.out.println("Adding 7: null to the tree");
    tree.put(7, null);
    System.out.println(tree.iget(7) + " is the value of getting(7)");
    System.out.println("Tree size " + tree.size());
    System.out.println("Adding 15 : \"chicken\" to tree");
    tree.iput(15, "chicken");
    System.out.println("Tree size " + tree.size());
    System.out.println("Adding 12 : \"birds\" to tree");
    tree.iput(12, "birds");
    System.out.println("Tree size " + tree.size());
    System.out.println("Adding 7 : \"elephant\" to tree...key 7 is already in the tree");
    tree.iput(7, "elephant");
    System.out.println("Tree size " + tree.size());
  }


}