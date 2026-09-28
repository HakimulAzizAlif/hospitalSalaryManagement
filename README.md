# hospitalSalaryManagement
Hospital Payroll Management System
A Java Swing desktop application for managing hospital staff payroll — registering employees, calculating role-specific monthly salaries, maintaining a staff directory, and generating executive reports with data persistence.
Overview
The Hospital Payroll Management System centralizes payroll processing for healthcare institutions into a single desktop application. Librarians and HR managers can register personnel, track role-based compensation (such as doctor consultation fees or nurse overtime rates), search and remove records, and generate comprehensive payroll summaries — all backed by flat-file storage that persists across sessions.
Objectives
 * Centralize management of hospital staff records and compensation workflows.
 * Apply core OOP principles — Encapsulation, Abstraction, Inheritance, and Polymorphism.
 * Deliver a clean, multi-tabbed GUI built with Java Swing (JTabbedPane).
 * Validate user input and manage errors gracefully with custom exceptions.
 * Provide real-time payroll summary reports for administrative analysis.
 * Persist data reliably using local text file storage (hospital_employees.txt).
🧩 Key Features
| Module | Description |
|---|---|
| Register Staff | Dynamic form to add Doctors (base salary, consultation fee, patients treated) or Nurses (base salary, overtime hours, overtime rate). |
| Staff Directory | Tabular overview (JTable) displaying all registered staff with live formatted salary calculations. |
| Search & Delete | Search employee details by ID or remove selected records directly from persistent storage. |
| Payroll Report | Generate real-time summary reports showing total expenses, average pay, and the highest-paid employee. |
Architecture
Built around a clean object model with clear separation of concerns:
 * Frontend — Java Swing, with tabbed navigation (JTabbedPane) separating forms, tables, and reports.
 * Backend — Database class handles file I/O operations, input reading, searching, and record deletions.
 * Exception Handling — Custom exceptions (InvalidSalaryException, EmployeeNotFoundException) provide robust error reporting via JOptionPane dialogs.
Object Model
| Class / Interface | Description |
|---|---|
| Main | Entry point that launches HospitalSalaryGUI on the Event Dispatch Thread. |
| Payable (interface) | Defines the core salary calculation contract (calculateSalary()). |
| Employee (abstract) | Base class implementing Payable for shared fields (id, name, department, baseSalary). |
| Doctor | Concrete subclass of Employee with fee-per-patient salary calculations. |
| Nurse | Concrete subclass of Employee with hourly overtime salary calculations. |
| Database | Handles flat-file persistence and record management for hospital_employees.txt. |
| HospitalSalaryGUI | Main Swing interface incorporating dynamic forms, tables, and summary reports. |
OOP in practice: Doctor and Nurse inherit from Employee and implement Payable. Each subclass overrides calculateSalary(), getRole(), and toDataString() to dynamically execute role-specific behavior — demonstrating polymorphism throughout the application.
⚠️ Exception Handling
| Exception | Triggered when… |
|---|---|
| InvalidSalaryException | Negative values are entered for salaries, fees, or overtime, or when registering a duplicate Employee ID. |
| EmployeeNotFoundException | A searched or targeted Employee ID does not exist in the database. |
| NumberFormatException | Non-numeric input is supplied to numeric fields (e.g., base salary, overtime hours). |
| IOException | File read/write issues occur during database persistence operations. |
Data Persistence
Data is saved as plain, comma-separated text in hospital_employees.txt in the root project directory (created automatically on first run):
hospital_employees.txt

Each record is serialized using the toDataString() method:
 * Doctor: Doctor,ID,Name,Department,BaseSalary,ConsultationFee,PatientsTreated
 * Nurse: Nurse,ID,Name,Department,BaseSalary,OvertimeHours,OvertimeRate
Getting Started
Requirements
 * JDK 11 or higher
 * IntelliJ IDEA (Community or Ultimate) — recommended
Run in IntelliJ IDEA
 * Extract/clone the project folder.
 * Open IntelliJ IDEA → File → Open → select the project folder.
 * Wait for IntelliJ to index the project files.
 * Select a JDK 11+ installation if prompted.
 * Click the green run arrow next to Main, or go to Run → Run 'Main'.
Run from the Command Line
javac *.java
java Main

Project Structure
HospitalPayrollSystem/
|
|-- Payable.java                   # Core interface contract
|-- Employee.java                  # Abstract base class for staff
|-- Doctor.java                    # Doctor subclass
|-- Nurse.java                     # Nurse subclass
|-- InvalidSalaryException.java    # Custom validation exception
|-- EmployeeNotFoundException.java # Custom lookup exception
|-- Database.java                  # File I/O persistence manager
|-- HospitalSalaryGUI.java         # Main Java Swing interface
|-- Main.java                      # Program entry point
|-- hospital_employees.txt         # Auto-generated persistent data file
|-- README.md                      # Documentation

Future Enhancements
 * Migrate storage from flat-file (hospital_employees.txt) to MySQL or SQLite.
 * Tax and insurance deduction calculation engine.
 * Export summary reports to PDF or CSV files.
 * Role-based login and authentication system for HR admins vs. staff.
👥 Group Members & Task Distribution
| SL | Student Name | ID | Assigned Responsibilities / Files |
|---|---|---|---|
| 1 | Hakimul Aziz Alif | 2024200000151 | Main.java, InvalidSalaryException.java, EmployeeNotFoundException.java, HospitalSalaryGUI.java |
| 2 | Ishrat Jahan | 2024100000112 | Doctor.java, Nurse.java |
| 3 | Zarin Tasnim Totinee | 2024100000110 | Employee.java, Payable.java |
| 4 | Sourabh Barmon | Pending | Database.java |
📝 Conclusion
This project demonstrates a complete application of Object-Oriented Design — combining interface contracts, abstract base classes, concrete sub-classing, encapsulated file persistence, and custom exception handling — with an intuitive Java Swing GUI. It offers a structured and scalable platform for managing hospital staff compensation.
