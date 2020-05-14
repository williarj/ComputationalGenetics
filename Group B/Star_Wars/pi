import sys
import numpy as np
import operator
# requires file to be in SLiM's sample data format - 'outputSample' or 'outputFull'
#input: filename, popSize (sample size of file), genome Size, number of windows

class Mutation:
    def __init__(self, kind, position, num):
        self.type = kind
        self.position = position
        self.num = num

def main():
    file = open(sys.argv[1],'r')
    lines = file.readlines()
    mmap = parsePopulation(lines,2)
    popSize = int(sys.argv[2])
    gSize = float(sys.argv[3])
    numWindows = int(sys.argv[4])
    
    wholePi = calcPi(mmap, popSize)
    avgPi = np.sum(wholePi)/gSize
    

    
    windowPis = inWindow(wholePi, mmap, gSize, numWindows)
    
    print('Resulting Window Size: ', float(gSize/numWindows))
    print('Avg Pi: ', avgPi)
    print()
    print('Pi in each window:')
    print(windowPis)
    print()
    
    orderedMap = {}
    for i in range(len(windowPis)):
        orderedMap[i] = windowPis[i]
    orderedMap = sorted(orderedMap.items(), key = operator.itemgetter(1))
    print("Pi's ordered by magnitude")
    print(orderedMap)
    
def inWindow(wholePi, mmap,gSize,numWindows):
    wSize = float(gSize/numWindows)
    ub = wSize-1
    windowPis = []
    windowPi = 0.0
    i=0
    for m in mmap:
        
        if m.position < ub:
            windowPi += wholePi[i]
            
        else: 
            ub += wSize
            windowPis.append(float(windowPi/wSize))
            windowPi = float(wholePi[i])
            
        i+=1
    windowPis.append(float(windowPi/wSize))
    return windowPis
        
    
def calcPi(mmap, popSize):
    pi = []
    for m in mmap:
        thisPi = m.num*(popSize-m.num)
        pi.append(thisPi)
    return pi
    
def parsePopulation(lines, index):
    localMMap = {}
    i = index
    while(lines[i] != "Genomes:\n"):
        lines[i] = lines[i][:len(lines[i])]
        temp = lines[i].split(" ")
        localMMap[temp[0]] = Mutation(temp[2], int(temp[3]), int(temp[8]))
        i += 1
    
    return sorted(localMMap.values(), key=lambda m: m.position)
            
        
    
    
    
if __name__ == "__main__":
    main()