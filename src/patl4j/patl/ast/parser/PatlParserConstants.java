/* Generated By:JavaCC: Do not edit this line. PatlParserConstants.java */
package patl4j.patl.ast.parser;


/**
 * Token literal values and constants.
 * Generated by org.javacc.parser.OtherFilesGen#start()
 */
public interface PatlParserConstants {

  /** End of File. */
  int EOF = 0;
  /** RegularExpression Id. */
  int LPAREN = 10;
  /** RegularExpression Id. */
  int RPAREN = 11;
  /** RegularExpression Id. */
  int LBRACE = 12;
  /** RegularExpression Id. */
  int RBRACE = 13;
  /** RegularExpression Id. */
  int LBRACKET = 14;
  /** RegularExpression Id. */
  int RBRACKET = 15;
  /** RegularExpression Id. */
  int SEMICOLON = 16;
  /** RegularExpression Id. */
  int COMMA = 17;
  /** RegularExpression Id. */
  int DOT = 18;
  /** RegularExpression Id. */
  int ARROW = 19;
  /** RegularExpression Id. */
  int CONSTANT = 20;
  /** RegularExpression Id. */
  int DIGIT = 21;
  /** RegularExpression Id. */
  int NEW = 22;
  /** RegularExpression Id. */
  int MOD = 23;
  /** RegularExpression Id. */
  int IDENTIFIER = 24;
  /** RegularExpression Id. */
  int LETTER = 25;
  /** RegularExpression Id. */
  int PART_LETTER = 26;

  /** Lexical state. */
  int DEFAULT = 0;
  /** Lexical state. */
  int IN_COMMENT = 1;

  /** Literal token values. */
  String[] tokenImage = {
    "<EOF>",
    "\" \"",
    "\"\\r\"",
    "\"\\t\"",
    "\"\\n\"",
    "\"/*\"",
    "\"/*\"",
    "\"*/\"",
    "<token of kind 8>",
    "<token of kind 9>",
    "\"(\"",
    "\")\"",
    "\"{\"",
    "\"}\"",
    "\"[\"",
    "\"]\"",
    "\";\"",
    "\",\"",
    "\".\"",
    "\"->>\"",
    "<CONSTANT>",
    "<DIGIT>",
    "\"new\"",
    "\"m\"",
    "<IDENTIFIER>",
    "<LETTER>",
    "<PART_LETTER>",
    "\":\"",
    "\"+\"",
    "\"-\"",
    "\"=\"",
  };

}