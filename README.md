# Hospital Payroll Management System

A Java Swing desktop application for managing hospital staff payroll — registering doctors and nurses, calculating salaries automatically, and generating payroll reports with full data persistence.

---

## Overview

The Hospital Payroll Management System centralizes staff salary management into a single desktop app. Administrators can register doctors and nurses, browse the staff directory, search or delete records, and generate a payroll summary — all backed by simple, human-readable file storage that survives application restarts.

## Objectives

- Centralize management of hospital staff records and salary calculations
- Apply core OOP principles — Encapsulation, Abstraction, Inheritance, and Polymorphism
- Deliver a clean, tabbed GUI built with Java Swing
- Validate input and handle errors gracefully with custom exceptions
- Give administrators full control over records and payroll reporting
- Persist data reliably across sessions using file-based storage

## 🧩 Key Features

| Module                | Description                                                                                          |
| --------------------- | ---------------------------------------------------------------------------------------------------- |
| **Register Staff**    | Add a Doctor or Nurse; the form adapts its fields to the selected role                               |
| **Staff Directory**   | View all employees in a table with base and total salary; refresh, search by ID, or delete a record  |
| **Payroll Report**    | Generate a summary with total employees, total monthly expense, average pay, and highest paid staff  |
| **Salary Calculation**| Total salary is computed automatically per role using polymorphism                                   |
| **Validation**        | Rejects empty fields, non-numeric input, negative values, and duplicate employee IDs                 |

## Salary Calculation

| Role       | Formula                                            |
| ---------- | -------------------------------------------------- |
| **Doctor** | `Base Salary + (Consultation Fee × Patients Treated)` |
| **Nurse**  | `Base Salary + (Overtime Hours × Overtime Rate)`      |

## Architecture

Built around a clean object model with clear separation of concerns:

- **Frontend** — Java Swing (`HospitalSalaryGUI`) with a `JTabbedPane` for Register, Directory, and Report views
- **Backend** — `Database` handles reading, writing, searching, and deleting records in a text file
- **Exception Handling** — Custom exceptions (`InvalidSalaryException`, `EmployeeNotFoundException`) provide robust, user-friendly error reporting via `JOptionPane`

### Object Model

| Class                     | Description                                                                          |
| ------------------------- | ------------------------------------------------------------------------------------ |
| `Main`                    | Application entry point; launches the GUI on the Swing event thread                  |
| `Payable` *(interface)*   | Declares `calculateSalary()`                                                         |
| `Employee` *(abstract)*   | Base class for shared fields (id, name, department, baseSalary) with validation      |
| `Doctor`, `Nurse`         | Employee subtypes with role-specific fields and salary logic                         |
| `Database`                | Data layer for file persistence, search, and delete                                  |
| `HospitalSalaryGUI`       | Swing GUI: registration form, staff table, and report panel                          |

**OOP in practice:** `Doctor` and `Nurse` each override `calculateSalary()`, `getRole()`, and `toDataString()` — demonstrating polymorphism, so the GUI and report work with any `Employee` without knowing its concrete type.

## ⚠️ Exception Handling

| Exception                   | Triggered when…                                                                          |
| --------------------------- | ---------------------------------------------------------------------------------------- |
| `InvalidSalaryException`    | A salary, fee, overtime, or patient count is negative, or an employee ID already exists  |
| `EmployeeNotFoundException` | A searched or deleted employee ID does not exist                                         |
| `NumberFormatException`     | A numeric form field contains non-numeric text (handled in the GUI)                      |

## Data Persistence

Data is stored as plain, comma-delimited text in `hospital_employees.txt`, created automatically on first run:

```
hospital_employees.txt
```

Each line follows this format:

```
Role,ID,Name,Department,BaseSalary,Extra1,Extra2

Doctor,D101,Dr. Rahman,Cardiology,50000.0,500.0,40
Nurse,N201,Ayesha Khan,Emergency,25000.0,10.0,150.0
```

For a Doctor, `Extra1` and `Extra2` are the consultation fee and patients treated. For a Nurse, they are overtime hours and overtime rate.

## Getting Started

### Requirements

- JDK 8 or higher
- Any Java IDE (IntelliJ IDEA, Eclipse, NetBeans) — or just a terminal

### Run in an IDE

1. Extract/clone the project.
2. Open your IDE → **File → Open** → select the project folder.
3. Wait for the IDE to index the project.
4. Select a JDK 8+ installation if prompted.
5. Run `Main.java`.

### Run from the Command Line

```
javac *.java
java Main
```

## Project Structure

```
HospitalPayrollSystem/
|
|-- InvalidSalaryException.java
|-- EmployeeNotFoundException.java
|-- Payable.java
|-- Employee.java
|-- Doctor.java
|-- Nurse.java
|-- Database.java
|-- HospitalSalaryGUI.java
|-- Main.java
|
|-- hospital_employees.txt   (auto-generated)
|-- README.md
```

## Future Enhancements

- Migrate storage to MySQL or SQLite for a persistent database backend
- Edit existing employee records from the GUI
- Support more roles (Surgeon, Technician, Administrative Staff)
- Export payroll reports to CSV or PDF
- Search and filter directly in the staff table
- Login system with role-based access for administrators

---

## 👥 Group Members

| SL | Student Name | ID           |
| -- | ------------ | ------------ |
| 1  | Your Name    | Your ID      |
| 2  | Member Name  | Member ID    |
| 3  | Member Name  | Member ID    |

---

## 📝 Conclusion

This project demonstrates a complete, practical application of object-oriented design — an abstract base class, an interface, polymorphic salary calculation, and robust exception handling — combined with a functional Swing GUI and file-based persistence. It offers an organized, scalable foundation for managing hospital staff and payroll.
