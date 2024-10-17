package kronos;

public interface FactoriesInterface {
    public interface LogFactory {
        void Log(String message);
        void Warn(String message);
        void Error(String message);
    }

    public interface MathFactory {
        Number sum(Number[] args);
        Number sub(Number[] args);
        Number mult(Number[] args);
        Number div(Number[] args);
    }
}
