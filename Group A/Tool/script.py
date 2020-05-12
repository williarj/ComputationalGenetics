import math
import sys
import matplotlib.pyplot as plt
import numpy as np

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
    windowProb = []
    totalProb = countProbability(0, maxPos, mutations, total)
    while windowSize*(i+1) <= maxPos:
        posMin = windowSize*i
        posMax = windowSize*(i+1)
        windowProb.append(countProbability(posMin, posMax, mutations, total)/1)
        i += 1
    windows = []
    i = 1
    while i <= len(windowProb):
        windows.append(i)
        i = i+1
    fig, ax = plt.subplots()
    ax.plot(windows, windowProb)
    plt.show()
    
def countProbability(posMin, posMax, mutations, total):
    count = posMin
    prob = 1.0
    while count <= posMax:
        if count in mutations:
            indProb = mutations[count]/1
            prob *= (math.factorial(total) / (math.factorial(mutations[count]) * (math.factorial(total-mutations[count])))) * indProb ^ count * (1-indProb) ^ (total-count)
        count += 1
    return prob

if __name__ == "__main__":
    main()