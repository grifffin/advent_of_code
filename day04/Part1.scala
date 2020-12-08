import scala.io.Source

object Part1 {
  def main(args: Array[String]): Unit = {
    val filename = "input"
    val file = Source.fromFile(filename).getLines().mkString("\n")
    val passports = file.split("\n\n")
    val checkFor = "byr" :: "iyr" :: "eyr" :: "hgt" :: "hcl" :: "ecl" :: "pid" :: Nil
    var numValid = 0
    for(passport <- passports) {
      val fields = passport.split("\\s+").map(s => s.substring(0, 3))
      var valid = true
      for(el <- checkFor) {
        if(!fields.contains(el)) valid = false
      }
      if(valid) numValid += 1
    }
    println(numValid)
  }
}
