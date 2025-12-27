package com.mortgage_plan_calculator;

public class InterchangeableValues
{
//////Member variables////////////////////////////////////////////////////////////////////////////////////////////////////
    private double mEquity;
    private int mRepaymentPeriod;
    private double mTotalMonthlyPayment;
    private int mRepaymentPeriodLimit;
    private double mLoanAmountLimit;
    private double mLoanAmountAfterEquity;

//////Constructor////////////////////////////////////////////////////////////////////////////////////////////////////
    public InterchangeableValues (double equity, int repaymentPeriod, double totalMonthlyPayment, int repaymentPeriodLimit, double loanAmountLimit)
    {
        mEquity = equity;
        mRepaymentPeriod = repaymentPeriod;
        mTotalMonthlyPayment = totalMonthlyPayment;
        mRepaymentPeriodLimit = repaymentPeriodLimit;
        mLoanAmountLimit = loanAmountLimit;
    }

    //////Get functions////////////////////////////////////////////////////////////////////////////////////////////////////
    public double getEquity()                                               { return mEquity; }
    public int getRepaymentPeriod()                                         { return mRepaymentPeriod; }
    public double getTotalMonthlyPayment()                                  { return mTotalMonthlyPayment; }
    public int getRepaymentPeriodLimit()                                    { return mRepaymentPeriodLimit; }
    public double getLoanAmountLimit()                                      { return mLoanAmountLimit; }
    public double getLoanAmountAfterEquity()                                { return mLoanAmountAfterEquity; }

//////Set functions////////////////////////////////////////////////////////////////////////////////////////////////////
    public void setEquity(double equity)                                    { mEquity = equity; }
    public void setRepaymentPeriod(int repaymentPeriod)                     { mRepaymentPeriod = repaymentPeriod; }
    public void setTotalMonthlyPayment(double totalMonthlyPayment)          { mTotalMonthlyPayment = totalMonthlyPayment; }
    public void setRepaymentPeriodLimit(int repaymentPeriodLimit)           { mRepaymentPeriodLimit = repaymentPeriodLimit; }
    public void setLoanAmountLimit(double loanAmountLimit)                  { mLoanAmountLimit = loanAmountLimit; }
    public void setLoanAmountAfterEquity(double loanAmountAfterEquity)      { mLoanAmountAfterEquity = loanAmountAfterEquity; }
}
