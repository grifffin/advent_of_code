import scala.io.Source
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer

object Part1 {

  def main(args: Array[String]): Unit = {
    val filename = "input"

    class Bag(var contains: ArrayBuffer[String], var shiny: Boolean)

    var bags = new HashMap[String, Bag]

    for (line <- Source.fromFile(filename).getLines()) {
      val split = line.split(' ')
      val bagName = split(0) + split(1)
      val shiny = bagName == "shinygold"
      var contains = new ArrayBuffer[String]
      if(split(4) != "no") {
        for(i <- 5 until split.length by 4) {
          contains += split(i) + split(i+1)
        }
      }
      bags += (bagName -> new Bag(contains, shiny))
    }

    var changed = true
    while(changed) {
      changed = false
      for((name, bag) <- bags) {
        if(!bag.shiny) {
          for(child <- bag.contains) {
            if(bags(child).shiny) {
              bag.shiny = true
              changed = true
            }
          }
        }
      }
    }
    var numShiny = 0
    for((name, bag) <- bags) {
      if(bag.shiny) {
        numShiny += 1
      }
    }
    println(numShiny - 1)
  }
}
