package utils

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import entities.Entity.{Job}
import spray.json.{DefaultJsonProtocol, PrettyPrinter}

trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol{
  implicit val printer = PrettyPrinter
  implicit val jobFormat = jsonFormat12(Job)
}
