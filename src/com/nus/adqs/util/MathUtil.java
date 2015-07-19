package com.nus.adqs.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class MathUtil {
	
	public static final BigDecimal HUNDRED = new BigDecimal("100");
	public static final BigDecimal TEN = new BigDecimal("10");
	public static final BigDecimal ZERO = new BigDecimal("0");
	public static final BigDecimal NINETY = new BigDecimal("90");
	public static final BigDecimal EIGHTY = new BigDecimal("80");
	
	public static final DecimalFormat MASK_TWO_DIGIT = new DecimalFormat("00");
	
	public static BigDecimal getPercentage(BigDecimal amt){
		try{
			return MathUtil.HUNDRED.divide(amt,4,BigDecimal.ROUND_HALF_UP);
		}catch(Exception e){
			return MathUtil.ZERO;
		}
	}
	
	public static BigDecimal convertBigDecimal(Object value){
		try{
			String str = String.valueOf(value);
			if(StringUtil.isEmpty(str))
				return MathUtil.ZERO;
			
			return new BigDecimal(str);
		}catch(Exception e){
			return MathUtil.ZERO;
		}
	}
	
	public static int getRoundedUpTenth(int val){
		return MathUtil.getNearestTenth(val,BigDecimal.ROUND_UP);
	}
	
	public static int getRoundedDownTenth(int val){
		return MathUtil.getNearestTenth(val,BigDecimal.ROUND_DOWN);
	}
	
	
	public static int getNearestTenth(int val,int rounding){
		BigDecimal number = MathUtil.convertBigDecimal(val);
		if(number.equals(MathUtil.ZERO))
			return 0;
		
		number = number.movePointLeft(1);
		number = number.setScale(0,rounding).multiply(MathUtil.TEN);
		return number.intValue();
	}
	
	


}
