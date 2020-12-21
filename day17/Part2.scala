import scala.io.Source
import scala.collection.mutable.HashSet

object Part2 {
  def main(args: Array[String]): Unit = {
    val filename = "input"

    var game: HashSet[(Int, Int, Int, Int)] = HashSet()

    def countNeighbors(w: Int, x: Int, y: Int, z: Int): Int = {
      var count = 0
      for(h <- -1 to 1) {
        for(i <- -1 to 1) {
          for(j <- -1 to 1) {
            for(k <- -1 to 1) {
              if((h != 0 || i != 0 || j != 0 || k != 0) && game.contains((w+h, x+i, y+j, z+k))) {
                count += 1
              }
            }
          }
        }
      }
      return count
    }

    for((line, y) <- Source.fromFile(filename).getLines().zipWithIndex) {
      for((char, x) <- line.toCharArray.zipWithIndex) {
        // yeah it needs double parens, eat my shorts
        if(char == '#') game += ((0, x, y, 0))
      }
    }

    var newGame: HashSet[(Int, Int, Int, Int)] = HashSet()
    for(cycle <- 1 to 6) {
      for(w <- 0 - (cycle * 2) until 1 + (cycle * 2)) {
        for(x <- 0 - (cycle * 2) until 8 + (cycle * 2)) {
          for(y <- 0 - (cycle * 2) until 8 + (cycle * 2)) {
            for(z <- 0 - (cycle * 2) until 1 + (cycle * 2)) {
              val count = countNeighbors(w, x, y, z)
              if(game.contains((w, x, y, z)) && (count == 2 || count == 3)) newGame += ((w, x, y, z))
              else if(!game.contains((w, x, y, z)) && count == 3) newGame += ((w, x, y, z))
            }
          }
        }
      }
      game = newGame
      newGame = HashSet()
    }
    println(game.size)
  }
}
