public class HashMap<Key, Value> {
  
  private class Node<Key, Value> {
    Key key;
    Value value;
    Node<Key, Value> next;
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
    Node<Key, Value> bucket = table[hash(key)];
    if (bucket == null) {
      return null;
    } else {
      for (Node<Key, Value> cur = bucket; cur != null; cur = cur.next) {
        if (key.equals(cur.key)) {
          return cur.value;
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
      for (Node<Key, Value> cur = table[index]; cur != null; cur = cur.next) {
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
      Node<Key, Value> victim = table[index];
      table[index] = table[index].next;
      size--;
      return victim.value;
    }
    for (Node<Key, Value> cur = table[index]; cur.next != null; cur = cur.next) {
      if (key.equals(cur.next.key)) {
        Node<Key, Value> victim = cur.next;
        cur.next = cur.next.next;
        size--;
        return victim.value;
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
          for (Node<Key, Value> cur = table[i]; cur != null; cur = cur.next) {
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
    HashMap<Key, Value> temp = new HashMap<Key, Value>(newCapacity);
    for (int i = 0; i < capacity; i++) {
      if (table[i] != null) {
        for (Node<Key, Value> current = table[i]; current != null; current = current.next) {
          temp.put(current.key, current.value);
        }
      }
    }
    this.capacity = temp.capacity;
    this.table = temp.table;
    this.size = temp.size;
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
    for (int i = 24; i < 400; i++) {
      map.put(i, "ALIEN");
    }
    System.out.println(map);
    System.out.println(map.get(1));
    System.out.println(map.get(3));
    System.out.println(map.get(5));
    System.out.println(map.get(22));
    System.out.println("The size is " + map.size());
  }


}