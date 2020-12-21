import scala.io.Source

object Part2 {
  def main(args: Array[String]): Unit = {
    val filename = "input"

    val lines = Source.fromFile(filename).getLines().toList
    val busMap = lines(1)
      .replaceAll("x", "-1")
      .split(',')
      .map(_.toInt)
      .zipWithIndex
      .filter(_._1 != -1)
      .map(el => (el._1, el._1 - el._2))
      .toMap

    var solvedFor = 0
    var start = busMap.keys.max + busMap(busMap.keys.max)
    println(busMap.values)
  }
}
