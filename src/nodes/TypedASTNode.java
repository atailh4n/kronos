package nodes;

// TypedASTNode.java
public abstract class TypedASTNode extends ASTNode {
    public enum Type {
        Int, String, Arguments, Array
    }

    public abstract Type getType();
}
