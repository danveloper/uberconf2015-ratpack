import app.Messages
import static ratpack.groovy.Groovy.ratpack

ratpack {
  bindings {
    bindInstance(new Messages("Hello World!"))
  }
  handlers {
    get { Messages messages ->
      render messages.getDefaultMessage()
    }
  }
}
