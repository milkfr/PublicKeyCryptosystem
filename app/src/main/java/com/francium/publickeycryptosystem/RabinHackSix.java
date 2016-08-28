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

import com.francium.Rabin.RabinMethod;
import com.francium.cipher.Rabin;
import com.francium.cipher.Util;

public class RabinHackSix extends Activity{

	private String exam;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rabin_hack_six);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		RabinMethod.LENGTH = 101;
		Rabin rabin = new Rabin(Util.NumGen(101).toString());
		TextView rabin_hack_six_p = (TextView) findViewById(R.id.rabin_hack_six_p);
		TextView rabin_hack_six_q = (TextView) findViewById(R.id.rabin_hack_six_q);
		TextView rabin_hack_six_n = (TextView) findViewById(R.id.rabin_hack_six_n);
		TextView rabin_hack_six_ciphertext = (TextView) findViewById(R.id.rabin_hack_six_ciphertext);
		TextView rabin_hack_six_suffix = (TextView) findViewById(R.id.rabin_hack_six_suffix);
		rabin_hack_six_n.setText("n = " + rabin.getRkm().getN().toString());
		rabin_hack_six_p.setText("p = " + rabin.getRkm().getP().toString());
		rabin_hack_six_q.setText("q = " + rabin.getRkm().getQ().toString());
		rabin_hack_six_ciphertext.setText("ciphertext = " + rabin.getCiphertext());
		rabin_hack_six_suffix.setText("suffix = " + rabin.getSuffix());
		exam = rabin.getRdrm().getDecResult();

//		TextView a = (TextView) findViewById(R.id.rabin_hack_six);
//		a.setText(exam);
	}

	public void rabinTest(View view) {
		EditText rabin_hack_six_edit_text = (EditText) findViewById(R.id.rabin_hack_six_edit_text);
		if(exam.equals(rabin_hack_six_edit_text.getText().toString())){
			openOptionsDialog1();
		} else {
			openOptionsDialog2();
		}
	}

	public void openOptionsDialog1() {
		new AlertDialog.Builder(this).
		setTitle("WIN").
		setMessage("恭喜你通过的第六关，点击进入下一关").
		setPositiveButton("NEXT LEVEL", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = new Intent(RabinHackSix.this, RabinHackSeven.class);
				startActivity(intent);
			}
		}).show();
	}

	public void openOptionsDialog2() {
		new AlertDialog.Builder(this).
		setTitle("FIELD").
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
			Intent nextIntent = new Intent(this, RabinHackSeven.class);
            startActivity(nextIntent); 
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}
