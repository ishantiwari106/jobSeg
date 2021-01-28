import entities.Entity.Job
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers
import services.JobService

class TestApi extends AnyFunSuite with Matchers{

  val clientId = "600d8ec8b2825c699f488910"

  test("Should create feeds") {
    assert(JobService.createFeeds(clientId))
  }

  test("Should create a job") {
    val jobs: List[Job] = List(new Job("Test Job", None, "Joveo", "Kanpur", "UttarPradesh",
    "India", "Test Job", "https://www.joveo.com/careers", "Test", None, 7,
      "2020-01-28"))
    JobService.createJobs(jobs, clientId)
  }
}
