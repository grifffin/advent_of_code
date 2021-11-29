import scala.io.Source
import scala.collection.mutable.ListBuffer

object Part2 {
  def main(args: Array[String]): Unit = {
    val filename = "input"

    class Rule(
      val name: String,
      val start1: Int,
      val end1: Int,
      val start2: Int,
      val end2: Int,
      var possibleSpots: ListBuffer[Int] = ListBuffer[Int]()
    ) {
        def checkVal(value: Int): Boolean = {
          (start1 <= value && value <= end1) || (start2 <= value && value <= end2)
      }
    }
    var reading = "rules"
    var rules = ListBuffer[Rule]()
    var tickets = ListBuffer[Array[Int]]()
    var yourTicket: Array[Int] = Array[Int]()
    var badValTotal = 0
    for(line <- Source.fromFile(filename).getLines()) {
      if(line == "your ticket:") reading = "yourTicket"
      else if(line == "nearby tickets:") reading = "tickets"
      else if(line != "" && reading == "rules") {
        val split1 = line.split(": ")
        val split2 = split1(1).split('-')
        val split3 = split2(1).split(" or ")
        rules += new Rule(
          name=split1(0),
          start1=split2(0).toInt,
          end1=split3(0).toInt,
          start2=split3(1).toInt,
          end2=split2(2).toInt
        )
      } else if(line != "" && reading == "tickets") {
        val values = line.split(',').map(_.toInt)
        var valid = true
        for(value <- values) {
          var good = false
          for(rule <- rules) {
            if(rule.checkVal(value)) good = true
          }
          if(!good) valid = false
        }
        if(valid) tickets += values
      } else if(line != "" && reading == "yourTicket") {
        yourTicket = line.split(',').map(_.toInt)
      }
    }

    for(rule <- rules) {
      for(i <- 0 until tickets(0).length) {
        if(tickets.forall(ticket => rule.checkVal(ticket(i)))) {
          rule.possibleSpots += i
          // println(rule.name, i)
        }
      }
    }
    // rules.foreach(r => println(r.name, r.possibleSpots))
    while(!rules.forall(_.possibleSpots.size == 1)) {
      for(rule <- rules) {
        if(rule.possibleSpots.size == 1) {
          // println(rule.name)
          rules.foreach(r => if (r != rule) r.possibleSpots = r.possibleSpots.filter(_ != rule.possibleSpots(0)))
        }
      }
    }
    var product: Long = 1
    for(rule <- rules) {
      // println(rule.name, yourTicket(rule.possibleSpots(0)))
      if(rule.name.split(' ')(0) == "departure") {
        product *= yourTicket(rule.possibleSpots(0))
      }
    }
    println(product)
  }
}
