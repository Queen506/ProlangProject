import java.io.*;
import java.util.*;
%%
%class MyLexer
%unicode
%line
%column

%{
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

%}

%{
// Symbol Table
private HashMap<String, Integer> symbolTable = new HashMap<>();
%}





PLUS = "+"
MINUS = "-"
MULTIPLY = "*"
DIVIDE = "/"
EQUALS = "="
GREATER_THAN = ">"
GREATER_THAN_OR_EQUAL = ">="
LESS_THAN = "<"
LESS_THAN_OR_EQUAL = "<="
EQUAL_TO = "=="
INCREMENT = "\\+\\+"
DECREMENT = "--"

LPAREN = "("
RPAREN = ")"
SEMICOLON = ";"
IF = "if"
THEN = "then"
ELSE = "else"
ENDIF = "endif"
WHILE = "while"
DO = "do"
ENDWHILE = "endwhile"
PRINT = "print"
NEWLINE = "newline"
READ = "read"
INTEGER = [0-9]+
IDENTIFIER = [a-zA-Z][a-zA-Z0-9]*
STRING = \"(\\.|[^\"\\])*\"
COMMENT = "/*"([^*]|" *"[^/])*"*/"|"//".*





%%
{PLUS}                 { return new Yytoken(Yytoken.TOKEN_PLUS, yytext()); }
{MINUS}                { return new Yytoken(Yytoken.TOKEN_MINUS, yytext()); }
{MULTIPLY}             { return new Yytoken(Yytoken.TOKEN_MULTIPLY, yytext()); }
{DIVIDE}               { return new Yytoken(Yytoken.TOKEN_DIVIDE, yytext()); }
{EQUALS}               { return new Yytoken(Yytoken.TOKEN_EQUALS, yytext()); }
{GREATER_THAN}         { return new Yytoken(Yytoken.TOKEN_GREATER_THAN, yytext()); }
{GREATER_THAN_OR_EQUAL} { return new Yytoken(Yytoken.TOKEN_GREATER_THAN_OR_EQUAL, yytext()); }
{LESS_THAN}            { return new Yytoken(Yytoken.TOKEN_LESS_THAN, yytext()); }
{LESS_THAN_OR_EQUAL}   { return new Yytoken(Yytoken.TOKEN_LESS_THAN_OR_EQUAL, yytext()); }
{EQUAL_TO}             { return new Yytoken(Yytoken.TOKEN_EQUAL_TO, yytext()); }
{INCREMENT}            { return new Yytoken(Yytoken.TOKEN_INCREMENT, yytext()); }
{DECREMENT}            { return new Yytoken(Yytoken.TOKEN_DECREMENT, yytext()); }


{LPAREN}               { return new Yytoken(Yytoken.TOKEN_LPAREN, yytext()); }
{RPAREN}               { return new Yytoken(Yytoken.TOKEN_RPAREN, yytext()); }
{SEMICOLON}            { return new Yytoken(Yytoken.TOKEN_SEMICOLON, yytext()); }
{IF}                   { return new Yytoken(Yytoken.TOKEN_IF, yytext()); }
{THEN}                 { return new Yytoken(Yytoken.TOKEN_THEN, yytext()); }
{ELSE}                 { return new Yytoken(Yytoken.TOKEN_ELSE, yytext()); }
{ENDIF}                { return new Yytoken(Yytoken.TOKEN_ENDIF, yytext()); }
{WHILE}                { return new Yytoken(Yytoken.TOKEN_WHILE, yytext()); }
{DO}                   { return new Yytoken(Yytoken.TOKEN_DO, yytext()); }
{ENDWHILE}             { return new Yytoken(Yytoken.TOKEN_ENDWHILE, yytext()); }
{PRINT}                { return new Yytoken(Yytoken.TOKEN_PRINT, yytext()); }
{NEWLINE}              { return new Yytoken(Yytoken.TOKEN_NEWLINE, yytext()); }
{READ}                 { return new Yytoken(Yytoken.TOKEN_READ, yytext()); }
{STRING}               { return new Yytoken(Yytoken.TOKEN_STRING, yytext()); }
{INTEGER}              { return new Yytoken(Yytoken.TOKEN_INTEGER, yytext()); }
{IDENTIFIER}           {
                        // Check if the identifier is in the symbol table
                        if (symbolTable.containsKey(yytext())) {
                            return new Yytoken(Yytoken.TOKEN_IDENTIFIER, yytext());
                        } else {
                            // Add the identifier to the symbol table
                            symbolTable.put(yytext(), symbolTable.size() + 1);
                            return new Yytoken(Yytoken.TOKEN_IDENTIFIER, yytext());
                        }
                    }
{COMMENT}              { return new Yytoken(Yytoken.TOKEN_COMMENT, yytext()); }
" "                   { }
.                      { System.out.println("Unrecognized: " + yytext()); }


