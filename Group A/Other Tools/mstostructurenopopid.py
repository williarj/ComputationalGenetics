import sys

def main():
    file = open(sys.argv[1], "r")
    fileW = open(sys.argv[2], "w")
    i = 0
    lines = []
    for line in file:
        lines.append(line)
        i += 1
    i = 0
    line = ""
    while i < len(lines[3]):
        line += "Site" + str(i) + "\t"
        i += 1
    fileW.write(line + "\n")
    i = 3
    index = 0
    while i < len(lines):
        fLine = lines[i]
        sLine = lines[i+1]
        i = i + 2
        j = 0
        line = "Sample" + str(index) + "\t"
        index += 1
        while j < len(fLine):
            count = 0
            if fLine[j] == "1":
                count += 1
            if sLine[j] == "1":
                count += 1
            line += str(count) + "\t"
            j += 1
        fileW.write(line + "\n")

if __name__ == "__main__":
    main()
