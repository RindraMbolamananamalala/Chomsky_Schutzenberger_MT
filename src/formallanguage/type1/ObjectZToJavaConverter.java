package formallanguage.type1;

import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import formallanguage.type2.Brace;
import formallanguage.type2.Parenthesis;
import formallanguage.type2.QuotationMark;
import formallanguage.type2.StackDataStructure;

/**
 * 
 * @author Rindra MBOLAMANANAMALALA
 * @generated
 *
 */
public class ObjectZToJavaConverter {
	 public static void main(String []args) {
		 //Launching the entire conversion process
		 launchTheEntireObjectZJavaConversionProcess();
	 }
	  
	 /**
	  * Launching the entire conversion process
	  * @generated
	  */
	 private static void launchTheEntireObjectZJavaConversionProcess() {
		 String currentLocation = getCurrentLocation();			
		 File currentRepository = new File(currentLocation);
		 for(File file : currentRepository.listFiles()) {
		     if(file.getPath().endsWith(".java") 
		    		 // The converter class doesn't have to be converted
		    		 && !file.getPath().endsWith("ObjectZToJavaConverter.java")
		    		 // The character to balance superclass doesn't have to be converted
		    		 && !file.getPath().endsWith("CharacterToBalance.java")
		    		 // The parenthesis class doesn't have to be converted
		    		 && !file.getPath().endsWith("Parenthesis.java")
		    		// The tack data structure class doesn't have to be converted
		    		 && !file.getPath().endsWith("StackDataStructure.java")
		    	 ) {
		    	 
		    	 //getting the content of the file in which the conversion process will take place
		    	 String textToConvert = getFileContent(file);		    						
		    	 
		    	 //Parentheses matching verification
		    	 boolean isParenthesesMatching=true;
		    	 isParenthesesMatching = getParenthesesMatchingValidity(textToConvert);
		    	 if(!isParenthesesMatching) {
		    		 //Can't convert Object-Z parts
		    		 JOptionPane.showMessageDialog(null, textToConvert + "Error(s) on characters to balance balancing encountered by the Conversion Stack!");
		    	 }else {
		    		 //The Conversion process can then take place 
		    		 String convertedText = convertText(textToConvert);
		    		//Replacing the old file by the new converted file
		    		 writeOnAFile(file, convertedText);
		    	 }					
		     }
		 }	
	 }
	 
	
	 /**
	  * 
	  * Verifying if the parentheses encountered in each 
	  * Object-Z part are correctly balanced or not  
	  * @generated
	  * @param text The text containing the Object-Z parts to convert
	  * @return The validity of the parentheses matching inside the Object-Z parts  
	  */
	 private static boolean getParenthesesMatchingValidity(String text) {
			StackDataStructure parenthesesStack=new StackDataStructure();
			for(int i=0; i!=text.length(); ++i) {
				if(text.charAt(i)=='(') {
					parenthesesStack.push(new Parenthesis("" + text.charAt(i), i));
				}
				if(text.charAt(i)==')') {
					if(parenthesesStack.isEmpty()) {
						return false;
					}
					parenthesesStack.pop();
				}
			}
			return parenthesesStack.isEmpty();
		}
	 
	 /**
	  * convert all the Object-Z parts into their equivalence in the Java
	  * Programming Language 
	  * @generated
	  * @param text The text containing the Object-Z parts to convert
	  * @return The new version of the text, with Object-Z parts converted into their Java equivalence
	  */
	 private static String convertText(String text) {
			String convertedText = text;
			char []tabConvertedText = null;
			
			//"#((\\w+))" conversion
			while(convertedText.contains("#(")) {
				tabConvertedText = convertedText.toCharArray();
				for(int i = 0; i != tabConvertedText.length; ++ i) {
					if(tabConvertedText[i] == '#') {
						int openingParenthesisPosition = i + 1;
						if(tabConvertedText[openingParenthesisPosition] == '(') {
							int closingParenthesesisPosition = getMatchingParenthesisPosition(convertedText, openingParenthesisPosition);
							convertedText = convertedText.substring(0, openingParenthesisPosition - 1) 
												+ convertedText.substring(openingParenthesisPosition, closingParenthesesisPosition + 1) 
												+ ".size()" 
												+ convertedText.substring(closingParenthesesisPosition + 1);
							}
						break;
					}
				}
			}
	
			//"([\\(]{1,})(\\w+)([\\)]{1,}).ToCharBag\\(\\)" conversion
			while(convertedText.contains(".ToCharBag()")) {
				tabConvertedText = convertedText.toCharArray();
				int closingParenthesesisPosition = convertedText.indexOf(".ToCharBag()") - 1;
				for(int i = 0; i != tabConvertedText.length; ++ i) {
					if(tabConvertedText[i] =='(' && getMatchingParenthesisPosition(convertedText, i) == closingParenthesesisPosition) {
						int openingParenthesisPosition = i;
						convertedText = convertedText.substring(0, openingParenthesisPosition)
											+ "HiLLSStudioAdditionalFunctions.toCharList("
											+ convertedText.substring(openingParenthesisPosition, closingParenthesesisPosition + 1)
											+ ")"
											+ convertedText.substring(closingParenthesesisPosition + 1); 
						convertedText = convertedText.replaceFirst(".ToCharBag\\(\\)", "");
						break;
					}
				}
			}	
			
			//"([\\(]{1,})(\\w+)([\\)]{1,}).ToCharSet\\(\\)" conversion
			while(convertedText.contains(".ToCharSet()")) {
				tabConvertedText = convertedText.toCharArray();
				int closingParenthesesisPosition = convertedText.indexOf(".ToCharSet()") - 1;
				for(int i = 0; i != tabConvertedText.length; ++ i) {
					if(tabConvertedText[i] =='(' && getMatchingParenthesisPosition(convertedText, i) == closingParenthesesisPosition) {
						int openingParenthesisPosition = i;
						convertedText = convertedText.substring(0, openingParenthesisPosition)
											+ "HiLLSStudioAdditionalFunctions.toCharList("
											+ convertedText.substring(openingParenthesisPosition, closingParenthesesisPosition + 1)
											+ ")"
											+ convertedText.substring(closingParenthesesisPosition + 1); 
						convertedText = convertedText.replaceFirst(".ToCharSet\\(\\)", "");
						break;
					}
				}
			}	
			
			//(System__OZ.out__OZ.print__OZ)([\\(]{1})([\\w \\W&&[^;]]+)([\\)]{1})(;) conversion
			while(convertedText.contains("System__OZ.out__OZ.print__OZ")) {
				int openingParenthesesisPosition = convertedText.indexOf("System__OZ.out__OZ.print__OZ") 
														+ "System__OZ.out__OZ.print__OZ".length()  ;
				int closingParenthesesisPosition = getMatchingParenthesisPosition(convertedText, openingParenthesesisPosition);
				convertedText = convertedText.substring(0, openingParenthesesisPosition + 1)
									+ treatStringCalledAsAParameter(
											convertedText.substring(openingParenthesesisPosition + 1, closingParenthesesisPosition))
									+ convertedText.substring(closingParenthesesisPosition);
				convertedText = convertedText.replaceFirst("System__OZ.out__OZ.print__OZ", "System.out.print");
			}
			
			//(.)(isTypeOf)([\\(]{1})(\\w{1,}) conversion
			while(convertedText.replaceAll(" ", "")
							   .contains(".isTypeOf")) {
				int openingParenthesesisPosition = convertedText.indexOf(".isTypeOf") 
						+ ".isTypeOf".length()  ;
				int closingParenthesesisPosition = getMatchingParenthesisPosition(convertedText, openingParenthesesisPosition);
				convertedText = convertedText.substring(0, convertedText.indexOf(".isTypeOf"))
									+ " " 
										+ "instanceof" 
										+ " "
										+ convertedText.substring(openingParenthesesisPosition + 1, closingParenthesesisPosition)
									+ convertedText.substring(closingParenthesesisPosition + 1);
			}
			
			//(.)(isKindOf)([\\(]{1})(\\w{1,}) conversion
			while(containsRegExPattern(convertedText, "([ ]{0,})(.)([ ]{0,})(isKindOf)([ ]{0,})([\\(]{1,1})")) {
				int openingParenthesesisPosition =  getIndexOfRegExPatternEnd(convertedText, "([ ]{0,})(.)([ ]{0,})(isKindOf)([ ]{0,})([\\(]{1,1})") 
														- 1;
				int closingParenthesesisPosition = getMatchingParenthesisPosition(convertedText, openingParenthesesisPosition);
				convertedText = convertedText.substring(0, getIndexOfRegExPatternStart(convertedText, "([ ]{0,})(.)([ ]{0,})(isKindOf)([ ]{0,})([\\(]{1,1})"))
									+ " " 
										+ "instanceof" 
										+ " "
										+ convertedText.substring(openingParenthesesisPosition + 1, closingParenthesesisPosition)
									+ convertedText.substring(closingParenthesesisPosition + 1);
			}
			
			//(\\w \\W)([\\)]{1})(.)(AsType)([\\(]{1})(\\w \\W)([\\)]{1}) conversion
			while(convertedText.contains(".AsType(")) {
				tabConvertedText = convertedText.toCharArray();
				int variableClosingParenthesisPosition = convertedText.indexOf(".AsType(") - 1 ;
				int variableOpeningParenthesisPosition = -1;
				for(int i = 0; i != tabConvertedText.length; ++ i) {
					if(tabConvertedText[i] =='(' && getMatchingParenthesisPosition(convertedText, i) == variableClosingParenthesisPosition) {
						variableOpeningParenthesisPosition = i;
						break;
					}
				}
				int functionOpeningParenthesisPosition = convertedText.indexOf(".AsType(") 
																			+ ".AsType".length();
				int functionClosingParenthesisPosition = getMatchingParenthesisPosition(convertedText, functionOpeningParenthesisPosition);
				convertedText = convertedText.substring(0, variableOpeningParenthesisPosition) 
									+ "(" + convertedText.substring(functionOpeningParenthesisPosition + 1, functionClosingParenthesisPosition) + ")"
									+ "(" + convertedText.substring(variableOpeningParenthesisPosition + 1, variableClosingParenthesisPosition) + ")"
									+ convertedText.substring(functionClosingParenthesisPosition + 1);
			}	
			
			return convertedText;
		}
	 
	 /**
	  * 
	  * A function which returns the converted version of a text still written
	  * with Object-Z syntaxes.
	  * @generated
	  * @param text The String called as a parameter by a function to be treated
	  * @return The converted version of the String given in the parameter
	  */
	 private static String treatStringCalledAsAParameter(String text) {
		 String result = text;
		 result = result.trim();
		 int cptQuotationMarks = 0;
		 int currentOpeningQMPosition = -1;
		 for (int i = 0; i != result.length(); ++ i) {
			 if(result.charAt(i) == '"') {
				 ++ cptQuotationMarks;
				 if(cptQuotationMarks % 2 != 0) {
					 currentOpeningQMPosition = i;
				 }
			 }else{
				 if(result.charAt(i) == ' ' 
						 && !isPositionSituatedBetween(i
								 		, currentOpeningQMPosition
								 		, getMatchingQuotationMark(result, currentOpeningQMPosition))) {
					 result = result.substring(0, i)
							 	+ " OP__OZ(OZ__+__OZ)__OZ "
							 	+ result.substring(i+1);
					 i = i + " OP__OZ(OZ__+__OZ)__OZ ".length() - 1;
				 }
			 }
		 }
		 result = result.replaceAll("(([ ]{0,})(OP__OZ\\(OZ__\\+__OZ\\)__OZ)([ ]{0,})){1,}", " + ");
		 return result;
	 }
	 
	 
	 /**
	  * 
	  * Getting the position of the matching closing parenthesis of an opening parenthesis. 
	  * @generated 
	  * @param text The text in which the parenthesis have been written 
	  * @param openingParenthesisPos The position of the opening parenthesis
	  * @return The position of the matching closing parenthesis
	  */
	 private static int getMatchingParenthesisPosition(String text, int openingParenthesisPos) {
			StackDataStructure parenthesesStack=new StackDataStructure();
			for(int i = 0; i != text.length(); ++ i) {
				if(text.charAt(i) == '(') {
					parenthesesStack.push(new Parenthesis("" + text.charAt(i), i));
				}
				if(text.charAt(i) == ')') {
					if(((Parenthesis)parenthesesStack.pop()).getPosition() == openingParenthesisPos) {
						return i;
					}
				}
			}
			//no matching closing parenthesis
			return -1;
		}
	  
	 /**
	  * 
	  * Getting the position of the matching closing quotation mark of an opening quotation mark. 
	  * @generated 
	  * @param text The text in which the quotation marks have been written in 
	  * @param openingQuotationMarkPosition The position of the opening quotation mark
	  * @return The position of the matching closing quotation mark
	  */
	 private static int getMatchingQuotationMark(String text, int openingQuotationMarkPosition) {
		 StackDataStructure quotationMarksStack = new StackDataStructure();
		 int cptQuotationMarks = 0;
		 for(int i = 0; i != text.length(); ++i) {
			 if(text.charAt(i) == '"') {
				 ++ cptQuotationMarks;
				 if(cptQuotationMarks % 2 != 0) {
					 quotationMarksStack.push(new QuotationMark("\"", i));
				 }else {
					 int currentOpeningQuotationMarkPosition = ((QuotationMark)quotationMarksStack.pop()).getPosition();
					 if(currentOpeningQuotationMarkPosition == openingQuotationMarkPosition) {
						 return i;
					 }
				 }
			 }
		 }
		// no matching closing quotation mark was found
		return -1;
	 }
	 
	 /**
	  * 
	  * Getting the position of the matching right brace of a left brace
	  * @generated 
	  * @param text The text in which the braces have been written in
	  * @param leftBracePosition The position of the left brace
	  * @return The position of the matching right brace
	  */
	 private static int getMatchingRightBrace(String text, int leftBracePosition) {
		 StackDataStructure bracesStack = new StackDataStructure();
		 int cptBraces = 0;
		 for(int i = 0; i != text.length(); ++i) {
			 if(text.charAt(i) == '"') {
				 ++ cptBraces;
				 if(cptBraces % 2 != 0) {
					 bracesStack.push(new Brace("\"", i));
				 }else {
					 int currentLeftBracePosition = ((QuotationMark)bracesStack.pop()).getPosition();
					 if(currentLeftBracePosition == leftBracePosition) {
						 return i;
					 }
				 }
			 }
		 }
		// no matching right brace was found
		return -1;
	 }
	 
	 private static boolean containsRegExPattern(String text, String pattern) {
		  Pattern regExPattern = Pattern.compile(pattern);
		  Matcher regExMatcher = regExPattern.matcher(text);
		  return regExMatcher.find();
	 }
	 
	 private static int getIndexOfRegExPatternStart(String text, String pattern) {
		 Pattern regExPattern = Pattern.compile(pattern);
		 Matcher regExMatcher = regExPattern.matcher(text);
		 if(regExMatcher.find())
			 return regExMatcher.start();
		 // 0 occurrences of the pattern was found
		 return -1;
	 }
	 
	 private static int getIndexOfRegExPatternEnd(String text, String pattern) {
		 Pattern regExPattern = Pattern.compile(pattern);
		 Matcher regExMatcher = regExPattern.matcher(text);
		 if(regExMatcher.find())
			 return regExMatcher.end();
		 // 0 occurrences of the pattern was found
		 return -1;
	 }
	 
	 /**
	  * 
	  *	A function that determines whether or not a given position's value is within a given range
	  * @param currentPosition The position to be tested 
	  * @param lowerBound The range's lower bound
	  * @param upperBound The range's upper bound
	  * @return TRUE if the position value is within the range, 
	  * 		FALSE otherwise.
	  */
	 private static boolean isPositionSituatedBetween(int currentPosition, int lowerBound, int upperBound) {
		 return currentPosition > lowerBound 
				 	&& currentPosition < upperBound;
	 }
	 
	/**
	 * 
	  * Getting the current location in which the files to convert are found
	  * @return The current location
	  */
	 private static String getCurrentLocation() {
		String result=null;
		try {
			result=new File(".").getCanonicalPath();
		} catch (IOException e) {
			System.err.println("" + e);
			return null;
		}
		return result;
	 }
	 
	 /**
	  * 
	  * Getting all the text content of a given file.
	  * @generated
	  * @param file The file in which the text is supposed to be retrieved
	  * @return The text content of the given file
	  */
	 private static String getFileContent(File file) {
		 String content = "";
		 try{
	    	 InputStream inputStream = new FileInputStream(file.getPath()); 
			 InputStreamReader inputStreamreader = new InputStreamReader(inputStream);
			 BufferedReader reader=new BufferedReader(inputStreamreader);
			 String line;
			 while ((line = reader.readLine()) != null){
				 content += line 
						 		+ "\n";
			 }
			 reader.close();
    	 }catch (Exception e){
    		 System.out.println(e.toString());
    		 return null;
    	 }
		 return content;
	 }
	 
	 /**
	  * 
	  * Writing a desired text in a given file as the latter's new content.
	  * @generated
	  * @param file The file in which the text will be written 
	  * @param content The text to write in the file
	  */
	 private static void writeOnAFile(File file, String content) {
		 try {
			 FileWriter writer=new FileWriter(file.getPath());
			 writer.write(content);
			 writer.close();
		 } catch (IOException e) {
			 System.out.println(e.toString());
		 }
	 }
}
