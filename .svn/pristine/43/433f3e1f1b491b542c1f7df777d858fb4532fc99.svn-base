package com.nus.adqs.docpreview.helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.pdfbox.util.TextPosition;

public class PdfTextTokenizer extends PDFTextStripper{
	
	
	public PdfTextTokenizer() throws IOException {
		super();
	}

	private static final List<String> STOP_WORDS = Arrays.asList(new String[]{""," ",";",".",":","?","!",",","<",">","(",")","{","}"});
	
//	private List<PdfTextScalar> processedTexts = new ArrayList<PdfTextScalar>();
//	public List<PdfTextScalar> getProcessedTexts() {return processedTexts;}
//	public void setProcessedTexts(List<PdfTextScalar> processedTexts) {this.processedTexts = processedTexts;}
	
	private String[] searchWords;
	public String[] getSearchWords() {return searchWords;}
	public void setSearchWords(String... searchWords) {this.searchWords = searchWords;}
	
	private PdfTextScalar pdfText = new PdfTextScalar();
	public PdfTextScalar getPdfText() {return pdfText;}
	public void setPdfText(PdfTextScalar pdfText) {this.pdfText = pdfText;}
	
	private float currentPosY = -1; //needed to detect new line
	private float currentEndPosX = -1;
	
	private Vector<List<TextPosition>> textsPosition = new Vector<List<TextPosition>>();
	public Vector<List<TextPosition>> getTextsPosition() {return textsPosition;}
	public void setTextsPosition(Vector<List<TextPosition>> textsPosition) {this.textsPosition = textsPosition;}

	private List<PdfTextScalar> constructedWords = new ArrayList<PdfTextScalar>();
	public List<PdfTextScalar> getConstructedWords() {return constructedWords;}
	public void setConstructedWords(List<PdfTextScalar> constructedWords) {this.constructedWords = constructedWords;}

	private List<String> constructedText = new ArrayList<String>();
	public List<String> getConstructedText() {return constructedText;}
	public void setConstructedText(List<String> constructedText) {this.constructedText = constructedText;}
	
	
	@Override
	public void writePage() throws IOException{
		super.writePage();
//		super.setSortByPosition(true);
		this.textsPosition = super.getCharactersByArticle();
	}

	
	public void generateTextToWords(){
		for(List<TextPosition> positions : textsPosition){
			for(TextPosition text : positions){
				this.processTextToWords(text);
			}
		}
	}
	
	
	
	private void processTextToWords( TextPosition text ){
//		textPos.add(text);
		try{
			if(this.isEndOfWord(text)){
				//if(pdfText.match(searchWords))
//					processedTexts.add(pdfText);
				constructedWords.add(pdfText);
				constructedText.add(pdfText.getText().toString().trim());
				
				//this.addTextScalar();
				pdfText = new PdfTextScalar();
			}
			
			pdfText.appendText(text);
			currentPosY = text.getYDirAdj();
			currentEndPosX = text.getXDirAdj() + text.getWidth();
			
//			super.processTextPosition(text);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
	
	
	private boolean isEndOfWord(TextPosition text){
		
		// stop word seperator
		if(STOP_WORDS.contains(text.getCharacter()))
				return true;
		
		// change of new line
		if((currentPosY>=0) &&  currentPosY!= text.getYDirAdj())
			return true;
		
		//currentEndPosX
		
		if((currentEndPosX >=0)){
			float spaceResult = (text.getXDirAdj()) - currentEndPosX;
			if(spaceResult > 0.9)
				return true;
		}
		
		
		return false;
	}
	
	
	public void cleanUp(){
//		processedTexts = new ArrayList<PdfTextScalar>();
		constructedWords =  new ArrayList<PdfTextScalar>();
		pdfText = new PdfTextScalar();
	}
	
	@Override
	public void resetEngine() {
		this.cleanUp();
		super.resetEngine();
	}
	
	public void showConstructedText(){
		for(String constructedText : this.getConstructedText()){
			System.err.println(constructedText);
		}
	}
	
	/*public void showTextPosition(){
		for(TextPosition textPos : this.getTextPos()){
			System.err.println( "String[" +textPos.getCharacter() + " - " + textPos.getXDirAdj() + "," +
					textPos.getYDirAdj() + " fs=" + textPos.getFontSize() + " xscale=" +
	                textPos.getXScale() + " height=" + textPos.getHeightDir() + " space=" +
	                textPos.getWidthOfSpace() + " width=" +
	                textPos.getWidthDirAdj() + "]" + textPos.getCharacter() );
		}
		
		
		System.err.println( "String[" +text.getCharacter() + " - " + text.getXDirAdj() + "," +
            text.getYDirAdj() + " fs=" + text.getFontSize() + " xscale=" +
            text.getXScale() + " height=" + text.getHeightDir() + " space=" +
            text.getWidthOfSpace() + " width=" +
            text.getWidthDirAdj() + "]" + text.getCharacter() );
		
	}*/

}
