import scala.io.Source
import scala.collection.mutable.HashSet

object Part1 {
  def main(args: Array[String]): Unit = {
    val filename = "input"
    val lines = Source.fromFile(filename).getLines().toList
    val blackTiles = HashSet[(Int, Int)]()
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
    println(blackTiles.size)
  }
}
