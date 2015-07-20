package app;

import ratpack.exec.Promise;
import ratpack.exec.ExecControl;
import java.nio.file.Path;
import java.nio.file.Files;
import java.io.ByteArrayInputStream;
import java.util.Properties;

public class Messages {
  private final Path messages;

  public Messages(Path messages) {
    this.messages = messages;
  }

  public Promise<String> getDefaultMessage() { 
    return getProperties().map(props -> (String)props.get("default"));
  }

  public Promise<String> getUberConfMessage() {
    return getProperties().map(props -> (String)props.get("uberconf"));
  }

  public Promise<String> getSpringOneMessage() {
    return getProperties().map(props -> (String)props.get("springone"));
  }

  private Promise<Properties> getProperties() {
    return getExecControl().blocking(() -> Files.readAllBytes(messages))
            .map(ByteArrayInputStream::new)
            .map(is -> {
              Properties props = new Properties();
              props.load(is);
              return props;
            });
  }

  private ExecControl getExecControl() {
    return ExecControl.current();
  }
}
