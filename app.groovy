@GrabResolver(name='jfrog', root='http://oss.jfrog.org/oss-snapshot-local')
@Grab('io.ratpack:ratpack-groovy:0.9.19-SNAPSHOT')

import static ratpack.groovy.Groovy.ratpack

ratpack {
  handlers {
    path("api") {
      byMethod {
        get {
          render "Hello API!"
        }
      }
    }
    all {
      byMethod {
        get {
          render "Hello World"
        }
        post {
          def body = request.body.text
          render "Got your text: $body"
        }
      }
    }
  }
}
