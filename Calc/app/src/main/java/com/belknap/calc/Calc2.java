package com.belknap.calc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Kristine on 4/17/2015.
 */
public class Calc2 extends ActionBarActivity
{
	CalcUtil calcUtil = new CalcUtil();
	private Button buttonSwitch, buttonDel;
	private Button buttonCalc0, buttonCalc1, buttonCalc2, buttonCalc3;
	private Button buttonCalc4, buttonCalc5, buttonCalc6, buttonCalc7;
	private Button buttonCalc8, buttonCalc9, buttonCalcA, buttonCalcB;
	private Button buttonCalcC, buttonCalcD, buttonCalcE, buttonCalcF;
	private float currentResult;

	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calc2);
		buttonSwitch = (Button) findViewById(R.id.buttonSwitch);
		buttonDel = (Button) findViewById(R.id.buttonDel);
		calcUtil.calcText = (TextView) findViewById(R.id.calcText);

		Intent intent = getIntent();
		String tmp = intent.getStringExtra("calcText");
		if (tmp == null)
			tmp = "";
		calcUtil.calcText.setText(tmp);
		calcUtil.pendingOps = intent.getParcelableArrayListExtra("pendingOps");
		if (calcUtil.pendingOps == null)
			calcUtil.pendingOps = new ArrayList<Command>();
		calcUtil.currentText = intent.getStringExtra("currentText");
		calcUtil.incomingText = intent.getStringExtra("incomingText");
		calcUtil.hasDec = intent.getBooleanExtra("hasDec", false);
		calcUtil.cntDec = intent.getIntExtra("cntDec", 0);
		calcUtil.currOperandI = intent.getLongExtra("currOperandI", 0);
		calcUtil.currOperandD = intent.getLongExtra("currOperandD", 0);
		currentResult = intent.getFloatExtra("currentResult", 0);

		buttonCalc0 = (Button) findViewById(R.id.button0);
		buttonCalc1 = (Button) findViewById(R.id.button1);
		buttonCalc2 = (Button) findViewById(R.id.button2);
		buttonCalc3 = (Button) findViewById(R.id.button3);
		buttonCalc4 = (Button) findViewById(R.id.button4);
		buttonCalc5 = (Button) findViewById(R.id.button5);
		buttonCalc6 = (Button) findViewById(R.id.button6);
		buttonCalc7 = (Button) findViewById(R.id.button7);
		buttonCalc8 = (Button) findViewById(R.id.button8);
		buttonCalc9 = (Button) findViewById(R.id.button9);
		buttonCalcA = (Button) findViewById(R.id.buttonA);
		buttonCalcB = (Button) findViewById(R.id.buttonB);
		buttonCalcC = (Button) findViewById(R.id.buttonC);
		buttonCalcD = (Button) findViewById(R.id.buttonD);
		buttonCalcE = (Button) findViewById(R.id.buttonE);
		buttonCalcF = (Button) findViewById(R.id.buttonF);

		buttonCalc0.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View view)
			{
				/* TODO: Sin() */
			}
		});
		buttonCalc1.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View view)
			{
                /* TODO: Cos() */
			}
		});
		buttonCalc2.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View view)
			{
                /* TODO: Tan() */
			}
		});
		buttonCalc3.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View view)
			{
                /* TODO: blank */
			}
		});
		buttonCalc4.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View view)
			{
                /* TODO: ln() */
			}
		});
		buttonCalc5.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View view)
			{
                /* TODO: Log() */
			}
		});
		buttonCalc6.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View view)
			{
                /* TODO: pi */
			}
		});
		buttonCalc7.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View view)
			{
                /* TODO: blank */
			}
		});
		buttonCalc8.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View view)
			{
                /* TODO: % */
			}
		});
		buttonCalc9.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View view)
			{
				calcUtil.addCommand("fac", "!");
			}
		});
		buttonCalcA.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View view)
			{
                /* TODO: sqr rt */
			}
		});
		buttonCalcB.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View view)
			{
                /* TODO: ^ */
			}
		});
		buttonCalcC.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View view)
			{
                /* TODO: ( */
			}
		});
		buttonCalcD.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View view)
			{
                /* TODO: ) */
			}
		});
		buttonCalcE.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View view)
			{
                /* TODO: blank */
			}
		});
		buttonCalcF.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View view)
			{
                /* TODO: blank */
			}
		});

		buttonSwitch.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View view)
			{
				Intent intent = new Intent(Calc2.this, MainActivity.class);
				intent.putExtra("calcText", calcUtil.calcText.getText());
				intent.putExtra("pendingOps", calcUtil.pendingOps);
				intent.putExtra("currentText", calcUtil.currentText);
				intent.putExtra("incomingText", calcUtil.incomingText);
				intent.putExtra("hasDec", calcUtil.hasDec);
				intent.putExtra("cntDec", calcUtil.cntDec);
				intent.putExtra("currOperandI", calcUtil.currOperandI);
				intent.putExtra("currOperandD", calcUtil.currOperandD);
				intent.putExtra("currentResult", currentResult);
				intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				startActivity(intent);
			}
		});
		buttonDel.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View view)
			{
				calcUtil.removeDigit();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings)
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
