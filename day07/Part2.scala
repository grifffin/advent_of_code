import scala.io.Source
import scala.collection.mutable.HashMap

object Part1 {

  def main(args: Array[String]): Unit = {
    val filename = "input"

    // yes, this class does nothing now, but I'm following part 1's example
    class Bag(var contains: HashMap[String, Int])

    var bags = new HashMap[String, Bag]

    for (line <- Source.fromFile(filename).getLines()) {
      val split = line.split(' ')
      val bagName = split(0) + split(1)
      var contains = new HashMap[String, Int]
      if(split(4) != "no") {
        for(i <- 4 until split.length by 4) {
          val childName = split(i+1) + split(i+2)
          val childNumber = split(i)
          contains += (childName -> childNumber.toInt)
        }
      }
      bags += (bagName -> new Bag(contains))
    }

    def countBags(name: String): Int = {
      val bag = bags(name)
      var count = 1
      if(!bag.contains.isEmpty) {
        for((child, numChild) <- bag.contains) {
          count += countBags(child) * numChild
        }
      }
      return count
    }

    println(countBags("shinygold") - 1)
  }
}
