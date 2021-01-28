import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import endpoints.Endpoint

import scala.io.StdIn

object Main extends App {
  implicit val system = ActorSystem("akka-http-rest-server")
  implicit val materializer = ActorMaterializer()
  implicit val ec = system.dispatcher

  val host = "127.0.0.1"
  val port = 8080

  val httpServerFuture = Http().bindAndHandle(new Endpoint().routes, host, port)
  StdIn.readLine()
  httpServerFuture
    .flatMap(_.unbind())
    .onComplete(_ => system.terminate())
}
