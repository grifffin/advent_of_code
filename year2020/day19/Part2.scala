import scala.io.Source
import scala.collection.mutable.HashMap
import scala.collection.mutable.ListBuffer

object Part2 {
  def main(args: Array[String]): Unit = {
    val filename = "input"
    val lines = Source.fromFile(filename).getLines().toList
    val blankIndex = lines.indexOf("")
    val rules = lines.slice(0, blankIndex).map(line => (line.split(": ")(0).toInt, line.split(": ")(1))).toMap
    val messages = lines.drop(blankIndex)
    val regexes = HashMap[Int, String]()
    def getRegex(ruleId: Int): String = {
      if(ruleId == 8) return getRegex(42) + '+'
      else if(ruleId == 11) return "(" + getRegex(42) + "{x}" + getRegex(31) + "{x}" + ")"
      else if(regexes.contains(ruleId)) return regexes(ruleId)
      else {
        val rule = rules(ruleId).split(" ")
        var str = "(?:"
        for(token <- rule) {
          if(token == "\"a\"" || token == "\"b\"" || token == "|") str += token.replaceAll("\"", "")
          else str += getRegex(token.toInt)
        }
        return str + ")"
      }
    }

    val regex = getRegex(0)
    var matches = ListBuffer[Boolean]()
    for(message <- messages) {
      var i = 1
      var matched = false
      while(!matched && i < 5) {
        matched = message.matches(regex.replaceAll("x", i.toString))
        if(matched) print(i, "")
        i += 1
      }
      matches += matched
    }
    println()
    println(matches.count(_ == true))
  }
}
