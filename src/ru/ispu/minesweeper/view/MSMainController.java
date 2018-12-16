package ru.ispu.minesweeper.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.ispu.minesweeper.Mainsweeper;

public class MSMainController {
	@FXML
	private TextField n;
	@FXML
	private TextField m;
	@FXML
	private TextField k;
	private Mainsweeper main;
	private Stage stage;
	
	@FXML
	public void initialize() {}
	
	@FXML 
	private void ok()
	{
		main.createPole(Integer.parseInt(n.getText()), Integer.parseInt(m.getText()), Integer.parseInt(k.getText()));
		stage.close();
	}
	
	public void setStage(Stage stage)
	{
		this.stage = stage;
	}
	
	public void setMain(Mainsweeper main)
	{
		this.main = main;
	}
}
