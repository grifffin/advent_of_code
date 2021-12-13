fish = File.read('input').split(',').map(&:to_i)
(0...80).each do |i|
  births = fish.count(0)
  fish.map! { |fsh| fsh == 0 ? 6 : fsh - 1 }
  fish = fish + ([8] * births)
end
puts fish.length
