puts File.read('input').each_line.reduce(0) { |sum, line| sum + line.to_i / 3 - 2 }

