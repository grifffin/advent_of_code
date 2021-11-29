import scala.io.Source
import scala.collection.mutable.Queue

object Part2 {
  def main(args: Array[String]): Unit = {
    val filename = "input"
    val file = Source.fromFile(filename).getLines().toList
    // this is the number for my input from part one
    // todo, call part one instead of use this number
    val magicNumber = 1930745883
    var i = 0
    var nums = new Queue[Long]
    while(i < file.length) {
      if(nums.size < 2 || nums.sum < magicNumber) {
        nums.enqueue(file(i).toLong)
        i += 1
      }
      else if(nums.sum > magicNumber) nums.dequeue()
      else if(nums.sum == magicNumber) {
        println(nums.min + nums.max)
        i = file.length
      }
    }
  }
}
