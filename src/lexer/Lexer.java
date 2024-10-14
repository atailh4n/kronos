package lexer;

import java.util.ArrayList;
import java.util.List;

public class Lexer {
    private final String code;
    private int position;

    public Lexer(String code) {
        this.code = code;
        this.position = 0;
    }

    public List<Token> tokenize() {
        List<Token> tokens = new ArrayList<>();

        while (position < code.length()) {
            char currentChar = code.charAt(position);

            if (Character.isWhitespace(currentChar)) {
                position++;
                continue;
            }

            if (Character.isLetter(currentChar)) {
                StringBuilder identifier = new StringBuilder();
                while (position < code.length() &&
                        (Character.isLetterOrDigit(code.charAt(position)) || code.charAt(position) == '_')) {
                    identifier.append(code.charAt(position));
                    position++;
                }
                tokens.add(new Token(Token.TokenType.IDENTIFIER, identifier.toString()));
                continue;
            }

            if (currentChar == '.') {
                tokens.add(new Token(Token.TokenType.DOT, "."));
                position++;
            }

            else if (currentChar == '(') {
                tokens.add(new Token(Token.TokenType.LPAREN, "("));
                position++;
            }

            else if (currentChar == ')') {
                tokens.add(new Token(Token.TokenType.RPAREN, ")"));
                position++;
            }

            else if (Character.isDigit(currentChar)) {
                StringBuilder number = new StringBuilder();
                while (position < code.length() && Character.isDigit(code.charAt(position))) {
                    number.append(code.charAt(position));
                    position++;
                }
                tokens.add(new Token(Token.TokenType.NUMBER, number.toString()));
            }

            else if (currentChar == ';') {
                tokens.add(new Token(Token.TokenType.SEMICOLON, ";"));
                position++;
            }

            else {
                System.out.println("Unrecognized Char: " + currentChar);
                position++;
            }
        }

        tokens.add(new Token(Token.TokenType.EOF, ""));
        return tokens;
    }
}
