import scala.io.Source

object Part1 {

  def main(args: Array[String]): Unit = {
    val filename = "input"
    var maxId = 0

    for (line <- Source.fromFile(filename).getLines()) {
      val binaryLine = line.map(c => if (c == 'F' || c == 'L') '0' else '1')
      val seatId = Integer.parseInt(binaryLine, 2)
      maxId = if(seatId > maxId) seatId else maxId
    }

    println(maxId)
  }
}
