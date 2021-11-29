file = open('input', 'r')
text = file.readlines()[1]
file.close()

places = {}
i = 0
for el in text.split(','):
    if el != 'x':
        places[int(el)] = i
    i += 1

i = 51098619
good = 0
while(good < 9):
    good = 0
    for key in places.keys():
        if (i + places[key]) % key == 0:
            good += 1
    if(good > 7):
        print(i)
    i += 212264257
