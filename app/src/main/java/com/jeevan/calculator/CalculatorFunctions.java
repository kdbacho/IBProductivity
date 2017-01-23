package com.jeevan.calculator;

/**
 * Created by JeevanD on 16-04-17.
 */
public class CalculatorFunctions
{
    private static final double TOLLERANCE = 1e-10;
    // Gaussian elimination with partial pivoting
    public static RationalNumber[] gaussian(RationalNumber[][] A, RationalNumber[] b)
    {
        int N  = b.length;

        for (int p = 0; p < N; p++) {
            // find pivot row and swap
            int max = p;
            for (int i = p + 1; i < N; i++) {
                if (A[i][p].abs().greaterThan(A[max][p].abs()))
                    max = i;
            }
            RationalNumber[] temp = A[p]; A[p] = A[max]; A[max] = temp;
            RationalNumber  t    = b[p]; b[p] = b[max]; b[max] = t;

            // singular or nearly singular
            if (A[p][p].abs().getDecimal() <= TOLLERANCE) {
                throw new RuntimeException("Matrix is singular or nearly singular");
            }

            // pivot within A and b
            for (int i = p + 1; i < N; i++) {
                RationalNumber alpha = A[i][p].divide(A[p][p]);
                b[i] = b[i].add(alpha.multiply(b[p]).negate());
                for (int j = p; j < N; j++) {
                    A[i][j] = A[i][j].add(alpha.multiply(A[p][j]).negate());
                }
            }
        }

        // back substitution
        RationalNumber[] x = new RationalNumber[N];
        RationalNumber sum;
        for (int i = N - 1; i >= 0; i--) {
            sum = new RationalNumber(0,1);
            for (int j = i + 1; j < N; j++) {
                sum = sum.add(A[i][j].multiply(x[j]));
            }
            x[i] = b[i].add(sum.negate()).divide(A[i][i]);
        }
        return x;
    }

    public static RationalNumber dotProduct(RationalNumber a, RationalNumber b,
                                            RationalNumber c, RationalNumber x,
                                            RationalNumber y, RationalNumber z)
    {
        return a.multiply(x).add(b.multiply(y)).add(c.multiply(z));
    }

    public static RationalNumber[] crossProduct(RationalNumber a, RationalNumber b,
                                                RationalNumber c, RationalNumber x,
                                                RationalNumber y, RationalNumber z)
    {
        RationalNumber r1 = b.multiply(z).add(c.multiply(y).negate());
        RationalNumber r2 = c.multiply(x).add(a.multiply(z).negate());
        RationalNumber r3 = a.multiply(y).add(b.multiply(x).negate());
        RationalNumber[] vec = {r1, r2, r3};
        return vec;
    }
}
