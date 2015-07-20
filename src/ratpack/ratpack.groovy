import static app.MessageConstants.*
import static ratpack.groovy.Groovy.ratpack

ratpack {
  handlers {
    get {
      render HELLO_WORLD
    }
  }
}
