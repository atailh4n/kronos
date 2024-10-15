package kronos;

public class ConsoleLogFactory implements LogFactory {
    @Override
    public void Log(String message) {
        System.out.println("[LOG] " + message);
    }

    @Override
    public void Warn(String message) {
        System.out.println("[WARN] " + message);
    }
}
