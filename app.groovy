@GrabResolver(name='jfrog', root='http://oss.jfrog.org/oss-snapshot-local')
@Grab('io.ratpack:ratpack-groovy:0.9.19-SNAPSHOT')

import static ratpack.groovy.Groovy.ratpack

ratpack {
  handlers {

    when { request.headers.'User-Agent' == "demo" }
         {
           get {
             render "Hello Demo!"
           } 
         }

    all {
      render "Hello World!"
    }
  }
}
