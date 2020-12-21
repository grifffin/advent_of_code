import scala.io.Source
import scala.collection.mutable.HashMap

object Part1 {
  def main(args: Array[String]): Unit = {
    val filename = "input"
    val lines = Source.fromFile(filename).getLines().toList
    val blankIndex = lines.indexOf("")
    val rules = lines.slice(0, blankIndex).map(line => (line.split(": ")(0).toInt, line.split(": ")(1))).toMap
    val messeges = lines.drop(blankIndex)
    val regexes = HashMap[Int, String]()
    def getRegex(ruleId: Int): String = {
      if(regexes.contains(ruleId)) return regexes(ruleId)
      else {
        val rule = rules(ruleId).split(" ")
        var str = "("
        for(token <- rule) {
          if(token == "\"a\"" || token == "\"b\"" || token == "|") str += token.replaceAll("\"", "")
          else str += getRegex(token.toInt)
        }
        return str + ")"
      }
    }

    val regex = getRegex(0)
    println(messeges.count(_.matches(regex)))
  }
}
