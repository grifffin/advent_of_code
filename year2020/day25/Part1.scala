import scala.io.Source

object Part1 {
  def main(args: Array[String]): Unit = {
    val filename = "input"
    val lines = Source.fromFile(filename).getLines().toList
    val cardkey = lines(0).toInt
    val doorkey = lines(1).toInt
    var cardloop = 0
    var cardvalue = 1
    while(cardvalue != cardkey) {
      cardvalue *= 7
      cardvalue %= 20201227
      cardloop += 1
    }
    var encryption: Long = 1
    for(i <- 1 to cardloop) {
      encryption *= doorkey
      encryption %= 20201227
    }
    println(encryption)
  }
}
