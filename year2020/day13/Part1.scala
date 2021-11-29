import scala.io.Source

object Part1 {
  def main(args: Array[String]): Unit = {
    val filename = "input"

    val lines = Source.fromFile(filename).getLines().toList
    val departure = lines(0).toInt
    val busIds = lines(1).replaceAll("x,", "").split(',').map(_.toInt)
    val waitTimes = busIds.map(id => id - (departure % id))
    println(busIds(waitTimes.indexOf(waitTimes.min)) * waitTimes.min)
  }
}
