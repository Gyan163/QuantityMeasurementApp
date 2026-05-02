public enum LengthUnit {

    FEET(1.0),
    INCH(1.0 / 12.0),
    YARD(3.0),
    CM(0.393701 / 12.0);

    private final double toFeetFactor;

    LengthUnit(double toFeetFactor) {
        this.toFeetFactor = toFeetFactor;
    }

    // Convert current unit → base (feet)
    public double convertToBase(double value) {
        return value * toFeetFactor;
    }

    // Convert base (feet) → current unit
    public double convertFromBase(double baseValue) {
        return baseValue / toFeetFactor;
    }
}