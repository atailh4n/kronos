package interpreter;

import factory.LogFactory;
import parser.ASTNode;
import parser.FunctionCall;
import parser.NumberNode;

public class Interpreter {
    public void interpret(ASTNode node) {
        if (node instanceof FunctionCall func) {
            if (func.isStatic()) {
                String[] parts = func.getName().split("\\.");
                String className = parts[0];
                String methodName = parts[1];

                if ("LogFactory".equals(className) && "Log".equals(methodName)) {
                    if (func.getArguments().isEmpty()) {
                        System.out.println("No arguments provided for Log.");
                        return;
                    }

                    ASTNode argNode = func.getArguments().get(0);
                    if (argNode instanceof NumberNode) {
                        String argument = ((NumberNode) argNode).getValue();
                        LogFactory.Log(argument);
                    } else {
                        System.out.println("Invalid argument type for Log: " + argNode);
                    }
                }
            } else {
                // Diğer metot çağrılarını işle
                System.out.println("Other Function Called: " + func.getName());
                for (ASTNode arg : func.getArguments()) {
                    System.out.println("Argument/s: " + arg);
                }
            }
        }
    }
}
