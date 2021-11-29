import scala.io.Source

object Part2 {
  def main(args: Array[String]): Unit = {
    val filename = "input"

    var shipX = 0
    var shipY = 0
    var waypX = 10
    var waypY = 1
    for(line <- Source.fromFile(filename).getLines()) {
      val letter = line.charAt(0)
      val number = line.substring(1).toInt
      if(letter == 'N') waypY += number
      else if(letter == 'E') waypX += number
      else if(letter == 'S') waypY -= number
      else if(letter == 'W') waypX -= number
      else if(letter == 'L' || letter == 'R') {
        var dir = (((letter.toInt - 79) / 3) * number) % 360
        if(dir < 0) dir += 360
        if(dir == 90) {
          val temp = waypX
          waypX = waypY
          waypY = -temp
        } else if(dir == 180) {
          waypY *= -1
          waypX *= -1
        } else if(dir == 270) {
          val temp = waypX
          waypX = -waypY
          waypY = temp
        }
      } else if(letter == 'F') {
        shipX += waypX * number
        shipY += waypY * number
      }
    }
    println(shipX.abs + shipY.abs)
  }
}
