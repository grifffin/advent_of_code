import scala.io.Source
import scala.collection.mutable.HashSet
import scala.collection.mutable.ListBuffer
import annotation.tailrec

object Part1 {
  def main(args: Array[String]): Unit = {
    val filename = "input"

    @tailrec
    def reverse(in: Int, n: Int = 10, out: Int = 0): Int =
      if (n == 0) out
      else reverse(in >>> 1, n - 1, (out << 1) | (in & 1))

    // the rotation occurs after the reflection
    class Tile(val id: Int, val rightEdge: Int, val leftEdge: Int, val upEdge: Int, val downEdge: Int, var rot90: Boolean, var rot180: Boolean, var reflected: Boolean) {
      def matchEdge(edge: Int, direction: String): Boolean {
        for(i <- 0 until 8) {
          this.rot90 = i % 2 == 1
          this.rot180 = i % 4 > 1
          this.reflected = i > 3
          if(direction == "right" && edge == this.getTrueRight) return true
          else if(direction == "down" && edge == this.getTrueDown) return true
          else if(direction == "left" && edge == this.getTrueLeft) return true
          else if(direction == "up" && edge == this.getTrueUp) return true
        }
      }

      def getTrueRight(): Int = {
        if(!this.rot90 && !this.rot180 && !this.reflected) return this.rightEdge
        else if(this.rot90 && !this.rot180 && !this.reflected) return this.upEdge
        else if(!this.rot90 && this.rot180 && !this.reflected) return this.leftEdge
        else if(this.rot90 && this.rot180 && !this.reflected) return this.downEdge
        else if(!this.rot90 && this.rot180 && this.reflected) return reverse(this.rightEdge)
        else if(this.rot90 && !this.rot180 && this.reflected) return reverse(this.upEdge)
        else if(!this.rot90 && !this.rot180 && this.reflected) return reverse(this.leftEdge)
        else if(this.rot90 && this.rot180 && this.reflected) return reverse(this.downEdge)
      }

      def getTrueLeft(): Int = {
        if(!this.rot90 && !this.rot180 && !this.reflected) return this.leftEdge
        else if(this.rot90 && !this.rot180 && !this.reflected) return this.downEdge
        else if(!this.rot90 && this.rot180 && !this.reflected) return this.rightEdge
        else if(this.rot90 && this.rot180 && !this.reflected) return this.upEdge
        else if(!this.rot90 && this.rot180 && this.reflected) return reverse(this.leftEdge)
        else if(this.rot90 && !this.rot180 && this.reflected) return reverse(this.downEdge)
        else if(!this.rot90 && !this.rot180 && this.reflected) return reverse(this.rightEdge)
        else if(this.rot90 && this.rot180 && this.reflected) return reverse(this.upEdge)
      }

      def getTrueUp(): Int = {
        if(!this.rot90 && !this.rot180 && !this.reflected) return this.upEdge
        else if(this.rot90 && !this.rot180 && !this.reflected) return this.leftEdge
        else if(!this.rot90 && this.rot180 && !this.reflected) return this.downEdge
        else if(this.rot90 && this.rot180 && !this.reflected) return this.rightEdge
        else if(!this.rot90 && !this.rot180 && this.reflected) return reverse(this.upEdge)
        else if(this.rot90 && !this.rot180 && this.reflected) return reverse(this.rightEdge)
        else if(!this.rot90 && this.rot180 && this.reflected) return reverse(this.downEdge)
        else if(this.rot90 && this.rot180 && this.reflected) return reverse(this.leftEdge)
      }

      def getTrueDown(): Int = {
        if(!this.rot90 && !this.rot180 && !this.reflected) return this.downEdge
        else if(this.rot90 && !this.rot180 && !this.reflected) return this.rightEdge
        else if(!this.rot90 && this.rot180 && !this.reflected) return this.upEdge
        else if(this.rot90 && this.rot180 && !this.reflected) return this.leftEdge
        else if(!this.rot90 && !this.rot180 && this.reflected) return reverse(this.downEdge)
        else if(this.rot90 && !this.rot180 && this.reflected) return reverse(this.leftEdge)
        else if(!this.rot90 && this.rot180 && this.reflected) return reverse(this.upEdge)
        else if(this.rot90 && this.rot180 && this.reflected) return reverse(this.rightEdge)
      }
    }
    val lines = Source.fromFile(filename).getLines().toList
    var
  }
}
