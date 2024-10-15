package nodes;

public class IdentifierNode extends TypedASTNode {
    public final String value;

    public IdentifierNode(String value) {
        this.value = value;
    }

    @Override
    public Type getType() {
        return Type.String;
    }

    @Override
    public String toString() {
        return "IdentifierNode{" + "value=" + value + '}';
    }
}
