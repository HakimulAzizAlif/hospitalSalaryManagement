public abstract class Employee implements Payable {
    private String id;
    private String name;
    private String department;
    private double baseSalary;
    public Employee(String id, String name, String department, double baseSalary) throws InvalidSalaryException {
        if (baseSalary < 0) {
            throw new InvalidSalaryException("Base salary cannot be negative!");
        }
        this.id = id;
        this.name = name;
        this.department = department;
        this.baseSalary = baseSalary;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public double getBaseSalary() {
        return baseSalary;
    }
    public void setBaseSalary(double baseSalary) throws InvalidSalaryException {
        if (baseSalary < 0) {
            throw new InvalidSalaryException("Base salary cannot be negative!");
        }
        this.baseSalary = baseSalary;
    }
    public abstract String getRole();
    public abstract String toDataString();
}
