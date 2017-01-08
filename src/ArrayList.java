public class ArrayList implements List {
  private Object[] array;
  final static int DEFAULT_SIZE = 10;
  
  //Constructor for array list of default size.
  public ArrayList() {
    array = new Object[DEFAULT_SIZE];
  }
  
  @Override
  public boolean isEmpty() {
    if (array.length == 0) {
      return true;
    } else {
      return false;
    }
  }
  
  @Override
  public int size() {
    return array.length;
  }
  
  @Override
  public ReturnObject get(int index) {
    ReturnObjectImpl indexToCheck = checkIndex(index);
    return indexToCheck;
  }
  
  @Override
  public ReturnObject remove(int index) {
    ReturnObjectImpl indexToCheck = checkIndex(index);
    if (!indexToCheck.hasError()) {
      Object[] transfer = new Object[array.length - 1];
      for (int i = 0; i <= transfer.length; i++) {
        if (i < index) {
          transfer[i] = array[i];
        } else if (i > index) {
          transfer[i - 1] = array[i];
        }
      }
      array = transfer;
    }
    return indexToCheck;
  }
  
  @Override
  public ReturnObject add(int index, Object item) {
    ReturnObjectImpl indexToCheck = checkIndex(index);
    if (indexToCheck.hasError()) {
      return indexToCheck;
    }
    ReturnObjectImpl objectToCheck = checkObject(item);
    if (!objectToCheck.hasError()) {
      Object[] transfer = new Object[array.length + 1];
      for (int i = array.length; i >= 0; i-- ) {
        if (i > index) {
          transfer[i] = array[i - 1];
        } else if (i == index) {
          transfer[i] = item;
        } else if (i < index) {
          transfer[i] = array[i];
        }
      }
      array = transfer;
      return null;
    } else {
      return objectToCheck;
    }
  }
  
  @Override
  public ReturnObject add(Object item) {
    ReturnObjectImpl objectToCheck = checkObject(item);
    if (!objectToCheck.hasError()) {
      Object[] transfer = new Object[array.length + 1];
      for (int i = 0; i < array.length; i++) {
        transfer[i] = array[i];
      }
      transfer[transfer.length - 1] = item;
      array = transfer;
      return null;
    } else {
      return objectToCheck;
    }
  }
  
  private ReturnObjectImpl checkIndex (int indexCheck) {
    if (isEmpty()) {
      return new ReturnObjectImpl(ErrorMessage.EMPTY_STRUCTURE);
    } else if (indexCheck < 0 || indexCheck >= array.length) {
      return new ReturnObjectImpl(ErrorMessage.INDEX_OUT_OF_BOUNDS);
    } else {
      return new ReturnObjectImpl(array[indexCheck]);
    }
  }
  
  private ReturnObjectImpl checkObject (Object objectCheck) {
    if (objectCheck == null) {
      return new ReturnObjectImpl(ErrorMessage.INVALID_ARGUMENT);
    } else {
      return new ReturnObjectImpl(objectCheck);
    }
  }
}