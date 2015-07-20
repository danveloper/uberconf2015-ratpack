@Grab('io.ratpack:ratpack-groovy:0.9.18')

import static ratpack.groovy.Groovy.ratpack

ratpack {
  handlers {
    prefix("api") {
      get {
        render "Hello World!"
      }
    }
  }
}
