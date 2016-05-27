package com.imagehandler;

import static org.bytedeco.javacpp.lept.pixDestroy;
import static org.bytedeco.javacpp.lept.pixRead;
import static org.junit.Assert.assertTrue;
import java.io.File;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.lept.PIX;
import org.bytedeco.javacpp.tesseract.TessBaseAPI;

public class ImageHandler {

	private File chosenFile;
	private String languageinImage;

	public ImageHandler(File chosenFile, String languageinImage) {
		this.chosenFile = chosenFile;
		this.languageinImage = languageinImage;
	}

	public File getFile() {
		return chosenFile;
	}

	public void setFile(File chosenFile) {
		this.chosenFile = chosenFile;
	}

	public String generateText(){
		BytePointer outText;
		System.out.println(languageinImage);
        TessBaseAPI api = new TessBaseAPI();
        // Initialize tesseract-ocr with English, without specifying tessdata path
        if (api.Init("F:\\J2ee\\workspace\\OCRTool\\", languageinImage) != 0) {
            System.err.println("Could not initialize tesseract.");
            System.exit(1);
        }
        // Open input image with leptonica library
        System.out.println("chosen file is " + chosenFile);
        PIX image = pixRead(chosenFile.getAbsolutePath());
        api.SetImage(image);
        // Get OCR result
        outText = api.GetUTF8Text();
        String output = outText.getString();
        assertTrue(!output.isEmpty());
        System.out.println("OCR output:\n" + output);
        // Destroy used object and release memory
        api.End();
        outText.deallocate();
        pixDestroy(image);
        
        return output;
	}

}
