sum = 0
File.read('input').each_line do |line|
  fuel = line.to_i
  while fuel > 8 do
    fuel = fuel / 3 - 2
    sum += fuel
  end
end
puts sum
