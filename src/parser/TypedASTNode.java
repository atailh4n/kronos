package parser;

// TypedASTNode.java
public abstract class TypedASTNode extends ASTNode {
    public enum Type {
        INTEGER, FUNCTION_CALL, STRING
    }

    public abstract Type getType();
}
