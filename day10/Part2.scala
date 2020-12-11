import scala.io.Source
import scala.collection.mutable.HashMap

object Part2 {
  
  def main(args: Array[String]): Unit = {
    val filename = "input"
    val nums = 0 :: Source.fromFile(filename).getLines().toList.map((s:String) => s.toInt).sorted

    var arrangements = new HashMap[Int, Long]

    def arrange(i: Int): Long = {
      var acc: Long = 0
      if(arrangements.keySet.exists(_ == i)) return arrangements(i)
      if(i == nums.length - 1) {
        acc = 1
      }
      else {
        for(j <- i + 1 to i + 3) {
          if(j < nums.length && nums(j) - nums(i) <= 3) {
            acc += arrange(j)
          }
        }
      }
      arrangements += i -> acc
      return acc
    }

    println(arrange(0))
  }
}
