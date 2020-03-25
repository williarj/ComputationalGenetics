import sys

def main():
    file = open(sys.argv[1], "r");
    i = 0;
    lines = [];
    for line in file:
        i = i+1;
        if i > 3:
            lines.append(line);
    currPair = 0;
    chromLength = len(lines[0]);
    totalHetero = 0;
    totalHomo1 = 0;
    totalHomo0 = 0;
    while (currPair < len(lines)):
        first = lines[currPair];
        second = lines[currPair+1];
        currPair = currPair + 2;
        currSite = 0;
        while (currSite < chromLength):
            firstSite = first[currSite:currSite+1];
            secondSite = second[currSite:currSite+1];
            if (firstSite != secondSite):  
                totalHetero = totalHetero + 1;
            elif (firstSite == "0"):
                totalHomo0 = totalHomo0 + 1;
            else:
                totalHomo1 = totalHomo1 + 1;
            currSite = currSite+1;
    totalHetero = float(totalHetero);
    totalHomo0 = float(totalHomo0);
    totalHomo1 = float(totalHomo1);
    totalAlleles = float(chromLength * len(lines));
    p = (2.0*totalHomo0+totalHetero)/totalAlleles;
    q = 1.0-p;
    observedHomo0 = float(totalHomo0/(totalAlleles/2));
    observedHomo1 = float(totalHomo1/(totalAlleles/2));
    observedHetero = float(totalHetero/(totalAlleles/2));
    expoHomo0 = p*p;
    expoHomo1 = q*q;
    expoHetero = 2*p*q;
    print("Observed Genotypes: ")
    print("00: ", observedHomo0)
    print("Hetero: ", observedHetero)
    print("11: ", observedHomo1)
    print("Expected Genotypes: ")
    print("00: ", expoHomo0)
    print("Hetero: ", expoHetero)
    print("11: ", expoHomo1)
    print("Percentage Difference From Observed and Expected")
    print("00: ", abs(observedHomo0-expoHomo0)/((expoHomo0+observedHomo0)/2))
    print("Hetero: ", abs(observedHetero-expoHetero)/((expoHetero+observedHetero)/2))
    print("11: ", abs(observedHomo1-expoHomo1)/((expoHomo1+observedHomo1)/2))

if __name__ == "__main__":
    main();
