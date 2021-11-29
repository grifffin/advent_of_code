import scala.io.Source
import scala.collection.mutable.HashSet

object Part1 {
  def main(args: Array[String]): Unit = {
    val filename = "input"

    def compute(str: String): (Long, Int) = {
      var acc: Long = 0
      var plus = true
      var i = 0
      while(i < str.length) {
        str.charAt(i) match {
          case ')' => return((acc, i))
          case '(' => {
            val result = compute(str.substring(i+1))
            if(plus) acc += result._1 else acc *= result._1
            i += result._2 + 1
          }
          case '+' => plus = true
          case '*' => plus = false
          case other => if(plus) acc += other.toInt - 48 else acc *= other.toInt - 48
        }
        i += 1
      }
      return((acc, i))
    }

    var acc: Long = 0
    for(line <- Source.fromFile(filename).getLines()) {
      var total = compute(line.replaceAll(" ", ""))._1
      acc += total
    }

    println(acc)
  }
}
