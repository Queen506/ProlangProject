public class Yytoken {
    public static final int TOKEN_PLUS = 101;
    public static final int TOKEN_MINUS = 102;
    public static final int TOKEN_MULTIPLY = 103;
    public static final int TOKEN_DIVIDE = 104;
    public static final int TOKEN_EQUALS = 105;
    public static final int TOKEN_GREATER_THAN = 106;
    public static final int TOKEN_GREATER_THAN_OR_EQUAL = 107;
    public static final int TOKEN_LESS_THAN = 108;
    public static final int TOKEN_LESS_THAN_OR_EQUAL = 109;
    public static final int TOKEN_EQUAL_TO = 110;
    public static final int TOKEN_INCREMENT = 111;
    public static final int TOKEN_DECREMENT = 112;

    public static final int TOKEN_LPAREN = 113;
    public static final int TOKEN_RPAREN = 114;
    public static final int TOKEN_SEMICOLON = 115;
    public static final int TOKEN_IF = 116;
    public static final int TOKEN_THEN = 117;
    public static final int TOKEN_ELSE = 118;
    public static final int TOKEN_ENDIF = 119;
    public static final int TOKEN_WHILE = 120;
    public static final int TOKEN_DO = 121;
    public static final int TOKEN_ENDWHILE = 122;
    public static final int TOKEN_PRINT = 123;
    public static final int TOKEN_NEWLINE = 124;
    public static final int TOKEN_READ = 125;
    public static final int TOKEN_IDENTIFIER = 126;
    public static final int TOKEN_INTEGER = 127;
    public static final int TOKEN_STRING = 128;
    public static final int TOKEN_COMMENT = 129;

    private int type;
    private String text;

    public Yytoken(int type, String text) {
        this.type = type;
        this.text = text;
    }

    public int getType() {
        return type;
    }

    public String getText() {
        return text;
    }
}