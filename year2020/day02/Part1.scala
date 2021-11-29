import scala.io.Source

object Part1 {
  def main(args: Array[String]): Unit = {
    val filename = "input"

    var numValid = 0

    for (line <- Source.fromFile(filename).getLines()) {
      val parts = line.split(" ")
      val range = parts(0).split("-")
      val letter = parts(1).charAt(0)
      val instances = parts(2).count(_ == letter)
      if(range(0).toInt <= instances && range(1).toInt >= instances) {
        numValid += 1
      }
    }

    println(numValid)
  }
}
