package com.jeevan.calculator;

/**
 * Created by JeevanD on 16-04-17.
 */
public class RationalNumber
{
    public int numerator;
    public int denominator;

    public RationalNumber(int numerator, int denominator)
    {
        this.numerator = numerator;
        this.denominator = denominator;
        reduce();
    }

    public static int euclidGcd(int p, int q)
    {
        if (q == 0)
            return p;
        else
            return euclidGcd(q, p % q);
    }

    public static RationalNumber convert(double num)
    {
        Double d = num;
        String[] splitter = d.toString().split("\\.");
        int x = splitter[1].length();
        d*=Math.pow(10,x);
        int numerator = d.intValue();
        int denominator = (int)Math.pow(10, x);
        return new RationalNumber(numerator, denominator);
    }

    public void reduce()
    {
        int s = euclidGcd(Math.abs(numerator), Math.abs(denominator));
        numerator/=s;
        denominator/=s;
        if(numerator < 0 && denominator < 0)
        {
            numerator = Math.abs(numerator);
            denominator = Math.abs(denominator);
        }
        else if(denominator < 0)
        {
            numerator = -numerator;
            denominator = -denominator;
        }
    }

    public double getDecimal()
    {
        return ((double)numerator)/((double)denominator);
    }

    public RationalNumber add(RationalNumber other)
    {
        int num = this.numerator * other.denominator + other.numerator
                * this.denominator;
        int den = this.denominator * other.denominator;
        return new RationalNumber(num, den);
    }

    public RationalNumber multiply(RationalNumber other)
    {
        int num = this.numerator * other.numerator;
        int den = this.denominator * other.denominator;
        return new RationalNumber(num, den);
    }

    public RationalNumber divide(RationalNumber other)
    {
        int num = this.numerator * other.denominator;
        int den = this.denominator * other.numerator;
        return new RationalNumber(num, den);
    }

    public boolean greaterThan(RationalNumber other)
    {
        return this.numerator * other.denominator > this.denominator * other.numerator;
    }

    public boolean equals(RationalNumber other)
    {
        return this.numerator * other.denominator == this.denominator * other.numerator;
    }

    public RationalNumber abs()
    {
        int num = Math.abs(numerator);
        int den = Math.abs(denominator);
        return new RationalNumber(num, den);
    }

    public RationalNumber negate()
    {
        int num = -1*numerator;
        int den = denominator;
        return new RationalNumber(num,den);
    }

    public RationalNumber pow(int exp)
    {
        if(exp == 0)
            return new RationalNumber(1,1);
        RationalNumber r = new RationalNumber(1,1);
        for(int i = 1; i <= exp; i++)
            r = r.multiply(this);
        return r;
    }

    public String toString()
    {
        reduce();
        if(denominator == 1)
            return Integer.toString(numerator);
        return numerator + "/" + denominator;
    }

    public static RationalNumber parseRational(String s)
    {
        try
        {
            return new RationalNumber(Integer.parseInt(s),1);
        }catch (NumberFormatException e)
        {
            String[] tok = s.split("/");
            return new RationalNumber(Integer.parseInt(tok[0]), Integer.parseInt(tok[1]));
        }
    }
}
