import scala.io.Source
import scala.collection.mutable.HashMap

object Part2 {
  def main(args: Array[String]): Unit = {
    val filename = "input"

    var seq = Source.fromFile(filename).mkString.trim.split(',').map(_.toInt)
    var map = new HashMap[Int, Int]()
    for ((el, i) <- seq.slice(0, seq.size - 1).zipWithIndex) {
      map += (el -> i)
    }
    var last = seq.last
    for(i <- seq.size until 30000000) {
      val next = if(map.contains(last)) i - map(last) - 1 else 0
      map(last) = i - 1
      last = next
    }
    println(last)
  }
}
