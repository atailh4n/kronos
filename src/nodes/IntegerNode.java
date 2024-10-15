package nodes;

public class IntegerNode extends TypedASTNode {
    public final int value;

    public IntegerNode(int value) {
        this.value = value;
    }

    @Override
    public Type getType() {
        return Type.Int;
    }

    @Override
    public String toString() {
        return "IntegerNode{" + "value=" + value + '}';
    }
}
