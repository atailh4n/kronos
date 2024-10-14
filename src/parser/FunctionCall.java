package parser;

import java.util.List;

public class FunctionCall extends ASTNode {
    private final String name;
    private final List<ASTNode> arguments;
    private final boolean isStatic;

    public FunctionCall(String name, List<ASTNode> arguments, boolean isStatic) {
        this.name = name;
        this.arguments = arguments;
        this.isStatic = isStatic;
    }

    public String getName() {
        return name;
    }

    public List<ASTNode> getArguments() {
        return arguments;
    }

    public boolean isStatic() {
        return isStatic;
    }

    @Override
    public String toString() {
        return "FunctionCall{" +
                "name='" + name + '\'' +
                ", arguments=" + arguments +
                ", isStatic=" + isStatic +
                '}';
    }
}
