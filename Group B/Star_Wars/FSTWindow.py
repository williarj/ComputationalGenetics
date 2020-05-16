import sys
import os
import matplotlib.pyplot as plt
import numpy as np
# requires file to be  in SLiM's MS format
#input: gSize, number of windows, total pop file, sub pop 1, sub pop 2...

def main():
    num_file = len(sys.argv) - 3
    gSize = int(sys.argv[1])
    numWindows = int(sys.argv[2])
    
    raw_h = []
    for i in range(0, num_file):
        file = open(sys.argv[3 + i])
        lines = file.readlines()
        raw_h.append(parsePopulation(lines))
    fst = []
    for i in range(1, len(raw_h)):
        fst.append(generateFST(raw_h[i], raw_h[0]))
        
    print('FST results')
    print()
    
    for i in range(num_file-1):
        sumFST = 0.0
        for j in fst[i]:
            sumFST += j[1]
        tempStr = sys.argv[4 + i].split('/')
        filename = tempStr[len(tempStr) - 1]
        print('Avg FST result for ', sys.argv[4+i], ': ', np.round(sumFST/gSize,3))
        print('FST in windows of size ', gSize/numWindows, ': ')
        windowFST = fstWindows(fst[i], numWindows, gSize)
        print(windowFST)
        print()
        plt.figure(i)
        plt.title('FST in Windows')
        plt.xticks(range(numWindows))
        plt.xlabel('Windows')
        plt.ylabel('FST')
        plt.scatter(range(len(windowFST)), windowFST)
        plt.savefig(filename[:len(filename) - 3] + 'png', dpi=300)
        
def fstWindows(fst, numWindows, gSize):
    windSize = 1.0/numWindows
    ub = windSize
    wFST = 0.0
    wFSTs = []
    for pos in fst:
        if pos[0] <ub:
            wFST += pos[1]
        else:
            ub += windSize
            wFSTs.append(np.round((wFST/(windSize*gSize)),3))
            wFST = pos[1]
    wFSTs.append(np.round((wFST/(windSize*gSize)),3))
    return wFSTs
            
    
    
def generateFST(h_s, h_t):
    fst = []
    for key in h_s:
        if(h_s[key] == 0):
            continue
        fst.append([key, 1-(h_s[key]/h_t[key])])
    fst = sorted(fst, key = lambda m:m[0])
    return fst


def parsePopulation(lines):
    h = {}
    pos_raw = lines[2].split(" ");
    positions = []
    for i in range(1, len(pos_raw)):
        positions.append(float(pos_raw[i]))
    for i in range(0, len(lines[3]) - 1):
        num_zero = 0;
        for j in range(3, len(lines)):
            if(int(lines[j][i]) == 0):
                num_zero += 1
        temp_q = num_zero/(len(lines) - 3)
        h[positions[i]] = temp_q * (1-temp_q)
    return h


if __name__ == "__main__":
    main();