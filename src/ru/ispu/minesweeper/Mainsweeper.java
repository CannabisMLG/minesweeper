package ru.ispu.minesweeper;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import ru.ispu.minesweeper.view.MSMainController;

public class Mainsweeper extends Application {
	
	private int[][] pole;
	private Stage stage;
	private int n, m, k;
	
	public void createPole(int n, int m, int k)
	{
		this.n = n;
		this.m = m;
		this.k = k;
		pole = new int[n][m];
		for(int i = 0; i < n;i++)
			for(int j = 0; j < m; j++)
				pole[i][j] = 0;
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
						for(int c = m - 2; c < m - 1; c++) if(pole[j][c] == -1) pole[i][m-1]++;
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
		/*for(int i = 0; i < n;i++)
		{
			for(int j = 0; j < m; j++)
			{
				if(pole[i][j] == 0) System.out.print(".");
				else if(pole[i][j] == -1) System.out.print("*");
				else System.out.print(pole[i][j]);
			}
			System.out.println();
		}*/
		showPole();
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
			Scene scene = new Scene(page);
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {e.printStackTrace();}
	}
	
	public void showPole()
	{
		try 
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Mainsweeper.class.getResource("view/Pole.fxml"));
			FlowPane page = (FlowPane) loader.load();
			Button[][] b = new Button[n][m];
			for(int i = 0; i < n; i++)
				for(int j = 0; j < m; j++)
				{
					b[i][j] = new Button();
					b[i][j].setMaxWidth(25);
					b[i][j].setMinWidth(25);
					b[i][j].setPrefWidth(25);
					b[i][j].setMaxHeight(25);
					b[i][j].setMinHeight(25);
					b[i][j].setPrefHeight(25);
					page.getChildren().add(b[i][j]);
				}
			Stage pStage = new Stage();
			pStage.setScene(new Scene(page));
			pStage.setHeight(n * 25 + 38);
			pStage.setWidth(m * 25 + 20); 
			pStage.show();
		}catch(Exception e) {e.printStackTrace();}
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
