import sys
from itertools import combinations

class GenomePair:
    def __init__(self, a="", b=""):
        self.geneA = a
        self.geneB = b


def main():
    file = open(sys.argv[1], 'r')
    lines = file.readlines()
    chromeLength = len(lines[3])
    population = [];
    i = 3
    while(i < len(lines) - 1):
        temp = GenomePair(lines[i], lines[i+1])
        population.append(temp)
        i += 2
    s = 0
    for i in range(0, chromeLength):
        diffA = False
        diffB = False
        firstA = population[0].geneA[i]
        firstB = population[0].geneB[i]
        for j in range(1, len(population)):
            if(population[j].geneA[i] != firstA):
                diffA = True
            if(population[j].geneB[i] != firstB):
                diffB = True
        if(diffA):
            s += 1
        if(diffB):
            s += 1

    comb = combinations(population, 2)
    count = 0
    diff_sum = 0.0
    for pair in comb:
        count += 1
        for i in range(0, chromeLength):
            if(pair[0].geneA[i] != pair[1].geneA[i]):
                diff_sum += 1
            if(pair[0].geneB[i] != pair[1].geneB[i]):
                diff_sum += 1

    pi = diff_sum/count

    print("Size of population: ", len(population))
    print("Length of Chromosome: ", chromeLength)
    print("S: ", s)
    print("Pi: ", pi)

    
        
if __name__ == "__main__":
    main();
