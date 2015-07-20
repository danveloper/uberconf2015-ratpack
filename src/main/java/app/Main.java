package app;

import ratpack.server.RatpackServer;

import static app.MessageConstants.*;

public class Main {

  public static void main(String[] args) throws Exception {
    RatpackServer.start(spec -> spec.
      handlers(chain -> chain
        .all(ctx -> ctx.render(HELLO_WORLD))
      )
    );
  }
}
