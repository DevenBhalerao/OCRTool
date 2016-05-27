package com.ocrtool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class HomePage implements Initializable {

	@FXML
	private Button chooseFile;

	@FXML
	private Label selectedFileName;

	@FXML
	private ImageView image;

	@FXML
	private Button extractText;
	
	@FXML
	private ComboBox<String> languageinImage;
	
	private File chosenFile;
	
	private HashMap<String, String> langNametoTessDataName;

	@FXML
	private void onChooseFile(MouseEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setInitialDirectory(new File("./"));
		chosenFile = fileChooser.showOpenDialog(null);
		InputStream fileStream = null;
		if (chosenFile != null) {
			try {
				fileStream = new FileInputStream(chosenFile);
			} catch (FileNotFoundException e) {
				System.err.println("file not dound");
				e.printStackTrace();
			}
		}
		System.err.println("file chosen is : " + chosenFile);
		Image imageChosen = new Image(fileStream);
		System.err.println("image chosen is " + imageChosen);
		image.setImage(imageChosen);
	}
	
	@FXML
	private void onGetText(MouseEvent event) throws IOException {
		System.out.println("gettext triggered");
		Stage stage = (Stage) image.getScene().getWindow();
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OutputPage.fxml"));
		Parent root = (Parent) fxmlLoader.load();
		OutputPage controller = fxmlLoader.<OutputPage> getController();
		System.out.println("chosen file in homepage" + chosenFile);
		controller.setFile(chosenFile);
		controller.setLanguage(langNametoTessDataName.get(languageinImage.getSelectionModel().getSelectedItem()));
		stage.setTitle("Output");
		Scene scene = new Scene(root, 833, 652);
		scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		fillComboBox();
		fillLanguageMap();
	}

	private void fillLanguageMap() {
		langNametoTessDataName = new HashMap<>();
		langNametoTessDataName.put("Bengali", "ben");
		langNametoTessDataName.put("English", "eng");
		langNametoTessDataName.put("Gujarati", "guj");
		langNametoTessDataName.put("Hindi", "hin");
		langNametoTessDataName.put("Kannada", "kan");
		langNametoTessDataName.put("Marathi", "mar");
		langNametoTessDataName.put("Tamil", "tam");
		langNametoTessDataName.put("Malayalam", "mal");
		
	}

	private void fillComboBox() {
		List<String> listofAvailableLanguages =  new ArrayList<String>(Arrays.asList("Bengali", "English", "Gujarati", "Hindi", "Kannada", "Marathi", "Tamil", "Malayalam"))  ;
		languageinImage.getItems().addAll(listofAvailableLanguages);
	}
	
	

}
