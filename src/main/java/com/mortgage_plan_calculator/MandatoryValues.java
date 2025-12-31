package com.mortgage_plan_calculator;

public class MandatoryValues
{
    private final String mPlanName;
    private final double mLoanAmount;
    private final double mInterestRate;

    public MandatoryValues (String planName, double loanAmount, double interestRate)
    {
        mPlanName = planName;
        mLoanAmount = loanAmount;
        mInterestRate = interestRate;
    }

//////Get functions////////////////////////////////////////////////////////////////////////////////////////////////////
    public String getPlanName()             { return mPlanName; }
    public double getLoanAmount()           { return mLoanAmount; }
    public double getInterestRate()         { return mInterestRate; }
}
