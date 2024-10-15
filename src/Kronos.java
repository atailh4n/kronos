import interpreter.Interpreter;
import lexer.Lexer;
import lexer.Token;
import nodes.ASTNode;
import parser.Parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Kronos {
    public static void main(String[] args) {
        if (args.length == 0 || "--help".equals(args[0])) {
            System.out.println("Usage: kronos [options] <source-file>");
            return;
        }
        if ("--version".equals(args[0])) {
            System.out.println("Kronos Version 1.0");
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
