package app;

import rx.Observable;

/**
 * A service for encapsulating the retrieval of messages.
 */
public interface MessageService {

  /**
   * @return An observable of the default message
   */
  Observable<String> getDefaultMessage();

  /**
   * @return An observable of the UberConf message
   */
  Observable<String> getUberConfMessage();

  /**
   * @return An observable of the SpringOne message
   */
  Observable<String> getSpringOneMessage();

  /**
   * @return Observables of all known messages
   */
  Observable<Message> getAllMessages();
}
