import scala.io.Source

object Part2 {
  // this returns the highest value under the searchVal
  def getIndexOfClosest(searchVal: Int, nums: List[Int]): Int = {
    var curr = 0
    while(nums(curr) < searchVal) curr += 1
    return curr - 1
  }

  def main(args: Array[String]): Unit = {
    // grab the input and sort it
    val filename = "input"
    var nums = Source.fromFile(filename).getLines().toList.map((s:String) => s.toInt).sorted
    // for each value, k, search for two values that sum to 2020 - k
    // the search per value is O(n), so O(n^2) in total
    for(i <- 0 until nums.length) {
      val targetSum = 2020 - nums(i)
      // the indices of the values being searched
      // they start at the values on either side of the target / 2
      var index1 = getIndexOfClosest(targetSum / 2, nums)
      var index2 = index1 + 1
      while(index1 > -1 && index2 < nums.length) {
        if(nums(index1) + nums(index2) == targetSum) {
          println(nums(i), nums(index1), nums(index2))
          println(nums(i) * nums(index1) * nums(index2))
          return
        } else if(nums(index1) + nums(index2) < targetSum) {
          // move the higher index up if the sum is too low
          index2 = if (index2 == i - 1) index2 + 2 else index2 + 1
        } else {
          // move the lower index down if the sum is too high
          index1 = if (index1 == i + 1) index1 - 2 else index1 - 1
        }
      }
    }
  }
}
