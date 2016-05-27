package com.ocrtool;

import java.io.File;

import com.imagehandler.ImageHandler;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.HTMLEditor;

public class OutputPage {
	
	@FXML
	private HTMLEditor textEditor;
	
	@FXML
	private Button export;
	
	private File chosenFile;
	
	private String languageinImage;
	
	@FXML
	private void onExport(MouseEvent event) {
		ImageHandler imagehandler = new ImageHandler(chosenFile, languageinImage);
		imagehandler.generateText();
	}

	public void setLanguage(String langname) {
		this.languageinImage = langname;		
	}

	public void setFile(File chosenFile) {
		this.chosenFile = chosenFile;
	}

}
