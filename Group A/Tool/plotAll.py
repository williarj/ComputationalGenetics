import math
from math import comb
import sys
import matplotlib.pyplot as plt
import numpy as np
from matplotlib.collections import LineCollection
from matplotlib.colors import ListedColormap, BoundaryNorm
import os

#!
#mutation type needs to be adjusted for Dn/Ds depending on data

def main():
    folder = (sys.argv[1])
    for fileF in os.listdir(folder):
        if not fileF.startswith('.') and fileF.endswith('.txt'):
            #type = int(sys.argv[2])
            fullPath =  os.path.join(folder, fileF)
            file = open(fullPath, "r")
            windowSize = 16000
            fileName = os.path.basename(fileF).split(".")[0]
            print("working on "+fileName+" graphs.")
            stdDevMultiLim = 1.5
            if (len(sys.argv) >= 5):
                stdDevMultiLim = float(sys.argv[4])
            foundMutations = False
            total = -1
            mutations = {}
            maxPos = -1
            popSize = -1
            
            for line in file:
                if "Mutations:" in line:
                    foundMutations = True
                    continue
                if "Genomes:" in line:
                    foundMutations = False
                    break
                if "#OUT:" in line:
                    parts = line.split(" ")
                    popSize = int(parts[1])
                    total = int(parts[4])  #position
                if foundMutations:
                    lines = line.split(" ")
                    mutations[int(lines[3])] = Mut(lines[2], int(lines[8]))
                    if int(lines[3]) > maxPos:
                        maxPos = int(lines[3])
            max = windowSize
        
            plot(total, mutations, maxPos, popSize, max, fileName, stdDevMultiLim, windowSize,  0) #CLR
            #plot(total, mutations, maxPos, popSize, max, fileName, stdDevMultiLim, windowSize,  1) #Pi
            plot(total, mutations, maxPos, popSize, max, fileName, stdDevMultiLim, windowSize,  2) #Dn/Ds
            #plot(total, mutations, maxPos, popSize, max, fileName, stdDevMultiLim, windowSize, 3)
    
    
    
def plot(total, mutations, maxPos, popSize, max, fileName, stdDevMultiLim, windowSize, type):

    if type == 3:
        return ne(popSize, mutations,  maxPos, total, fileName)
    else:
        #Window calculations depending on type called
        i = 0
        windowsProb = []
        totalProb = callCorrectType(0, maxPos, mutations, total, type, popSize, maxPos)
        while windowSize*(i) <= maxPos:
            posMin = windowSize*i
            posMax = windowSize*(i+1)
            if type == 0:
                windowsProb.append(callCorrectType(posMin, posMax, mutations, total, type, popSize, maxPos)-totalProb)
            else:
                windowsProb.append(callCorrectType(posMin, posMax, mutations, total, type, popSize, maxPos))
            i += 1
        windows = []
        i = 1
        while i <= len(windowsProb):
            windows.append(i*windowSize)
            i = i+1
            
        #graph stuff
        fig, ax = plt.subplots()
        barlist = ax.bar(windows, windowsProb, lw = 0.25, edgecolor = 'black', width = windowSize * 0.9)
        fig.canvas.set_window_title("" + axisLable(type) + "[" + str(windowSize) + "]") #Window title
        
        rect = fig.patch
        rect.set_facecolor('dimgray')
       
        for i in range(0, len(barlist)):
            if (np.abs(windowsProb[i]-np.mean(windowsProb)) > stdDevMultiLim*np.std(windowsProb)):
                barlist[i].set_facecolor('orangered')
            else:
                barlist[i].set_facecolor('gray')
       
        ax.set_xlabel('Base Pairs', fontsize=14)
        ax.set_ylabel(axisLable(type), fontsize=14)
        ax.set_title(fileName.capitalize() + " " + axisLable(type) + " Plot", fontsize=14)
        ax.grid(True, ls = '--', lw = .5)
        plt.tight_layout()
        #plt.show()
        saveFolder = "/Users/heinrich/ComputationalGenetics/Group A/Results/plots/"
        location = (saveFolder + fileName + axisLable(type) + "[" + str(windowSize) + "]")
        plt.savefig(location, dpi = 300, optimize = True, bbox_inches='tight')
        
        plt.close()

#name of correct type
def axisLable(type):
    if type == 0: #clr
        return "CLR"
    elif type == 1: #pi
        return "Pi"
    elif type == 2: #dn/ds
        return "DnDs"
    elif type == -45:#fst
        return "FST"
    elif type == 3: #ne
        return "Null" #shouldn't show
    else:
        print("error 1")
        sys.exit()

#calles function based on type input
def callCorrectType(posMin, posMax, mutations, total, type, popSize, maxPos):
    if type == 0: #clr
        return clrProbability(posMin, posMax, mutations, total)
    elif type == 1: #pi
        return piProbability(posMin, posMax, mutations, total)
    elif type == 2: #dn/ds
        return dndsProbability(posMin, posMax, mutations, total)
    elif type == -45:#fst
        return fstProbability(posMin, posMax, mutations, total)
    elif type == 3: #ne
        return ne(popSize, mutations,  maxPos, total)
        sys.exit()
    else:
        print("error 2")
        sys.exit()
    
#CLR window calculation
def clrProbability(posMin, posMax, mutations, total):
    pos = posMin
    prob = 1.0
    while pos <= posMax:
        if pos in mutations:
            indProb = float(mutations[pos].number)/float(total)
            probWithoutLog = (math.factorial(total) / (math.factorial(mutations[pos].number) * (math.factorial(total-mutations[pos].number)))) * indProb ** mutations[pos].number * (1-indProb) ** (total - mutations[pos].number)
            prob += math.log(probWithoutLog)
        pos += 1
    return prob

#Pi window calculation
def piProbability(posMin, posMax, mutations, total):
    pos = posMin
    prob = 0.0
    while pos <= posMax:
        if pos in mutations:
            indProb = mutations[pos].number * (total-mutations[pos].number)
            prob += indProb
        pos += 1
    divide = comb(total, 2) + 0.0
    
    #float((float(total)*(float(total-1)))/2.0)
    #print(str(divide)+" choose value")
    print(str(prob/divide)+" final pi ->"+str(prob)+" / "+str(divide))
    return prob/divide #SWITCHED TO GET BETWEEN 0-1
    
    #in the script we are
    
    #a) multiplying the how many people have the mutation by the total number of samples - many people have the mutation
    #b) multiplying how many sample there are by 1 - that and then didide by 2
    
    # and finally divide a by b

#Dn/Ds window calculation
def dndsProbability(posMin, posMax, mutations, total):
    pos = posMin
    countSyn = 0
    countNonSyn = 0
    while pos <= posMax:
        if pos in mutations:
            if mutations[pos].type == "m1" or mutations[pos].type == "m2":
                countSyn += 1
            else:
                countNonSyn += 1
        pos += 1
    if countSyn == 0:
        return 0
    else:
        return float(countNonSyn)/float(countSyn)

#FST
def fstProbability(posMin, posMax, mutations, total):
   
    #print("FST plot")
    
    pos = posMin
  
                
    #TODO
                
        
    print("we need some extra time for this...")
    print("thx")
    sys.exit()
    return float(countNonSyn)/float(countSyn)

#Ne
def ne(popSize, mutations, maxPos, total, fileName):
    pi =  piProbability(0,maxPos, mutations, total)
    mutationRate = 10**-7# == 1e-7
    b =4.0*float(mutationRate)
    
    print("The Ne of " + fileName + " is aproximately:")
    print(float(pi/b))
    
    
#stores mutation type (eg m1) & how many individuals in sample have it
class Mut:

    def __init__(self, type, number): #number: how many people have it
        self.type = type
        self.number = number


if __name__ == "__main__":
    main()


