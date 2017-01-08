public class ReturnObjectImpl implements ReturnObject {
  private Object object = null;
  private ErrorMessage errorMessage = ErrorMessage.NO_ERROR;
  
  public ReturnObjectImpl(Object object) {
    this.object = object;
  }
  
  public ReturnObjectImpl(ErrorMessage error) {
    this.errorMessage = error;
  }
  
  @Override
  public boolean hasError() {
    if (errorMessage == ErrorMessage.NO_ERROR) {
      return false;
    } else {
      return true;
    }
  }
  
  @Override
  public ErrorMessage getError() {
    return errorMessage;
  }
  
  @Override
  public Object getReturnValue() {
    return object;
  }
}