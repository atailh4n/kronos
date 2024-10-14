package parser;

import lexer.Token;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    private final List<Token> tokens;
    private int currentIndex = 0;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    public ASTNode parse() {
        return parseFunctionCall();
    }

    private ASTNode parseFunctionCall() {
        if (currentIndex >= tokens.size()) {
            return null;
        }

        Token currentToken = tokens.get(currentIndex);
        if (currentToken.type != Token.TokenType.IDENTIFIER) {
            throw new RuntimeException("Unexpected token: " + currentToken.value);
        }

        String functionName = currentToken.value;
        currentIndex++;

        if (currentIndex >= tokens.size() || tokens.get(currentIndex).type != Token.TokenType.DOT) {
            throw new RuntimeException("Unexpected token: " + (currentIndex < tokens.size() ? tokens.get(currentIndex).value : "EOF"));
        }
        currentIndex++;

        if (currentIndex >= tokens.size() || tokens.get(currentIndex).type != Token.TokenType.IDENTIFIER) {
            throw new RuntimeException("Unexpected token: " + (currentIndex < tokens.size() ? tokens.get(currentIndex).value : "EOF"));
        }

        String methodName = tokens.get(currentIndex).value;
        currentIndex++;

        if (currentIndex >= tokens.size() || tokens.get(currentIndex).type != Token.TokenType.LPAREN) {
            throw new RuntimeException("Unexpected token: " + (currentIndex < tokens.size() ? tokens.get(currentIndex).value : "EOF"));
        }
        currentIndex++;

        List<ASTNode> arguments = new ArrayList<>();
        while (currentIndex < tokens.size() && tokens.get(currentIndex).type != Token.TokenType.RPAREN) {
            if (tokens.get(currentIndex).type == Token.TokenType.NUMBER) {
                arguments.add(new NumberNode(tokens.get(currentIndex).value));
                currentIndex++;
            }
        }

        if (currentIndex >= tokens.size() || tokens.get(currentIndex).type != Token.TokenType.RPAREN) {
            throw new RuntimeException("Unexpected token: " + (currentIndex < tokens.size() ? tokens.get(currentIndex).value : "EOF"));
        }
        currentIndex++;

        if (currentIndex >= tokens.size() || tokens.get(currentIndex).type != Token.TokenType.SEMICOLON) {
            throw new RuntimeException("Unexpected token: " + (currentIndex < tokens.size() ? tokens.get(currentIndex).value : "EOF"));
        }
        currentIndex++;

        boolean isStatic = true;
        return new FunctionCall(functionName + "." + methodName, arguments, isStatic);
    }
}
