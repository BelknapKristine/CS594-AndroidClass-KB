package com.belknap.calc;

import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Kristine on 5/3/2015.
 */
public class CalcUtil
{
	public TextView calcText;
	public ArrayList<Command> pendingOps;
	public String currentText = "", incomingText = "";
	public boolean hasDec = false;
	public int cntDec = 0;
	public long currOperandI = 0, currOperandD = 0;

	public void processOps()
	{
		ArrayList<Number> operands = newOperands();
		if (pendingOps.size() > 0)
		{
			pendingOps.get(pendingOps.size() - 1).addOperands(operands, hasDec);
			currentText += "=";
			for (int cntOps = 0; cntOps < pendingOps.size(); cntOps++)
			{
				if (pendingOps.get(cntOps).checkDec())
				{
					double tmp = pendingOps.get(cntOps).operation().doubleValue();
					if (cntOps == pendingOps.size() - 1)
						currentText += String.valueOf(tmp);
					if (cntOps < pendingOps.size() - 1)
						pendingOps.get(cntOps + 1).updateOperands(0, tmp, true);
				} else
				{
					long tmp = pendingOps.get(cntOps).operation().longValue();
					if (cntOps == pendingOps.size() - 1)
						currentText += String.valueOf(tmp);
					if (cntOps < pendingOps.size() - 1)
						pendingOps.get(cntOps + 1).updateOperands(0, tmp, false);
				}
			}
			calcText.setText(currentText);
			currentText = "";
			pendingOps.clear();
		}
		resetCurrOperand();
	}

	public void resetCurrOperand()
	{
		hasDec = false;
		cntDec = 0;
		currOperandI = 0;
		currOperandD = 0;
		incomingText = "";
	}

	public ArrayList newOperands()
	{
		ArrayList<Number> operands = new ArrayList();
		if (hasDec)
			operands.add(currOperandI + (currOperandD / Math.pow(10, cntDec)));
		else
			operands.add(currOperandI);
		currentText += incomingText;
		return operands;
	}

	public void addCommand(String name, String command)
	{
		ArrayList<Number> operands = newOperands();
		if (pendingOps.size() > 0)
			pendingOps.get(pendingOps.size() - 1).addOperands(operands, hasDec);
		pendingOps.add(new Command(name));
		pendingOps.get(pendingOps.size() - 1).setOperands(operands, hasDec);
		currentText += command;
		calcText.setText(currentText);
		resetCurrOperand();
	}

	public void newDigit(int digit)
	{
		if (!hasDec)
		{
			currOperandI *= 10;
			currOperandI += digit;
		} else
		{
			cntDec++;
			currOperandD *= 10;
			currOperandD += digit;
		}
		incomingText += String.valueOf(digit);
		calcText.setText(currentText + incomingText);
	}

	public void removeDigit()
	{
		if (incomingText.length() > 0)
		{
			if (hasDec)
			{
				if (cntDec > 0)
				{
					cntDec--;
					currOperandD -= currOperandD % 10;
					currOperandD /= 10;
				} else
					hasDec = false;
			} else
			{
				currOperandI -= currOperandI % 10;
				currOperandI /= 10;
			}
			incomingText = incomingText.substring(0, incomingText.length() - 1);
		} else if (currentText.length() > 0)
			currentText = currentText.substring(0, currentText.length() - 1);
		calcText.setText(currentText + incomingText);
	}
}
