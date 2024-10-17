package kronos;

public class MathFactory implements FactoriesInterface.MathFactory {
    @Override
    public Number sum(Number[] args) {
        double res = 0;
        for (Number arg : args) res += arg.doubleValue();
        return res;
    }

    @Override
    public Number sub(Number[] args) {
        double res = 0;
        for (Number arg : args) res -= arg.doubleValue();
        return res;
    }

    @Override
    public Number mult(Number[] args) {
        double res = 0;
        for (Number arg : args) res *= arg.doubleValue();
        return res;
    }

    @Override
    public Number div(Number[] args) {
        double res = 0;
        for (Number arg : args) res /= arg.doubleValue();
        return res;
    }
}
