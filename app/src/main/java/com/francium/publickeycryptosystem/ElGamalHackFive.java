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

import com.francium.ElGamal.ElGamalMethod;
import com.francium.cipher.ElGamal;
import com.francium.cipher.Util;

public class ElGamalHackFive extends Activity {

	private String exam1;
	private String exam2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_elgamal_hack_five);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		ElGamalMethod.LENGTH = 101;
		ElGamal elgamal = new ElGamal(Util.NumGen(101).toString());
		TextView elgamal_hack_five_p = (TextView) findViewById(R.id.elgamal_hack_five_p);
		TextView elgamal_hack_five_g = (TextView) findViewById(R.id.elgamal_hack_five_g);
		TextView elgamal_hack_five_ga = (TextView) findViewById(R.id.elgamal_hack_five_ga);
		TextView elgamal_hack_five_message = (TextView) findViewById(R.id.elgamal_hack_five_message);
		elgamal_hack_five_p.setText("p = " + elgamal.getEgkm().getP().toString());
		elgamal_hack_five_g.setText("g = " + elgamal.getEgkm().getG().toString());
		elgamal_hack_five_ga.setText("p = " + elgamal.getEgkm().getGamod().toString());
		elgamal_hack_five_message.setText("message = " + elgamal.getPlaintext());
		exam1 = elgamal.getEgerm().getU().toString();
		exam2 = elgamal.getEgerm().getV().toString();
//		TextView a = (TextView) findViewById(R.id.elgamal_hack_five);
//		a.setText("u = "+ exam1 + "v = " + exam2);
	}

	public void elgamalTest(View view) {
		EditText elgamal_hack_five_edit_text_u = (EditText) findViewById(R.id.elgamal_hack_five_edit_text_u);
		EditText elgamal_hack_five_edit_text_v = (EditText) findViewById(R.id.elgamal_hack_five_edit_text_v);
		if(exam1.equals(elgamal_hack_five_edit_text_u.getText().toString()) && exam2.equals(elgamal_hack_five_edit_text_v.getText().toString())){
			openOptionsDialog1();
		} else {
			openOptionsDialog2();
		}
	}

	public void openOptionsDialog1() {
		new AlertDialog.Builder(this).
				setTitle("WIN").
				setMessage("恭喜你通过的第五关，点击进入下一关").
				setPositiveButton("NEXT LEVEL", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent intent = new Intent(ElGamalHackFive.this, ElGamalHackSix.class);
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
				Intent nextIntent = new Intent(this, ElGamalHackSix.class);
				startActivity(nextIntent);
				break;
		}
		return super.onOptionsItemSelected(item);
	}
}
