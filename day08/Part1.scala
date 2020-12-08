import scala.io.Source
import scala.collection.mutable.HashSet

object Part1 {
  def main(args: Array[String]): Unit = {
    val filename = "input"
    val file = Source.fromFile(filename).getLines().toList
    var acc = 0
    var currline = 0
    var visited = new HashSet[Int]
    while(!visited.contains(currline)) {
      visited += currline
      val line = file(currline).split(" ")
      if(line(0) == "acc") {
        acc += line(1).toInt
        currline += 1
      } else if(line(0) == "jmp") {
        currline += line(1).toInt
      } else if(line(0) == "nop") {
        currline += 1
      }
    }
    println(acc)
  }
}
