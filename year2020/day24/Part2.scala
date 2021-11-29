import scala.io.Source
import scala.collection.mutable.HashSet

object Part2 {
  def main(args: Array[String]): Unit = {
    val filename = "input"
    val lines = Source.fromFile(filename).getLines().toList
    var blackTiles = HashSet[(Int, Int)]()
    for(line_ <- lines) {
      var line = line_
      val nes = line.toSeq.sliding(2).map(_.unwrap).count(_ == "ne")
      line = line.replaceAll("ne", "")
      val nws = line.toSeq.sliding(2).map(_.unwrap).count(_ == "nw")
      line = line.replaceAll("nw", "")
      val ses = line.toSeq.sliding(2).map(_.unwrap).count(_ == "se")
      line = line.replaceAll("se", "")
      val sws = line.toSeq.sliding(2).map(_.unwrap).count(_ == "sw")
      line = line.replaceAll("sw", "")
      val es = line.count(_ == 'e')
      val ws = line.count(_ == 'w')
      val x = es - ws + nes - sws
      val y = nws - ses + nes - sws
      if(blackTiles.contains((x, y)))
        blackTiles -= ((x, y))
      else
        blackTiles += ((x, y))
    }

    def countSurroundingBlackTiles(x: Int, y: Int): Int = {
      var count = 0
      if(blackTiles.contains((x+1, y+1))) count += 1
      if(blackTiles.contains((x, y+1))) count += 1
      if(blackTiles.contains((x+1, y))) count += 1
      if(blackTiles.contains((x-1, y-1))) count += 1
      if(blackTiles.contains((x, y-1))) count += 1
      if(blackTiles.contains((x-1, y))) count += 1
      return count
    }

    var newConfig = HashSet[(Int, Int)]()
    for(i <- 1 to 100) {
      val minx = blackTiles.map(_._1).min
      val maxx = blackTiles.map(_._1).max
      val miny = blackTiles.map(_._2).min
      val maxy = blackTiles.map(_._2).max
      for(x <- minx - 1 to maxx + 1) {
        for(y <- miny - 1 to maxy + 1) {
          val count = countSurroundingBlackTiles(x, y)
          if(blackTiles.contains((x, y)) && count != 0 && count <= 2) newConfig += ((x, y))
          if(!blackTiles.contains((x, y)) && count == 2) newConfig += ((x, y))
        }
      }
      blackTiles = newConfig
      newConfig = HashSet[(Int, Int)]()
    }
    println(blackTiles.size)
  }
}
