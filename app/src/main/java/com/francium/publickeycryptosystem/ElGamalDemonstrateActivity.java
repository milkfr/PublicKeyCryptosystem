package com.francium.publickeycryptosystem;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.francium.ElGamal.ElGamalMethod;
import com.francium.cipher.ElGamal;

public class ElGamalDemonstrateActivity extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_elgamal_demonstrate);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		Intent intent = getIntent();
		String message = intent.getStringExtra("message");
		ElGamalMethod.LENGTH = 20;
		ElGamal elgamal = new ElGamal(message);
		TextView elgamal_p = (TextView) findViewById(R.id.elgamal_p);
		elgamal_p.setText("p = " + elgamal.getEgkm().getP().toString());
		TextView elgamal_p2 = (TextView) findViewById(R.id.elgamal_p2);
		elgamal_p2.setText("p = " + elgamal.getEgkm().getP().toString());
		TextView elgamal_g = (TextView) findViewById(R.id.elgamal_g);
		elgamal_g.setText("g = " + elgamal.getEgkm().getG().toString());
		TextView elgamal_g2 = (TextView) findViewById(R.id.elgamal_g2);
		elgamal_g2.setText("g = " + elgamal.getEgkm().getG().toString());
		TextView elgamal_a = (TextView) findViewById(R.id.elgamal_a);
		elgamal_a.setText("a = " + elgamal.getEgkm().getA().toString());
		TextView elgamal_a2 = (TextView) findViewById(R.id.elgamal_a2);
		elgamal_a2.setText("a = " + elgamal.getEgkm().getA().toString());
		TextView elgamal_ga = (TextView) findViewById(R.id.elgamal_ga);
		elgamal_ga.setText("g^a(mod p) = " + elgamal.getEgkm().getGamod().toString());
		TextView elgamal_ga2 = (TextView) findViewById(R.id.elgamal_ga2);
		elgamal_ga2.setText("g^a(mod p) = " + elgamal.getEgkm().getGamod().toString());
		TextView elgamal_plaintext = (TextView) findViewById(R.id.elgamal_plaintext);
		elgamal_plaintext.setText("message = " + elgamal.getPlaintext());
		TextView elgamal_k = (TextView) findViewById(R.id.elgamal_k);
		elgamal_k.setText("k = " + elgamal.getEgerm().getK().toString());
		TextView elgamal_u = (TextView) findViewById(R.id.elgamal_u);
		elgamal_u.setText("u = " + elgamal.getEgerm().getU().toString());
		TextView elgamal_u2 = (TextView) findViewById(R.id.elgamal_u2);
		elgamal_u2.setText("u = " + elgamal.getEgerm().getU().toString());
		TextView elgamal_v = (TextView) findViewById(R.id.elgamal_v);
		elgamal_v.setText("v = " + elgamal.getEgerm().getV().toString());
		TextView elgamal_v2 = (TextView) findViewById(R.id.elgamal_v2);
		elgamal_v2.setText("v = " + elgamal.getEgerm().getV().toString());
		TextView elgamal_plaintext2 = (TextView) findViewById(R.id.elgamal_plaintext2);
		elgamal_plaintext2.setText("message = " + elgamal.getDecResult());
	}
	
	public void elgamalHack(View view) {
		Intent intent = new Intent(this, ElGamalHackOne.class);
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
			Intent intent = new Intent(this, ElGamalActivity.class);
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
