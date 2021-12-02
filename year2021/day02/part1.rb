parts = File.read('input').each_line.partition { |line| line[0] == 'f' }
x = parts[0].map { |line| line[8..].to_i }.sum
y = parts[1].map { |line| line.sub('up ','-').sub('down ', '').to_i }.sum
puts x * y
