/****************************************************************
* Node (for LinkedLists)
*
* @author Varun Maudgalya
*****************************************************************/
public class Node<Item extends Comparable> {
  private Node next;
  private Item data;

  public Node() {

  }

  public Node(Item data) {
    this.data = data;
  }

  public void setData(Item data) {
    this.data = data;
  }

  public void setNext(Node next) {
    this.next = next;
  }

  public Node getNext() {
    return this.next;
  }

  public Item getData() {
    return this.data;
  }

  public boolean hasNext() {
    return next != null;
  }

  public String toString() {
    return this.data + "->";
  }

}