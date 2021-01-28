package repository

import config.Mongo
import entities.Entity.{Publisher}
import org.bson.types.ObjectId
import org.mongodb.scala.model.Filters.in

import scala.concurrent.Future

object PublisherRepository {

  val publishers = Mongo.collectionPublisher

  def findByIdIn(ids: List[ObjectId]): Future[Seq[Publisher]] = {
    publishers.find(in("_id", ids:_*)).toFuture()
  }
}
