import scala.io.Source

object Part2 {
  def main(args: Array[String]): Unit = {
    val filename = "input"

    var numValid = 0

    for (line <- Source.fromFile(filename).getLines()) {
      val parts = line.split(" ")
      val positions = parts(0).split("-")
      val letter = parts(1).charAt(0)
      val checkPos0 = parts(2).charAt(positions(0).toInt - 1) == letter 
      val checkPos1 = parts(2).charAt(positions(1).toInt - 1) == letter
      if((checkPos0 || checkPos1) && !(checkPos0 && checkPos1)) {
        numValid += 1
      }
    }

    println(numValid)
  }
}
