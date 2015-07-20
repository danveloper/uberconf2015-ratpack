import app.Messages

import static ratpack.registry.Registry.single
import static ratpack.groovy.Groovy.ratpack

ratpack {
  bindings {
    bindInstance(new Messages("Hello World!"))
  }
  handlers {
    all {
      def defaultMessage
      switch(request.headers.'User-Agent') { 
        case 'uberconf':
          defaultMessage = "Hello UberConf!"
          break
        case 'springone':
          defaultMessage = "Hello SpringOne2gx!"
          break
      }
      if (defaultMessage) {
        def messages = new Messages(defaultMessage)
        next(single(messages))
      } else {
        next()
      }
    }

    get { Messages messages ->
      render(messages.getDefaultMessage())
    }
  }
}
