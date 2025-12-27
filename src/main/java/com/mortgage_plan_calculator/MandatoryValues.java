package com.mortgage_plan_calculator;

public class MandatoryValues
{
    private final String mPlanName;
    private final double mLoanAmount;
    private final double mInterestRate;
    private final double mEstablishmentFee;
    private final double mAdminFee;

    public MandatoryValues (String planName, double loanAmount, double interestRate, double establishmentFee, double adminFee)
    {
        mPlanName = planName;
        mLoanAmount = loanAmount;
        mInterestRate = interestRate;
        mEstablishmentFee = establishmentFee;
        mAdminFee = adminFee;
    }

//////Get functions////////////////////////////////////////////////////////////////////////////////////////////////////
    public String getPlanName()             { return mPlanName; }
    public double getLoanAmount()           { return mLoanAmount; }
    public double getInterestRate()         { return mInterestRate; }
    public double getEstablishmentFee()     { return mEstablishmentFee; }
    public double getAdminFee()             { return mAdminFee; }
}
