package parser;

public class IntegerNode extends TypedASTNode {
    public final int value;

    public IntegerNode(int value) {
        this.value = value;
    }

    @Override
    public Type getType() {
        return Type.INTEGER;
    }

    @Override
    public String toString() {
        return "IntegerNode{" + "value=" + value + '}';
    }
}
