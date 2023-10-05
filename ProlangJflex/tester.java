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
                    // ประมวลผลเฉพาะโทเคนที่ไม่ใช่ความคิดเห็น
                    if (token.getType() == MyLexer.TOKEN_IDENTIFIER) {
                        String identifier = token.getText();
                        if (symbolTable.containsKey(identifier)) {
                            System.out.println("identifier \"" + identifier + "\" already in symbol table");
                        } else {
                            System.out.println("new identifier: " + identifier);
                            // เพิ่ม identifier ลงใน symbol table
                            symbolTable.put(identifier, true);
                        }
                    } else if (token.getType() == MyLexer.TOKEN_STRING) {
                        System.out.println("string: " + token.getText());
                    } else {
                        System.out.println("Token: " + token.getType() + ", Value: " + token.getText());
                    }
                }
            } catch (IOException e) {
                // จัดการกับ IOException ที่เกิดขึ้นในการอ่านโทเคน
                e.printStackTrace();
            }
        } while (token != null && token.getType() != -1);
    }
}