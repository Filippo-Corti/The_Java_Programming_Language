import z3 as z3
import numpy as np

#Definitely not made by me but I just can't spend more time on this

def part2():
    """
    Solve for 9 variables:

    three points in times: t,u,v
    stone starting location: a,b,c
    stone starting speed: d,e,f

    stone:  S(t) = (a,b,c) + (d,e,f)*t
    hail 0: H0(t) = A0 + t*V0
    hail 1: H1(t) = A1 + t*V1
    hail 2: H2(t) = A2 + t*V2

    The stone trajectory intersects the three hails at three different times:
    S(t) = H0(t)
    S(u) = H1(u)
    S(v) = H2(v)

    Each of these gives rise to three equations (in the x, y, and z axes),
    giving the total of nine below.
    """
    puzzle = np.fromregex('input.txt', r"-?\d+", [('', np.int64)]).astype(np.int64).reshape(-1, 2, 3)
    three = puzzle[:3]
    t, u, v, a, b, c, d, e, f = z3.Reals("t u v a b c d e f")
    (A0x, A0y, A0z), (V0x, V0y, V0z) = three[0]
    (A1x, A1y, A1z), (V1x, V1y, V1z) = three[1]
    (A2x, A2y, A2z), (V2x, V2y, V2z) = three[2]
    eqs = [
        a + t * d == A0x + t * V0x,
        b + t * e == A0y + t * V0y,
        c + t * f == A0z + t * V0z,
        a + u * d == A1x + u * V1x,
        b + u * e == A1y + u * V1y,
        c + u * f == A1z + u * V1z,
        a + v * d == A2x + v * V2x,
        b + v * e == A2y + v * V2y,
        c + v * f == A2z + v * V2z,
    ]
    s = z3.Solver()
    s.add(*eqs)
    s.check()
    r = s.model()
    pos = [r[x].as_long() for x in [a, b, c]]
    print(pos)
    print(sum(pos))


if __name__ == "__main__":
    part2()