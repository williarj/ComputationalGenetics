import sys
import os
import numpy as np
#usage: n total subpop1
def main():
	num_file = len(sys.argv) - 2
	n = int(sys.argv[1])
	raw_h = []
	for i in range(0, num_file):
		file = open(sys.argv[2 + i])
		lines = file.readlines()
		raw_h.append(parsePopulation(lines))
	fst = []
	for i in range(1, len(raw_h)):
		fst.append(generateFST(raw_h[i], raw_h[0]))
	tempStr = sys.argv[3].split('/')
	filename = tempStr[len(tempStr) - 1]
	temp_fst = []
	for j in range(0, len(fst[0])):
		temp_fst.append(fst[0][j][1])
	fst_mean = np.mean(temp_fst)
	migration_rate = abs((1-fst_mean)/(4*n*fst_mean))
	print('Migration rate for' + filename + ': ' + str(migration_rate))



def generateFST(h_s, h_t):
	fst = []
	for key in h_s:
		if(h_t[key] == 0):
			continue
		fst.append([key, 1-(h_s[key]/h_t[key])])
	return fst


def parsePopulation(lines):
	h = {}
	pos_raw = lines[2].split(" ");
	positions = []
	for i in range(1, len(pos_raw)):
		positions.append(float(pos_raw[i]))
	for i in range(0, len(lines[3]) - 1):
		num_zero = 0.0;
		for j in range(3, len(lines)):
			if(float(lines[j][i]) == 0):
				num_zero += 1
		temp_q = num_zero/(len(lines) - 3)
		print(num_zero)
		h[positions[i]] = temp_q * (1-temp_q)
	return h


if __name__ == "__main__":
    main();