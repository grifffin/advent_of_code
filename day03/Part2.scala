import scala.io.Source

object Part2 {
  def main(args: Array[String]): Unit = {
    val filename = "input"
    val lines = Source.fromFile(filename).getLines().toList.zipWithIndex
    var product:Long = 1
    for(slope <- 1 :: 3 :: 5 :: 7 :: Nil) {
      var numTrees = 0

      for ((line, lineNum) <- lines) {
        if(line.charAt((lineNum * slope) % line.length) == '#') numTrees += 1
      }
      product *= numTrees
    }
    val slope = 2
    var numTrees = 0
    for ((line, lineNum) <- lines) {
      if(lineNum%slope == 0 && line.charAt((lineNum / 2) % line.length) == '#') numTrees += 1
    }
    product *= numTrees
    println(product)
  }
}
