package operations;

import nodes.ASTNode;

public class AddDependencies extends ASTNode {
    private final String name;
    private final String fnName;
    private final String path;

    public AddDependencies(String name, String fnName, String path) {
        this.name = name;
        this.fnName = fnName;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public String getFnName() {
        return fnName;
    }

    public String getPath() {
        return path;
    }

    @Override
    public String toString() {
        return "AddDependency{" +
                "name='" + name + '\'' +
                ", path=" + path +
                ", fnName=" + fnName +
                '}';
    }
}
