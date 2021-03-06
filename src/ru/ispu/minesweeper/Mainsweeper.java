package ru.ispu.minesweeper;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import ru.ispu.minesweeper.control.MButton;
import ru.ispu.minesweeper.view.MSMainController;

public class Mainsweeper extends Application {
	
	private int[][] pole;
	private Stage stage, pStage;
	private int n, m, k, flags, ck;
	private MButton[][] b;
	private boolean fClick = true;
	
	public void createPole(int k)
	{
		ck = k;
		while(k != 0)
		{
			int y = (int) (Math.random()*n);
			int x = (int) (Math.random()*m);
			if(pole[y][x] == 0)
			{
				pole[y][x] = -1;
				k--;
			}
		}
		for(int i = 0; i < n; i++)
			for(int j = 0; j < m; j++)
				if(pole[i][j] != -1) pole[i][j] = 0;
		this.k = n*m;
		System.out.println(k);
		if(n == 1)
		{
			if(m != 1)
				{
					if(pole[0][0] == 0 && pole[0][1] == -1) pole[0][0] = 1;
					if(pole[0][m-1] == 0 && pole[0][m-2] == -1) pole[0][m-1] = 1;
					for(int i = 1;i < m-1; i++)
						if(pole[0][i] == 0)
							for(int j = i - 1; j < i + 2; j++) 
								if(pole[0][j] == -1) pole[0][i]++;
					for(int i = 0; i < m; i++)
						if(pole[0][i] == -1) System.out.print("*");
						else if(pole[0][i] == 0) System.out.print(".");
						else System.out.print(pole[0][i]);
				}
			else if(k == 0)System.out.println(".");
				else System.out.println("*");
		}
		else if(m == 1)
		{
			if(pole[0][0] == 0 && pole[1][0] == -1) pole[0][0] = 1;
			if(pole[n-1][0] == 0 && pole[n-2][0] == -1) pole[n-1][0] = 1;
			for(int i = 1;i < n-1; i++)
				if(pole[i][0] == 0)
					for(int j = i - 1; j < i + 2; j++) 
						if(pole[j][0] == -1) pole[i][0]++;
			for(int i = 0; i < n; i++)
				if(pole[i][0] == -1) System.out.println("*");
				else if(pole[i][0] == 0) System.out.println(".");
				else System.out.println(pole[i][0]);
		}
		else
		{
			for(int i = 1; i < m-1; i++)
			{
				if(pole[0][i] == 0)
				{
					for(int j = 0; j < 2; j++)
						for(int c = (i - 1); c <= (i + 1); c++) 
							if(pole[j][c] == -1) 
								pole[0][i]++;
				}
			}
			for(int i = 1; i < m-1; i++)
			{
				if(pole[n-1][i] == 0)
				{
					for(int j = n-2; j < n; j++)
						for(int c = i - 1; c <= i + 1; c++) if(pole[j][c] == -1) pole[n-1][i]++;
				}
			}
			for(int i = 1; i < n-1; i++)
			{
				if(pole[i][0] == 0)
				{
					for(int j = (i - 1); j <= (i + 1); j++)
						for(int c = 0; c < 2; c++) if(pole[j][c] == -1) pole[i][0]++;
				}
			}
			for(int i = 1; i < n-1; i++)
			{
				if(pole[i][m-1] == 0)
				{
					for(int j = i - 1; j <= i + 1; j++)
						for(int c = m - 2; c < m; c++) if(pole[j][c] == -1) pole[i][m-1]++;
				}
			}
			if(pole[0][0] != -1)
				for(int i = 0; i < 2; i++)
					for(int j = 0; j < 2; j++) if(pole[i][j] == -1) pole[0][0]++;
			if(pole[0][m - 1] != -1)
				for(int i = 0; i < 2; i++)
					for(int j = m - 2; j < m; j++) if(pole[i][j] == -1) pole[0][m - 1]++;
			if(pole[n - 1][0] != -1)
				for(int i = n - 2; i < n; i++)
					for(int j = 0; j < 2; j++) if(pole[i][j] == -1) pole[n - 1][0]++;
			if(pole[n - 1][m - 1] != -1)
				for(int i = n - 2; i < n; i++)
					for(int j = m - 2; j < m; j++) if(pole[i][j] == -1) pole[n - 1][m - 1]++;
			for(int i = 1; i < n-1; i++)
			{
				for(int j = 1; j < m-1; j++)
				{
					if(pole[i][j] == 0)
					{
						for(int z = i - 1; z <= i + 1; z++)
							for(int c = j - 1; c <= j + 1; c++) if(pole[z][c] == -1) pole[i][j]++;
					}
				}
			}
		}
	}
	
	public void showStartDialog()
	{
		try
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Mainsweeper.class.getResource("view/MSMain.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			MSMainController controller = loader.getController();
			controller.setMain(this);
			controller.setStage(stage);
			Scene scene = new Scene(page);
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {e.printStackTrace();}
	}
	
	public void showPole(int n, int m, int k1)
	{
		this.n = n;
		this.m = m;
		k = k1;
		flags = k;
		pole = new int[n][m];
		for(int i = 0; i < n;i++)
			for(int j = 0; j < m; j++)
				pole[i][j] = 0;
		try 
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Mainsweeper.class.getResource("view/Pole.fxml"));
			FlowPane page = (FlowPane) loader.load();
			b = new MButton[n][m];
			for(int i = 0; i < n; i++)
				for(int j = 0; j < m; j++)
				{
					b[i][j] = new MButton();
					b[i][j].setMaxWidth(25);
					b[i][j].setMinWidth(25);
					b[i][j].setPrefWidth(25);
					b[i][j].setMaxHeight(25);
					b[i][j].setMinHeight(25);
					b[i][j].setPrefHeight(25);
					b[i][j].setXY(j, i);
					MButton button = b[i][j];
					button.setOnMouseClicked(new EventHandler<MouseEvent>() {
						public void handle(MouseEvent arg0) {
							if(fClick) firstClick(button);
							else stdClick(arg0, button);
						}});
					page.getChildren().add(b[i][j]);
				}
			pStage = new Stage();
			pStage.setScene(new Scene(page));
			pStage.setHeight(n * 25 + 27);
			pStage.setWidth(m * 25 + 8);
			pStage.setResizable(false);
			pStage.show();
		}catch(Exception e) {e.printStackTrace();}
	}

	public void firstClick(MButton button)
	{
		pole[button.getY()][button.getX()] = 1;
		createPole(k);
		int val = pole[button.getY()][button.getX()];
		if(val == 0 && !button.isFlag()) checkEmp(button, 0);
		else {
			button.setCText(val + "", val);
			k--;
			isWin();
		}
		fClick = false;
	}

	public void stdClick(MouseEvent arg0, MButton button)
	{
		int val = pole[button.getY()][button.getX()];
		if(arg0.getButton() == MouseButton.PRIMARY)
		{
			if(val == 0 && !button.isFlag()) checkEmp(button, 0);
			else if(val == -1 && !button.isFlag()) gameOver();
			else if(!button.isFlag() && button.getText().equals(""))
			{
				button.setCText(val + "", val);
				k--;
				isWin();
			}
			else if(!button.getText().equals("")) {
				checkEmp(button, 1);
				isWin();
			}

		}
		if(arg0.getButton() == MouseButton.SECONDARY)
		{
			if(button.isFlag())
			{
				k++;
				flags++;
				button.changeFlag();
				System.out.println(k);
			}
			else if(flags > 0)
			{
				if(button.getText().equals("?") || button.getText().equals(""))
				{
					k--;
					flags--;
					button.changeFlag();
					System.out.println(k);
					isWin();
				}
			}
		}
	}
	
	public void checkEmp(MButton button, int t)
	{
		int x = button.getX(), y = button.getY();
		int ta = 0, fa = 0;
		if(x == 0 && y == 0)
		{
			for(int i = y; i < y + 2; i++)
				for(int j = x; j < x + 2; j++)
                    if(t == 1)
                    {
                        if(b[i][j].isFlag() && pole[i][j] == -1) ta++;
                        if(b[i][j].isFlag() && pole[i][j] != -1) fa++;
                    }
					else pr(i, j);
		}
		if(x == m-1 && y == n-1)
		{
			for(int i = y - 1; i < y + 1; i++)
				for(int j = x - 1; j < x + 1; j++)
                    if(t == 1)
                    {
                        if(b[i][j].isFlag() && pole[i][j] == -1) ta++;
                        if(b[i][j].isFlag() && pole[i][j] != -1) fa++;
                    }
                    else pr(i, j);
		}
		if(x == 0 && y == n-1)
		{
			for(int i = y - 1; i < y + 1; i++)
				for(int j = x; j < x + 2; j++)
                    if(t == 1)
                    {
                        if(b[i][j].isFlag() && pole[i][j] == -1) ta++;
                        if(b[i][j].isFlag() && pole[i][j] != -1) fa++;
                    }
                    else pr(i, j);
		}
		if(x == m-1 && y == 0)
		{
			for(int i = 0; i < 2; i++)
				for(int j = x - 1; j < x + 1; j++)
                    if(t == 1)
                    {
                        if(b[i][j].isFlag() && pole[i][j] == -1) ta++;
                        if(b[i][j].isFlag() && pole[i][j] != -1) fa++;
                    }
                    else pr(i, j);
		}
		if(y == 0 && x > 0 && x < m - 1)
		{
			for(int i = y; i < y + 2; i++)
				for(int j = x - 1; j < x + 2; j++)
                    if(t == 1)
                    {
                        if(b[i][j].isFlag() && pole[i][j] == -1) ta++;
                        if(b[i][j].isFlag() && pole[i][j] != -1) fa++;
                    }
                    else pr(i, j);
		}
		if(y == n - 1 && x > 0 && x < m - 1)
		{
			for(int i = y - 1; i < y + 1; i++)
				for(int j = x - 1; j < x + 2; j++)
                    if(t == 1)
                    {
                        if(b[i][j].isFlag() && pole[i][j] == -1) ta++;
                        if(b[i][j].isFlag() && pole[i][j] != -1) fa++;
                    }
                    else pr(i, j);
		}
		
		if(x == 0 && y > 0 && y < n - 1)
		{
			for(int i = y - 1; i < y + 2; i++)
				for(int j = x; j < x + 2; j++)
                    if(t == 1)
                    {
                        if(b[i][j].isFlag() && pole[i][j] == -1) ta++;
                        if(b[i][j].isFlag() && pole[i][j] != -1) fa++;
                    }
                    else pr(i, j);
		}
		if(x == m-1 && y > 0 && y < n - 1)
		{
			for(int i = y - 1; i < y + 2; i++)
				for(int j = x - 1; j < x + 1; j++)
                    if(t == 1)
                    {
                        if(b[i][j].isFlag() && pole[i][j] == -1) ta++;
                        if(b[i][j].isFlag() && pole[i][j] != -1) fa++;
                    }
                    else pr(i, j);
		}
		if(x > 0 && x < m - 1 && y > 0 && y < n - 1)
		{
			for(int i = y - 1; i < y + 2; i++)
				for(int j = x - 1; j < x + 2; j++)
                    if(t == 1)
                    {
                        if(b[i][j].isFlag() && pole[i][j] == -1) ta++;
                        if(b[i][j].isFlag() && pole[i][j] != -1) fa++;
                    }
                    else pr(i, j);
		}
		if(fa > 0) gameOver();
		if(fa == 0 && ta == pole[y][x])
        {
            if(x == 0 && y == 0)
            {
                for(int i = y; i < y + 2; i++)
                    for(int j = x; j < x + 2; j++)
                        if(pole[i][j] > 0 && b[i][j].getText().equals(""))
                        {
                            b[i][j].setCText(pole[i][j] + "", pole[i][j]);
                            k--;
                        }
                else if(pole[i][j] == 0 && !b[i][j].isDisable()) checkEmp(b[i][j], 0);
            }
            if(x == m-1 && y == n-1)
            {
                for(int i = y - 1; i < y + 1; i++)
                    for(int j = x - 1; j < x + 1; j++)
                        if(pole[i][j] > 0 && b[i][j].getText().equals(""))
                        {
                            b[i][j].setCText(pole[i][j] + "", pole[i][j]);
                            k--;
                        }
                        else if(pole[i][j] == 0 && !b[i][j].isDisable()) checkEmp(b[i][j], 0);
            }
            if(x == 0 && y == n-1)
            {
                for(int i = y - 1; i < y + 1; i++)
                    for(int j = x; j < x + 2; j++)
                        if(pole[i][j] > 0 && b[i][j].getText().equals(""))
                        {
                            b[i][j].setCText(pole[i][j] + "", pole[i][j]);
                            k--;
                        }
                        else if(pole[i][j] == 0 && !b[i][j].isDisable()) checkEmp(b[i][j], 0);
            }
            if(x == m-1 && y == 0)
            {
                for(int i = 0; i < 2; i++)
                    for(int j = x - 1; j < x + 1; j++)
                        if(pole[i][j] > 0 && b[i][j].getText().equals(""))
                        {
                            b[i][j].setCText(pole[i][j] + "", pole[i][j]);
                            k--;
                        }
                        else if(pole[i][j] == 0 && !b[i][j].isDisable()) checkEmp(b[i][j], 0);
            }
            if(y == 0 && x > 0 && x < m - 1)
            {
                for(int i = y; i < y + 2; i++)
                    for(int j = x - 1; j < x + 2; j++)
                        if(pole[i][j] > 0 && b[i][j].getText().equals(""))
                        {
                            b[i][j].setCText(pole[i][j] + "", pole[i][j]);
                            k--;
                        }
                        else if(pole[i][j] == 0 && !b[i][j].isDisable()) checkEmp(b[i][j], 0);
            }
            if(y == n - 1 && x > 0 && x < m - 1)
            {
                for(int i = y - 1; i < y + 1; i++)
                    for(int j = x - 1; j < x + 2; j++)
                        if(pole[i][j] > 0 && b[i][j].getText().equals(""))
                        {
                            b[i][j].setCText(pole[i][j] + "", pole[i][j]);
                            k--;
                        }
                        else if(pole[i][j] == 0 && !b[i][j].isDisable()) checkEmp(b[i][j], 0);
            }

            if(x == 0 && y > 0 && y < n - 1)
            {
                for(int i = y - 1; i < y + 2; i++)
                    for(int j = x; j < x + 2; j++)
                        if(pole[i][j] > 0 && b[i][j].getText().equals(""))
                        {
                            b[i][j].setCText(pole[i][j] + "", pole[i][j]);
                            k--;
                        }
                        else if(pole[i][j] == 0 && !b[i][j].isDisable()) checkEmp(b[i][j], 0);
            }
            if(x == m-1 && y > 0 && y < n - 1)
            {
                for(int i = y - 1; i < y + 2; i++)
                    for(int j = x - 1; j < x + 1; j++)
                        if(pole[i][j] > 0 && b[i][j].getText().equals(""))
                        {
                            b[i][j].setCText(pole[i][j] + "", pole[i][j]);
                            k--;
                        }
                        else if(pole[i][j] == 0 && !b[i][j].isDisable()) checkEmp(b[i][j], 0);
            }
            if(x > 0 && x < m - 1 && y > 0 && y < n - 1)
            {
                for(int i = y - 1; i < y + 2; i++)
                    for(int j = x - 1; j < x + 2; j++)
                        if(pole[i][j] > 0 && b[i][j].getText().equals(""))
                        {
                            b[i][j].setCText(pole[i][j] + "", pole[i][j]);
                            k--;
                        }
                        else if(pole[i][j] == 0 && !b[i][j].isDisable()) checkEmp(b[i][j], 0);
            }
        }
	}

	public void pr(int i, int j)
	{
		if(pole[i][j] > 0 && b[i][j].getText().equals(""))
		{
			b[i][j].setCText(pole[i][j] + "", pole[i][j]);
			k--;
		}
		else if(pole[i][j] == 0)
		{
			if(!b[i][j].isDisable())
			{
				b[i][j].setDisable(true);
				checkEmp(b[i][j], 0);
				k--;
			}
		}
		if(b[i][j].isFlag())
		{
			b[i][j].changeFlag();
			flags++;
		}
	}
	
	public void gameOver()
	{
		for(int i = 0; i < n; i++)
			for(int j = 0; j < m; j++)
				if(pole[i][j] == -1)
				{
					if(b[i][j].isFlag()) b[i][j].setStyle("-fx-background-color: CCFF00;");
					else b[i][j].setStyle("-fx-background-color: FF6666;");
					b[i][j].setText("*");
				}
		Alert alert = new Alert(AlertType.WARNING);
        alert.initOwner(stage);
        alert.setTitle("���� ��������!");
        alert.setHeaderText("�� ���������");
        alert.setContentText(":(");
        alert.showAndWait();
        pStage.close();
	}

	public void isWin()
	{
		if(k == 0 || k == ck)
		{
			for(int i = 0; i < n; i++)
				for(int j = 0; j < m; j++)
				{
					if(pole[i][j] == -1) b[i][j].setText("?");
				}
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.initOwner(stage);
			alert.setTitle("���� ��������!");
			alert.setHeaderText("�� ��������");
			alert.setContentText(":)");
			alert.showAndWait();
			pStage.close();
		}
	}
	
	@Override
	public void start(Stage primaryStage) {
		stage = primaryStage;
		stage.setResizable(false);
		showStartDialog();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
