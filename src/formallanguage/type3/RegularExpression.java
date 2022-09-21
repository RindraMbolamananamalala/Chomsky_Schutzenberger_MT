package formallanguage.type3;

/**
 * 
 * @author Rindra Mbolamananamalala
 * 
 *
 */
public class RegularExpression {
	
	/**
	 * 
	 * @param objectZString The String still written in Object-Z to be translated into Java (at the level
	 * of the Type 3 : Regular Expression).
	 * @return A Type 3 converted string.
	 */
	public String convert(String objectZString) {
		String stringToReturn = "";
		stringToReturn = objectZString
							 // Object-Z's "#" function conversion (without parentheses)
							 .replaceAll("(#)([\\w]+)", "$2.size()")
							 
							 // Object-Z's "Equals()" function conversion
							 .replaceAll(".Equals\\(", ".equals\\(")
							 
							 // Object-Z's "Contains()" function conversion
							 .replaceAll(".Contains\\(", ".contains\\(")
							 
							 // Object-Z's "ToCharBag()" function conversion (for String without parentheses balancing)
							 .replaceAll("([\\w]+)(.ToCharBag\\(\\))", "AdditionalFunctions.toCharList\\($1\\)")
							 
							 // Object-Z's "ToCharSet()" function conversion (for String without parentheses balancing)
							 .replaceAll("([\\w]+)(.ToCharSet\\(\\))", "AdditionalFunctions.toCharList\\($1\\)")
							 
							 // Object-Z's  "ToString()" function conversion
							 .replaceAll(".ToString\\(\\)", ".toString\\(\\)")
							 
							 // Object-Z's "Print" function conversion
							 .replaceAll("(Print)([[\\w \\W [ℕℤ𝔸≤]]&&[^;]]+)(;)", "System__OZ.out__OZ.print__OZ\\($2\\)$3")
							 
							 // Object-Z's "Random(), Random(A) and Random(A, A)" functions conversion
							 .replaceAll("(Random)([ ]{0,})([\\(]{1,1})([\\w [ℕ]]{0,})", "StudioAdditionalFunctions.getInstance().getRandomNumber$3$4")
							 
							 // Object-Z's "Inc()" function conversion
							 .replaceAll("(Inc)([ ]{0,})(\\({1,1})", "AdditionalFunctions.inc$3")
							 
							 // Object-Z's "Dec()" function conversion
							 .replaceAll("(Dec)([ ]{0,})(\\({1,1})", "AdditionalFunctions.dec$3")
							 
							 // Object-Z's "AsType()" function conversion (for object's name calling not between parentheses)
							 .replaceAll("([\\w]+)(.)(AsType\\()", "\\($1\\)$2$3")
							 
							 // Object-Z's "ℕ" type conversion«ENDREM»
							 .replaceAll("([^\\w&&[^ℕ]]{1,})(ℕ)(([^\\w&&[^ℕ]]{0,}))","$1int$3").replaceAll("^ℕ$", "int")
							 
							 // Object-Z's "ℤ" type conversion«ENDREM»
							 .replaceAll("([^\\w&&[^ℤ]]{1,})(ℤ)(([^\\w&&[^ℤ]]{0,}))","$1int$3").replaceAll("^ℤ$", "int")
							 
							 // Object-Z's "𝔸" type conversion«ENDREM»
							 .replaceAll("([^\\w&&[^𝔸]]{1,})(𝔸)(([^\\w&&[^𝔸]]{0,}))","$1double$3").replaceAll("^𝔸$", "double")  .replaceAll("([^\\w&&[^𝔸]]{1,})(A)(([^\\w&&[^𝔸]]{1,}))","$1double$3").replaceAll("^A$", "double")
							 
							 // Object-Z's "Char" type conversion
							 .replaceAll("([\\W]{1,})(Char)([\\W]{1,})", "$1char$3").replaceAll("^Char$", "char")
							
							 // Object-Z's "Boolean" type conversion
							 .replaceAll("([\\W]{1,})(Boolean)([\\W]{1,})", "$1boolean$3").replaceAll("^Boolean$", "boolean")
							 
							 // Object-Z's  "div" operator conversion
							 .replaceAll("([\\W]{1,})(div)([\\W]{1,})", "$1/$3")
							 
							 // Object-Z's "mod" operator conversion
							 .replaceAll("([\\W]{1,})(mod)([\\W]{1,})", "$1%$3")
							 
							 // Object-Z's "≤" operator conversion
							 .replaceAll("≤", "<=")
							 
							 // Object-Z's "≥" operator conversion
							 .replaceAll("≥", ">=")
							 
							 // Object-Z's "For" loop conversion
							 .replaceAll("([\\W]{1,})(For)([\\s]{0,})([\\(]{1,1})([\\w \\W]{1,})", "$1for$4$5").replaceAll("(^For)([\\s]{0,})([\\(]{1,1})([\\w \\W]{1,})", "for$3$4")
							 
							 // Object-Z's "≠" operator conversion
							 .replaceAll("≠", "!=")
							 
							 // Object-Z's "Foreach" loop conversion
							 .replaceAll("([\\W])(Foreach)([\\s]{0,})([\\(]{1,1})([\\s]{0,})(([\\w [ℕℤ𝔸]]{1,})([\\s]{1,})([\\w]{1,}))([\\s]{1,})(in)([\\s]{1,})([\\w \\W]{1,})", "$1for$4$7 $9 : $13")
							 
							 // Object-Z's "Do...While..." loop conversion
							 .replaceAll("([\\W]{1,})(Do)([\\w \\W]{1,})(While)([\\w \\W &&[^;]]{1,})(;)", "$1do$3while($5)$6")
							 
							 // Object-Z's "While" loop conversion
							 .replaceAll("([\\W]{0,})(While)([\\s]{0,})([\\W \\w && [^\\{\\}]]{1,})([\\s]{0,})([\\{]{1,1})([\\W \\w]{1,})", "$1while($4)$6$7")
							 
							 // Object-Z's "If...Then...Else If...Else...Endif" conditional instructions block conversion
							 .replaceAll("([\\W]{1,})(If)([\\s]{0,})(((?!If|Then)[\\W \\w]){1,})([\\W]{1,})(Then)([\\W]{1,})", "$1if($4){$8")
							 .replaceAll("([\\W]{1,})(Else)([\\s]{1,})", "$1}else ")
							 .replaceAll("([\\W]+)(Endif$)", "$1}").replaceAll("([\\W]+)(Endif)([\\s]{1,})", "$1}")
							 .replaceAll("([\\W]{1,})(else(?!([\\s]{0,})(if)([\\W]){1,}))([\\W \\w]{1,})", "$1$2{$6")
							 
							 // Object-Z's "Switch...Case..." conditional instructions block conversion
							 .replaceAll("([\\W]{1,})([\\s]{0,})(Case)([\\s]{1,})([\"]{0,1})([\\w [\\s]]+)([\"]{0,1})([\\s]{0,})(:)([\\s]{0,})(((?!([\\W]{1,})(Default)([\\W]{1,})|([\\W]{1,})(Case)([\\s]{1,})(([\"]{0,1})([\\w [\\s]]{1,})([\"]{0,1}))([\\s]{0,})(:)([\\s]{0,}))([\\w \\W])){1,})", "$1break;\ncase $5$6$7 : $11")
							 .replaceAll("([\\W]{1,})([\\s]{0,})(Switch)([\\s]{0,})([\\w]+)([\\s]{0,})([\\{]{1,1})([\\s]{0,})((break)(;))", "$1switch ($5) $7").replaceAll("(^Switch)([\\s]{0,})([\\w]+)([\\s]{0,})([\\{]{1,1})([\\s]{0,})((break)(;))", "switch ($3) $5")
							 .replaceAll("([\\W]{1,})([\\s]{0,})(Default)([\\s]{0,})(:)([\\s]{0,})([\\w \\W &&[^\\}]]{0,})([\\}]{1,1})", "$1break;\ndefault $5 $7\nbreak;\n$8");
						 
		 return stringToReturn;
	}

}
