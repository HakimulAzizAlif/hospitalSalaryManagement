public class Doctor extends Employee
    {
    private double consultationFee;
        
    private int patientsTreated;
        
    public Doctor(String id, String name, String department, double baseSalary, double consultationFee, int patientsTreated) throws InvalidSalaryException {
        super(id, name, department, baseSalary);
        
        if (consultationFee < 0 || patientsTreated < 0)
        {
            throw new InvalidSalaryException("Consultation fee and patient count must be non-negative!");
        }
        this.consultationFee = consultationFee;
        this.patientsTreated = patientsTreated;
    }
    public double getConsultationFee()
        {
        return consultationFee;
    }
    public int getPatientsTreated()
        {
        return patientsTreated;
    }
    @Override
    public double calculateSalary()
        {
        return getBaseSalary() + (consultationFee * patientsTreated);
    }
    @Override
    public String getRole()
    {
        return "Doctor";
    }
    @Override
    public String toDataString() {
        return "Doctor," + getId() + "," + getName() + "," + getDepartment() + "," + getBaseSalary() + "," + consultationFee + "," + patientsTreated;
    }
}
