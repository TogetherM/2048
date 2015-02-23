package com.example.m;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Point;
import android.hardware.Camera.Size;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.GridLayout;
import android.widget.LinearLayout;

@SuppressLint("NewApi")
public class GameView extends GridLayout{
	int cardWidth;
	float startX,startY,stopX,stopY;
	private Card[][] card = new Card[4][4];
	private List<Point> list = new ArrayList<Point>();
	@SuppressLint("NewApi")
	public GameView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		inView();
	}

	@SuppressLint("NewApi")
	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		inView();
	}

	public GameView(Context context) {
		super(context);
		inView();
	}

	private void inView(){
		setColumnCount(4);
		setOrientation(LinearLayout.VERTICAL);
		setBackgroundResource(R.drawable.round_background);
		setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent even) {
				switch(even.getAction()){
				case MotionEvent.ACTION_DOWN:
					startX = even.getX();
					startY = even.getY();
					break;
				case MotionEvent.ACTION_UP:
					stopX = even.getX()-startX;
					stopY = even.getY()-startY;
					
					if(Math.abs(stopX)>Math.abs(stopY)){ 
						if(stopX<-5){
							TurnLeft();
						}else if(stopX>5){
							TurnRight();
						}
					}else{
						if(stopY<-5){
							TurnUp();
						}else if(stopY>5){
							TurnDown();
						}
					}
					break;
				}
				return true;
			}
		});
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		Config.CARD_WIDTH = (Math.min(w, w)-10)/4;
		addCard(Config.CARD_WIDTH, Config.CARD_WIDTH);
		startGame();
	}
	public void addCard(int width,int height){
		Card c;
		LinearLayout line;
		LinearLayout.LayoutParams lineLp;
		
		
		for(int y=0;y<4;y++){
			line = new LinearLayout(getContext());
			lineLp = new LinearLayout.LayoutParams(-1, width);
			addView(line, lineLp);
			
			for(int x=0;x<4;x++){
				c = new Card(getContext());
				c.setNum(0);
				line.addView(c, width, height);
				card[x][y] = c;
			}
		}
	}
	
	public void startGame(){
		MainActivity.getMain().share();
		MainActivity.getMain().clearScore();
		MainActivity.getMain().getshare();
		
		for(int y=0;y<4;y++){
			for(int x=0;x<4;x++){
				card[x][y].setNum(0);
			}
		}
		
		addRandomNum();
		addRandomNum();
	}
	public void addRandomNum(){
		list.clear();
		
		for(int y=0;y<4;y++){
			for(int x=0;x<4;x++){
				if(card[x][y].getNum()<=0){
					list.add(new Point(x,y));
				}
			}
		}
		if(list.size()>0){
			Point p = list.remove((int)(Math.random()*list.size()));
			card[p.x][p.y].setNum(Math.random()>0.1?2:4);
			
			MainActivity.getMain().getAnimatio().createScaleTo1(card[p.x][p.y]);
		}
	}
	private void TurnLeft(){
		boolean random = false;
		
		for(int y=0;y<4;y++){
			for(int x=0;x<4;x++){
				for(int xl=x+1;xl<4;xl++){
					if(card[xl][y].getNum()>0){
						if(card[x][y].getNum()<=0){
							MainActivity.getMain().getAnimatio().createMoveAnim(card[xl][y],card[x][y], xl, x, y, y);
							card[x][y].setNum(card[xl][y].getNum());
							card[xl][y].setNum(0);
							x--;
							random = true;
						}else if(card[x][y].equals(card[xl][y])){
							MainActivity.getMain().getAnimatio().createMoveAnim(card[xl][y],card[x][y], xl, x, y, y);
							card[x][y].setNum(card[xl][y].getNum()*2);
							card[xl][y].setNum(0);
							MainActivity.getMain().addScore(card[x][y].getNum());
							random = true;
						}
						
						break;
					}
				}
			}
		}
		if(random==true){
			addRandomNum();
			OverGame();
		}
	}
	private void TurnRight(){
		boolean random = false;
		
		for(int y=0;y<4;y++){
			for(int x=3;x>=0;x--){
				for(int xl=x-1;xl>=0;xl--){
					if(card[xl][y].getNum()>0){
						if(card[x][y].getNum()<=0){
							MainActivity.getMain().getAnimatio().createMoveAnim(card[xl][y], card[x][y], xl, x, y, y);
							card[x][y].setNum(card[xl][y].getNum());
							card[xl][y].setNum(0);
							x++;
							random = true;
						}else if(card[x][y].equals(card[xl][y])){
							MainActivity.getMain().getAnimatio().createMoveAnim(card[xl][y], card[x][y], xl, x, y, y);
							card[x][y].setNum(card[xl][y].getNum()*2);
							card[xl][y].setNum(0);
							MainActivity.getMain().addScore(card[x][y].getNum());
							random = true;
						}
						
						break;
					}
				}
			}
		}
		if(random==true){
			addRandomNum();
			OverGame();
		}
	}
	private void TurnUp(){
		boolean random = false;
		
		for(int x=0;x<4;x++){
			for(int y=0;y<4;y++){
				for(int yl=y+1;yl<4;yl++){
					if(card[x][yl].getNum()>0){
						if(card[x][y].getNum()<=0){
							MainActivity.getMain().getAnimatio().createMoveAnim(card[x][yl],card[x][y],x,x,yl,y);
							card[x][y].setNum(card[x][yl].getNum());
							card[x][yl].setNum(0);
							y--;
							random = true;
						}else if(card[x][y].equals(card[x][yl])){
							MainActivity.getMain().getAnimatio().createMoveAnim(card[x][yl],card[x][y],x,x,yl,y);
							card[x][y].setNum(card[x][yl].getNum()*2);
							card[x][yl].setNum(0);
							MainActivity.getMain().addScore(card[x][y].getNum());
							random = true;
						}
						
						break;
					}
				}
			}
		}
		if(random==true){
			addRandomNum();
			OverGame();
		}
	}
	private void TurnDown(){
		boolean random = false;
		
		for(int x=0;x<4;x++){
			for(int y=3;y>=0;y--){
				for(int yl=y-1;yl>=0;yl--){
					if(card[x][yl].getNum()>0){
						if(card[x][y].getNum()<=0){
							MainActivity.getMain().getAnimatio().createMoveAnim(card[x][yl],card[x][y],x,x,yl,y);
							card[x][y].setNum(card[x][yl].getNum());
							card[x][yl].setNum(0);
							y++;
							random = true;
						}else if(card[x][y].equals(card[x][yl])){
							MainActivity.getMain().getAnimatio().createMoveAnim(card[x][yl],card[x][y],x,x,yl,y);
							card[x][y].setNum(card[x][yl].getNum()*2);
							card[x][yl].setNum(0);
							MainActivity.getMain().addScore(card[x][y].getNum());
							random = true;
						}
						break;
					}
				}
			}
		}
		
		if(random==true){
			addRandomNum();
			OverGame();
		}
	}
	
	public void OverGame(){
		boolean complete = true;
		
		ALL:
		for(int y=0;y<4;y++){
			for(int x=0;x<4;x++){
				if(card[x][y].getNum()==0
						||x>0&&card[x][y].equals(card[x-1][y])
						||x<3&&card[x][y].equals(card[x+1][y])
						||y>0&&card[x][y].equals(card[x][y-1])
						||y<3&&card[x][y].equals(card[x][y+1])
						){
					
					complete = false;
					break ALL;
				}
			}
		}
		if(complete){
			new AlertDialog.Builder(getContext())
			.setTitle("游戏结束")
			.setMessage("你个垃圾")
			.setPositiveButton("重来",new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					// TODO Auto-generated method stub
					startGame();
				}
			} ).show();
		}
	}
}
















