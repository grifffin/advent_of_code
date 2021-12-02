answer = File.read('input').each_line.map(&:to_i).each_cons(2).reduce(0) do
  |count, el| el[0] < el[1] ? count + 1 : count
end
puts answer
