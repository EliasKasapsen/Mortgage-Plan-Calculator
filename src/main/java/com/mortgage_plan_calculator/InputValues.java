package com.mortgage_plan_calculator;

public class InputValues
{
//////Member variables////////////////////////////////////////////////////////////////////////////////////////////////////
    MandatoryValues mMandatoryValues;
    InterchangeableValues mInterchangeableValues;
    
//////Constructor////////////////////////////////////////////////////////////////////////////////////////////////////
    public InputValues(MandatoryValues mandatoryValues, InterchangeableValues interchangeableValues)
    {
        mMandatoryValues = mandatoryValues;
        mInterchangeableValues = interchangeableValues;
    }
    
//////Get functions////////////////////////////////////////////////////////////////////////////////////////////////////
    public MandatoryValues getMandatoryValues()                 { return mMandatoryValues; }
    public InterchangeableValues getInterchangeableValues()     { return mInterchangeableValues; }
}
