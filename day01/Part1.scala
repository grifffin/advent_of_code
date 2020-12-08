import scala.io.Source
import scala.collection.mutable.HashSet

object Part1 {
  def main(args: Array[String]): Unit = {
    val filename = "input"
    var nums = new HashSet[Int]

    for (line <- Source.fromFile(filename).getLines()) {
      val lineNum = line.toInt
      if (nums.contains(2020 - lineNum)) {
        println(2020 - lineNum, lineNum)
        println((2020 - lineNum) * lineNum)
      }
      nums += lineNum
    }
  }
}
