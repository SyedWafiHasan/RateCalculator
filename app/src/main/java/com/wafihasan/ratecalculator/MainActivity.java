package com.wafihasan.ratecalculator;

import java.text.DecimalFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    double baseRateValue = 0.0;
    double discountReceivedValue = 0.0;
    double gstValue = 0.0;
    double mrpValue = 0.0;
    double netRateValue = 0.0;
    double profitPercentValue = 0.0;
    double discountProfitValue = 0.0;
    double profitSPValue = 0.0;

    TextView netRate;
    TextView profitPercent;
    TextView discountedProfit;
    TextView profitSP;

    EditText baseRate;
    EditText discountReceived;
    EditText gst;
    EditText mrp;

    Button calculate;
    Button clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        netRate = (TextView) findViewById(R.id.netRate);
        profitPercent = (TextView) findViewById(R.id.profitPercent);
        discountedProfit = (TextView) findViewById(R.id.discProfit);
        profitSP = (TextView) findViewById(R.id.profitSP);

        baseRate = (EditText) findViewById(R.id.baseRate);
        discountReceived = (EditText) findViewById(R.id.discountReceived);
        gst = (EditText) findViewById(R.id.gst);
        mrp = (EditText) findViewById(R.id.mrp);

        calculate = (Button) findViewById(R.id.calculate);
        clear = (Button) findViewById(R.id.clear);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate();
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                netRate.setText("");
                profitPercent.setText("");
                profitSP.setText("");
                discountedProfit.setText("");
                gst.setText("");
                baseRate.setText("");
                discountReceived.setText("");
                mrp.setText("");
            }
        });
    }


    public void calculate()
    {
        if(gst.getText().toString().length() == 0 || baseRate.getText().toString().length() == 0 ||
                discountReceived.getText().toString().length() == 0 || mrp.getText().toString().length() == 0)
        {
            Toast.makeText(getApplicationContext(), "Enter all values!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            baseRateValue = Double.parseDouble(baseRate.getText().toString());
            discountReceivedValue = Double.parseDouble(discountReceived.getText().toString());
            gstValue = Double.parseDouble(gst.getText().toString());
            mrpValue = Double.parseDouble(mrp.getText().toString());

            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            netRateValue = baseRateValue * 1.00 * (1 - (discountReceivedValue / 100)) * (1 + (gstValue / 100));
            netRate.setText(decimalFormat.format(netRateValue));

            profitPercentValue = ((mrpValue - netRateValue) * 100 / netRateValue);
            profitPercent.setText(decimalFormat.format(profitPercentValue));

            discountProfitValue = (mrpValue * 0.95 - netRateValue) * (100 / netRateValue);
            discountedProfit.setText(decimalFormat.format(discountProfitValue));

            profitSPValue = (mrpValue * 0.95 - netRateValue) * (100 / (mrpValue * 0.95));
            profitSP.setText(decimalFormat.format(profitSPValue));
        }
    }
}
//net rate = base rate * 1.02 * (1- discount/100) + (1 + gst/100
//profit % = (mrp - net rate) * 100/net rate
//Discounted profit by 5% = (mrp*0.95 - net rate) * 100/net rate
//profit wrt sp = (mrp*0.95 - net rate) * 100/(mrp * 0.95)