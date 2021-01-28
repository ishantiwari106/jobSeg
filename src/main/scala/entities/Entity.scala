package entities

import org.bson.types.ObjectId

object Entity {

  case class Client(_id: ObjectId, name: String)

  case class Job(title: String, clientId: Option[String] = None, company: String, city: String, state: String,
                 country: String, description: String, url: String, category: String, department: Option[String],
                 referencenumber: Int, date: String)

  case class JobGroup(_id: ObjectId, clientId: String, ruleIds: List[String], publisherIds: List[String], operator: String)

  case class Publisher(_id: ObjectId, name: String, outboundFileName: String)

  case class Rule(_id: ObjectId, field: String, operator: String, value: String)
}
