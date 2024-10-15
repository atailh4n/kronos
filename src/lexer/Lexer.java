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

    private char peekNext() {
        if (position + 1 < code.length()) {
            return code.charAt(position + 1);
        }
        return '\0';
    }

    public List<Token> tokenize() throws IllegalStateException {
        List<Token> tokens = new ArrayList<>();

        while (position < code.length()) {
            char currentChar = code.charAt(position);

            if (Character.isWhitespace(currentChar)) {
                position++;
                continue;
            }

            if (code.startsWith("depends", position)) {
                tokens.add(new Token(Token.TokenType.DEPENDS, "depends"));
                position += "depends".length();
                continue;
            }

            if (Character.isLetter(currentChar)) {
                StringBuilder identifier = new StringBuilder();
                while (position < code.length() && Character.isLetterOrDigit(code.charAt(position))) {
                    identifier.append(code.charAt(position));
                    position++;
                }
                tokens.add(new Token(Token.TokenType.IDENTIFIER, identifier.toString()));
                continue;
            } else if (Character.isDigit(currentChar)) {
                StringBuilder number = new StringBuilder();
                while (position < code.length() && Character.isDigit(code.charAt(position))) {
                    number.append(code.charAt(position));
                    position++;
                }
                tokens.add(new Token(Token.TokenType.NUMBER, number.toString()));
                continue;
            }

            switch (currentChar) {
                case '=':
                    if (peekNext() == '=') {
                        tokens.add(new Token(Token.TokenType.EQUALS, "=="));
                        position++;
                    } else {
                        tokens.add(new Token(Token.TokenType.ASSIGN, "="));
                    }
                    break;
                case '!':
                    if (peekNext() == '=') {
                        tokens.add(new Token(Token.TokenType.NOT_EQUALS, "!="));
                        position++;
                    } else {
                        throw new IllegalStateException("Unexpected character: " + currentChar);
                    }
                    break;
                case '+':
                    if (peekNext() == '=') {
                        tokens.add(new Token(Token.TokenType.PLUS_ASSIGN, "+="));
                        position++;
                    } else {
                        tokens.add(new Token(Token.TokenType.PLUS, "+"));
                    }
                    break;
                case '-':
                    if (peekNext() == '=') {
                        tokens.add(new Token(Token.TokenType.MINUS_ASSIGN, "-="));
                        position++;
                    } else {
                        tokens.add(new Token(Token.TokenType.MINUS, "-"));
                    }
                    break;
                case '&':
                    if (peekNext() == '&') {
                        tokens.add(new Token(Token.TokenType.AND, "&&"));
                        position++;
                    } else {
                        throw new IllegalStateException("Unexpected character: " + currentChar);
                    }
                    break;
                case '?':
                    if (peekNext() == '?') {
                        tokens.add(new Token(Token.TokenType.NULL_COALESCE, "??"));
                        position++;
                    } else {
                        // Geçersiz durum veya uygun bir hata işleme
                        throw new IllegalStateException("Unexpected character: " + currentChar);
                    }
                    break;
                case '.':
                    tokens.add(new Token(Token.TokenType.DOT, "."));
                    break;
                case '(':
                    tokens.add(new Token(Token.TokenType.LPAREN, "("));
                    break;
                case ')':
                    tokens.add(new Token(Token.TokenType.RPAREN, ")"));
                    break;
                case ';':
                    tokens.add(new Token(Token.TokenType.SEMICOLON, ";"));
                    break;
                case '{':
                    tokens.add(new Token(Token.TokenType.LBRACE, "{"));
                    break;
                case '}':
                    tokens.add(new Token(Token.TokenType.RBRACE, "}"));
                    break;
                case '[':
                    tokens.add(new Token(Token.TokenType.LBRACKET, "["));
                    break;
                case ']':
                    tokens.add(new Token(Token.TokenType.RBRACKET, "]"));
                    break;
                default:
                    throw new IllegalStateException("Kronos found unexpected value can damage system: '" + currentChar + "' at " + position);
            }
            position++;
        }

        tokens.add(new Token(Token.TokenType.EOF, ""));
        return tokens;
    }
}
