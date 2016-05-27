package com.ocrtool;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.imagehandler.ImageHandler;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;

public class OutputPage implements Initializable {
	
	@FXML
	private HTMLEditor textEditor;
	
	@FXML
	private Button export;
	
	private File chosenFile;
	
	private String languageinImage;
	
	private String textinImage;
	
	@FXML
	private void onExport(MouseEvent event) {
		FileChooser fileChooser = new FileChooser();
		File file = fileChooser.showSaveDialog(textEditor.getScene().getWindow());
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(file);
			fileWriter.write(textEditor.getHtmlText());
			fileWriter.close();
		} catch (IOException e) {
			System.err.println("file invaild");
			e.printStackTrace();
		}
		
	}

	public void setLanguage(String langname) {
		this.languageinImage = langname;		
	}

	public void setFile(File chosenFile) {
		System.out.println("file is set in outpage");
		this.chosenFile = chosenFile;
	}

	public void setText() {
		ImageHandler imagehandler = new ImageHandler(chosenFile, languageinImage);
		imagehandler.setFile(chosenFile);
		this.textinImage = imagehandler.generateText();
		System.out.println("text in image is " + textinImage);
		textEditor.setHtmlText(textinImage);
		
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

	

	

}
