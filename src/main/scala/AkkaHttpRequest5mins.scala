import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.ContentTypes
import akka.http.scaladsl.model.HttpEntity
import akka.http.scaladsl.model.HttpMethods
import akka.http.scaladsl.model.HttpRequest
import akka.stream.ActorMaterializer

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

object AkkaHttpRequest5mins {

  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()
  import system.dispatcher

  val source =
    """
      |object SimpleApp {
      |  val aField = 2
      |
      |  def aMethod(x: Int) = x + 1
      |
      |  def main(args: Array[String]) = {
      |    println(aMethod(aField))
      |  }
      |}
""".stripMargin

  val request = HttpRequest(
    method = HttpMethods.POST,
    uri = "http://markup.su/api/highlighter",
    entity = HttpEntity(
      ContentTypes.`application/x-www-form-urlencoded`,
      s"source=${
        import java.net.URLEncoder
        URLEncoder.encode(source.trim, "UTF-8")
      }&language=Scala&theme=Sunburst"
    )
  )

  def simpleRequest() = {
    val responseFuture = Http().singleRequest(request)
    responseFuture.flatMap(_.entity.toStrict(2 seconds)).map(_.data.utf8String).foreach(println)
  }

  def main(args: Array[String]): Unit =
    simpleRequest()

}
