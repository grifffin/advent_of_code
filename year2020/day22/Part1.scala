import scala.io.Source
import scala.collection.mutable.ArrayDeque

object Part1 {
  def main(args: Array[String]): Unit = {
    val filename = "input"
    val file = Source.fromFile(filename).mkString
    val split = file.split("\n\nPlayer 2:\n")
    val p1text = split(0).substring(10)
    val p2text = split(1)
    val p1hand = ArrayDeque[Int]()
    val p2hand = ArrayDeque[Int]()
    for(card <- p1text.split("\n").map(_.toInt)) p1hand += card
    for(card <- p2text.split("\n").map(_.toInt)) p2hand += card
    while(p1hand.length != 0 && p2hand.length != 0) {
      val p1card = p1hand.remove(0)
      val p2card = p2hand.remove(0)
      if(p1card > p2card) p1hand ++= p1card :: p2card :: Nil
      else if(p2card > p1card) p2hand ++= p2card :: p1card :: Nil
    }
    val winnerhand = if(p1hand.length > p2hand.length) p1hand else p2hand
    var score = 0
    for((card, index) <- winnerhand.toList.reverse.zipWithIndex) score += card * (index+1)
    println(score)
  }
}
