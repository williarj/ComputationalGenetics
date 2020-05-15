import sys
import numpy as np
import operator
# requires file to be in SLiM's sample data format - 'outputSample' or 'outputFull'
#input: filename, popSize (sample size of file), genome Size, mutation rate
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
    mu = float(sys.argv[4])
    
    wholePi = calcPi(mmap, popSize)
    avgPi = np.sum(wholePi)/gSize
    ne = avgPi/(4*mu)
    print('Ne for ' + sys.argv[1] + ' is ' + str(ne))

    
    
        
    
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