package com.fengqwq.box;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;
import android.view.View.*;
/**
 * 使用DES算法来加密、解密文件
 * 
 * Created by 梦雪 on 2019/08/17.
 * @Description DES算法（加密、解密）文件
 * 欢迎加入Android开发技术交流②群：758110864
 */
public class MainActivity extends AppCompatActivity {
	private EditText etEncrypt;
	private EditText etDecrypt;
	private Button btEncrypt;
	private Button btDecrypt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		etEncrypt = findViewById(R.id.content_mainEditText1);
		etDecrypt = findViewById(R.id.content_mainEditText2);
		btEncrypt = findViewById(R.id.contentmainButton1);
		btDecrypt = findViewById(R.id.contentmainButton2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

		btEncrypt.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {
					// TODO: Implement this method
					String str=etEncrypt.getText().toString().trim();
					Des.encryptFile(str, str.substring(0, str.lastIndexOf(".")) + "-已加密" + str.substring(str.lastIndexOf(".")), MD5.md5("梦雪"));
				}
			});
		btDecrypt.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {
					// TODO: Implement this method
					String str=etDecrypt.getText().toString().trim();
					Des.decryptFile(str, str.substring(0, str.lastIndexOf(".")).replaceAll("-已加密", "") + "-已解密" + str.substring(str.lastIndexOf(".")), MD5.md5("梦雪"));
				}
			});

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
				}
			});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
