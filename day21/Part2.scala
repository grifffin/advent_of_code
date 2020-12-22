import scala.io.Source
import scala.collection.mutable.HashMap
import scala.collection.mutable.HashSet
import scala.collection.mutable.ListBuffer

object Part2 {
  def main(args: Array[String]): Unit = {
    val filename = "input"
    val file = Source.fromFile(filename)
    val lines = file.getLines().toList
    val sources = HashMap[String, ListBuffer[String]]()
    for(line <- lines) {
      val split = line.split(" \\(contains ")
      val ingredients = split(0).split(" ")
      val allergens = split(1).replace(")", "").split(", ")
      for(allergen <- allergens) {
        if(sources.contains(allergen)) sources(allergen) = sources(allergen).filter(ingredients.contains(_))
        else sources += (allergen -> ingredients.to(ListBuffer))
      }
    }
    while(!sources.forall(_._2.size == 1)) {
      for((ing, alls) <- sources) {
        if(alls.size == 1) {
          sources.foreach(tup => if(tup._1 != ing) sources(tup._1) = tup._2.filter(_ != alls(0)))
        }
      }
    }
    println(sources.keys.toList.sorted.map(sources(_)(0)).mkString(","))
  }
}
