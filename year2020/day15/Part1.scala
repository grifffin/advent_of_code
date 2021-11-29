import scala.io.Source

object Part1 {
  def main(args: Array[String]): Unit = {
    val filename = "input"

    var seq = Source.fromFile(filename).mkString.trim.split(',').map(_.toInt).toList.reverse
    for(i <- seq.size until 2020) {
      val age = seq.tail.indexOf(seq.head) + 1
      seq = (if(age != -1) age else 0) :: seq
    }
    println(seq.head)
  }
}
