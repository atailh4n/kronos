import interpreter.Interpreter;
import lexer.Lexer;
import lexer.Token;
import parser.ASTNode;
import parser.Parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Enter a file name please.");
            return;
        }

        String fileName = args[0];
        try {
            String code = Files.readString(Path.of(fileName));

            // Tokenize et
            Lexer lexer = new Lexer(code);
            List<Token> tokens = lexer.tokenize();

            Parser parser = new Parser(tokens);
            ASTNode ast = parser.parse();

            Interpreter interpreter = new Interpreter();
            interpreter.interpret(ast);

            System.out.println("Kronos exited successfully.");
        } catch (IOException e) {
            System.err.println("Error while reading file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Kronos Runtime Error: " + e.getMessage());
        }
    }
}
