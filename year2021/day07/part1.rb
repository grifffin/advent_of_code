crabs = File.read('input').split(',').map(&:to_i)
min = crabs.min
max = crabs.max
best_fuel = 9999999
best_pos = -1
(min..max).each do |i|
  fuel = crabs.reduce(0) { |sum, crab| sum + (crab - i).abs }
  if fuel < best_fuel
    best_fuel = fuel
    best_pos = i
  end
end
puts best_fuel
