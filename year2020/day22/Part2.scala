import scala.io.Source
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.HashSet
import annotation.tailrec

object Part2 {
  def playGame(p1hand: ListBuffer[Int], p2hand: ListBuffer[Int], states: HashSet[List[Int]]): Unit = {
    while(p1hand.length != 0 && p2hand.length != 0) {
      val state = p1hand.toList ++ (1000 :: Nil) ++ p2hand.toList
      if(states.contains(state)) {
        p1hand ++= p2hand
        p2hand.clear()
        return
      } else states += state
      val p1card = p1hand.remove(0)
      val p2card = p2hand.remove(0)
      if(p1hand.length >= p1card && p2hand.length >= p2card) {
        // play sub game
        val p1subhand = p1hand.slice(0, p1card)
        val p2subhand = p2hand.slice(0, p2card)
        val substates = HashSet[List[Int]]()
        playGame(p1subhand, p2subhand, substates)
        if(p1subhand.length > p2subhand.length) p1hand ++= p1card :: p2card :: Nil
        else p2hand ++= p2card :: p1card :: Nil
      } else {
        if(p1card > p2card) p1hand ++= p1card :: p2card :: Nil
        else p2hand ++= p2card :: p1card :: Nil
      }
    }
  }

  def computeScore(cards: ListBuffer[Int]): Int = cards.toList.reverse.zipWithIndex.map(x => x._1*(x._2+1)).sum

  def main(args: Array[String]): Unit = {
    val filename = "input"
    val file = Source.fromFile(filename).mkString
    val split = file.split("\n\nPlayer 2:\n")
    val p1text = split(0).substring(10)
    val p2text = split(1)
    val p1hand = p1text.split("\n").map(_.toInt).to(ListBuffer)
    val p2hand = p2text.split("\n").map(_.toInt).to(ListBuffer)
    val states = HashSet[List[Int]]()

    playGame(p1hand, p2hand, states)
    val winnerhand = if(p1hand.length > p2hand.length) p1hand else p2hand
    println(computeScore(winnerhand))
  }
}
