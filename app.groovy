@Grab('io.ratpack:ratpack-groovy:0.9.18')

import static ratpack.groovy.Groovy.ratpack

ratpack {
  handlers {
    get("foo") {
      render "Hello Foo!"
    }
    post("bar") {
      render "Hello Bar!"
    }
    put("baz") {
      render "Hello Baz!"
    }
    patch("qux") {
      render "Hello Qux!"
    }
    delete("quux") {
      render "Hello Quux!"
    }
  }
}
