answer = File.read('input').each_line.map(&:to_i).each_cons(3).each_cons(2).reduce(0) do
  |count, el| el[0].sum < el[1].sum ? count + 1 : count
end
puts answer
