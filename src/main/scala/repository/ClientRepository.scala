package repository

import config.Mongo
import entities.Entity.Client
import org.bson.types.ObjectId
import org.mongodb.scala.Completed
import org.mongodb.scala.model.Filters.equal
import org.mongodb.scala.model.Projections._

import scala.concurrent.Future

object ClientRepository {

  val clients = Mongo.collectionClients

  def insert(client: Client): Future[Completed] = {
    clients.insertOne(client).toFuture()
  }

  def findById(clientId: String): Future[Client] = {
    clients.find(equal("_id", new ObjectId(clientId))).first().toFuture()
  }
}
