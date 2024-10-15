package nodes;

public class CombinedNode extends ASTNode {
    private final ASTNode dependsNode;
    private final ASTNode functionCallNode;

    public CombinedNode(ASTNode dependsNode, ASTNode functionCallNode) {
        this.dependsNode = dependsNode;
        this.functionCallNode = functionCallNode;
    }

    public ASTNode getDependsNode() {
        return dependsNode;
    }

    public ASTNode getFunctionCallNode() {
        return functionCallNode;
    }
}
