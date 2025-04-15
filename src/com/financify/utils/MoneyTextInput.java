package com.financify.utils;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class MoneyTextInput extends PlainDocument{

	private boolean containsDot = false;

	@Override
	public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {

		if(str==null){
			return;
		}

		String currentText = getText(0, getLength());
		if(isDouble(str)){
			super.insertString(offs, str, a);
		} else if(str.equals(".") && !containsDot(currentText)){
			super.insertString(offs, str, a);
		}
	}	

	private boolean isDouble(String str){
		try {
			Double.parseDouble(str)	;
			return true;
		} catch (Exception e) {}
		return false;
	}

	private boolean containsDot(String str){
		for(int i = 0; i < str.length(); i++){
			if(str.charAt(i) == '.') return true;
		}
		return false;
	}
	
}
