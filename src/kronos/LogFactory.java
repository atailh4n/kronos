package kronos;

public class LogFactory implements FactoriesInterface.LogFactory {
    @Override
    public void Log(String message) {
        System.out.println("[LOG] " + message);
    }

    @Override
    public void Warn(String message) {
        System.out.println("[WARN] " + message);
    }

    @Override
    public void Error(String message) {
        System.out.println("[ERROR] " + message);
    }
}
