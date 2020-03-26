import sys

def main():
    file = open(sys.argv[1], "r");
    i = 0;
    lines = [];
    for line in file:
        i = i+1;
        if i > 3:
            lines.append(line);
    currSite = 0;
    chromLength = len(lines[0]);
    totalAlleles = len(lines)
    isNot = []
    isEq = []
    boundary = 0.06
    while (currSite < chromLength):
        currPair = 0;
        totalHetero = 0
        totalHomo0 = 0
        totalHomo1 = 0
        while (currPair < len(lines)):
            firstSite = lines[currPair][currSite:currSite+1];
            secondSite = lines[currPair+1][currSite:currSite+1];
            if (firstSite != secondSite):  
                totalHetero = totalHetero + 1;
            elif (firstSite == "0"):
                totalHomo0 = totalHomo0 + 1;
            else:
                totalHomo1 = totalHomo1 + 1;
            currPair = currPair+2;
        totalHetero = float(totalHetero);
        totalHomo0 = float(totalHomo0);
        totalHomo1 = float(totalHomo1);
        p = (2.0*totalHomo0+totalHetero)/totalAlleles;
        q = 1.0-p;
        observedHomo0 = float(totalHomo0/(totalAlleles/2));
        observedHomo1 = float(totalHomo1/(totalAlleles/2));
        observedHetero = float(totalHetero/(totalAlleles/2));
        expoHomo0 = p*p;
        expoHomo1 = q*q;
        expoHetero = 2*p*q;
        if ((not isInEq(observedHomo0, expoHomo0, boundary)) or (not isInEq(observedHetero, expoHetero, boundary)) or (not isInEq(observedHomo1, expoHomo1, boundary))):
            isNot.append(currSite)
        else:
            isEq.append(currSite)
        currSite += 1
    print("Site not in equillibrium:")
    print(isNot)
    print("Site in equillibrium: ")
    print(isEq)

def isInEq(observed, expo, boundary):
    diff = 0
    if (observed == 0 and expo == 0):
        diff = 0
    elif (observed == 0 and expo > boundary):
        diff = 1
    elif (expo == 0 and observed > boundary):
        diff = 1
    elif (observed == 0 or expo == 0):
        diff = 0
    else:
        diff = abs(observed-expo)/((expo+observed)/2)
    if (diff < boundary):
        return True
    else:
        return False
        
if __name__ == "__main__":
    main();
