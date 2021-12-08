boards = File.read('input').split("\n\n")
draws = boards.shift.split(',').map(&:to_i)

boards.map! { | board | board.split("\n").map { | line | line.split(' ').map(&:to_i) } }

def check_rows(board)
  board.reduce(false) do | solved, line |
    solved || line.uniq.length == 1
  end
end

def check_solved(board)
  check_rows(board) || check_rows(board.transpose)
end

# keep track of winners, remove them once they're found, then score the last one
winners = []
last_draw = -1
draws.each do | draw |
  # array difference
  (boards - winners).each do | board |
    board.map! do | line |
      line.map { | el | el == draw ? -1 : el }
    end
    if check_solved board
      winners.append(board)
      last_draw = draw
    end
  end
end

puts winners.last.flatten.map { | el | el == -1 ? 0 : el }.sum * last_draw
