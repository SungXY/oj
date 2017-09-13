/* Generated By:JavaCC: Do not edit this line. Token.java Version 5.0 */
/* JavaCCOptions:TOKEN_EXTENDS=,KEEP_LINE_COL=null,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package com.bupt.service.search.parser.cpp;

/**
 * Describes the input token stream.
 */

public class Token implements java.io.Serializable {

  /**
   * The version identifier for this Serializable class.
   * Increment only if the <i>serialized</i> form of the
   * class changes.
   */
  private static final long serialVersionUID = 1L;

  /**
   * An integer that describes the kind of this token.  This numbering
   * system is determined by JavaCCParser, and a table of these numbers is
   * stored in the file ...Constants.java.
   */
  public int kind;

  /** The line number of the first character of this Token. */
  public int beginLine;
  /** The column number of the first character of this Token. */
  public int beginColumn;
  /** The line number of the last character of this Token. */
  public int endLine;
  /** The column number of the last character of this Token. */
  public int endColumn;

  /**
   * The string image of the token.
   */
  public String image;

  /**
   * A reference to the next regular (non-special) token from the input
   * stream.  If this is the last token from the input stream, or if the
   * token manager has not read tokens beyond this one, this field is
   * set to null.  This is true only if this token is also a regular
   * token.  Otherwise, see below for a description of the contents of
   * this field.
   */
  public Token next;

  /**
   * This field is used to access special tokens that occur prior to this
   * token, but after the immediately preceding regular (non-special) token.
   * If there are no such special tokens, this field is set to null.
   * When there are more than one such special token, this field refers
   * to the last of these special tokens, which in turn refers to the next
   * previous special token through its specialToken field, and so on
   * until the first special token (whose specialToken field is null).
   * The next fields of special tokens refer to other special tokens that
   * immediately follow it (without an intervening regular token).  If there
   * is no such token, this field is null.
   */
  public Token specialToken;

  /**
   * An optional attribute value of the Token.
   * Tokens which are not used as syntactic sugar will often contain
   * meaningful values that will be used later on by the compiler or
   * interpreter. This attribute value is often different from the image.
   * Any subclass of Token that actually wants to return a non-null value can
   * override this method as appropriate.
   */
  public Object getValue() {
    switch(kind){
	    case 0: return "EOF";
	    case 1: return "LINE_NUMBER";
	    case 2: return "LINE_DIRECTIVE";
	    case 3: return "AFTER_LINE_DIRECTIVE";
	    case 4: return "IN_LINE_COMMENT";
	    case 5: return "IN_COMMENT";
	    case 6: return "PREPROCESSOR_OUTPUT";
	    case 19: return "LCURLYBRACE";
	    case 20: return "RCURLYBRACE";
	    case 21: return "LSQUAREBRACKET";
	    case 22: return "RSQUAREBRACKET";
	    case 23: return "LPARENTHESIS";
	    case 24: return "RPARENTHESIS";
	    case 25: return "SCOPE";
	    case 26: return "COLON";
	    case 27: return "SEMICOLON";
	    case 28: return "COMMA";
	    case 29: return "QUESTIONMARK";
	    case 30: return "ELLIPSIS";
	    case 31: return "ASSIGNEQUAL";
	    case 32: return "TIMESEQUAL";
	    case 33: return "DIVIDEEQUAL";
	    case 34: return "MODEQUAL";
	    case 35: return "PLUSEQUAL";
	    case 36: return "MINUSEQUAL";
	    case 37: return "SHIFTLEFTEQUAL";
	    case 38: return "SHIFTRIGHTEQUAL";
	    case 39: return "BITWISEANDEQUAL";
	    case 40: return "BITWISEXOREQUAL";
	    case 41: return "BITWISEOREQUAL";
	    case 42: return "OR";
	    case 43: return "AND";
	    case 44: return "BITWISEOR";
	    case 45: return "BITWISEXOR";
	    case 46: return "AMPERSAND";
	    case 47: return "EQUAL";
	    case 48: return "NOTEQUAL";
	    case 49: return "LESSTHAN";
	    case 50: return "GREATERTHAN";
	    case 51: return "LESSTHANOREQUALTO";
	    case 52: return "GREATERTHANOREQUALTO";
	    case 53: return "SHIFTLEFT";
	    case 54: return "SHIFTRIGHT";
	    case 55: return "PLUS";
	    case 56: return "MINUS";
	    case 57: return "STAR";
	    case 58: return "DIVIDE";
	    case 59: return "MOD";
	    case 60: return "PLUSPLUS";
	    case 61: return "MINUSMINUS";
	    case 62: return "TILDE";
	    case 63: return "NOT";
	    case 64: return "DOT";
	    case 65: return "POINTERTO";
	    case 66: return "DOTSTAR";
	    case 67: return "ARROWSTAR";
	    case 68: return "AUTO";
	    case 69: return "BREAK";
	    case 70: return "CASE";
	    case 71: return "CATCH";
	    case 72: return "CHAR";
	    case 73: return "CONST";
	    case 74: return "CONTINUE";
	    case 75: return "_DEFAULT";
	    case 76: return "DELETE";
	    case 77: return "DO";
	    case 78: return "DOUBLE";
	    case 79: return "ELSE";
	    case 80: return "ENUM";
	    case 81: return "EXTERN";
	    case 82: return "FLOAT";
	    case 83: return "FOR";
	    case 84: return "FRIEND";
	    case 85: return "GOTO";
	    case 86: return "IF";
	    case 87: return "INLINE";
	    case 88: return "MUTABLE";
	    case 89: return "EXPLICIT";
	    case 90: return "DYNAMIC_CAST";
	    case 91: return "STATIC_CAST";
	    case 92: return "REINTERPRET_CAST";
	    case 93: return "CONST_CAST";
	    case 94: return "TYPEID";
	    case 95: return "WCHAR_T";
	    case 96: return "BOOL";
	    case 97: return "INT";
	    case 98: return "LONG";
	    case 99: return "NEW";
	    case 100: return "PRIVATE";
	    case 101: return "PROTECTED";
	    case 102: return "PUBLIC";
	    case 103: return "REDECLARED";
	    case 104: return "REGISTER";
	    case 105: return "RETURN";
	    case 106: return "SHORT";
	    case 107: return "SIGNED";
	    case 108: return "SIZEOF";
	    case 109: return "STATIC";
	    case 110: return "STRUCT";
	    case 111: return "CLASS";
	    case 112: return "TYPENAME";
	    case 113: return "SWITCH";
	    case 114: return "TEMPLATE";
	    case 115: return "EXPORT";
	    case 116: return "THIS";
	    case 117: return "TRY";
	    case 118: return "TYPEDEF";
	    case 119: return "UNION";
	    case 120: return "UNSIGNED";
	    case 121: return "VIRTUAL";
	    case 122: return "VOID";
	    case 123: return "VOLATILE";
	    case 124: return "WHILE";
	    case 125: return "OPERATOR";
	    case 126: return "TRUETOK";
	    case 127: return "FALSETOK";
	    case 128: return "THROW";
	    case 129: return "BOOLEAN";
	    case 130: return "OCTALINT";
	    case 131: return "OCTALLONG";
	    case 132: return "UNSIGNED_OCTALINT";
	    case 133: return "UNSIGNED_OCTALLONG";
	    case 134: return "DECIMALINT";
	    case 135: return "DECIMALLONG";
	    case 136: return "UNSIGNED_DECIMALINT";
	    case 137: return "UNSIGNED_DECIMALLONG";
	    case 138: return "HEXADECIMALINT";
	    case 139: return "HEXADECIMALLONG";
	    case 140: return "UNSIGNED_HEXADECIMALINT";
	    case 141: return "UNSIGNED_HEXADECIMALLONG";
	    case 142: return "FLOATONE";
	    case 143: return "FLOATTWO";
	    case 144: return "CHARACTER";
	    case 145: return "STRING";
	    case 149: return "ID";
	    default: return "DEFAULT";

    }
  }

  /**
   * No-argument constructor
   */
  public Token() {}

  /**
   * Constructs a new token for the specified Image.
   */
  public Token(int kind)
  {
    this(kind, null);
  }

  /**
   * Constructs a new token for the specified Image and Kind.
   */
  public Token(int kind, String image)
  {
    this.kind = kind;
    this.image = image;
  }

  /**
   * Returns the image.
   */
  public String toString()
  {
    return image;
  }

  /**
   * Returns a new Token object, by default. However, if you want, you
   * can create and return subclass objects based on the value of ofKind.
   * Simply add the cases to the switch for all those special cases.
   * For example, if you have a subclass of Token called IDToken that
   * you want to create if ofKind is ID, simply add something like :
   *
   *    case MyParserConstants.ID : return new IDToken(ofKind, image);
   *
   * to the following switch statement. Then you can cast matchedToken
   * variable to the appropriate type and use sit in your lexical actions.
   */
  public static Token newToken(int ofKind, String image)
  {
    switch(ofKind)
    {
      default : return new Token(ofKind, image);
    }
  }

  public static Token newToken(int ofKind)
  {
    return newToken(ofKind, null);
  }

}
/* JavaCC - OriginalChecksum=7d8f34df21570aec3df1e6a9189f0a6e (do not edit this line) */
