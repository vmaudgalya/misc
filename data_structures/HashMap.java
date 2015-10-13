public class HashMap<Key, Value> {
  
  private class Node<Key, Value> {
    Key key;
    Value value;
    Node next;
    public Node(Key key, Value value, Node next) {
      this.key = key;
      this.value = value;
      this.next = next;
    }

    public String toString() {
      return "{" + this.key + ":" + this.value + "}";
    }
  }

  private Node<Key, Value>[] table;
  private int capacity;
  private int size;
  private static final double LOAD_FACTOR = 3;

  public HashMap(int capacity) {
    this.capacity = capacity;
    this.size = 0;
    table = (Node<Key, Value>[]) new Node[capacity];
  }

  public HashMap() {
    this(31);
  }

  private int hash(Key key) {
    nullCheck(key);
    return (key.hashCode() & 0x7fffffff) % capacity;
  }

  public Value get(Key key) {
    nullCheck(key);
    Node bucket = table[hash(key)];
    if (bucket == null) {
      return null;
    } else {
      for (Node cur = bucket; cur != null; cur = cur.next) {
        if (key.equals(cur.key)) {
          return (Value) cur.value;
        }
      }
      return null;
    }
  }

  public void put(Key key, Value value) {
    nullCheck(key);
    int index = hash(key);
    if (table[index] == null) {
      table[index] = new Node(key, value, null);
    } else {
      for (Node cur = table[index]; cur != null; cur = cur.next) {
        if (key.equals(cur.key)) {
          cur.value = value;
          return;
        }
      }
      table[index] = new Node(key, value, table[index]);
    }
    size++;
    if (size >= LOAD_FACTOR * capacity) {
      resize((capacity * 2) + 1);
    }
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public int size() {
    return size;
  }

  public boolean containsKey(Key key) {
    nullCheck(key);
    return get(key) != null;
  }

  public Value remove(Key key) {
    nullCheck(key);
    int index = hash(key);
    if (table[index] == null) {
      return null;
    } else if (key.equals(table[index].key)) {
      Node victim = table[index];
      table[index] = table[index].next;
      size--;
      return (Value) victim.value;
    }
    for (Node cur = table[index]; cur.next != null; cur = cur.next) {
      if (key.equals(cur.next.key)) {
        Node victim = cur.next;
        cur.next = cur.next.next;
        size--;
        return (Value) victim.value;
      }
    }
    return null;
  }

  public String toString() {
    if (isEmpty()) {
      return "{}";
    } else {
      StringBuilder builder = new StringBuilder();
      builder.append("{");
      int seen = 0;
      for (int i = 0; i < capacity; i++) {
        if (table[i] != null) {
          for (Node cur = table[i]; cur != null; cur = cur.next) {
            seen++;
            builder.append(cur.toString() + (seen != size ? "," : ""));
          }
        }
      }
      builder.append("}");
      return builder.toString();
    }
  }

  private void resize(int newCapacity) {
    Node[] resized = (Node[]) new Node[newCapacity];
    for (int i = 0; i < capacity; i++) {
      resized[i] = table[i];
    }
    table = resized;
    this.capacity = newCapacity;
  }

  private void nullCheck(Key key) {
    if (key == null) {
      throw new NullPointerException("Null keys not supported");
    }
  }

  public static void main(String[] args) {
    HashMap<Integer, String> map = new HashMap<Integer, String>();
    System.out.println(map);
    map.put(1, "Dolphin");
    System.out.println(map);
    map.put(3, null);
    System.out.println(map);
    map.put(5, "Seahorse");
    System.out.println(map);
    map.put(2, "Whale");
    System.out.println(map);
    map.put(7, "Crab");
    System.out.println(map);
    map.put(22, "Shark");
    System.out.println(map);
    System.out.println(map.size());
    System.out.println("Removed " + map.remove(5));
    System.out.println("HashMap is now: " + map);

    System.out.println("Resizing test");
    for (int i = 0; i < 2000; i++) {
      map.put(i, "ALIEN");
    }
    System.out.println(map);

  }


}