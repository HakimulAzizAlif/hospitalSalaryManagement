public class Nurse extends Employee {
    private double overtimeHours;
    private double overtimeRate;
    public Nurse(String id, String name, String department, double baseSalary, double overtimeHours, double overtimeRate) throws InvalidSalaryException {
        super(id, name, department, baseSalary);
        if (overtimeHours < 0 || overtimeRate < 0) {
            throw new InvalidSalaryException("Overtime values must be non-negative!");
        }
        this.overtimeHours = overtimeHours;
        this.overtimeRate = overtimeRate;
    }
    public double getOvertimeHours() {
        return overtimeHours;
    }
    public double getOvertimeRate() {
        return overtimeRate;
    }
    @Override
    public double calculateSalary() {
        return getBaseSalary() + (overtimeHours * overtimeRate);
    }
    @Override
    public String getRole() {
        return "Nurse";
    }
    @Override
    public String toDataString() {
        return "Nurse," + getId() + "," + getName() + "," + getDepartment() + "," + getBaseSalary() + "," + overtimeHours + "," + overtimeRate;
    }
}
