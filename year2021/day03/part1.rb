cols = File.read('input').each_line.map(&:chars).transpose
# cols has an array of '\n's at the end
cols = cols[0...-1]
gamma = cols.map do |col|
  col.count('0') > col.length / 2 ? '0' : '1'
end.join.to_i(2) # make array of chars to binary int
# epsilon is one's complement of gamma, so xor with ones as long as gamma
puts gamma * (gamma ^ ('1' * cols.length).to_i(2))
