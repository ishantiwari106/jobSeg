package services

import entities.Entity.{Job, Rule}
import org.bson.conversions.Bson
import org.bson.types.ObjectId
import org.mongodb.scala.model.Filters
import org.mongodb.scala.model.Filters.{equal, gt, gte, lt, lte}
import repository.{ClientRepository, JobGroupRepository, JobRepository, PublisherRepository, RuleRepository}
import spray.json.enrichAny
import utils.JsonSupport

import java.io.{File, FileWriter, PrintWriter}
import scala.concurrent.Await
import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

object JobService extends JsonSupport{

  val path = "/Users/ishantiwari/practice_files/"

  def createJobs(jobs: List[Job], clientId: String) : Unit = {
    val client = Await.result(ClientRepository.findById(clientId), 2 seconds)
    checkRequestValidity(client)
    val jobsToSave = jobs.map(j => j.copy(clientId = Some(clientId)))
    jobsToSave.foreach(job => Await.result(JobRepository.insert(job), 2 second))
  }

  def createFeeds(clientId: String): Unit = {
    val jobGroups = Await.result(JobGroupRepository.findByClientId(clientId), 2 seconds)
    checkRequestValidity(jobGroups)
    for(job <- jobGroups) {
      val ruleIds = job.ruleIds.map(j => new ObjectId(j))
      val rules = Await.result(RuleRepository.findByIdIn(ruleIds), 1 second)
      val publisherIds = job.publisherIds.map(p => new ObjectId(p))
      val publishers = Await.result(PublisherRepository.findByIdIn(publisherIds), 1 second)
      val conditions = rules.map(r => getCondition(r))
      val condition = job.operator match {
        case "AND" => Filters.and(conditions:_*)
        case "OR" => Filters.or(conditions:_*)
      }
      val jobs = Await.result(JobRepository.findByCondition(condition), 2 seconds)
      publishers.foreach(p => updateOutboundFeed(p.outboundFileName, jobs))
    }
  }

  def checkRequestValidity(param: Any): Unit = {
    if(param == null) {
      throw new RuntimeException("Invalid Client")
    }
  }

  def getCondition(rule: Rule): Bson = {
    rule.operator match {
      case "equal" => equal(rule.field, rule.value)
      case "gte" => gte(rule.field, rule.value)
      case "lte" => lte(rule.field, rule.value)
      case "gt" => gt(rule.field, rule.value)
      case "lt" => lt(rule.field, rule.value)
    }
  }

  def updateOutboundFeed(outboundFileName: String, jobs: Seq[Job]): Unit = {
    val file = path+outboundFileName

    val writer = new FileWriter(new File(file), true)
    writer.write(jobs.toJson.prettyPrint)
    writer.close()
  }
}

/*
* db.getCollection("jobGroups").insertOne(
{
    "clientId" : "600d8ec8b2825c699f488910",
    "ruleIds" : [ "60111f2ff76d6f9fbb1ec1c1", "60111fa4f76d6f9fbb1ec1c2" ],
    "publisherIds" : [ "600e3cf066ce9319faa18a04", "600e3cfd66ce9319faa18a05", "600e3d1766ce9319faa18a06" ],
    "operator" :"AND"
})
* */