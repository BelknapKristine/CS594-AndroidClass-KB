package com.belknap.calc;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Command implements Parcelable
{
	private String name;
	private int comm = 0;
	private int rank = 20;
	private boolean hasDec = false;
	private ArrayList<Number> operands;

	public Command(String nm)
	{
		name = nm;
		if (name == "add")
		{
			comm = 1;
			rank = 4;
		} else if (name == "sub")
		{
			comm = 2;
			rank = 4;
		} else if (name == "mul")
		{
			comm = 3;
			rank = 3;
		} else if (name == "div")
		{
			comm = 4;
			rank = 3;
			hasDec = true;
		} else if (name == "pow")
		{
			comm = 5;
			rank = 2;
		} else if (name == "fac")
		{
			comm = 6;
			rank = 2;
		}
	}

	public void setOperands(ArrayList<Number> prnds, boolean hsDc)
	{
		operands = prnds;
		if (hasDec || hsDc)
			hasDec = true;
	}

	public void addOperands(ArrayList<Number> prnds, boolean hsDc)
	{
		operands.addAll(prnds);
		if (hasDec || hsDc)
			hasDec = true;
	}

	public void updateOperands(int ndx, Number prnd, boolean hsDc)
	{
		operands.set(ndx, prnd);
		if (hasDec || hsDc)
			hasDec = true;
	}

	public boolean checkDec()
	{
		return hasDec;
	}

	public Number operation()
	{
		Number result = 0;
		switch (comm)
		{
			case 1:
				if (hasDec)
					result = operands.get(0).doubleValue() + operands.get(1).doubleValue();
				else
					result = operands.get(0).intValue() + operands.get(1).intValue();
				break;
			case 2:
				if (hasDec)
					result = operands.get(0).doubleValue() - operands.get(1).doubleValue();
				else
					result = operands.get(0).intValue() - operands.get(1).intValue();
				break;
			case 3:
				if (hasDec)
					result = operands.get(0).doubleValue() * operands.get(1).doubleValue();
				else
					result = operands.get(0).intValue() * operands.get(1).intValue();
				break;
			case 4:
				result = operands.get(0).doubleValue() / operands.get(1).doubleValue();
				break;
			case 5:
				result = Math.pow(operands.get(0).doubleValue(), operands.get(1).doubleValue());
				break;
			case 6:
				for (int cnt = operands.get(0).intValue(); cnt > 0; cnt--)
					result = result.intValue() + cnt;
				break;
		}
		return result;
	}

	@Override
	public int describeContents()
	{
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags)
	{

	}
}