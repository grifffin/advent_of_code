import scala.collection.mutable.HashMap

object Part2 {
  def main(args: Array[String]): Unit = {
    val input = "562893147".split("").map(_.toInt)
    val max = 1000000
    val cycles = 10000000
    class Cup(var value: Int, var next: Cup) {
      def getLast(): Cup = {
        var current = this
        while(current.next != null && current.next != this) current = current.next
        return current
      }

      override def toString(): String = {
        var rtnstr = this.value.toString()
        var current = this.next
        while(current != this && current != null) {
          rtnstr += current.value.toString
          current = current.next
        }
        return rtnstr
      }

      // don't search in a circular list
      def contains(n: Int): Boolean =
        return this.value == n || (this.next != null && this.next.contains(n))
    }
    var cups = new HashMap[Int, Cup]()
    var current = new Cup(input(0), null)
    cups += (input(0) -> current)
    for(x <- input.slice(1, input.length)) {
      var nextCup = new Cup(x.toInt, null)
      current.getLast().next = nextCup
      cups += (x -> nextCup)
    }
    var last = current.getLast()
    for(x <- 10 to max) {
      var nextCup = new Cup(x, null)
      last.next = nextCup
      cups += (x -> nextCup)
      last = last.next
    }
    last.next = current
    for(i <- 1 to cycles) {
      val pickup = current.next
      current.next = current.next.next.next.next
      pickup.next.next.next = null
      var destinationVal = if(current.value == 1) max else current.value - 1
      while(pickup.contains(destinationVal)) 
        destinationVal = if(destinationVal == 1) max else destinationVal - 1
      var destination = cups(destinationVal)
      pickup.next.next.next = destination.next
      destination.next = pickup
      current = current.next
    }
    println(cups(1).next.value.toLong * cups(1).next.next.value)
  }
}
