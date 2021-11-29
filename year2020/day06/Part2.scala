import scala.io.Source

object Part2 {
  def main(args: Array[String]): Unit = {
    val filename = "input"
    val file = Source.fromFile(filename).getLines().mkString("\n")
    val groups = file.split("\n\n")
    var sum = 0
    for(group <- groups) {
      val numMembers = group.count(_ == '\n') + 1
      for(ch <- group.replaceAll("\n", "").toList.distinct) {
        if(numMembers == group.count(_ == ch)) sum += 1
      }
    }
    println(sum)
  }
}
