x = 0
y = 0
aim = 0
parts = File.read('input').each_line do |line|
  if line[0] == 'f'
    amt = line[8..].to_i
    x += amt
    y += amt * aim
  else
    amt = line.sub('up ', '-').sub('down ', '').to_i
    aim += amt
  end
end
puts x * y
