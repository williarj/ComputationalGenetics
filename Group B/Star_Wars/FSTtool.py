import sys
import matplotlib.pyplot as pyplot

#usage: folder_name total subpop1 subpop2 ..
def main:
	num_file = sys.argc - 2
	raw_h = []
	for i in range(0, num_file):
		file = open(sys.argv[2 + i])
		lines = file.readlines()
		raw_h.append(parsePopulation(lines))
	fst = []
	for i in range(1, len(raw_h)):
		fst.append(generateFST(raw_h[i], raw_h[0]))




def generateFST(h_s, h_t):
	fst = []
	for i in range(0, len(h_s)):
		fst.append(1-(h_s[i]/h_t[i]))
	return fst


def parsePopulation(lines):
	h = []
	for i in range(0, len(lines[3])):
		num_zero = 0;
		for j in range(3, len(lines)):
			if(int(lines[j][i]) == 0):
				num_zero += 1
		temp_q = num_zero/(len(lines) - 3)
		h.append(temp_q * (1-temp_q))
	return h


if __name__ == "__main__":
    main();