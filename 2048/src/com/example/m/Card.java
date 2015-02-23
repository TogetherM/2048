package com.example.m;


import android.content.Context;
import android.graphics.Color;
import android.text.TextPaint;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

public class Card extends FrameLayout{
	private int num = 0;
	private TextView text;
	public Card(Context context) {
		super(context);
		
		text = new TextView(getContext());
		LayoutParams root = new LayoutParams(-1,-1);
		addView(text,root);
		
		setNum(0);
		
		TextPaint te = text.getPaint();
		te.setFakeBoldText(true);
		text.setTextSize(33);
		text.setGravity(Gravity.CENTER);
		text.setBackgroundResource(R.drawable.round);
		
		root.setMargins(10, 10, 0, 0);
	}
	public void setNum(int num){
		this.num = num;
		if(num<=0){
			text.setText("");
		}else{
			text.setText(num+"");
		}
		
		switch(num){
		case 0:
			text.setBackgroundResource(R.drawable.round);
			text.setTextColor(Color.rgb(200, 181, 149));
			break;
		case 2:
			text.setBackgroundResource(R.drawable.round2);
			text.setTextColor(Color.rgb(200, 181, 149));
			break;
		case 4:
			text.setBackgroundResource(R.drawable.round4);
			break;
		case 8:
			text.setBackgroundResource(R.drawable.round8);
			text.setTextColor(Color.rgb(252, 254, 255));
			break;
		case 16:
			text.setBackgroundResource(R.drawable.round16);
			text.setTextColor(Color.rgb(252, 254, 255));
			break;
		case 32:
			text.setBackgroundResource(R.drawable.round32);
			text.setTextColor(Color.rgb(252, 254, 255));
			break;
		case 64:
			text.setBackgroundResource(R.drawable.round64);
			break;
		case 128:
			text.setBackgroundResource(R.drawable.round128);
			break;
		case 256:
			text.setBackgroundResource(R.drawable.round128);
			text.setTextColor(Color.rgb(252, 254, 255));
			break;
		case 512:
			text.setBackgroundResource(R.drawable.round128);
			text.setTextColor(Color.rgb(252, 254, 255));
			break;
		case 1024:
			text.setBackgroundResource(R.drawable.round128);
			break;
		case 2048:
			text.setBackgroundResource(R.drawable.round2048);
			text.setTextColor(Color.rgb(252, 254, 255));
			break;
		case 4096:
			text.setBackgroundResource(R.drawable.round4096);
			text.setTextColor(Color.rgb(252, 254, 255));
			break;
		}
	}
	public int getNum(){
		return num;
	}
	
	public boolean equals(Card c){
		return getNum()==c.getNum();
	}
	public TextView getLabel(){
		return text;
	}	
	protected Card clone(){
		Card c= new Card(getContext());
		c.setNum(getNum());
		return c;
	}
}
