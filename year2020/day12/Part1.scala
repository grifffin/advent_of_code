import scala.io.Source

object Part1 {
  def main(args: Array[String]): Unit = {
    val filename = "input"

    var x = 0
    var y = 0
    var dir = 0
    for(line <- Source.fromFile(filename).getLines()) {
      val letter = line.charAt(0)
      val number = line.substring(1).toInt
      if(letter == 'N') y += number
      else if(letter == 'E') x += number
      else if(letter == 'S') y -= number
      else if(letter == 'W') x -= number
      else if(letter == 'L' || letter == 'R') {
        dir = (dir + (((letter.toInt - 79) / 3) * number)) % 360
        if(dir < 0) dir += 360
      } else if(letter == 'F') {
        x -= (((dir - 90) / 90) % 2) * number
        y += (((dir - 180) / 90) % 2) * number
      }
    }
    println(x.abs + y.abs)
  }
}
