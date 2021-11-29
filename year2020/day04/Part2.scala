import scala.io.Source

object Part2 {
  def main(args: Array[String]): Unit = {
    val filename = "input"
    val file = Source.fromFile(filename).getLines().mkString("\n")
    val passports = file.split("\n\n")
    var numValid = 0
    for(passport <- passports) {
      val fields = passport.split("\\s+")
      var numValidFields = 0
      for(field <- fields.map(f => f.split(":"))) {
        val value = field(1)
        val name = field(0)
        if(name == "byr") {
          val byr = value.toIntOption.getOrElse(-1)
          if(byr >= 1920 && byr <= 2002) numValidFields += 1
        } else if(name == "iyr") {
          val iyr = value.toIntOption.getOrElse(-1)
          if(iyr >= 2010 && iyr <= 2020) numValidFields += 1
        } else if(name == "eyr") {
          val eyr = value.toIntOption.getOrElse(-1)
          if(eyr >= 2020 && eyr <= 2030) numValidFields += 1
        } else if(name == "hgt") {
          if(value.length == 5 &&value.substring(3) == "cm") {
            val hgt = value.substring(0, 3).toIntOption.getOrElse(-1)
            if(hgt >= 150 && hgt <= 193) numValidFields += 1
          } else if(value.length == 4 && value.substring(2) == "in") {
            val hgt = value.substring(0, 2).toIntOption.getOrElse(-1)
            if(hgt >= 59 && hgt <= 76) numValidFields += 1
          }
        } else if(name == "hcl") {
          if(value.matches(raw"\#[0-9a-fA-F]{6}")) numValidFields += 1
        } else if(name == "ecl") {
          if(("amb"::"blu"::"brn"::"gry"::"grn"::"hzl"::"oth"::Nil).contains(value)) numValidFields += 1
        } else if(name == "pid") {
          if(value.matches(raw"\d{9}")) numValidFields += 1
        }
      }
      if(numValidFields == 7) numValid += 1
    }
    println(numValid)
  }
}
