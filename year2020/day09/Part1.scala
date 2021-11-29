import scala.io.Source
import scala.collection.mutable.HashSet

object Part1 {
  def checkSum(set: HashSet[Long], sum: Long): Boolean = {
    for(subset <- set.subsets(2)) {
      if(subset.sum == sum) return true
    }
    return false
  }

  def main(args: Array[String]): Unit = {
    val filename = "input"
    val file = Source.fromFile(filename).getLines().toList
    var prev25 = new HashSet[Long]
    for(i <- 0 until file.length) {
      val num = file(i).toLong
      if(i > 24) {
        if(!checkSum(prev25, num)) println(num)
        prev25 -= file(i - 25).toLong
      }
      prev25 += num
    }
  }
}
