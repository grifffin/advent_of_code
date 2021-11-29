import scala.io.Source
import scala.collection.mutable.HashSet

object Part1 {
  def main(args: Array[String]): Unit = {
    val filename = "input"
    var nums = new HashSet[Int]

    var prev = 0
    var oneDiffs = 0
    var threeDiffs = 0
    for (line <- Source.fromFile(filename).getLines().toList.map((s:String) => s.toInt).sorted) {
      if(line - prev == 1) oneDiffs += 1
      else if(line - prev == 3) threeDiffs += 1
      prev = line
    }
    threeDiffs += 1
    println(oneDiffs * threeDiffs)
  }
}
