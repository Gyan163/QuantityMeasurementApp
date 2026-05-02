// ================= WEIGHT QUANTITY CLASS =================
static class QuantityWeight {
    double value;
    WeightUnit unit;

    QuantityWeight(double value, WeightUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    double toKg() {
        return unit.convertToBase(value);
    }

    boolean isEqual(QuantityWeight other) {
        return Math.abs(this.toKg() - other.toKg()) < 0.0001;
    }

    QuantityWeight convertTo(WeightUnit targetUnit) {
        double base = this.toKg();
        double result = targetUnit.convertFromBase(base);
        return new QuantityWeight(result, targetUnit);
    }

    // Addition (UC6 style)
    QuantityWeight add(QuantityWeight other) {
        double sumKg = this.toKg() + other.toKg();
        double result = this.unit.convertFromBase(sumKg);
        return new QuantityWeight(result, this.unit);
    }

    // Addition with target (UC7 style)
    QuantityWeight add(QuantityWeight other, WeightUnit targetUnit) {
        double sumKg = this.toKg() + other.toKg();
        double result = targetUnit.convertFromBase(sumKg);
        return new QuantityWeight(result, targetUnit);
    }

    public String toString() {
        return value + " " + unit;
    }
}