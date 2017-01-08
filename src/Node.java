public class Node {
  private Object value = null;
  private Node previous = null;
  private Node next = null;
  
  public Node (Object value) {
    this.value = value;
  }
  
  public void setNext(Node next) {
    this.next = next;
  }
  
  public void setPrevious(Node previous) {
    this.previous = previous;
  }
  
  public Object getValue() {
    return value;
  }
  
  public Node getNext() {
    return next;
  }
  
  public Node getPrevious() {
    return previous;
  }
}