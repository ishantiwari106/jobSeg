package config

import entities.Entity.{Client, Job, JobGroup, Publisher, Rule}
import org.bson.codecs.configuration.CodecRegistries.{fromProviders, fromRegistries}
import org.bson.codecs.configuration.CodecRegistry
import org.mongodb.scala.MongoClient.DEFAULT_CODEC_REGISTRY
import org.mongodb.scala.{MongoClient, MongoCollection, MongoDatabase}
import org.mongodb.scala.bson.codecs.Macros._

object Mongo {
  val codecRegistry: CodecRegistry = fromRegistries(fromProviders(classOf[Job], classOf[Rule], classOf[Publisher],
    classOf[Client], classOf[JobGroup]), DEFAULT_CODEC_REGISTRY )
  val mongoClient: MongoClient = MongoClient()
  val database: MongoDatabase = mongoClient.getDatabase("admin").withCodecRegistry(codecRegistry)
  val collectionJob: MongoCollection[Job] = database.getCollection("jobs")
  val collectionRule: MongoCollection[Rule] = database.getCollection("rules")
  val collectionPublisher: MongoCollection[Publisher] = database.getCollection("publishers")
  val collectionClients: MongoCollection[Client] = database.getCollection("clients")
  val collectionJobGroups: MongoCollection[JobGroup] = database.getCollection("jobGroups")
}

