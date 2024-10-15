package nodes;

public class StringNode extends TypedASTNode {
    public final String value;

    public StringNode(String value) {
        this.value = value;
    }

    @Override
    public Type getType() {
        return Type.String;
    }

    @Override
    public String toString() {
        return "StringNode{" + "value=" + value + '}';
    }
}
