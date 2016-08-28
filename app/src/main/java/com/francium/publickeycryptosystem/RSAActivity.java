package com.francium.publickeycryptosystem;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class RSAActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rsa);
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}
	
	public void rsaDemonstration(View view) {
        Intent intent = new Intent(this, RSADemonstrateActivity.class);
        EditText editText = (EditText) findViewById(R.id.rsa_edit_text);
        String message = null;
        message = editText.getText().toString();
        System.out.println(message);
        if(message.equals("")){
            openOptionsDialog();
        } else {
            intent.putExtra("message", message);
            startActivity(intent);
        }
    }
	
	public void openOptionsDialog() {
        new AlertDialog.Builder(this).
                setTitle("ERROR").
                setMessage("please input a number").
                setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
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
			Intent intent = new Intent(this, MainActivity.class);
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
