import sys
import os
import matplotlib.pyplot as plt
import numpy as np
#usage: folder_name total subpop1 subpop2 ..
def main():
	num_file = len(sys.argv) - 2
	raw_h = []
	for i in range(0, num_file):
		file = open(sys.argv[2 + i])
		lines = file.readlines()
		raw_h.append(parsePopulation(lines))
	fst = []
	for i in range(1, len(raw_h)):
		fst.append(generateFST(raw_h[i], raw_h[0]))
	for i in range(0, num_file - 1):
		tempStr = sys.argv[3 + i].split('/')
		filename = tempStr[len(tempStr) - 1]
		position = []
		temp_fst = []
		for j in range(0, len(fst[i])):
			position.append(fst[i][j][0])
			temp_fst.append(fst[i][j][1])
		plt.figure(i)
		temp_fst_1 = sorted(temp_fst)
		quartile = np.percentile(temp_fst_1, [25, 75])
		iqr = quartile[1] - quartile[0]
		upperbound = quartile[1] + 1.5 * iqr
		lowerbound = quartile[0] - 1.5 * iqr
		plt.xticks(np.arange(0, 1, 0.1), rotation=90)
		plt.title('FST vs Sites w/ Outlier Boundaries')
		plt.xlabel('Site')
		plt.ylabel('FST')
		plt.plot(position, np.ones(len(position)) * upperbound, c='r', label='Upperbound')
		plt.plot(position, np.ones(len(position)) * lowerbound, c='g', label = 'Lowerbound')
		plt.scatter(position, temp_fst, s=5)
		plt.legend(bbox_to_anchor=(0.8, 1.15), loc='upper left', borderaxespad=0.)
		if not os.path.exists(sys.argv[1]):
			os.makedirs(sys.argv[1])
		plt.savefig(sys.argv[1] + '/' + filename[:len(filename) - 3] + 'png', dpi=300)



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
		num_zero = 0;
		for j in range(3, len(lines)):
			if(int(lines[j][i]) == 0):
				num_zero += 1
		temp_q = num_zero/(len(lines) - 3)
		h[positions[i]] = temp_q * (1-temp_q)
	return h


if __name__ == "__main__":
    main();