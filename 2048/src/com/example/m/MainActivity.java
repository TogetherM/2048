package com.example.m;

import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextPaint;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TextView score,max,z;
	public static MainActivity main;
	private int scored;
	private int number;
	private TextView button;
	public AnimLayer animation = null;
	public GameView gameview;
	public MainActivity() {
		main = this;
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		score = (TextView) findViewById(R.id.score);
		max = (TextView) findViewById(R.id.num);
		z = (TextView) findViewById(R.id.Z);
		animation = (AnimLayer) findViewById(R.id.anim);
		gameview = (GameView) findViewById(R.id.gameview);
		button = (TextView) findViewById(R.id.button);
		MainActivity.getMain().getshare();
		
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				gameview.startGame();
			}
		});
		
		TextPaint to = score.getPaint();
		TextPaint zo = z.getPaint();
		TextPaint bo = max.getPaint();
		to.setFakeBoldText(true);
		zo.setFakeBoldText(true);
		bo.setFakeBoldText(true);
	}
	
	public static MainActivity getMain(){
		return main;
	}
	public void clearScore(){
		scored = 0;
		showScore();
	}
	
	
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		
		if(keyCode==KeyEvent.KEYCODE_BACK){
			share();
		}
		return super.onKeyDown(keyCode, event);
	}
	public void addScore(int s){
		scored +=s; 
		showScore();
	}
	public void showScore(){
		
		score.setText(scored+"");
		System.out.println("sdsdsd:"+number);
		if(number<scored){
			max.setText(scored+"");
		}
	}
	public AnimLayer getAnimatio(){
		return animation;
	}
	public void share(){
		SharedPreferences share = getSharedPreferences("TEXT", Activity.MODE_PRIVATE);
		Editor editor = share.edit();
		editor.putString("text", scored+"");
		editor.commit();
	}
	
	public void getshare(){
		SharedPreferences share = getSharedPreferences("TEXT", Activity.MODE_PRIVATE);
		String str = share.getString("text", "0");
		number = Integer.parseInt(str);
		max.setText(number+"");
	}
}







