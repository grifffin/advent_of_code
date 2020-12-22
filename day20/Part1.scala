import scala.io.Source
import scala.collection.mutable.HashSet
import scala.collection.mutable.ListBuffer
import annotation.tailrec
import java.lang.Integer.parseInt

object Part1 {
  def main(args: Array[String]): Unit = {
    val filename = "input"

    @tailrec
    def reverse(in: Int, n: Int = 10, out: Int = 0): Int =
      if (n == 0) out
      else reverse(in >>> 1, n - 1, (out << 1) | (in & 1))

    // the rotation occurs after the reflection
    class Tile(val id: Int, val rightEdge: Int, val leftEdge: Int, val upEdge: Int, val downEdge: Int, var rot90: Boolean = false, var rot180: Boolean = false, var reflected: Boolean = false) {
      def matchEdge(edge: Int, direction: String): Boolean = {
        for(i <- 0 until 8) {
          this.rot90 = i % 2 == 1
          this.rot180 = i % 4 > 1
          this.reflected = i > 3
          if(direction == "right" && edge == this.getTrueRight) return true
          else if(direction == "down" && edge == this.getTrueDown) return true
          else if(direction == "left" && edge == this.getTrueLeft) return true
          else if(direction == "up" && edge == this.getTrueUp) return true
        }
        return false
      }

      def getEdges: List[Int] = this.rightEdge :: this.leftEdge :: this.upEdge :: this.downEdge :: Nil

      def getTrueRight: Int = {
        if(!this.rot90 && !this.rot180 && !this.reflected) return this.rightEdge
        else if(this.rot90 && !this.rot180 && !this.reflected) return this.upEdge
        else if(!this.rot90 && this.rot180 && !this.reflected) return this.leftEdge
        else if(this.rot90 && this.rot180 && !this.reflected) return this.downEdge
        else if(!this.rot90 && this.rot180 && this.reflected) return reverse(this.rightEdge)
        else if(this.rot90 && !this.rot180 && this.reflected) return reverse(this.upEdge)
        else if(!this.rot90 && !this.rot180 && this.reflected) return reverse(this.leftEdge)
        else return reverse(this.downEdge)
      }

      def getTrueLeft: Int = {
        if(!this.rot90 && !this.rot180 && !this.reflected) return this.leftEdge
        else if(this.rot90 && !this.rot180 && !this.reflected) return this.downEdge
        else if(!this.rot90 && this.rot180 && !this.reflected) return this.rightEdge
        else if(this.rot90 && this.rot180 && !this.reflected) return this.upEdge
        else if(!this.rot90 && this.rot180 && this.reflected) return reverse(this.leftEdge)
        else if(this.rot90 && !this.rot180 && this.reflected) return reverse(this.downEdge)
        else if(!this.rot90 && !this.rot180 && this.reflected) return reverse(this.rightEdge)
        else return reverse(this.upEdge)
      }

      def getTrueUp: Int = {
        if(!this.rot90 && !this.rot180 && !this.reflected) return this.upEdge
        else if(this.rot90 && !this.rot180 && !this.reflected) return this.leftEdge
        else if(!this.rot90 && this.rot180 && !this.reflected) return this.downEdge
        else if(this.rot90 && this.rot180 && !this.reflected) return this.rightEdge
        else if(!this.rot90 && !this.rot180 && this.reflected) return reverse(this.upEdge)
        else if(this.rot90 && !this.rot180 && this.reflected) return reverse(this.rightEdge)
        else if(!this.rot90 && this.rot180 && this.reflected) return reverse(this.downEdge)
        else return reverse(this.leftEdge)
      }

      def getTrueDown: Int = {
        if(!this.rot90 && !this.rot180 && !this.reflected) return this.downEdge
        else if(this.rot90 && !this.rot180 && !this.reflected) return this.rightEdge
        else if(!this.rot90 && this.rot180 && !this.reflected) return this.upEdge
        else if(this.rot90 && this.rot180 && !this.reflected) return this.leftEdge
        else if(!this.rot90 && !this.rot180 && this.reflected) return reverse(this.downEdge)
        else if(this.rot90 && !this.rot180 && this.reflected) return reverse(this.leftEdge)
        else if(!this.rot90 && this.rot180 && this.reflected) return reverse(this.upEdge)
        else return reverse(this.rightEdge)
      }
    }
    val lines = Source.fromFile(filename).getLines().toList
    var tiles = ListBuffer[Tile]()
    for(i <- 0 until lines.length if i % 12 == 0) {
      val id = lines(i).substring(5, 9).toInt
      val up = lines(i+1).replaceAll("\\.", "0").replaceAll("#", "1")
      val down = lines(i+10).replaceAll("\\.", "0").replaceAll("#", "1")
      val left = lines.slice(i+1, i+11).map(_.charAt(0)).mkString.replaceAll("\\.","0").replaceAll("#", "1")
      val right = lines.slice(i+1, i+11).map(_.charAt(9)).mkString.replaceAll("\\.","0").replaceAll("#", "1")
      tiles += new Tile(id, parseInt(right, 2), parseInt(left, 2), parseInt(up, 2), parseInt(down, 2))
    }
    var product: Long = 1
    for(tilea <- tiles) {
      var matches = 0
      for(edge <- tilea.getEdges) {
        for(tileb <- tiles) {
          if(tilea != tileb && (tileb.getEdges.contains(edge) || tileb.getEdges.map(reverse(_)).contains(edge))) matches += 1
        }
      }
      if(matches == 2) product *= tilea.id
    }
    println(product)
  }
}
