fish_list = File.read('input')
  .split(',')
  .map(&:to_i)
# use a hash instead of a list where values are counts
# e.g. {0=>count, 1=>count, ...}
fish = {}
(0..8).each do |i|
  fish[i] = fish_list.count(i)
end

(0...256).each do |i|
  # could be a loop but this is just so clear already
  births = fish[0]
  fish[0] = fish[1]
  fish[1] = fish[2]
  fish[2] = fish[3]
  fish[3] = fish[4]
  fish[4] = fish[5]
  fish[5] = fish[6]
  fish[6] = fish[7] + births
  fish[7] = fish[8]
  fish[8] = births
end
puts fish.values.sum
