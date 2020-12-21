file = open('input', 'r')
lines = file.readlines()
file.close()

for line in lines:
    line = line.replace('(', '').replace(')', '').replace('\n', '')
    if(max([len(el) for el in line.split(' ')]) > 1):
        print(line)
