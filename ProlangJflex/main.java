import java.io.IOException;
import java.io.StringReader;

public class main {
    public static void main(String[] args) throws IOException {
        MyLexer lexer = new MyLexer(new StringReader(
                " /*comment*/ "));
        Yytoken token;
        do {
            token = lexer.yylex();
            if (token != null) {
                System.out.println("Token: " + token.getType() + ", Value: " + token.getText());
            }
        } while (token != null && token.getType() != -1);
    }
}