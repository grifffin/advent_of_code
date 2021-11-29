import scala.io.Source

object Part2 {

  def main(args: Array[String]): Unit = {
    val filename = "input"
    val seatsFound = Array.fill[Boolean](1024)(false)

    for (line <- Source.fromFile(filename).getLines()) {
      val binaryLine = line.map(c => if (c == 'F' || c == 'L') '0' else '1')
      val seatId = Integer.parseInt(binaryLine, 2)
      seatsFound(seatId) = true
    }

    for (i <- 1 until 1023) {
      if (!seatsFound(i) && seatsFound(i-1) && seatsFound(i+1)) println(i)
    }
  }
}
