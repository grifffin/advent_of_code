file = open('input', 'r')
text = file.readlines()
file.close()

total = 0
for line in text:
    if line[1] == 'a':
        mask = line[7:]
        numX = mask.count('X')
        total += 2 ** numX
print(total)
