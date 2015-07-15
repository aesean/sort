package com.aesean.sort;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    IndexSort mIndexSort;
    private EditText et_ArraySize, et_ArrayMaxValue;
    private TextView tv_Result;
    private StringBuilder mResultStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mIndexSort = new IndexSort();
        et_ArraySize = (EditText) findViewById(R.id.et_ArraySize);
        et_ArrayMaxValue = (EditText) findViewById(R.id.et_ArrayMaxValue);
        tv_Result = (TextView) findViewById(R.id.tv_Result);
        mResultStr = new StringBuilder();
        mResultStr.append(tv_Result.getText().toString());

        if (savedInstanceState != null) {
            tv_Result.setText(savedInstanceState.getString("Result"));
            et_ArraySize.setText(savedInstanceState.getString("ArraySize"));
            et_ArrayMaxValue.setText(savedInstanceState.getString("ArrayMaxValue"));
        }

        findViewById(R.id.bt_Sort).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int arraySize = 0;
                boolean b = false;
                try {
                    arraySize = Integer.valueOf(et_ArraySize.getText().toString());
                    if (arraySize > 1000000) {
                        et_ArraySize.setError(getResources().getString(R.string.ArraySizeTips));
                        b = true;
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    et_ArraySize.setError(getResources().getString(R.string.ArraySizeTips));
                    b = true;
                }
                int arrayMaxValue = 0;
                try {
                    arrayMaxValue = Integer.valueOf(et_ArrayMaxValue.getText().toString());
                    if (arrayMaxValue > 1000000) {
                        et_ArraySize.setError(getResources().getString(R.string.ArraySizeTips));
                        b = true;
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    et_ArrayMaxValue.setError(getResources().getString(R.string.ArrayMaxValueTips));
                    b = true;
                }
                if (b)
                    return;
                mIndexSort.setArraySize(arraySize);
                mIndexSort.setArrayMaxValue(arrayMaxValue);
                mIndexSort.run();
                mResultStr.append(mIndexSort.getResult());
                tv_Result.setText(mResultStr.toString());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_about:
                about();
                break;
            case R.id.action_exit:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void about() {
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle(R.string.dialog_title);
        b.setMessage(R.string.dialog_content);
        b.setPositiveButton("确定", null);
        b.show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("Result", tv_Result.getText().toString());
        outState.putString("ArraySize", et_ArraySize.getText().toString());
        outState.putString("ArrayMaxValue", et_ArrayMaxValue.getText().toString());
    }
}
