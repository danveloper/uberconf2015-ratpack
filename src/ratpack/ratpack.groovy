import app.Messages
import java.nio.file.Paths;

import static ratpack.groovy.Groovy.ratpack

ratpack {
  bindings {
    def path = Paths.get("filesystem/messages.properties").toAbsolutePath()
    println path
    bindInstance(new Messages(path))
  }
  handlers {
    get { Messages messages ->
      def ua = request.headers.'User-Agent'
      if (ua == 'uberconf') {
        render messages.getUberConfMessage()
      } else if (ua == 'springone') {
        render messages.getSpringOneMessage()
      } else {
        render messages.getDefaultMessage()
      }
    }
  }
}
