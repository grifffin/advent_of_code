import scala.io.Source
import scala.collection.mutable.HashSet
import scala.collection.mutable.ListBuffer
import annotation.tailrec
import java.lang.Integer.parseInt

object Part2 {
  def main(args: Array[String]): Unit = {
    val filename = "input"

    @tailrec
    def reverse(in: Int, n: Int = 10, out: Int = 0): Int =
      if (n == 0) out
      else reverse(in >>> 1, n - 1, (out << 1) | (in & 1))

    // only do this to square things
    def transform(chars: Array[Array[Char]], rot90: Boolean, rot180: Boolean, reflect: Boolean): Array[Array[Char]] = {
      var newArr = chars.clone
      val size = newArr.length
      if(reflect) {
        val reflectArr = Array.ofDim[Char](size, size)
        for(i <- 0 until size) {
          reflectArr(i) = newArr(i).toList.reverse.toArray
        }
        newArr = reflectArr
      }
      if(rot90) {
        val rot90Arr = Array.ofDim[Char](size, size)
        for(i <- 0 until size) {
          rot90Arr(i) = newArr.map(_(i)).toList.reverse.toArray
        }
        newArr = rot90Arr
      }
      if(rot180) {
        val rot180Arr = Array.ofDim[Char](size, size)
        for(i <- 0 until size) {
          rot180Arr(i) = newArr(size - i - 1).toList.reverse.toArray
        }
        newArr = rot180Arr
      }
      return newArr
    }

    // the rotation occurs after the reflection
    class Tile(val id: Int,
               val rightEdge: Int,
               val leftEdge: Int,
               val upEdge: Int,
               val downEdge: Int,
               var contents: Array[Array[Char]],
               var rot90: Boolean = false,
               var rot180: Boolean = false,
               var reflected: Boolean = false
      ) {
      def matchEdge(edge: Int, direction: String): Boolean = {
        for(i <- 0 until 8) {
          this.rot90 = i % 2 == 1
          this.rot180 = i % 4 > 1
          this.reflected = i > 3
          if(direction == "right" && reverse(edge) == this.trueRightEdge) return true
          else if(direction == "down" && reverse(edge) == this.trueDownEdge) return true
          else if(direction == "left" && reverse(edge) == this.trueLeftEdge) return true
          else if(direction == "up" && reverse(edge) == this.trueUpEdge) return true
        }
        return false
      }

      def getEdges: List[Int] = this.rightEdge :: this.leftEdge :: this.upEdge :: this.downEdge :: Nil

      override def toString: String = f"$id, $rot90, $rot180, $reflected"

      def trueContents: Array[Array[Char]] = transform(this.contents, this.rot90, this.rot180, this.reflected)

      def trueRightEdge: Int = {
        if(!this.rot90 && !this.rot180 && !this.reflected) return this.rightEdge
        else if(this.rot90 && !this.rot180 && !this.reflected) return this.upEdge
        else if(!this.rot90 && this.rot180 && !this.reflected) return this.leftEdge
        else if(this.rot90 && this.rot180 && !this.reflected) return this.downEdge
        else if(!this.rot90 && this.rot180 && this.reflected) return reverse(this.rightEdge)
        else if(this.rot90 && !this.rot180 && this.reflected) return reverse(this.upEdge)
        else if(!this.rot90 && !this.rot180 && this.reflected) return reverse(this.leftEdge)
        else return reverse(this.downEdge)
      }

      def trueLeftEdge: Int = {
        if(!this.rot90 && !this.rot180 && !this.reflected) return this.leftEdge
        else if(this.rot90 && !this.rot180 && !this.reflected) return this.downEdge
        else if(!this.rot90 && this.rot180 && !this.reflected) return this.rightEdge
        else if(this.rot90 && this.rot180 && !this.reflected) return this.upEdge
        else if(!this.rot90 && this.rot180 && this.reflected) return reverse(this.leftEdge)
        else if(this.rot90 && !this.rot180 && this.reflected) return reverse(this.downEdge)
        else if(!this.rot90 && !this.rot180 && this.reflected) return reverse(this.rightEdge)
        else return reverse(this.upEdge)
      }

      def trueUpEdge: Int = {
        if(!this.rot90 && !this.rot180 && !this.reflected) return this.upEdge
        else if(this.rot90 && !this.rot180 && !this.reflected) return this.leftEdge
        else if(!this.rot90 && this.rot180 && !this.reflected) return this.downEdge
        else if(this.rot90 && this.rot180 && !this.reflected) return this.rightEdge
        else if(!this.rot90 && !this.rot180 && this.reflected) return reverse(this.upEdge)
        else if(this.rot90 && !this.rot180 && this.reflected) return reverse(this.rightEdge)
        else if(!this.rot90 && this.rot180 && this.reflected) return reverse(this.downEdge)
        else return reverse(this.leftEdge)
      }

      def trueDownEdge: Int = {
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
      val down = lines(i+10).replaceAll("\\.", "0").replaceAll("#", "1").reverse
      val left = lines.slice(i+1, i+11).map(_.charAt(0)).mkString.replaceAll("\\.","0").replaceAll("#", "1").reverse
      val right = lines.slice(i+1, i+11).map(_.charAt(9)).mkString.replaceAll("\\.","0").replaceAll("#", "1")
      val contents = lines.slice(i+2, i+10).map(_.substring(1, 9).toCharArray).toArray
      tiles += new Tile(id, parseInt(right, 2), parseInt(left, 2), parseInt(up, 2), parseInt(down, 2), contents)
    }

    var index = 0
    var corner: Tile = tiles(0)
    var matches = ListBuffer[Int]()
    while(matches.size != 2) {
      matches.clear()
      index += 1
      corner = tiles(index)
      for(edge <- corner.getEdges) {
        for(tile <- tiles) {
          if(corner != tile && (tile.getEdges.contains(edge) || tile.getEdges.map(reverse(_)).contains(edge))) matches += edge
        }
      }
    }
    corner.rot90 = false
    corner.rot180 = false
    corner.reflected = false
    if(matches.contains(corner.trueUpEdge)) {
      if(matches.contains(corner.trueLeftEdge)) corner.rot180 = true
      else corner.rot90 = true
    } else if(matches.contains(corner.trueLeftEdge)) {
      corner.rot90 = true
      corner.rot180 = true
    }

    // I could do this without the magic numbers, but why bother?
    val puzzleTiles = Array.ofDim[Tile](12, 12)
    var puzzle = Array.ofDim[Char](96, 96)
    def placeTile(tile: Array[Array[Char]], x: Int, y: Int): Unit = {
      for(i <- 0 until 8; j <- 0 until 8) {
        puzzle(x+i)(y+j) = tile(i)(j)
      }
    }

    for(i <- 0 until 144) {
      var nextTile: Tile = null
      if(i == 0) {
        nextTile = corner
        tiles -= corner
      } else if(i < 12) {
        val edgeToMatch = puzzleTiles(0)(i-1).trueRightEdge
        val next = tiles.find(_.matchEdge(edgeToMatch, "left"))
        tiles -= next.get
        nextTile = next.get
      } else {
        val edgeToMatch = puzzleTiles(i/12-1)(i%12).trueDownEdge
        val next = tiles.find(_.matchEdge(edgeToMatch, "up"))
        tiles -= next.get
        nextTile = next.get
      }
      puzzleTiles(i/12)(i%12) = nextTile
      placeTile(transform(nextTile.contents, nextTile.rot90, nextTile.rot180, nextTile.reflected), (i/12)*8, (i%12)*8)
    }

    val serpent = Array("                  # ".toCharArray,
                        "#    ##    ##    ###".toCharArray,
                        " #  #  #  #  #  #   ".toCharArray)
    def checkSerpent(x: Int, y: Int): Boolean = {
      for(j <- 0 until serpent.length; i <- 0 until serpent(0).length) {
        if(serpent(j)(i) == '#' && puzzle(y+j)(x+i) != '#') return false
      }
      return true
    }

    var count = 0
    for(i <- 0 until 8) {
      val rot90 = i % 2 == 1
      val rot180 = i % 4 > 1
      val reflected = i > 3
      puzzle = transform(puzzle, rot90, rot180, reflected)
      for(y <- 0 until puzzle.length - serpent.length; x <- 0 until puzzle(0).length - serpent(0).length)
        if(checkSerpent(x, y)) count += 1
    }
    val puzzleHashCount = puzzle.map(_.count(_ == '#')).sum
    val serpentHashCount = 15
    println(puzzleHashCount - (count * serpentHashCount))
  }
}
