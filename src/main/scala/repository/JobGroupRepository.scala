package repository

import config.Mongo
import entities.Entity.JobGroup
import org.mongodb.scala.model.Filters.equal

import scala.concurrent.Future

object JobGroupRepository {

  val jobGroups = Mongo.collectionJobGroups


  def findByClientId(clientId: String): Future[Seq[JobGroup]] = {
    jobGroups.find(equal("clientId", clientId)).toFuture()
  }
}
