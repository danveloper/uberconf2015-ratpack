import app.FilesystemMessageService
import app.MessageService
import ratpack.rx.RxRatpack

import java.nio.file.Paths

import static ratpack.groovy.Groovy.ratpack

ratpack {
  bindings {
    // initialize Ratpack's integration with RxJava
    RxRatpack.initialize()

    // bind the MessageService interface to the FilesystemMessageService implementation
    bindInstance(MessageService, new FilesystemMessageService(Paths.get("filesystem/messages.properties").toAbsolutePath()))
  }
  handlers {
    get { MessageService messageService ->
      def ua = request.headers.'User-Agent'
      if (ua == 'uberconf') {
        // converts the Observable back into a Ratpack promise, which
        // we can safely send through the rendering infrastructure
        render messageService.getUberConfMessage().promiseSingle()
      } else if (ua == 'springone') {
        // converts the Observable back into a Ratpack promise, which
        // we can safely send through the rendering infrastructure
        render messageService.getSpringOneMessage().promiseSingle()
      } else {
        // converts the Observable back into a Ratpack promise, which
        // we can safely send through the rendering infrastructure
        render messageService.getDefaultMessage().promiseSingle()
      }
    }

    get("all") { MessageService messageService ->
      render messageService
          .getAllMessages()
          .map { msg -> msg.content }
          .toList()
          .map { msgs -> msgs.join(',') }
          .promiseSingle()
    }
  }
}
