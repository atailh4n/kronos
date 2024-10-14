package lexer;

public class Token {
    public enum TokenType {COMMA, DOT, EOF, IDENTIFIER, LPAREN, NUMBER, RPAREN, SEMICOLON}

    public final TokenType type;
    public final String value;

    public Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }
}
