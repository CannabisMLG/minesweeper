package ru.ispu.minesweeper.control;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;

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

	public void setCText(String s, int val)
	{
		this.setText(s);
		this.setStyle("-fx-font-weight: bold");
		switch (val)
		{
			case 1: this.setTextFill(Color.BLUE); break;
			case 2: this.setTextFill(Color.GREEN); break;
			case 3: this.setTextFill(Color.RED); break;
			case 4: this.setTextFill(Color.web("#000066")); break;
			case 5: this.setTextFill(Color.web("#330000")); break;
			case 6: this.setTextFill(Color.web("#330066")); break;
			case 7: this.setTextFill(Color.web("#006600")); break;
			case 8: this.setTextFill(Color.web("#333333")); break;
		}
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
