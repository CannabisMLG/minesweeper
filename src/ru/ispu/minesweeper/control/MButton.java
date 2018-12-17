package ru.ispu.minesweeper.control;

import javafx.scene.control.Button;

public class MButton extends Button {
	int x, y;
	
	public void setXY(int x, int y)
	{
	 	this.x = x;
	 	this.y = y;
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
