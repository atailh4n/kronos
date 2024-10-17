package interpreter;

import nodes.ASTNode;
import nodes.CombinedNode;
import operations.AddDependencies;
import operations.FunctionCall;
import nodes.NumberNode;

import java.lang.reflect.Method;
import java.util.*;

public class Interpreter {
    private Map<String, Method> methods = new HashMap<>();
    private Map<String, Object> instances = new HashMap<>();

    public void interpret(ASTNode node) {
        if (node instanceof CombinedNode combinedNode) {
            // Tüm depends nodlarını sırayla işle
            for (ASTNode dependsNode : combinedNode.getDependsNodes()) {
                interpret(dependsNode);
            }

            // Tüm fonksiyon çağrılarını sırayla işle
            for (ASTNode functionNode : combinedNode.getFunctionNodes()) {
                interpret(functionNode);
            }
        } else if (node instanceof AddDependencies addDependencies) {
            addDependency(addDependencies);
        } else if (node instanceof FunctionCall func) {
            executeFunctionCall(func);
        }
    }


    private void addDependency(AddDependencies addDependencies) {
        try {
            Class<?> clazz = Class.forName(addDependencies.getPath());
            Object instance = clazz.getDeclaredConstructor().newInstance();
            instances.put(addDependencies.getClassName(), instance);
            Method method = clazz.getMethod(addDependencies.getMethodName(), String.class);
            methods.put(addDependencies.getMethodName(), method);

            System.out.println("[KRONOS] Dependency " + clazz.getName() + " loaded.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void executeFunctionCall(FunctionCall func) {
        try {
            String[] parts = func.getName().split("\\.");
            String className = parts[0];
            String methodName = parts[1];

            // Instance'ı map'ten çek
            Object instance = instances.get(className);
            if (instance == null) {
                throw new RuntimeException("Instance not found: " + className);
            }

            // Method'u map'ten çek
            Method method = methods.get(methodName);
            if (method == null) {
                throw new RuntimeException("Method not found: " + className + "." + methodName);
            }

            // Argümanı al ve fonksiyonu çalıştır
            ASTNode argNode = func.getArguments().get(0);
            if (argNode instanceof NumberNode) {
                String argument = ((NumberNode) argNode).getValue();
                method.invoke(instance, argument);
            } else {
                System.out.println("Invalid argument type: " + argNode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
