import scala.io.Source
import scala.collection.mutable.HashSet

object Part1 {
  def main(args: Array[String]): Unit = {
    val filename = "input"

    def sumOf(str: String): Long = {
      return(str.split('+').map(_.toLong).sum)
    }

    def compute(str: String): String = {
      if(str.contains("(")) {
        val index1 = str.lastIndexOf('(')
        val first = str.substring(0, index1)
        val sub = str.substring(index1+1)
        val index2 = sub.indexOf(')')
        return(compute(first + compute(sub.substring(0, index2)) + sub.substring(index2+1)))
      } else {
        return(str.split('*').map(sumOf(_)).product.toString)
      }
    }

    var acc: Long = 0
    for(line <- Source.fromFile(filename).getLines()) {
      var total = compute(line.replaceAll(" ", "")).toLong
      acc += total
    }

    println(acc)
  }
}
