import sys
from itertools import combinations
import functools

class Mutation:
	def __init__(self, kind, position, num):
		self.type = kind
		self.position = position
		self.num = num

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
	selection1 = []
	selection2 = []
	neutralRange = Range(float(sys.argv[3]), float(sys.argv[4]))
	for r in gRanges1:
		dnds = DNDS(popInfo1, r)
		pnps = PNPS(popInfo1, r)
		temp = 0.0
		if(dnds == 0):
			temp = sys.float_info.max
		else:
			temp = pnps/dnds
		if temp >= neutralRange.start and temp <= neutralRange.end:
			selection1.append("0")
		elif temp < neutralRange.start:
			selection1.append("+")
		else:
			selection1.append("-")

	for r in gRanges2:
		dnds = DNDS(popInfo2, r)
		pnps = PNPS(popInfo2, r)
		temp = 0.0
		if(dnds == 0):
			temp = sys.float_info.max
		else:
			temp = pnps/dnds
		if temp >= neutralRange.start and temp <= neutralRange.end:
			selection2.append("0")
		elif temp < neutralRange.start:
			selection2.append("+")
		else:
			selection2.append("-")
	print("Gene Locations for Population 1:")
	for r in gRanges1:
		print(r.start, r.end)
	print("Gene Locations for Population 2:")
	for r in gRanges2:
		print(r.start, r.end)
	print(selection1)
	print(selection2)




def DNDS(popInfo, gRange):
	dn = 0.0
	ds = 0.0
	for i in popInfo[1]:
		for s in i:
			if s.position in range(gRange.start, gRange.end):
				if(mTypeMap[s.type] == 1):
					ds += 1
				elif(mTypeMap[s.type] == 2):
					dn += 1
	if(ds == 0):
		return sys.float_info.max
	return dn/ds

def PNPS(popInfo, gRange):
	pn = 0.0
	ps = 0.0
	for m in popInfo[0].values():
		if m.position in range(gRange.start, gRange.end):
			temp = m.num * (popInfo[2] - m.num)
			if(mTypeMap[m.type] == 1):
				ps += temp
			elif(mTypeMap[m.type] == 2):
				pn += temp
	if(ps == 0):
		return sys.float_info.max
	return pn/ps
    


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
		lines[i] = lines[i][:len(lines[i])]
		temp = lines[i].split(" ")
		localMMap[temp[0]] = Mutation(temp[2], int(temp[3]), int(temp[8]))
		i += 1

	result.append(localMMap)
	i += 1
	pNum = 0
	while(i < len(lines)):
		pNum += 1
		lines[i] = lines[i][:len(lines[i]) - 1]
		temp = lines[i].split(" ")
		j = 2
		individual = []
		while(j < len(temp)):
			if(temp[j] == ''):
				j+=1
				continue
			individual.append(localMMap[temp[j]])
			j += 1 
		individual = sorted(individual, key=lambda m: m.position)
		pop.append(individual)
		i+=1
	result.append(pop)
	result.append(pNum)
	return result
    	

if __name__ == "__main__":
    main();