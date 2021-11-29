import scala.io.Source

object Part1 {
  def main(args: Array[String]): Unit = {
    val filename = "input"
    val file = Source.fromFile(filename).getLines().mkString("\n")
    val groups = file.split("\n\n")
    var sum = 0
    for(group <- groups) {
      sum += group.replaceAll("\n", "").toList.distinct.length
    }
    println(sum)
  }
}
