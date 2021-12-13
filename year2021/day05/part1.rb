lines = File.read('input').each_line.map do |line|
  line.split(' -> ').map { |el| el.split(',').map(&:to_i) } 
end
lines.select! { |line| line[0][0] == line[1][0] || line[0][1] == line[1][1] }
ocean = Array.new(1000) { Array.new(1000, 0) }
lines.each do |line|
  x1 = line[0][0]
  y1 = line[0][1]
  x2 = line[1][0]
  y2 = line[1][1]
  ([x1, x2].min..[x1,x2].max).each do |x|
    ([y1, y2].min..[y1, y2].max).each do |y|
      ocean[y][x] = ocean[y][x] + 1
    end
  end
end
puts ocean.flatten.count { |x| x > 1 }
