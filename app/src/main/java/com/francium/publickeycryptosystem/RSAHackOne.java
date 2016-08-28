package com.francium.publickeycryptosystem;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.francium.RSA.RSAMethod;
import com.francium.cipher.RSA;
import com.francium.cipher.Util;

public class RSAHackOne extends Activity {

	private String exam;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rsa_hack_one);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		RSAMethod.LENGTH = 11;
		RSA rsa = new RSA(Util.NumGen(11).toString());
		TextView rsa_hack_one_n = (TextView) findViewById(R.id.rsa_hack_one_n);
		TextView rsa_hack_one_e = (TextView) findViewById(R.id.rsa_hack_one_e);
		TextView rsa_hack_one_message = (TextView) findViewById(R.id.rsa_hack_one_message);
		rsa_hack_one_n.setText("n = " + rsa.getRsakm().getN().toString());
		rsa_hack_one_e.setText("e = " + rsa.getRsakm().getE().toString());
		rsa_hack_one_message.setText("message = " + rsa.getPlaintext());
		exam = rsa.getCiphertext();
		
//		TextView a = (TextView) findViewById(R.id.rsa_hack_one);
//		a.setText(exam);
	}
	
	public void rsaTest(View view) {
		EditText rsa_hack_one_edit_text = (EditText) findViewById(R.id.rsa_hack_one_edit_text);
		if(exam.equals(rsa_hack_one_edit_text.getText().toString())){
            openOptionsDialog1();
        } else {
        	openOptionsDialog2();
        }
	}
	
	public void openOptionsDialog1() {
        new AlertDialog.Builder(this).
                setTitle("WIN").
                setMessage("恭喜你通过的第一关，点击进入下一关").
                setPositiveButton("NEXT LEVEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    	Intent intent = new Intent(RSAHackOne.this, RSAHackTwo.class);
                    	startActivity(intent);
                    }
                }).show();
    }
	
	public void openOptionsDialog2() {
        new AlertDialog.Builder(this).
                setTitle("LOST").
                setMessage("失败了，点击重试").
                setPositiveButton("TRY AGAIN", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.hack, menu);
		getActionBar().setHomeButtonEnabled(true);
		
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
		case android.R.id.home:
			Intent intent = new Intent(this, MainActivity.class);
		    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		    startActivity(intent);
			break;
		case R.id.action_hack_next:
			Intent nextIntent = new Intent(this, RSAHackTwo.class);  
            startActivity(nextIntent); 
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}
