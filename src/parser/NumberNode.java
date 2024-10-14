package parser;

public class NumberNode extends ASTNode {
    private final String value;

    public NumberNode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "NumberNode{" + "value='" + value + '\'' + '}';
    }
}
