import scala.io.Source

object Part1 {
  def main(args: Array[String]): Unit = {
    val filename = "input"

    var seatingArea = Source.fromFile(filename).getLines().toList.map((s:String) => s.toCharArray).toArray

    def look(x: Int, y: Int, horiz: Int, vert: Int): Char = {
      var newX = x
      var newY = y
      var result: Char = 'g'
      while(result == 'g') {
        newX += horiz
        newY += vert
        if(newX<0 || newY<0 || newX>=seatingArea.length || newY>=seatingArea(0).length) result = '.'
        else if(seatingArea(newX)(newY) != '.') result = seatingArea(newX)(newY)
      }
      result
    }

    def checkSeat(x: Int, y: Int): Char = {
      val seat = seatingArea(x)(y)
      var result: Char = seat
      if(seat == '.') result = '.'
      else {
        var filledSeats = 0
        for(i <- -1 to 1; j <- -1 to 1) {
          if((i != 0 || j != 0) && look(x, y, i, j) == '#') {
            filledSeats += 1
          }
        }
        if(seat == 'L') {
          if(filledSeats == 0) result = '#'
          else result = 'L'
        } else {
          if(filledSeats >= 5) {
            result = 'L'
          }
          else result = '#'
        }
      }
      result
    }

    def deepEquals(arr1: Array[Array[Char]], arr2: Array[Array[Char]]): Boolean = {
      var equals = true
      for(i <- 0 until seatingArea.length; j <- 0 until seatingArea(0).length) {
        if(arr1(i)(j) != arr2(i)(j)) equals = false
      }
      return equals
    }

    var done = false
    var count = 0
    while(!done) {
      var newSeats = Array.ofDim[Char](seatingArea.length, seatingArea(0).length)
      for(i <- 0 until seatingArea.length; j <- 0 until seatingArea(0).length) {
        val char = checkSeat(i, j)
        newSeats(i)(j) = char
        if(char == '#') count += 1
        
      }
      if(deepEquals(seatingArea, newSeats)) {
        done = true
      }
      else count = 0
      seatingArea = newSeats
    }
    println(count)
  }
}
