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

	public static final Parcelable.Creator<Command> CREATOR = new Parcelable.Creator<Command>()
	{
		/**
		 * Create a new instance of the Parcelable class, instantiating it
		 * from the given Parcel whose data had previously been written by
		 * {@link android.os.Parcelable#writeToParcel Parcelable.writeToParcel()}.
		 *
		 * @param source The Parcel to read the object's data from.
		 * @return Returns a new instance of the Parcelable class.
		 */
		@Override
		public Command createFromParcel(Parcel source)
		{
			return null;
		}

		/**
		 * Create a new array of the Parcelable class.
		 *
		 * @param size Size of the array.
		 * @return Returns an array of the Parcelable class, with every entry
		 * initialized to null.
		 */
		@Override
		public Command[] newArray(int size)
		{
			return new Command[0];
		}
	};

	/**
	 * Describe the kinds of special objects contained in this Parcelable's
	 * marshalled representation.
	 *
	 * @return a bitmask indicating the set of special object types marshalled
	 * by the Parcelable.
	 */
	@Override
	public int describeContents()
	{
		return 0;
	}

	/**
	 * Flatten this object in to a Parcel.
	 *
	 * @param dest  The Parcel in which the object should be written.
	 * @param flags Additional flags about how the object should be written.
	 *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
	 */
	@Override
	public void writeToParcel(Parcel dest, int flags)
	{

	}
}