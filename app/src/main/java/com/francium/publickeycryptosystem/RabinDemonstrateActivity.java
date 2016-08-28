package com.francium.publickeycryptosystem;

import com.francium.Rabin.RabinMethod;
import com.francium.cipher.Rabin;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class RabinDemonstrateActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rabin_demonstrate);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		Intent intent = getIntent();
		String message = intent.getStringExtra("message");
		RabinMethod.LENGTH = 20;
		Rabin rabin = new Rabin(message);
		TextView rabin_p = (TextView) findViewById(R.id.rabin_p);
		rabin_p.setText("p = " + rabin.getRkm().getP().toString());
		TextView rabin_p2 = (TextView) findViewById(R.id.rabin_p2);
		rabin_p2.setText("p = " + rabin.getRkm().getP().toString());
		TextView rabin_q = (TextView) findViewById(R.id.rabin_q);
		rabin_q.setText("q = " + rabin.getRkm().getQ().toString());
		TextView rabin_q2 = (TextView) findViewById(R.id.rabin_q2);
		rabin_q2.setText("q = " + rabin.getRkm().getQ().toString());
		TextView rabin_n = (TextView) findViewById(R.id.rabin_n);
		rabin_n.setText("n = " + rabin.getRkm().getN().toString());
		TextView rabin_n2 = (TextView) findViewById(R.id.rabin_n2);
		rabin_n2.setText("n = " + rabin.getRkm().getN().toString());
		TextView rabin_plaintext = (TextView) findViewById(R.id.rabin_plaintext);
		rabin_plaintext.setText("message = " + rabin.getPlaintext());
		TextView rabin_suffix = (TextView) findViewById(R.id.rabin_suffix);
		rabin_suffix.setText("suffix = " + rabin.getSuffix());
		TextView rabin_m = (TextView) findViewById(R.id.rabin_m);
		rabin_m.setText("all message = " + rabin.getPlaintext()+rabin.getSuffix());
		TextView rabin_ciphertext = (TextView) findViewById(R.id.rabin_ciphertext);
		rabin_ciphertext.setText("c = " + rabin.getCiphertext());
		TextView rabin_ciphertext2 = (TextView) findViewById(R.id.rabin_ciphertext2);
		rabin_ciphertext2.setText("c = " + rabin.getCiphertext());
		TextView rabin_s = (TextView) findViewById(R.id.rabin_s);
		rabin_s.setText("s = " + rabin.getRdrm().getS().toString());
		TextView rabin_t = (TextView) findViewById(R.id.rabin_t);
		rabin_t.setText("t = " + rabin.getRdrm().getT().toString());
		TextView rabin_u = (TextView) findViewById(R.id.rabin_u);
		rabin_u.setText("u = " + rabin.getRdrm().getU().toString());
		TextView rabin_v = (TextView) findViewById(R.id.rabin_v);
		rabin_v.setText("v = " + rabin.getRdrm().getV().toString());
		TextView rabin_m1 = (TextView) findViewById(R.id.rabin_m1);
		rabin_m1.setText("m1 = " + rabin.getRdrm().getM1().toString());
		TextView rabin_m2 = (TextView) findViewById(R.id.rabin_m2);
		rabin_m2.setText("m2 = " + rabin.getRdrm().getM2().toString());
		TextView rabin_m3 = (TextView) findViewById(R.id.rabin_m3);
		rabin_m3.setText("m3 = " + rabin.getRdrm().getM3().toString());
		TextView rabin_m4 = (TextView) findViewById(R.id.rabin_m4);
		rabin_m4.setText("m4 = " + rabin.getRdrm().getM4().toString());
		TextView rabin_plaintext2 = (TextView) findViewById(R.id.rabin_plaintext2);
		rabin_plaintext2.setText("message = " + rabin.getRdrm().getDecResult());
	}
	
	public void rabinHack(View view) {
		Intent intent = new Intent(this, RabinHackOne.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		getActionBar().setHomeButtonEnabled(true);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
		case android.R.id.home:
			Intent intent = new Intent(this, RabinActivity.class);
		    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		    startActivity(intent);
			break;
		case R.id.action_github:
			Intent gitIntent = new Intent();  
			gitIntent.setAction(Intent.ACTION_VIEW);  
			gitIntent.addCategory(Intent.CATEGORY_BROWSABLE);  
			gitIntent.setData(Uri.parse(getString(R.string.address)));  
            startActivity(gitIntent); 
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}
