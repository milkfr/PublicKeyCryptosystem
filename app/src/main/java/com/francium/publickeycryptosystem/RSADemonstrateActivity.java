package com.francium.publickeycryptosystem;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.francium.RSA.RSAMethod;
import com.francium.cipher.RSA;

public class RSADemonstrateActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rsa_demonstrate);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		Intent intent = getIntent();
		String message = intent.getStringExtra("message");
		RSAMethod.LENGTH = 20;
		RSA rsa = new RSA(message);
		TextView rsa_p = (TextView) findViewById(R.id.rsa_p);
		rsa_p.setText("p = " + rsa.getRsakm().getP().toString());
		TextView rsa_q = (TextView) findViewById(R.id.rsa_q);
		rsa_q.setText("q = " + rsa.getRsakm().getQ().toString());
		TextView rsa_n = (TextView) findViewById(R.id.rsa_n);
		rsa_n.setText("n = " + rsa.getRsakm().getN().toString());
		TextView rsa_n2 = (TextView) findViewById(R.id.rsa_n2);
		rsa_n2.setText("n = " + rsa.getRsakm().getN().toString());
		TextView rsa_coPrime = (TextView) findViewById(R.id.rsa_coPirme);
		rsa_coPrime.setText("coPrime = " + rsa.getRsakm().getCoPrime().toString());
		TextView rsa_e = (TextView) findViewById(R.id.rsa_e);
		rsa_e.setText("e = " + rsa.getRsakm().getE().toString());
		TextView rsa_e2 = (TextView) findViewById(R.id.rsa_e2);
		rsa_e2.setText("e = " + rsa.getRsakm().getE().toString());
		TextView rsa_d = (TextView) findViewById(R.id.rsa_d);
		rsa_d.setText("d = " + rsa.getRsakm().getD().toString());
		TextView rsa_d2 = (TextView) findViewById(R.id.rsa_d2);
		rsa_d2.setText("d = " + rsa.getRsakm().getD().toString());
		TextView rsa_plaintext = (TextView) findViewById(R.id.rsa_plaintext);
		rsa_plaintext.setText("message = " + rsa.getPlaintext());
		TextView rsa_ciphertext = (TextView) findViewById(R.id.rsa_ciphertext);
		rsa_ciphertext.setText("c = " + rsa.getCiphertext());
		TextView rsa_ciphertext2 = (TextView) findViewById(R.id.rsa_ciphertext2);
		rsa_ciphertext2.setText("c = " + rsa.getCiphertext());
		TextView rsa_plaintext2 = (TextView) findViewById(R.id.rsa_plaintext2);
		rsa_plaintext2.setText("message = " + rsa.getDecResult());
	}
	
	public void rsaHack(View view) {
		Intent intent = new Intent(this, RSAHackOne.class);
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
			Intent intent = new Intent(this, RSAActivity.class);
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
