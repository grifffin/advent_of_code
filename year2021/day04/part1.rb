boards = File.read('input').split("\n\n")
draws = boards.shift.split(',')

boards.map! { | board | board.split("\n").map { | line | line.split(' ') } }

