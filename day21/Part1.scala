import scala.io.Source
import scala.collection.mutable.HashMap
import scala.collection.mutable.HashSet
import scala.collection.mutable.ListBuffer

object Part1 {
  def main(args: Array[String]): Unit = {
    val filename = "input"
    val file = Source.fromFile(filename)
    val lines = file.getLines().toList
    val sources = HashMap[String, ListBuffer[String]]()
    val safeIngs = HashSet[String]()
    for(line <- lines) {
      val split = line.split(" \\(contains ")
      val ingredients = split(0).split(" ")
      safeIngs ++= ingredients
      val allergens = split(1).replace(")", "").split(", ")
      for(allergen <- allergens) {
        if(sources.contains(allergen)) sources(allergen) = sources(allergen).filter(ingredients.contains(_))
        else sources += (allergen -> ingredients.to(ListBuffer))
      }
    }
    for((key, value) <- sources) safeIngs --= value
    var count = 0
    val words = lines.map(_.split(' ')).flatten
    for(ing <- safeIngs) count += words.count(_ == ing)
    println(count)
  }
}
