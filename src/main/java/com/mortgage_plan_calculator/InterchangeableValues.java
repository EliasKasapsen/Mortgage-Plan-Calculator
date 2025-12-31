package com.mortgage_plan_calculator;

public class InterchangeableValues
{
//////Member variables////////////////////////////////////////////////////////////////////////////////////////////////////
    private double mEquity;
    private double mEstablishmentFee;
    private double mAdminFee;
    private int mRepaymentPeriod;
    private double mTotalMonthlyPayment;
    private int mRepaymentPeriodLimit;
    private double mLoanAmountLimit;
    private double mLoanAmountAfterEquity;

//////Constructor////////////////////////////////////////////////////////////////////////////////////////////////////
    public InterchangeableValues (
        double equity,
        double establishmentFee,
        double adminFee,
        int repaymentPeriod,
        double totalMonthlyPayment,
        int repaymentPeriodLimit,
        double loanAmountLimit
    )
    {
        mEquity = equity;
        mRepaymentPeriod = repaymentPeriod;
        mTotalMonthlyPayment = totalMonthlyPayment;
        mRepaymentPeriodLimit = repaymentPeriodLimit;
        mLoanAmountLimit = loanAmountLimit;
        mEstablishmentFee = establishmentFee;
        mAdminFee = adminFee;
    }

    //////Get functions////////////////////////////////////////////////////////////////////////////////////////////////////
    public double getEquity()                                               { return mEquity; }
    public double getEstablishmentFee()                                     { return mEstablishmentFee; }
    public double getAdminFee()                                             { return mAdminFee; }
    public int getRepaymentPeriod()                                         { return mRepaymentPeriod; }
    public double getTotalMonthlyPayment()                                  { return mTotalMonthlyPayment; }
    public int getRepaymentPeriodLimit()                                    { return mRepaymentPeriodLimit; }
    public double getLoanAmountLimit()                                      { return mLoanAmountLimit; }
    public double getLoanAmountAfterEquity()                                { return mLoanAmountAfterEquity; }

//////Set functions////////////////////////////////////////////////////////////////////////////////////////////////////
    public void setEquity(double equity)                                    { mEquity = equity; }
    public void setEstablishmentFee(double establishmentFee)                { mEstablishmentFee = establishmentFee; }
    public void setAdminFee(double adminFee)                                { mAdminFee = adminFee; }
    public void setRepaymentPeriod(int repaymentPeriod)                     { mRepaymentPeriod = repaymentPeriod; }
    public void setTotalMonthlyPayment(double totalMonthlyPayment)          { mTotalMonthlyPayment = totalMonthlyPayment; }
    public void setRepaymentPeriodLimit(int repaymentPeriodLimit)           { mRepaymentPeriodLimit = repaymentPeriodLimit; }
    public void setLoanAmountLimit(double loanAmountLimit)                  { mLoanAmountLimit = loanAmountLimit; }
    public void setLoanAmountAfterEquity(double loanAmountAfterEquity)      { mLoanAmountAfterEquity = loanAmountAfterEquity; }
}
