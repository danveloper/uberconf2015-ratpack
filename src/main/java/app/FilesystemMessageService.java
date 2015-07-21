package app;

import ratpack.exec.ExecControl;
import ratpack.rx.RxRatpack;
import rx.Observable;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Callable;

import static ratpack.util.Exceptions.uncheck;

/**
 * A {@link app.MessageService} implementation that reads messages from the filesystem
 */
public class FilesystemMessageService implements MessageService {
  private final Path messages;

  public FilesystemMessageService(Path messages) {
    this.messages = messages;
  }

  @Override
  public Observable<String> getDefaultMessage() {
    return getAllMessages().filter(message -> "default".equals(message.getName())).map(Message::getContent);
  }

  @Override
  public Observable<String> getUberConfMessage() {
    return getAllMessages().filter(message -> "uberconf".equals(message.getName())).map(Message::getContent);
  }

  @Override
  public Observable<String> getSpringOneMessage() {
    return getAllMessages().filter(message -> "springone".equals(message.getName())).map(Message::getContent);
  }

  @Override
  public Observable<Message> getAllMessages() {
    return
        // make the blocking call to read the messages file
        blocking(() -> Files.readAllBytes(messages))
            // read the bytes into an input stream
            .map(ByteArrayInputStream::new)
            // map the InputStream to a Properties struct
            .map(is -> {
              Properties props = new Properties();
              try {
                props.load(is);
                return props;
              } catch (IOException e) {
                throw uncheck(e);
              }
            })
            // flatMap the entries in the properties file (this is so that we can deal with them individually)
            .flatMap(props -> Observable.from((Iterable<Map.Entry<Object, Object>>) props.entrySet()))
            // translate the properties file entry into a Message object
            .map(entry -> new Message((String) entry.getKey(), (String) entry.getValue()));
  }

  private <T> Observable<T> blocking(Callable<T> closure) {
    // converts the Promise returned from the blocking call into an Observable
    return RxRatpack.observe(ExecControl.current().blocking(closure));
  }
}
