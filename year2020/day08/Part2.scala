import scala.io.Source
import scala.collection.mutable.HashSet

object Part2 {
  def main(args: Array[String]): Unit = {
    val filename = "input"
    val file = Source.fromFile(filename).getLines().toList
    var visited = new HashSet[Int]

    // if the initial call returns 0, either the input is screwed up or the answer actually is 0
    def checkLine(lineNum: Int, acc: Int, madeChange: Boolean): Int = {
      if(lineNum == file.length) return acc
      if(visited.contains(lineNum)) return 0
      visited += lineNum
      val line = file(lineNum).split(" ")
      if(line(0) == "acc" || madeChange) {
        val nextLine = lineNum + (if (line(0) == "jmp") line(1).toInt else 1)
        val nextAcc = if(line(0) == "acc") acc + line(1).toInt else acc
        val next = checkLine(nextLine, nextAcc, madeChange)
        if(next == 0) {
          visited -= lineNum
          return 0
        } else return next
      } else {
        val next1 = checkLine(lineNum + line(1).toInt, acc, line(0) == "nop")
        val next2 = checkLine(lineNum + 1, acc, line(0) == "jmp")
        if(next1 != 0) return next1
        else if(next2 != 0) return next2
        else {
          visited -= lineNum
          return 0
        }
      }
    }
    println(checkLine(0, 0, false))
  }
}
