package com.nus.adqs.docpreview.helper;

import org.apache.pdfbox.util.TextPosition;

public class PdfTextScalar {
	
	private StringBuffer text = new StringBuffer("");
	public StringBuffer getText() {return text;}
	public void setText(StringBuffer text) {this.text = text;}
	
	private float posY = 0;
	public float getPosY() {return posY;}
	public void setPosY(float posY) {this.posY = posY;}
	
	private float posX = 0;
	public float getPosX() {return posX;}
	public void setPosX(float posX) {this.posX = posX;}
	
	private float height = 0;
	public float getHeight() {return height;}
	public void setHeight(float height) {this.height = height;}
	
	private float width = 0;
	public float getWidth() {return width;}
	public void setWidth(float width) {this.width = width;}
	
	
	public boolean match(String... searchWords){
//		String word = StringUtil.getEmptyIfNull(this.text.toString()).toLowerCase();
		for(String searchWord : searchWords){
//			System.err.println(" searchWords : " + searchWord + " == " + text.toString());
//			if(word.indexOf(searchWord) != -1)
//				return true;
			
			if(text.toString().trim().equalsIgnoreCase(searchWord))
				return true;
		}
		return false;
	}
	
	public void appendText(TextPosition textPos){
//		System.err.println(text.length()+" -  "+textPos.getCharacter() + " - X "+ textPos.getXDirAdj() + " - " + (textPos.getYDirAdj() - textPos.getHeight()));
		
		
		if(text.length() == 0){
			this.posY = textPos.getYDirAdj() - textPos.getHeight();//textPos.getYDirAdj();//textPos.getY();//textPos.getYDirAdj();
			this.posX = textPos.getXDirAdj();//textPos.getXDirAdj();//textPos.getX();//textPos.getXDirAdj();
//			textPos.getTextPos().
		}
//		System.err.println(textPos.getFont().getFontDescriptor().getFontName());
//		textPos.getFont().getFontDescriptor().ge
//		textPos.getFontSizeInPt()
		
		
		if(this.height < textPos.getHeight())
			this.height = textPos.getHeight();
		
		this.width += textPos.getWidthDirAdj();
		text.append(textPos.getCharacter());
	}
	
	
	

}
