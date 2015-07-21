package app;

/**
 * A model used to represent a keyed content
 */
public class Message {
  private final String name;
  private final String content;

  public Message(String name, String content) {
    this.name = name;
    this.content = content;
  }

  /**
   * @return the name of the content
   */
  public String getName() {
    return name;
  }

  /**
   * @return the content of the content
   */
  public String getContent() {
    return content;
  }
}
