package app;

import ratpack.server.RatpackServer;
import ratpack.guice.Guice;

public class Main {

  public static void main(String[] args) throws Exception {
    RatpackServer.start(spec -> spec
      .registry(Guice.registry(b -> b
        .bindInstance(new Messages("Hello World!"))
      ))
      .handlers(chain -> chain
        .get(ctx -> {
          Messages messages = ctx.get(Messages.class);
          ctx.render(messages.getDefaultMessage());
        })
      )
    );
  }
}
