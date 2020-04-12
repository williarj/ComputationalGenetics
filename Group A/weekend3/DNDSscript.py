import sys

def main():
    file = open(sys.argv[1], "r")
    i = 0
    numDict = {}
    typeDict = {}
    firstGenomesLine = -1
    lowerBound = 100044
    upperBound = 106478
    nonDiv = []
    synDiv = []
    anzGnm = False
    for line in file:
        if "Genomes:" in line:
            firstGenomesLine = i + 1
        elif firstGenomesLine == i:
            muts = line.rstrip("\n").split(" ")[2:]
            for mut in muts:
                if ((typeDict[mut] == "m1" or typeDict[mut] == "m2") and (int(numDict[mut]) > lowerBound and int(numDict[mut]) < upperBound)):
                    synDiv.append(numDict[mut])
                elif ((typeDict[mut] == "m3" or typeDict[mut] == "m4") and (int(numDict[mut]) > lowerBound and int(numDict[mut]) < upperBound)):
                    nonDiv.append(numDict[mut])
            break
        elif i > 2:
            numDict[line.split(" ")[0]] = line.split(" ")[3]
            typeDict[line.split(" ")[0]] = line.split(" ")[2]
        i += 1
    
    file = open(sys.argv[2], "r")
    i = 0
    numDict = {}
    typeDict = {}
    ratiosSym = []
    ratiosNon = []
    nonSam = []
    synSam = []
    anzGnm = False
    firstGenomesLine = -1
    totalInd = 0
    for line in file:
        if "Genomes:" in line:
            anzGnm = True
            firstGenomesLine = i + 1
        elif not anzGnm and i > 2:
            numDict[line.split(" ")[0]] = line.split(" ")[3]
            typeDict[line.split(" ")[0]] = line.split(" ")[2]
            if (line.split(" ")[2] == "m2"):
                ratiosSym.append(line.split(" ")[8])
            elif line.split(" ")[2] == "m3" or line.split(" ")[2] == "m4":
                ratiosNon.append(line.split(" ")[8])
        elif anzGnm:
            muts = line.rstrip("\n").split(" ")[2:]
            for mut in muts:
                if ((typeDict[mut] == "m1" or typeDict[mut] == "m2") and (int(numDict[mut]) > lowerBound and int(numDict[mut]) < upperBound)):
                    synSam.append(numDict[mut])
                elif ((typeDict[mut] == "m3" or typeDict[mut] == "m4") and (int(numDict[mut]) > lowerBound and int(numDict[mut]) < upperBound)):
                    nonSam.append(numDict[mut])
            totalInd += 1
        i += 1

    dN = len(Diff(nonDiv, nonSam))
    dS = len(Diff(synDiv, synSam))
    dNdS = float(dN)/float(dS)
    c = 1.0 - dNdS

    pN = float(sumRatios(ratiosNon, totalInd))/float(nChoose2(totalInd))
    pS = float(sumRatios(ratiosSym, totalInd))/float(nChoose2(totalInd))
    pNpS = float(pN)/float(pS)

    print "dN: ", dN
    print "dS: ", dS
    print "dN/dS: ", dNdS
    print "c: ", c
    if (dNdS < 1):
        print "Negative Selection"
    elif (dNdS == 1):
        print "Neutral Selection"
    elif (dNdS > 1):
        print "Positive Selection"
    print str(c * 100) + "% of mutations are bad"

    print "pN: ", pN
    print "pS: ", pS
    print "pN/pS:", pNpS
    if (pNpS < dNdS):
        print "Positive Selection"
    elif (pNpS > dNdS):
        print "Negative Selection"
    else:
        print "Neutral Selection"
    
def Diff(li1, li2): 
    li_dif = [i for i in li1 + li2 if i not in li1 or i not in li2] 
    return li_dif

def sumRatios(ratios, totalInd):
    totalSum = 0;
    for ratio in ratios:
        ratio = int(ratio)
        totalSum += ratio * (totalInd - ratio)
    return totalSum

def nChoose2(n):
    return (n * (n - 1) ) / 2
        
if __name__ == "__main__":
    main()
