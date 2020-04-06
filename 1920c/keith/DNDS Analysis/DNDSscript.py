import sys
typeDict = {}

def main():
    file = open(sys.argv[1], "r")
    i = 0
    numSyn = 0
    numNonSyn = 0
    for line in file:
        i = i+1
        if "Genomes:" in line:
            break
        if i > 1:
            if "m2" in line:
                numNonSyn += 1
            elif "m1" in line:
                numSyn += 1
    dnds = float(numNonSyn)/float(numSyn)
    c = 1.0 - dnds
    print "Divergence:"
    print "DN: ", numNonSyn
    print "DS: ", numSyn
    print "DN/DS: ", dnds
    print "c: ", c
    if (dnds < 1):
        print "Negative Selection"
    elif (dnds == 1):
        print "Neutral Selection"
    elif (dnds > 1):
        print "Positive Selection"
    print str(c*100) + "% of mutations are bad"
    file = open(sys.argv[2], "r")
    i = 0
    genomesSyn = []
    genomesNon = []
    numSyn = 0
    numNonSyn = 0
    anzGnm = False
    for line in file:
        if "Genomes:" in line:
            anzGnm = True;
            i = i + 1
            continue
        elif not anzGnm and i > 1:
            if "m2" in line:
                typeDict[line.split(" ")[0]] = "n"
                numNonSyn += 1
            elif "m1" in line:
                typeDict[line.split(" ")[0]] = "s"
                numSyn += 1
        elif anzGnm:
            muts = line.rstrip("\n").split(" ")[2:]
            genomesSyn.append(getSyn(muts))
            genomesNon.append(getNon(muts))
        i = i + 1
    i = 0
    numDiff = 0
    numComp = 0
    while (i < len(genomesSyn)):
        j = i+1
        while (j < len(genomesSyn)):
            numDiff += len(Diff(genomesSyn[i], genomesSyn[j]))
            numComp += 1
            j += 1
        i += 1
    pS = float(numDiff)/float(numComp)
    numDiff = 0
    numComp = 0
    i = 0
    while (i < len(genomesNon)):
        j = i+1
        while (j < len(genomesNon)):
            numDiff += len(Diff(genomesNon[i], genomesNon[j]))
            numComp += 1
            j += 1
        i += 1
    pN = float(numDiff)/float(numComp)
    dnds = float(numNonSyn)/float(numSyn)
    pnps = float(pN)/float(pS)
    c = 1.0 - dnds
    print "Sample (mK):"
    print "DN: ", numNonSyn
    print "DS: ", numSyn
    print "DN/DS: ", dnds
    print "c: ", c
    if (dnds < 1):
        print "Negative Selection"
    elif (dnds == 1):
        print "Neutral Selection"
    elif (dnds < 1):
        print "Positive Selection"
    print str(c*100) + "% of mutations are bad"
    print "PN: ", pN
    print "PS: ", pS
    print "PN/PS", pnps
    if (pnps < dnds):
        print "Positive Selection"
    elif (pnps > dnds):
        print "Negative Selection"
    else:
        print "Neutral Selection"
    
def Diff(li1, li2): 
    li_dif = [i for i in li1 + li2 if i not in li1 or i not in li2] 
    return li_dif 

def getNon(muts):
    newList = []
    for mut in muts:
        if (typeDict[mut] == "n"):
            newList.append(mut)
    return newList

def getSyn(muts):
    newList = []
    for mut in muts:
        if (typeDict[mut] == "s"):
            newList.append(mut)
    return newList
        
if __name__ == "__main__":
    main()
