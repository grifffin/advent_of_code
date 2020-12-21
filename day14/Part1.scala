import scala.io.Source
import scala.collection.mutable.HashMap
import java.lang.Long.parseLong

object Part1 {
  def applyMask(mask: String, number: Long): Long = {
    val step1 = parseLong(mask.replaceAll("0", "1").replaceAll("X", "0"), 2)
    val step2 = parseLong(mask.replaceAll("X", "0"), 2)
    var result = number & step1
    result = number - result
    result += step2
    return result
  }

  def main(args: Array[String]): Unit = {
    val filename = "input"

    var mask = ""
    var memory = new HashMap[Int, Long]
    for(line <- Source.fromFile(filename).getLines()) {
      if(line.substring(0, 4) == "mask") {
        mask = line.substring(7)
      } else {
        val equalPos = line.indexOf('=')
        val index = line.substring(4, equalPos - 2).toInt
        val value = line.substring(equalPos + 2).toLong
        memory(index) = applyMask(mask, value)
      }
    }
    println(memory.values.sum)
  }
}
