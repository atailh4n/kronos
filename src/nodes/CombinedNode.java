package nodes;

import java.util.List;

public class CombinedNode extends ASTNode {
    private final List<ASTNode> dependsNodes;
    private final List<ASTNode> functionNodes;

    public CombinedNode(List<ASTNode> dependsNodes, List<ASTNode> functionNodes) {
        this.dependsNodes = dependsNodes;
        this.functionNodes = functionNodes;
    }

    public List<ASTNode> getDependsNodes() {
        return dependsNodes;
    }

    public List<ASTNode> getFunctionNodes() {
        return functionNodes;
    }
}
