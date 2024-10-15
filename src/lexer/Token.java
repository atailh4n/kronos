package lexer;

public class Token {
    public enum TokenType {
        COMMA,
        DOT,
        EOF,
        IDENTIFIER,
        NUMBER,
        DEPENDS,
        LPAREN,
        LBRACE,
        RBRACE,
        LBRACKET,
        RBRACKET,
        RPAREN,
        SEMICOLON,
        MUL,
        DEPENDS_ALL,
        COLON,
        ASSIGN,
        EQUALS,
        NOT_EQUALS,
        PLUS,
        PLUS_ASSIGN,
        MINUS,
        MINUS_ASSIGN,
        AND,
        NULL_COALESCE
    }

    public final TokenType type;
    public final String value;

    public Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }
}
