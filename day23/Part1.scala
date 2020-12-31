object Part1 {
  def main(args: Array[String]): Unit = {
    val input = "562893147".split("").map(_.toInt)
    val max = input.max
    class Cup(var value: Int, var next: Cup) {
      def getLast(): Cup = {
        var current = this
        while(current.next != null && current.next != this) current = current.next
        return current
      }

      def getDestination(): Cup = {
        var current = this.next
        var high: Cup = null
        var highval = 0
        while(current != this) {
          val newval = ((current.value - this.value) + max) % max
          if(newval > highval) {
            highval = newval
            high = current
          }
          current = current.next
        }
        return high
      }

      override def toString(): String = {
        var rtnstr = this.value.toString()
        var current = this.next
        while(current != this) {
          rtnstr += current.value.toString
          current = current.next
        }
        return rtnstr
      }
    }
    var current = new Cup(input(0), null)
    for(char <- input.slice(1, input.length))
      current.getLast().next = new Cup(char.toInt, null)
    current.getLast().next = current
    for(i <- 1 to 100) {
      val pickup = current.next
      current.next = current.next.next.next.next
      val destination = current.getDestination()
      pickup.next.next.next = destination.next
      destination.next = pickup
      current = current.next
    }
    while(current.value != 1) current = current.next
    println(current.toString.substring(1))
  }
}
