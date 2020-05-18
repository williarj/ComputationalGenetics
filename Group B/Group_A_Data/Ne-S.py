import sys
import os
import numpy as np
#usage: mutation_rate genome_size subpop1 subpop2 .. s/4*mu
def main():
	num_file = len(sys.argv) - 3
	mu = float(sys.argv[1])
	genome_size = float(sys.argv[2])
	for i in range(0, num_file):
		file = open(sys.argv[3 + i])
		lines = file.readlines()
		s = int(lines[1].split(' ')[1])
		ne = s/(4*mu*genome_size)
		print('Ne for ' + sys.argv[3 + i] + ' is ' + str(ne))



if __name__ == "__main__":
    main();