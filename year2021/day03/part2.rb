rows = File.read('input').each_line.map(&:strip)
len = rows[0].length

o2 = ''
co2 = ''
(0...len).each do |i|
  o2_valids = rows.select { |row| row.start_with? o2 }
  if o2_valids.length > 1
    o2_count = o2_valids.count { |row| row[i] == '1' }
    o2 += (o2_count * 2 / o2_valids.length).to_s
  else
    o2 += o2_valids[0][i]
  end

  co2_valids = rows.select { |row| row.start_with? co2 }
  if co2_valids.length > 1
    co2_count = co2_valids.count { |row| row[i] == '1' }
    # I just flip the result with ^ 1
    co2 += (co2_count * 2 / co2_valids.length ^ 1).to_s
  else
    co2 += co2_valids[0][i]
  end
end

puts o2.to_i(2) * co2.to_i(2)
