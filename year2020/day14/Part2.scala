import scala.io.Source
import scala.collection.mutable.HashMap
import scala.collection.mutable.ListBuffer
import java.lang.Long.parseLong
import scala.math.pow

object Part2 {
  def applyMask(mask: String, number: String, step: Int): ListBuffer[String] = {
    var rtnList = ListBuffer[String]()
    if(mask.charAt(step) == '1') rtnList += "1"
    else if(mask.charAt(step) == '0') rtnList += number.charAt(step).toString
    else {
      rtnList += "1"
      rtnList += "0"
    }
    if(step < mask.length-1) {
      rtnList = for (el1 <- rtnList; el2 <- applyMask(mask, number, step+1)) yield el1 + el2
    }
    return rtnList
  }

  def main(args: Array[String]): Unit = {
    val filename = "input"

    var mask = ""
    var memory = new HashMap[Long, Long]
    for(line <- Source.fromFile(filename).getLines()) {
      if(line.substring(0, 4) == "mask") {
        mask = line.substring(7)
      } else {
        val equalPos = line.indexOf('=')
        var index = line.substring(4, equalPos - 2).toInt.toBinaryString
        index = "%36s".format(index).replace(" ", "0")
        val value = line.substring(equalPos + 2)
        val indeces = applyMask(mask, index, 0)
        for(newIndex <- indeces) memory(parseLong(newIndex, 2)) = value.toLong
      }
    }
    println(memory.values.sum)
  }
}
