import math
import sys
import matplotlib.pyplot as plt
import numpy as np
from matplotlib.collections import LineCollection
from matplotlib.colors import ListedColormap, BoundaryNorm

def main():
    file = open(sys.argv[1], "r")
    windowSize = int(sys.argv[2])
    stdDevMultiLim = 1.5
    if (len(sys.argv) >= 4):
        stdDevMultiLim = float(sys.argv[3])
    foundMutations = False
    total = 0
    mutations = {} 
    maxPos = -1
    for line in file:
        if "Mutations:" in line:
            foundMutations = True
            continue
        if "Genomes:" in line:
            foundMutations = False
            break
        if "#OUT:" in line:
            parts = line.split(" ")
            total = int(parts[4])  
        if foundMutations:
            lines = line.split(" ")
            mutations[int(lines[3])] = int(lines[8])
            if int(lines[3]) > maxPos:
                maxPos = int(lines[3])
    max = windowSize
    i = 0
    windowsProb = []
    totalProb = countProbability(0, maxPos, mutations, total)
    while windowSize*(i+1) <= maxPos: 
        posMin = windowSize*i
        posMax = windowSize*(i+1)
        windowsProb.append(countProbability(posMin, posMax, mutations, total)-totalProb)
        i += 1
    windows = []
    i = 1
    while i <= len(windowsProb):
        windows.append(i)
        i = i+1
    fig, ax = plt.subplots()
    barlist = ax.bar(windows, windowsProb)
    for i in range(0, len(barlist)):
        if (np.abs(windowsProb[i]-np.mean(windowsProb)) > stdDevMultiLim*np.std(windowsProb)):
            barlist[i].set_facecolor('r')
        else:
            barlist[i].set_facecolor('g')
    ax.set_xlabel('Window')
    ax.set_ylabel('CLR')
    ax.grid(True)
    plt.show()
    
def countProbability(posMin, posMax, mutations, total):
    pos = posMin
    prob = 1.0
    while pos <= posMax:
        if pos in mutations:
            indProb = float(mutations[pos])/float(total)
            probWithoutLog = (math.factorial(total) / (math.factorial(mutations[pos]) * (math.factorial(total-mutations[pos])))) * indProb ** mutations[pos] * (1-indProb) ** (total - mutations[pos])
            prob += math.log(probWithoutLog)
        pos += 1
    return prob

if __name__ == "__main__":
    main()