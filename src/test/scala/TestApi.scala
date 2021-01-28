import org.scalatest._
import services.JobService

class TestApi extends FlatSpec with Matchers{

  val clientId = "600d8ec8b2825c699f488910"

  "Create Feeds" should "create feeds of all publishers" in {
    JobService.createFeeds(clientId)
  }
}
