@GrabResolver(name='jfrog', root='http://oss.jfrog.org/oss-snapshot-local')
@Grab('io.ratpack:ratpack-groovy:0.9.19-SNAPSHOT')

import static ratpack.groovy.Groovy.ratpack

ratpack {
  handlers {
    all {
      byContent {
        json {
         render '{ "message": "Gave you JSON" }'
        }
        xml {
          render '<message>Gave you XML</message>'
        }
        html {
          render '<html><body>Gave you HTML</body></html>'
        }
        plainText {
          render 'Gave you plain text'
        }
        noMatch {
          render 'Unsupported content type'
        }
      }
    }
  }
}
