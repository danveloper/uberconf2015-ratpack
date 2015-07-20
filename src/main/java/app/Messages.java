package app;

public class Messages {
  private final String defaultMessage;

  public Messages(String defaultMessage) {
    this.defaultMessage = defaultMessage;
  }

  public String getDefaultMessage() { 
    return this.defaultMessage;
  }
}
