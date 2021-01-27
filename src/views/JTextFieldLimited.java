package views;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class JTextFieldLimited extends PlainDocument {

	private static final long serialVersionUID = -5189034767958830144L;
	private int charLimit;

	JTextFieldLimited(int limit){
		super();
		this.charLimit = limit;
	}

	JTextFieldLimited(int limit, boolean upper){
		super();
		this.charLimit = limit;
	}

	public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
		if (str == null)
			return;
		if ((getLength() + str.length()) <= charLimit) {
			super.insertString(offset, str, attr);
		}
	}

}
