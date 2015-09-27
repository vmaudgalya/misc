/***********************************************
* ArrayList API
* Implemented with a dynamically resizing array
* List does not support null elements.
* @author Varun Maudgalya
************************************************/
public class ArrayList<Item> {
  
  private int size;
  private Item[] array;

  public ArrayList() {
    array = (Item[]) new Object[1]; // Because Java doesn't allow generic arrays
  }

  /**************************************************************
  * Resizes the ArrayList when necessary to optimize memory usage
  * Time: O(n)
  * Space: O(n)
  **************************************************************/
  private void resize(int size) {
    Item[] resizedArray = (Item[]) new Object[size];
    for (int i = 0; i < array.length; i++) {
      resizedArray[i] = array[i];
    }
    array = resizedArray;
  }

  /*****************************
  * Adds to the end of the list
  * Time: O(1) (Ammortized)
  * Space: O(1)
  ******************************/
  public void append(Item data) {
    nullCheck(data);
    if (size == (array.length)) {
      resize(array.length*2);
    }
    array[size++] = data;
  }

  /***********************************
  * Removes item from specified index
  * Time: O(n)
  * Space: O(1)
  ************************************/
  public Item remove(int index) {
    indexCheck(index);
    emptyCheck();
    Item data = array[index];
    for (int i = index; i < size-1; i++) {
      array[i] = array[i+1];
      array[i+1] = null; // To avoid loitering
    }
    size--;
    if (size < array.length/4) {
      resize((int) array.length/2);
    }
    return data;
  }

  /********************************************
  * Returns the element at the specified index
  * Time: O(1)
  * Space: O(1)
  ********************************************/
  public Item get(int index) {
    indexCheck(index);
    return array[index];
  }

  /***************************************
  * Returns true if list contains the data
  * Time: O(n)
  * Space: O(1)
  ***************************************/
  public boolean contains(Item data) {
    nullCheck(data);
    for (int i = 0; i < size; i++) {
      if (array[i] == data) {
        return true;
      }
    }
    return false;
  }

  /***********************************
  * Returns true if the list is empty.
  * Time: O(1)
  * Space: O(1)
  ***********************************/
  public boolean isEmpty() {
    return size == 0;
  }

  /******************************
  * Returns the size of the list.
  * Time: O(1)
  * Space: O(1)
  *******************************/
  public int size() {
    return size;
  }

  /*****************
  * Prints the list.
  * Time: O(n)
  * Space: O(1)
  ******************/
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[ ");
    for (int i = 0; i < size; i++) {
      sb.append(array[i] + (i == size-1 ? " ]" : ", "));
    }
    return sb.toString();
  }

  /* Helper methods */
  private void nullCheck(Item data) {
    if (data == null) {
      throw new NullPointerException("Null items not allowed");
    }
  }

  private void indexCheck(int index) {
    if (index >= size) {
      throw new IndexOutOfBoundsException("Array Index " + index + " out of bounds!");
    }
  }

  private void emptyCheck() {
    if (isEmpty()) {
      throw new java.util.NoSuchElementException("List is empty!");
    }
  }

  /* For testing purposes */
  public static void main(String[] args) {
    ArrayList<Integer> list = new ArrayList<Integer>();
    for (int i = 0; i < 10; i++) {
      list.append(i);
      System.out.println("Appending " + i + " to the list" + list);
    }
    System.out.println("After removing " + list.remove(3) + " from the list " + list);
    System.out.println("After removing " + list.remove(1) + " from the list " + list);
    System.out.println("After removing " + list.remove(2) + " from the list " + list);
    System.out.println("The size of the list is: " + list.size());
    System.out.println("Does the list contain 15? " + list.contains(15));
    System.out.println("Does the list contain 2? " + list.contains(2));

  }


}