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

draws.each do | draw |
  boards.each do | board |
    board.map! do | line |
      line.map { | el | el == draw ? -1 : el }
    end
    if check_solved board
      puts board.flatten.map { | el | el == -1 ? 0 : el }.sum * draw
      exit
    end
  end
end
