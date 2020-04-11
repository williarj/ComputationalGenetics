import sys
from itertools import combinations
import functools

class Mutation:
	def __init__(self, kind, position):
		self.type = kind
		self.position = position

class Range:
	def __init__(self, start, end):
		self.start = start
		self.end = end

# 0 - non-coding
# 1 - synonymous
# 2 - nonsynonymous
mTypeMap = {"m1" : 0, "m2" : 1, "m3" : 2, "m4" : 2}

def main():
    file1 = open(sys.argv[1], 'r')
    lines1 = file1.readlines()
    file2 = open(sys.argv[2], 'r')
    lines2 = file2.readlines()
    popInfo1 = parsePopulation(lines1, 2)
    popInfo2 = parsePopulation(lines2, 2)
    sorted1 = sorted(list(popInfo1[0].values()), key=lambda m: m.position)
    sorted2 = sorted(list(popInfo2[0].values()), key=lambda m: m.position)
    gRanges1 = findRanges(sorted1)
    gRanges2 = findRanges(sorted2)
    


def findRanges(sortedList):
	gRanges = []
	isGene = False
	start = -1
	end = -1
	for g in sortedList:
		if(mTypeMap[g.type] > 0):
			isGene = True
			if(start < 0):
				start = g.position
			end = g.position
		elif(isGene):
			gRanges.append(Range(start, end))
			start = -1
			end = -1
			isGene = False
	return gRanges

def parsePopulation(lines, index):
	result = []
	localMMap = {}
	pop = []
	i = index
	while(lines[i] != "Genomes:\n"):
		lines[i] = lines[i][:len(lines[i]) - 2]
		temp = lines[i].split(" ")
		localMMap[temp[0]] = Mutation(temp[2], int(temp[3]))
		i += 1

	result.append(localMMap)
	i += 1
	while(i < len(lines)):
		lines[i] = lines[i][:len(lines[i]) - 2]
		temp = lines[i].split(" ")
		j = 2
		individual = []
		while(j < len(temp)):
			if(temp[j] == ''):
				j+=1
				continue
			individual.append(localMMap[temp[j]])
			j += 1 
		sorted(individual, key=lambda m: m.position)
		pop.append(individual)
		i+=1
	result.append(pop)
	return result
    	

if __name__ == "__main__":
    main();