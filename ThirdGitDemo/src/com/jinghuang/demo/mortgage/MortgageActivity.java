package com.jinghuang.demo.mortgage;

import java.text.DecimalFormat;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jinghuang.demo.R;

public class MortgageActivity extends Activity implements View.OnClickListener{

    private String TAG = getClass().getSimpleName();

    private EditText mPriceET;
    private EditText mAeraET;
    private EditText mMonthInterestET;
    private EditText mCommissionChargeET;
    private EditText mUnitPriceIncreaseET;
    private EditText mTotalPriceIncreaseET;

    private TextView mUnitPricesTV;
    private TextView mDownPaymentTV;
    private TextView mUnitPriceIncreasePercentTV;
    private TextView mTotalPriceIncreasePercentTV;
    private TextView mPayBackTV;
    private TextView mTipsTV;
    private TextView mFinalUnitPriceTV;
    private TextView mFinalTotalPriceTV;

    private Button mCaluBtn1;
    private Button mCaluBtn2;

    private double mTotalPrice;                 // 总价
    private double mTotalAera;                  // 面积
    private double mMonthInterest;              // 月供利息
    private double mUnitPrices = 0;                 // 单价
    private double mDownPayment;                // 首付
    private double mCommissionCharge;           //手续费
    private double mFinalUnitPrice;          //增幅后的单价
    private double mUnitPriceIncreasePercent;   //单价增幅比例
    private double mFinalTotalPrice;         //增幅后的总价
    private double mTotalPriceIncreasePercent;  //总价增幅比例

    private double mPayBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mortgage);
        initView();
    }

    private void initView() {
        mPriceET = (EditText) findViewById(R.id.prices);
        mPriceET.addTextChangedListener(new MyTextWatcher(mPriceET));

        mAeraET = (EditText) findViewById(R.id.aera);
        mAeraET.addTextChangedListener(new MyTextWatcher(mAeraET));

        mMonthInterestET = (EditText) findViewById(R.id.month_interest);
        mCommissionChargeET = (EditText) findViewById(R.id.commission_charge);
        mUnitPriceIncreaseET = (EditText) findViewById(R.id.unitPriceIncrease);
        mTotalPriceIncreaseET = (EditText) findViewById(R.id.totalPriceIncrease);

        mUnitPricesTV = (TextView) findViewById(R.id.unit_prices);
        mDownPaymentTV = (TextView) findViewById(R.id.down_payment);
        mUnitPriceIncreasePercentTV = (TextView) findViewById(R.id.unitPriceIncreasePercent);
        mTotalPriceIncreasePercentTV = (TextView) findViewById(R.id.totalPriceIncreasePercent);
        mPayBackTV = (TextView) findViewById(R.id.pay_back);
        mTipsTV = (TextView) findViewById(R.id.tips);
        mFinalUnitPriceTV = (TextView) findViewById(R.id.finalUnitPrice);
        mFinalTotalPriceTV = (TextView) findViewById(R.id.finalTotalPrice);

        mCaluBtn1 = (Button) findViewById(R.id.calculate1);
        mCaluBtn2 = (Button) findViewById(R.id.calculate2);
        mCaluBtn1.setOnClickListener(this);
        mCaluBtn2.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
            case R.id.calculate1:
                if (mTotalPrice == 0 || mTotalAera == 0) {
                    checkToShowToast(null, "请输入总价/面积!");
                    return;
                }
                calculateMethod1();
                break;
            case R.id.calculate2:
                if (mTotalPrice == 0 || mTotalAera == 0) {
                    checkToShowToast(null, "请输入总价/面积!");
                    return;
                }
                calculateMethod2();
                break;

        }
	}

    /*private void calculateMethod() {
        String strPrice = mPriceET.getText().toString();
        if (checkToShowToast(strPrice, "请输入总价")) return;
        mTotalPrice = Double.valueOf(strPrice);

        String strAera = mAeraET.getText().toString();
        if (checkToShowToast(strAera, "请输入面积")) return;
        mTotalAera = Double.valueOf(strAera);

        mUnitPrices = get2Double(mTotalPrice/mTotalAera);
        mDownPayment = mTotalPrice * 0.3;

        mUnitPricesTV.setText(String.valueOf(mUnitPrices));
        mDownPaymentTV.setText(String.valueOf(mDownPayment));
    }*/

    private void calculateMethod1() {

        toggleKeybord();
        mTotalPriceIncreaseET.setText(null);

        String strMonthInterest = mMonthInterestET.getText().toString();
        if (checkToShowToast(strMonthInterest, "请输入月供利息")) return;
        mMonthInterest = Double.valueOf(strMonthInterest);

        String strCommissionCharge = mCommissionChargeET.getText().toString();
        if (checkToShowToast(strCommissionCharge, "请输入手续费")) return;
        mCommissionCharge = Double.valueOf(strCommissionCharge);

        String strUnitPriceIncrease = mUnitPriceIncreaseET.getText().toString();
        if (checkToShowToast(strUnitPriceIncrease, "请输入单价增至")) return;
        mFinalUnitPrice = Double.valueOf(strUnitPriceIncrease);
        mFinalUnitPriceTV.setText(String.valueOf(mFinalUnitPrice));

        mFinalTotalPrice = get2Double(mFinalUnitPrice * mTotalAera);
        mFinalTotalPriceTV.setText(String.valueOf(mFinalTotalPrice));

        mUnitPriceIncreasePercent = get2Double((mFinalUnitPrice - mUnitPrices) / mUnitPrices * 100) ;
        mUnitPriceIncreasePercentTV.setText(String.valueOf(mUnitPriceIncreasePercent) + "%");

        mTotalPriceIncreasePercent = get2Double((mFinalTotalPrice - mTotalPrice) / mTotalPrice  * 100);
        mTotalPriceIncreasePercentTV.setText(String.valueOf(mTotalPriceIncreasePercent) + "%");

        mPayBack = get2Double(mFinalTotalPrice - mTotalPrice - mMonthInterest * 24 - mCommissionCharge);
        mPayBackTV.setText(String.valueOf(mPayBack));

        makeTips();
    }

    private void calculateMethod2() {

        toggleKeybord();
        mUnitPriceIncreaseET.setText(null);

        String strMonthInterest = mMonthInterestET.getText().toString();
        if (checkToShowToast(strMonthInterest, "请输入月供利息")) return;
        mMonthInterest = Double.valueOf(strMonthInterest);

        String strCommissionCharge = mCommissionChargeET.getText().toString();
        if (checkToShowToast(strCommissionCharge, "请输入手续费")) return;
        mCommissionCharge = Double.valueOf(strCommissionCharge);

        String strTotalPriceIncrease = mTotalPriceIncreaseET.getText().toString();
        if (checkToShowToast(strTotalPriceIncrease, "请输入总价增至")) return;
        mFinalTotalPrice = Double.valueOf(strTotalPriceIncrease);
        mFinalTotalPriceTV.setText(String.valueOf(mFinalTotalPrice));

        mFinalUnitPrice = get2Double(mFinalTotalPrice / mTotalAera);
        mFinalUnitPriceTV.setText(String.valueOf(mFinalUnitPrice));

        mUnitPriceIncreasePercent = get2Double((mFinalUnitPrice / mUnitPrices - 1) * 100) ;
        mUnitPriceIncreasePercentTV.setText(String.valueOf(mUnitPriceIncreasePercent) + "%");

        mTotalPriceIncreasePercent = get2Double((mFinalTotalPrice / mTotalPrice - 1) * 100 );
        mTotalPriceIncreasePercentTV.setText(String.valueOf(mTotalPriceIncreasePercent) + "%");

        mPayBack = get2Double(mFinalTotalPrice - mTotalPrice - mMonthInterest * 24 - mCommissionCharge);
        mPayBackTV.setText(String.valueOf(mPayBack));

        makeTips();

    }

	public static double get2Double(double a) {
		DecimalFormat df = new DecimalFormat("0.00");
		return new Double(df.format(a).toString());
	}

    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    public static boolean isDouble(String str) {
        boolean flag = true;
        try {
            Double temp = Double.valueOf(str);
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    private void toggleKeybord() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
    private boolean checkToShowToast(String target, String showtext) {
        if (TextUtils.isEmpty(target)) {
            Toast.makeText(this, showtext, Toast.LENGTH_SHORT).show();
            return true;
        }


        if (!isDouble(target)) {
            Toast.makeText(this, "请输入数字!", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    private void makeTips() {
        String tips = String.valueOf(mFinalTotalPrice) + " - " + String.valueOf(mTotalPrice) + " * 70% - "
                + String.valueOf(mMonthInterest) + " * 24 " + "- (" + String.valueOf(mTotalPrice) + " * 30% + "
                + String.valueOf(mCommissionCharge) + ") = " + String.valueOf(mPayBack);
        mTipsTV.setText(tips);

    }

    private class MyTextWatcher implements TextWatcher {

    	private EditText editTextId = null;
    	
        public MyTextWatcher(EditText id) {
			// TODO Auto-generated constructor stub
        	editTextId = id;
		}
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			// TODO Auto-generated method stub

            if (editTextId == mPriceET) {
                String strPrice = mPriceET.getText().toString();
                if (checkToShowToast(strPrice, "请输入总价")) {
                    mDownPaymentTV.setText(null);
                    mTotalPrice = 0;
                    mDownPayment = 0;
                    return;
                }
                mTotalPrice = Double.valueOf(strPrice);
                if (mTotalPrice <= 0) {
                	checkToShowToast(null, "总价格须大于0!");
                	return;
                }
                mDownPayment = mTotalPrice * 30 / 100;
                mDownPaymentTV.setText(String.valueOf(mDownPayment));

            } else if (editTextId == mAeraET) {
                String strAera = mAeraET.getText().toString();
                if (checkToShowToast(strAera, "请输入总面积")) {
                    mUnitPricesTV.setText(null);
                    mTotalAera = 0;
                    mUnitPrices = 0;
                    return;
                }
                mTotalAera = Double.valueOf(strAera);
                if (mTotalAera == 0) {
                    checkToShowToast(null, "面积数须大于0!");
                    return;
                }
                mUnitPrices = get2Double(mTotalPrice/mTotalAera);
                mUnitPricesTV.setText(String.valueOf(mUnitPrices));
            }
		}

		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub


		}
        
    }
}
