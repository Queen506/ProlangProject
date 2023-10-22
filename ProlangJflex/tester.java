import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;

public class tester {
    public static void main(String[] args) {
        try {
            // สร้าง symbol table เพื่อเก็บข้อมูล
            HashMap<String, Boolean> symbolTable = new HashMap<>();

            // อ่านข้อมูลจากไฟล์ input.txt
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                // ตรวจสอบและแปลงคำในบรรทัด
                processLine(line, symbolTable);
            }
            reader.close();
        } catch (IOException e) {
            // จัดการกับ IOException ที่เกิดขึ้น
            e.printStackTrace();
        }
    }

    private static void processLine(String line, HashMap<String, Boolean> symbolTable) {
        // ใช้ Lexer ที่ได้สร้างจาก JFlex เพื่อแปลงโทเคนจากข้อมูลบรรทัดนี้
        MyLexer lexer = new MyLexer(new StringReader(line));
        Yytoken token = null;
        do {
            try {
                token = lexer.yylex();
                if (token != null && token.getType() != MyLexer.TOKEN_COMMENT) {
                    if (token.getType() == Yytoken.TOKEN_IDENTIFIER) {
                        String identifier = token.getText();
                        if (symbolTable.containsKey(identifier)) {
                            System.out.println("identifier \"" + identifier +
                                    "\" already in the symbol table");
                        } else {
                            System.out.println("new identifier: " + identifier);
                            // Add the identifier to the symbol table
                            symbolTable.put(identifier, true);
                        }
                    } else if (token.getType() == Yytoken.TOKEN_PLUS ||
                            token.getType() == Yytoken.TOKEN_MINUS ||
                            token.getType() == Yytoken.TOKEN_MULTIPLY ||
                            token.getType() == Yytoken.TOKEN_DIVIDE ||
                            token.getType() == Yytoken.TOKEN_EQUALS ||
                            token.getType() == Yytoken.TOKEN_GREATER_THAN ||
                            token.getType() == Yytoken.TOKEN_GREATER_THAN_OR_EQUAL ||
                            token.getType() == Yytoken.TOKEN_LESS_THAN ||
                            token.getType() == Yytoken.TOKEN_LESS_THAN_OR_EQUAL ||
                            token.getType() == Yytoken.TOKEN_EQUAL_TO ||
                            token.getType() == Yytoken.TOKEN_INCREMENT ||
                            token.getType() == Yytoken.TOKEN_DECREMENT) {
                        System.out.println("operator: " + token.getText());
                    } else if (token.getType() == Yytoken.TOKEN_IF ||
                            token.getType() == Yytoken.TOKEN_THEN ||
                            token.getType() == Yytoken.TOKEN_ELSE ||
                            token.getType() == Yytoken.TOKEN_ENDIF ||
                            token.getType() == Yytoken.TOKEN_WHILE ||
                            token.getType() == Yytoken.TOKEN_DO ||
                            token.getType() == Yytoken.TOKEN_ENDWHILE ||
                            token.getType() == Yytoken.TOKEN_PRINT ||
                            token.getType() == Yytoken.TOKEN_READ) {
                        System.out.println("keyword: " + token.getText());
                    } else if (token.getType() == Yytoken.TOKEN_STRING) {
                        System.out.println("string: " + token.getText());
                    } else if (token.getType() == Yytoken.TOKEN_SEMICOLON) {
                        System.out.println("semicolon: " + token.getText());
                    } else if (token.getType() == Yytoken.TOKEN_LPAREN ||
                            token.getType() == Yytoken.TOKEN_RPAREN) {
                        System.out.println("parenthesis: " + token.getText());
                    } else if (token.getType() == Yytoken.TOKEN_INTEGER) {
                        System.out.println("integer: " + token.getText());
                    } else {
                        System.out.println("Unknown token: " + token.getText());
                    }
                }

            } catch (IOException e) {
                // จัดการกับ IOException ที่เกิดขึ้นในการอ่านโทเคน
                e.printStackTrace();
            }
        } while (token != null && token.getType() != -1);
    }
}