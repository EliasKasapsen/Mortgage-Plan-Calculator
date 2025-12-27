package com.mortgage_plan_calculator;
import java.util.ArrayList;

public final class Calculations
{
//////Member variables////////////////////////////////////////////////////////////////////////////////////////////////////
    private final InputValues mValues;
    private final ArrayList<Double> mMonthlyLoanRepaymentList;
    private final ArrayList<Double> mMonthlyInterestPaymentList;
    private final ArrayList<Double> mMonthlyTotalPaymentList;
    private final ArrayList<Double> mRemainingLoanAmountList;
    private double mTotalInterestPayment;
    private double mTotalFee;
    private double mTotalPayment;
    
//////Constructor////////////////////////////////////////////////////////////////////////////////////////////////////
    public Calculations(InputValues a)
    {
        mValues = a;
        mMonthlyLoanRepaymentList = new ArrayList<>();
        mMonthlyInterestPaymentList = new ArrayList<>();
        mMonthlyTotalPaymentList = new ArrayList<>();
        mRemainingLoanAmountList = new ArrayList<>();
        mTotalInterestPayment = 0.0;
        mTotalFee = 0.0;
        mTotalPayment = 0.0;

        calculatePaymentPlan();
    }

//////Calculation functions////////////////////////////////////////////////////////////////////////////////////////////////////
    public void calculatePaymentPlan()
    {
        MandatoryValues mandatoryValues = mValues.getMandatoryValues();
        InterchangeableValues interchangeableValues = mValues.getInterchangeableValues();
        
        double loanAmount = mandatoryValues.getLoanAmount() - interchangeableValues.getEquity();
        interchangeableValues.setLoanAmountAfterEquity(loanAmount);
        double monthlyInterestRate = mandatoryValues.getInterestRate() / 100 / 12;
        int repaymentPeriod = interchangeableValues.getRepaymentPeriod();
        double establishmentFee = mandatoryValues.getEstablishmentFee();
        double adminFee = mandatoryValues.getAdminFee();
        double totalMonthlyPayment = interchangeableValues.getTotalMonthlyPayment();
        int repaymentPeriodLimit = interchangeableValues.getRepaymentPeriodLimit();
        double loanAmountLimit = interchangeableValues.getLoanAmountLimit();

        if (repaymentPeriod != 0)
        {
            totalMonthlyPayment =
            (loanAmount * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, repaymentPeriod)) /
            (Math.pow(1 + monthlyInterestRate, repaymentPeriod) - 1);
            interchangeableValues.setTotalMonthlyPayment(totalMonthlyPayment);

            repaymentPeriodCalculation(loanAmount, monthlyInterestRate, establishmentFee, adminFee, repaymentPeriod, totalMonthlyPayment, repaymentPeriodLimit, loanAmountLimit);
        }
        else if (totalMonthlyPayment != 0)
        {
            totalMonthlyPaymentCalculation(loanAmount, monthlyInterestRate, establishmentFee, adminFee, totalMonthlyPayment, repaymentPeriodLimit, loanAmountLimit);
        }
    }

//////Helper functions////////////////////////////////////////////////////////////////////////////////////////////////////
    public void repaymentPeriodCalculation(
        double loanAmount, 
        double monthlyInterestRate, 
        double establishmentFee,
        double adminFee,
        int repaymentPeriod, 
        double totalMonthlyPayment, 
        int repaymentPeriodLimit, 
        double loanAmountLimit)
    {
        InterchangeableValues interchangeableValues = mValues.getInterchangeableValues();

        double interestPayment;
        double monthlyLoanRepayment;
        
        if (repaymentPeriodLimit != 0) { repaymentPeriod = repaymentPeriodLimit + 1; }

        for (int i = 0; i < repaymentPeriod - 1; i++)
        {
            interestPayment = (loanAmount * monthlyInterestRate);
            monthlyLoanRepayment = totalMonthlyPayment - interestPayment;
            loanAmount -= monthlyLoanRepayment;
            
            mMonthlyLoanRepaymentList.add(monthlyLoanRepayment);
            mMonthlyInterestPaymentList.add(interestPayment);
            mMonthlyTotalPaymentList.add(totalMonthlyPayment + adminFee);
            mRemainingLoanAmountList.add(loanAmount);

            mTotalInterestPayment += interestPayment;
            mTotalFee += adminFee;
            mTotalPayment += totalMonthlyPayment + adminFee;
            
            if (loanAmountLimit != 0 && loanAmount < loanAmountLimit)
            {
                interchangeableValues.setRepaymentPeriod(i + 1);
                mTotalFee += establishmentFee;
                mTotalPayment += establishmentFee;
                return;
            }
        }

        if (repaymentPeriodLimit != 0) {
            mTotalFee += establishmentFee;
            mTotalPayment += establishmentFee;
            return;
        }
        
        if (repaymentPeriodLimit == 0 && loanAmountLimit == 0)
        {
            lastMonthCalculation(loanAmount, monthlyInterestRate, establishmentFee, adminFee);
        }
        
    }

    public void totalMonthlyPaymentCalculation(
        double loanAmount, 
        double monthlyInterestRate, 
        double establishmentFee,
        double adminFee,
        double totalMonthlyPayment, 
        int repaymentPeriodLimit, 
        double loanAmountLimit)
    {
        InterchangeableValues interchangeableValues = mValues.getInterchangeableValues();
        
        double interestPayment;
        double monthlyLoanRepayment;
        int calculatedRepaymentPeriod = 0;

        while (totalMonthlyPayment < loanAmount)
        {
            interestPayment = monthlyInterestRate * loanAmount;
            monthlyLoanRepayment = totalMonthlyPayment - interestPayment - adminFee;
            loanAmount -= monthlyLoanRepayment;

            mMonthlyLoanRepaymentList.add(monthlyLoanRepayment);
            mMonthlyInterestPaymentList.add(interestPayment);
            mMonthlyTotalPaymentList.add(totalMonthlyPayment);
            mRemainingLoanAmountList.add(loanAmount);

            mTotalInterestPayment += interestPayment;
            mTotalFee += adminFee;
            mTotalPayment += totalMonthlyPayment + adminFee;
            calculatedRepaymentPeriod++;

            if (repaymentPeriodLimit != 0 && calculatedRepaymentPeriod == repaymentPeriodLimit)
            { 
                interchangeableValues.setRepaymentPeriod(calculatedRepaymentPeriod);
                mTotalFee += establishmentFee;
                mTotalPayment += establishmentFee;
                return;
            }

            if (loanAmountLimit != 0 && loanAmount < loanAmountLimit)
            {
                interchangeableValues.setRepaymentPeriod(calculatedRepaymentPeriod);
                mTotalFee += establishmentFee;
                mTotalPayment += establishmentFee;
                return;
            }
        }

        interchangeableValues.setRepaymentPeriod(calculatedRepaymentPeriod + 1);

        if (repaymentPeriodLimit == 0 && loanAmountLimit == 0)
        {
            lastMonthCalculation(loanAmount, monthlyInterestRate, establishmentFee, adminFee);
        }
    }

    public void lastMonthCalculation(
        double loanAmount, 
        double monthlyInterestRate, 
        double establishmentFee, 
        double adminFee)
    {
        double interestPayment = (monthlyInterestRate * loanAmount);
        double monthlyLoanRepayment = loanAmount;
        double totalMonthlyPayment = monthlyLoanRepayment + interestPayment;
        
        mMonthlyLoanRepaymentList.add(monthlyLoanRepayment);
        mMonthlyInterestPaymentList.add(interestPayment);
        mMonthlyTotalPaymentList.add(totalMonthlyPayment);
        mRemainingLoanAmountList.add(0.0);

        mTotalInterestPayment += interestPayment;
        mTotalFee += adminFee + establishmentFee;
        mTotalPayment += totalMonthlyPayment + adminFee + establishmentFee;
    }
    
//////Get functions////////////////////////////////////////////////////////////////////////////////////////////////////
    public InputValues getValues() { return mValues; }
    public double getMonthlyTotalPaymentAtIndex(int index)      { return mMonthlyTotalPaymentList.get(index); }
    public double getMonthlyLoanRepaymentAtIndex(int index)     { return mMonthlyLoanRepaymentList.get(index); }
    public double getMonthlyInterestPaymentAtIndex(int index)   { return mMonthlyInterestPaymentList.get(index); }
    public double getRemainingLoanAmountAtIndex(int index)      { return mRemainingLoanAmountList.get(index); }
    public double getTotalInterestPayment()                     { return mTotalInterestPayment; }
    public double getTotalFee()                                 { return mTotalFee; }
    public double getTotalPayment()                             { return mTotalPayment; }
}
