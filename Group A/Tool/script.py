import math
import sys
import matplotlib.pyplot as plt
import numpy as np
from matplotlib.collections import LineCollection
from matplotlib.colors import ListedColormap, BoundaryNorm

def main():
    file = open(sys.argv[1], "r")
    windowSize = int(sys.argv[2])
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
    print(totalProb)
    while windowSize*(i+1) <= maxPos:
        posMin = windowSize*i
        posMax = windowSize*(i+1)
        print(countProbability(posMin, posMax, mutations, total))
        windowsProb.append(countProbability(posMin, posMax, mutations, total)-totalProb)
        i += 1
    print(windowsProb)
    windows = []
    i = 1
    while i <= len(windowsProb):
        windows.append(i*windowSize)
        i = i+1
    fig, ax = plt.subplots()
    windowsProbDiff = np.ma.masked_where(np.abs(windowsProb-np.mean(windowsProb)) <= 1.3*np.std(windowsProb), windowsProb)
    windowsProb = np.ma.masked_where(np.abs(windowsProb-np.mean(windowsProb)) > 1.3*np.std(windowsProb), windowsProb)
    plt.scatter(windows, windowsProb, c='g')
    # scatter warning points in red (c='r')
    plt.scatter(windows, windowsProbDiff, c='r')
    ax.plot(windows, windowsProb)
    ax.set_xlabel('Position')
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