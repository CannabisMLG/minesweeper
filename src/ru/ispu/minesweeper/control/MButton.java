package ru.ispu.minesweeper.control;

import javafx.scene.control.Button;

public class MButton extends Button {
	private int x, y;
	boolean flag = false;
	
	public void setXY(int x, int y)
	{
	 	this.x = x;
	 	this.y = y;
	}
	
	public void changeFlag() 
	{
		flag = !flag;
		if(flag) this.setText("?");
		else this.setText("");
	}
	
	public boolean isFlag()
	{
		return flag;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
}
