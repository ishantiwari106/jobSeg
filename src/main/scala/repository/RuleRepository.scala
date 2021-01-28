package repository

import config.Mongo
import entities.Entity.Rule
import org.mongodb.scala.bson.ObjectId
import org.mongodb.scala.model.Filters.in

import scala.concurrent.Future

object RuleRepository {

  val rules = Mongo.collectionRule

  def findByIdIn(ids: List[ObjectId]): Future[Seq[Rule]] = {
    rules.find(in("_id", ids:_*)).toFuture()
  }
}
