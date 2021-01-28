package repository

import config.Mongo
import entities.Entity.Job
import org.bson.conversions.Bson
import org.mongodb.scala.Completed
import org.mongodb.scala.model.Projections.exclude

import scala.concurrent.Future

object JobRepository {

  val jobs = Mongo.collectionJob

  def insert(job: Job): Future[Completed] = {
    jobs.insertOne(job).toFuture()
  }

  def findAll(): Future[Seq[Job]] = {
    jobs.find().toFuture()
  }

  def findByCondition(condition: Bson): Future[Seq[Job]] = {
    jobs.find(condition).projection(exclude("_id")).toFuture()
  }
}
