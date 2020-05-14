import sys
import numpy as np
import matplotlib.pyplot as plt
#Requires file to be in  SLiM's sample format - 'outputSample' or 'outputFull'
#input: genome size, number of windows, file name

# 0 - non-coding
# 1 - synonymous
# 2 - nonsynonymous
mTypeMap = {"m1" : 0, "m2" : 1, "m3" : 2, "m4" : 2,"m5": 1, "m6": 2, "m7": 1, "m8": 1}

class Mutation:
    def __init__(self, kind, position, num):
        self.type = kind
        self.position = position
        self.num = num

def main():
    num_file = len(sys.argv) - 3
    gSize = float(sys.argv[1])
    numWindows = int(sys.argv[2])
    
    for i in range(0, num_file):
        file = open(sys.argv[3 + i])
        lines = file.readlines()
        popinfo = parsePopulation(lines,2)
        dnds = DNDS(popinfo,numWindows, gSize)
        plt.figure(i)
        plt.scatter(range(0,numWindows),dnds)
        plt.title('DNDS for ' + str(numWindows) + ' windows')
        plt.xlabel('Window')
        plt.ylabel('dN/dS')
        plt.savefig(sys.argv[3 + i][:len(sys.argv[3 + i]) - 4] + '_DNDS' + '.png', dpi=300)
    
    
def DNDS(popInfo, numWindows, gSize):
    
    windSize = gSize/numWindows
    dnds = []
    for j in range(numWindows):
        dn = 0.0
        ds = 0.0
        for i in popInfo[1]:
            for s in i:
                if s.position >= j*windSize and s.position < (j+1)*windSize:
                    if(mTypeMap[s.type] == 1):
                        ds += 1
                    elif(mTypeMap[s.type] == 2):
                        dn += 1
        
        if(ds == 0):
            dnds.append(-1)
        else:
            dnds.append(dn/ds)
    return dnds

def parsePopulation(lines, index):
    result = []
    localMMap = {}
    pop = []
    i = index
    while(lines[i] != "Genomes:\n"):
        lines[i] = lines[i][:len(lines[i])]
        temp = lines[i].split(" ")
        localMMap[temp[0]] = Mutation(temp[2], int(temp[3]), int(temp[8]))
        i += 1

    result.append(localMMap)
    i += 1
    pNum = 0
    while(i < len(lines)):
        pNum += 1
        lines[i] = lines[i][:len(lines[i]) - 1]
        temp = lines[i].split(" ")
        j = 2
        individual = []
        while(j < len(temp)):
            if(temp[j] == ''):
                j+=1
                continue
            individual.append(localMMap[temp[j]])
            j += 1 
        individual = sorted(individual, key=lambda m: m.position)
        pop.append(individual)
        i+=1
    result.append(pop)
    result.append(pNum)
    return result

if __name__ == "__main__":
    main();