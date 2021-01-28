package endpoints

import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._
import entities.Entity.Job
import services.JobService
import utils.JsonSupport

import scala.concurrent.ExecutionContext

class Endpoint (implicit val ec: ExecutionContext) extends JsonSupport{
  def routes(): Route = {
    pathPrefix("job") {
      post {
        path(Segment) { clientId =>
        pathEnd{
          entity(as[List[Job]]) { jobs =>
            JobService.createJobs(jobs, clientId)
            complete("Created Job")
          }
        }
      }
      } ~
      get {
        path(Segment / "feeds") { clientId =>
          JobService.createFeeds(clientId)
          complete("Done")
        }
      }
    }
  }
}
