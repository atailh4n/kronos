package interpreter;

import kronos.LogFactory;
import nodes.ASTNode;
import nodes.CombinedNode;
import operations.AddDependencies;
import operations.FunctionCall;
import nodes.NumberNode;
import kronos.ConsoleLogFactory;

import java.lang.reflect.Method;

public class Interpreter {
    private Method methodItself;
    private Object instance;

    public Interpreter() {
        LogFactory logFactory = new ConsoleLogFactory();
    }

    public void interpret(ASTNode node) {
        if (node instanceof CombinedNode combinedNode) {
            interpret(combinedNode.getDependsNode());
            interpret(combinedNode.getFunctionCallNode());
        } else if (node instanceof AddDependencies addDependencies) {
            try {
                Class<?> clazz = Class.forName(addDependencies.getPath());
                instance = clazz.getDeclaredConstructor().newInstance();
                methodItself = clazz.getMethod(addDependencies.getFnName(), String.class);
                System.out.println("[KRONOS] Dependency " + instance.getClass().toString() + " loaded.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (node instanceof FunctionCall func) {

            String[] parts = func.getName().split("\\.");
            String className = parts[0];
            String methodName = parts[1];

            ASTNode argNode = func.getArguments().get(0);

            try {
                if (func.getArguments().isEmpty()) {
                    System.out.println("No arguments provided for Log.");
                    return;
                }
                if (argNode instanceof NumberNode) {
                    String argument = ((NumberNode) argNode).getValue();
                    methodItself.invoke(instance, argument);
                } else {
                    System.out.println("Invalid argument type for :" + instance.getClass().getName() + argNode);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
