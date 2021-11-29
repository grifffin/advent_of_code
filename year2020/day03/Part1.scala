import scala.io.Source

object Part1 {
  def main(args: Array[String]): Unit = {
    val filename = "input"

    var numTrees = 0
    val slope = 3

    for ((line, lineNum) <- Source.fromFile(filename).getLines().toList.zipWithIndex) {
      if(line.charAt((lineNum * slope) % line.length) == '#') numTrees += 1
    }

    println(numTrees)
  }
}
