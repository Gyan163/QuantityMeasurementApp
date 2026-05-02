public enum WeightUnit {

    KG(1.0),
    G(1.0 / 1000.0),
    LB(0.453592);

    private final double toKgFactor;

    WeightUnit(double toKgFactor) {
        this.toKgFactor = toKgFactor;
    }

    // Convert → base (KG)
    public double convertToBase(double value) {
        return value * toKgFactor;
    }

    // Convert base (KG) → unit
    public double convertFromBase(double baseValue) {
        return baseValue / toKgFactor;
    }
}