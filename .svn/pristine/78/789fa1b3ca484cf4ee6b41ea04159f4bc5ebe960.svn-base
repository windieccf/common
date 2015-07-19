package com.nus.adqs.lib.excel.impl;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.nus.adqs.lib.excel.ExcelEngine;

public class TabCombineExcel extends ExcelEngine{
	
	
	public void init(){
		String folderPath = "C:/workbench/project/development/nus/excelmerge/";
		
		try {
			List<Workbook> workbooks = new ArrayList<Workbook>();
			workbooks.add(new XSSFWorkbook((new FileInputStream(folderPath+"work_01.xlsx")) ));
			workbooks.add(new XSSFWorkbook((new FileInputStream(folderPath+"work_02.xlsx")) ));
			Workbook clonedWorkBook = this.execute(workbooks);
			
			FileOutputStream fileOut = new FileOutputStream(folderPath+"combined.xlsx");
			clonedWorkBook.write(fileOut);
		    fileOut.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Workbook execute(List<Workbook> workbooks ){
		Workbook clonedWorkBook = new XSSFWorkbook();
		List<String> names = new ArrayList<String>();
		
		for(Workbook workbook : workbooks){
			// iterate through every workbook
			int sheetCount = workbook.getNumberOfSheets();
			for(int i = 0; i < sheetCount;i++){
				Sheet sourceSheet = workbook.getSheetAt(i);
//				System.err.println(sourceSheet.getNumMergedRegions());
				// check for empty row
				if(sourceSheet.getLastRowNum() == 0)
					continue;
				
				String sheetName = sourceSheet.getSheetName();
				String suffix = "";
				int nameIdx = 1;
				while(names.contains(sheetName+suffix)){
					nameIdx++;
					suffix = "_"+nameIdx;
				}
				names.add(sheetName+suffix);
					
				Sheet destinationSheet = clonedWorkBook.createSheet(sheetName);
				super.writeContent(-1,sourceSheet, destinationSheet, true);
			}
		}
		
		return clonedWorkBook;
	}
	
	
	public static void main (String... args){
		(new TabCombineExcel()).init();
	}
	
	
}
