package com.financify.utils;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class TextInputLimit extends PlainDocument{

	private int limit;

	public TextInputLimit(int limit){
		this.limit = limit;
	}

	@Override
	public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
		if(str == null){
			return;
		} else if(getLength() + str.length() <= limit){
			super.insertString(offs, str, a);
			
		}
	}
	
}
