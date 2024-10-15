package parser;

import lexer.Token;
import nodes.ASTNode;
import nodes.CombinedNode;
import nodes.IdentifierNode;
import nodes.NumberNode;
import operations.AddDependencies;
import operations.FunctionCall;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    private final List<Token> tokens;
    private int currentIndex = 0;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    public ASTNode parse() {
        ASTNode dependsNode = parseDepends();
        ASTNode functionCallNode = parseFunctionCall();
        if (dependsNode != null && functionCallNode != null) {
            return new CombinedNode(dependsNode, functionCallNode);
        } else if (dependsNode != null) {
            return dependsNode;
        } else {
            return functionCallNode;
        }
    }

    private ASTNode parseDepends() {
        if (currentIndex >= tokens.size()) {
            return null;
        }

        Token currentToken = tokens.get(currentIndex);
        if (currentToken.type == Token.TokenType.DEPENDS) {
            currentIndex++;
        } else {
            throw new RuntimeException("Expected 'depends', found: " + currentToken.value);
        }

        if (currentIndex >= tokens.size() || tokens.get(currentIndex).type != Token.TokenType.IDENTIFIER) {
            throw new RuntimeException("Expected IDENTIFIER after 'depends', found: " + (currentIndex < tokens.size() ? tokens.get(currentIndex).value : "EOF"));
        }
        String packageName = tokens.get(currentIndex).value;
        currentIndex++;

        if (currentIndex >= tokens.size() || tokens.get(currentIndex).type != Token.TokenType.DOT) {
            throw new RuntimeException("Expected '.', found: " + (currentIndex < tokens.size() ? tokens.get(currentIndex).value : "EOF"));
        }
        currentIndex++;

        if (currentIndex >= tokens.size() || tokens.get(currentIndex).type != Token.TokenType.IDENTIFIER && tokens.get(currentIndex).type != Token.TokenType.DEPENDS_ALL) {
            throw new RuntimeException("Expected IDENTIFIER or DEPENDS_ALL after '.', found: " + (currentIndex < tokens.size() ? tokens.get(currentIndex).value : "EOF"));
        }
        String className = tokens.get(currentIndex).value;
        currentIndex++;

        if (currentIndex >= tokens.size() || tokens.get(currentIndex).type != Token.TokenType.DOT) {
            throw new RuntimeException("Expected '.', found: " + (currentIndex < tokens.size() ? tokens.get(currentIndex).value : "EOF"));
        }
        currentIndex++;

        if (currentIndex >= tokens.size() || tokens.get(currentIndex).type != Token.TokenType.IDENTIFIER && tokens.get(currentIndex).type != Token.TokenType.DEPENDS_ALL) {
            throw new RuntimeException("Expected IDENTIFIER or DEPENDS_ALL after '.', found: " + (currentIndex < tokens.size() ? tokens.get(currentIndex).value : "EOF"));
        }
        String functionName = tokens.get(currentIndex).value;
        currentIndex++;

        if (currentIndex >= tokens.size() || tokens.get(currentIndex).type != Token.TokenType.SEMICOLON) {
            throw new RuntimeException("Expected ; after className, found: " + (currentIndex < tokens.size() ? tokens.get(currentIndex).value : "EOF"));
        }
        currentIndex++;

        return new AddDependencies(packageName, functionName, packageName+"."+className);
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
            } else if (tokens.get(currentIndex).type == Token.TokenType.IDENTIFIER) {
                arguments.add(new IdentifierNode(tokens.get(currentIndex).value));
                currentIndex++;
            }

            if (currentIndex < tokens.size() && tokens.get(currentIndex).type == Token.TokenType.COMMA) {
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
