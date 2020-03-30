import sys, argparse

parser = argparse.ArgumentParser(description="Given pi and S estimates returns Tajima's D statistic")
parser.add_argument("N", type=int, help="Sample size")
parser.add_argument("p", type=float, help="Pi")
parser.add_argument("S", type=float, help="S")
args = parser.parse_args()

N = args.N

a1 = sum([1.0/x for x in range(1, N)])
a2 = sum([1.0/(x**2) for x in range(1, N)])
b1 = (N+1.0)/(3*(N-1))
b2 = (2*(N**2+N+3.0))/(9*N*(N-1))
c1 = b1-1/a1
c2 = b2-(N+2.0)/(a1*N)+a2/(a1**2)
e1 = c1/a1
e2 = c2/(a1**2+a2)

D = (args.p-args.S/a1)/((e1*args.S+e2*args.S*(args.S-1))**0.5)

print("D=%s\nN=%s\npi=%s\nS=%s\na1=%s\na2=%s\nb1=%s\nb2=%s\nc1=%s\nc2=%s\ne1=%s\ne2=%s" % 
      (D, N, args.p, args.S, a1, a2, b1, b2, c1, c2, e1, e2))
