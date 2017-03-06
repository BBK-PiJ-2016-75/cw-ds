public class LinkedList implements List {
  private int listSize = 0;
  private Node head = null;

  @Override
  public boolean isEmpty() {
    if (head == null) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public int size() {
    return listSize;
  }

  @Override
  public ReturnObject get(int index) {
    if (isEmpty()) {
      return new ReturnObjectImpl(ErrorMessage.EMPTY_STRUCTURE);
    } else {
      ReturnObjectImpl checkCurrentNode = checkIndex(index);
      if (checkCurrentNode != null) {
        return checkCurrentNode;
      } else {
        ReturnObjectImpl getCurrentNode = traverseList(index);
        return getCurrentNode;
      }
    }
  }

  @Override
  public ReturnObject remove(int index) {
    if (isEmpty()) {
      return new ReturnObjectImpl(ErrorMessage.EMPTY_STRUCTURE);
    } else {
      ReturnObjectImpl checkCurrentNode = checkIndex(index);
      if (checkCurrentNode != null) {
        return checkCurrentNode;
      } else {
        Node currentNode = head;
        for (int i = 1; i < index; i++) {
          currentNode = currentNode.getNext();
        }
        boolean hasPrevious = false;
        boolean hasNext = false;
        Node previousNode = null;
        Node nextNode = null;
        if (currentNode.getPrevious() != null) {
          hasPrevious = true;
          previousNode = currentNode.getPrevious();
        }
        if (currentNode.getNext() != null) {
          hasNext = true;
          nextNode = currentNode.getNext();
        }
        if (hasPrevious && hasNext) {
          previousNode.setNext(nextNode);
          nextNode.setPrevious(previousNode);
        } else if (hasPrevious) {
          previousNode.setNext(null);
        } else if (hasNext) {
          head = currentNode.getNext();
          nextNode.setPrevious(null);
        }
        listSize--;
        ReturnObjectImpl getCurrentNode = traverseList(index);
        return getCurrentNode;
      }
    }
  }

  @Override
  public ReturnObject add(int index, Object item) {
    ReturnObjectImpl checkCurrentNode = checkIndex(index);
    if (checkCurrentNode != null) {
      return checkCurrentNode;
    } else {
      ReturnObjectImpl checkItem = checkNull(item);
      if (!checkItem.hasError()) {
        Node currentNode = head;
        for (int i = 1; i < index; i++) {
          currentNode = currentNode.getNext();
        }
        Node newNode = new Node(item);
        boolean hasPrevious = false;
        boolean hasNext = false;
        Node previousNode = null;
        Node nextNode = null;
        if (currentNode.getPrevious() != null) {
          hasPrevious = true;
          previousNode = currentNode.getPrevious();
        }
        if (currentNode.getNext() != null) {
          hasNext = true;
          nextNode = currentNode.getNext();
        }
        if (hasPrevious) {
          previousNode.setNext(newNode);
          newNode.setPrevious(previousNode);
        }
        if (hasNext) {
          nextNode.setPrevious(newNode);
          newNode.setNext(nextNode);
        }
        if (!hasPrevious) {
          newNode.setPrevious(null);
          head = newNode;
        }
        if (!hasNext) {
          newNode.setNext(null);
        }
        listSize++;
        return null;
      } else {
        return checkItem;
      }
    }
  }

  @Override
  public ReturnObject add(Object item) {
    ReturnObjectImpl checkItem = checkNull(item);
    if (!checkItem.hasError()) {
      Node currentNode = head;
      for (int i = 1; i < listSize; i++) {
        currentNode = currentNode.getNext();
      }
      Node newNode = new Node(item);
      Node previousNode = null;
      previousNode.setNext(newNode);
      newNode.setPrevious(previousNode);
      newNode.setNext(null);
      listSize++;
      return null;
    } else {
      return checkItem;
    }
  }

  //Check index is within range.
  private ReturnObjectImpl checkIndex(int index) {
    if (index < 1 || index > listSize) {
      return new ReturnObjectImpl(ErrorMessage.INDEX_OUT_OF_BOUNDS);
    } else {
      return null;
    }
  }

  //Traverse list to requested index position.
  private ReturnObjectImpl traverseList(int index) {
    Node currentNode = head;
    for (int i = 1; i < index; i++) {
      currentNode = currentNode.getNext();
    }
    return new ReturnObjectImpl(currentNode.getValue());
  }

  //Check that input is not a null object.
  private ReturnObjectImpl checkNull(Object item) {
    if (item == null) {
      return new ReturnObjectImpl(ErrorMessage.INVALID_ARGUMENT);
    } else {
      return new ReturnObjectImpl(item);
    }
  }
}