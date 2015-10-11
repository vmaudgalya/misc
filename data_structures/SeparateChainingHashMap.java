public class SeparateChainingHashMap<Key, Value> {
  private class Chain<Key, Value> {
    private class Node {
      Key key;
      Value value;
      Node next;

      public Node(Key key, Value value, Node next) {
        this.key = key;
        this.value = value;
        this.next = next;
      }
    }

    private Node head;

    public Value get(Key key) {
      for (Node current = head; current != null; current = current.next) {
        if (key.equals(current.key)) {
          return current.value;
        }
      }
      return null;
    }

    public boolean put(Key key, Value value) {
      for (Node current = head; current != null; current = current.next) {
        if (key.equals(current.key)) {
          current.value = value;
          return false;
        }
      }
      head = new Node(key, value, head);
      return true;
    }

    public Value remove(Key key) {
      if (key.equals(head.key)) {
        Value val = head.value;
        head = head.next;
        return val;
      }
      for (Node current = head; current.next != null; current = current.next) {
        if (key.equals(current.next.key)) {
          Value val = current.next.value;
          current.next = current.next.next;
          return val;
        }
      }
      return null;
    }
  }

  private Chain<Key, Value>[] table;
  private int size; // number of items in map
  private int capacity;

  public SeparateChainingHashMap(int capacity) {
    table = (Chain<Key, Value>[]) new Chain[capacity];
    for (int i = 0; i < capacity; i++) {
      table[i] = new Chain();
    }
    this.capacity = capacity;
  }

  public SeparateChainingHashMap() {
    this(31);
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public int size() {
    return size;
  }

  private int hash(Key key) {
    nullCheck(key);
    return (key.hashCode() & 0x7fffffff) % capacity; // avoid hashCode() negative ints
  }

  public Value get(Key key) {
    nullCheck(key);
    return table[hash(key)].get(key);
  }

  public void put(Key key, Value value) {
    nullCheck(key);
    if (table[hash(key)].put(key, value)) {
      size++;
    }
  }

  public boolean containsKey(Key key) {
    nullCheck(key);
    return get(key) != null;
  }

  public Value remove(Key key) {
    nullCheck(key);
    Value val = table[hash(key)].remove(key);
    if (val != null) {
      size--;
    }
    return val;
  }

  private void nullCheck(Key key) {
    if (key == null) {
      throw new NullPointerException("Null keys not permitted");
    }
  }

  public static void main(String[] args) {
    SeparateChainingHashMap<String, Integer> map = new SeparateChainingHashMap<String, Integer>();
    map.put("Dogs", 2);
    map.put("Cats", 40);
    map.put("Ducks", 7);
    System.out.println(map.containsKey("Ducks")); // true
    System.out.println(map.size()); // 3
    System.out.println(map.remove("Cats")); // 40
    System.out.println(map.size()); // 2


  }
}