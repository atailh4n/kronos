package operations;

import nodes.ASTNode;

public class AddDependencies extends ASTNode {
    private final String packageName;
    private final String className;
    private final String methodName;
    private final String path;

    public AddDependencies(String packageName, String className, String methodName, String path) {
        this.packageName = packageName;
        this.className = className;
        this.methodName = methodName;
        this.path = path;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getClassName() {
        return className;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getPath() {
        return path;
    }

    @Override
    public String toString() {
        return "Dependency{" +
                "packageName='" + packageName + '\'' +
                ", className=" + className +
                ", methodName=" + methodName +
                ", path=" + path +
                '}';
    }
}
