# hospitalSalaryManagement                    
# Hospital Payroll Management System

A Java Swing desktop application for managing hospital staff payroll — registering employees, calculating role-specific monthly salaries, maintaining a staff directory, and generating executive reports with data persistence[span_0](start_span)[span_0](end_span).

---

## Overview

The **Hospital Payroll Management System** centralizes payroll processing for healthcare institutions into a single desktop application[span_1](start_span)[span_1](end_span). HR managers and administrators can register personnel, track role-based compensation (such as doctor consultation fees or nurse overtime rates), search and remove records, and generate comprehensive payroll summaries — all backed by flat-file storage that persists across sessions[span_2](start_span)[span_2](end_span).

## Objectives

- Centralize management of hospital staff records and compensation workflows[span_3](start_span)[span_3](end_span).
- Apply core OOP principles — **Encapsulation**, **Abstraction**, **Inheritance**, and **Polymorphism**[span_4](start_span)[span_4](end_span).
- Deliver a clean, multi-tabbed GUI built with Java Swing (`JTabbedPane`)[span_5](start_span)[span_5](end_span).
- Validate user input and manage errors gracefully with custom exceptions[span_6](start_span)[span_6](end_span).
- Provide real-time payroll summary reports for administrative analysis[span_7](start_span)[span_7](end_span).
- Persist data reliably using local text file storage (`hospital_employees.txt`)[span_8](start_span)[span_8](end_span).

## 🧩 Key Features

| Module | Description |
|---|---|
| **Register Staff** | Dynamic form to add Doctors (base salary, consultation fee, patients treated) or Nurses (base salary, overtime hours, overtime rate)[span_9](start_span)[span_9](end_span). |
| **Staff Directory** | Tabular overview (`JTable`) displaying all registered staff with live formatted salary calculations[span_10](start_span)[span_10](end_span). |
| **Search & Delete** | Search employee details by ID or remove selected records directly from persistent storage[span_11](start_span)[span_11](end_span). |
| **Payroll Report** | Generate real-time summary reports showing total expenses, average pay, and the highest-paid employee[span_12](start_span)[span_12](end_span). |

## Architecture

Built around a clean object model with clear separation of concerns[span_13](start_span)[span_13](end_span):

- **Frontend** — Java Swing, with tabbed navigation (`JTabbedPane`) separating forms, tables, and reports[span_14](start_span)[span_14](end_span).
- **Backend** — `Database` class handles file I/O operations, input reading, searching, and record deletions[span_15](start_span)[span_15](end_span).
- **Exception Handling** — Custom exceptions (`InvalidSalaryException`, `EmployeeNotFoundException`) provide robust error reporting via `JOptionPane` dialogs[span_16](start_span)[span_16](end_span).

### Object Model

| Class / Interface | Description |
|---|---|
| `Main` | Entry point that launches `HospitalSalaryGUI` on the Event Dispatch Thread[span_17](start_span)[span_17](end_span). |
| `Payable` *(interface)* | Defines the core salary calculation contract (`calculateSalary()`)[span_18](start_span)[span_18](end_span). |
| `Employee` *(abstract)* | Base class implementing `Payable` for shared fields (`id`, `name`, `department`, `baseSalary`)[span_19](start_span)[span_19](end_span). |
| `Doctor` | Concrete subclass of `Employee` with fee-per-patient salary calculations[span_20](start_span)[span_20](end_span). |
| `Nurse` | Concrete subclass of `Employee` with hourly overtime salary calculations[span_21](start_span)[span_21](end_span). |
| `Database` | Handles flat-file persistence and record management for `hospital_employees.txt`[span_22](start_span)[span_22](end_span). |
| `HospitalSalaryGUI` | Main Swing interface incorporating dynamic forms, tables, and summary reports[span_23](start_span)[span_23](end_span). |

**OOP in practice:** `Doctor` and `Nurse` inherit from `Employee` and implement `Payable`[span_24](start_span)[span_24](end_span). Each subclass overrides `calculateSalary()`, `getRole()`, and `toDataString()` to dynamically execute role-specific behavior — demonstrating polymorphism throughout the application[span_25](start_span)[span_25](end_span).

## ⚠️ Exception Handling

| Exception | Triggered when… |
|---|---|
| `InvalidSalaryException` | Negative values are entered for salaries, fees, or overtime, or when registering a duplicate Employee ID[span_26](start_span)[span_26](end_span). |
| `EmployeeNotFoundException` | A searched or targeted Employee ID does not exist in the database[span_27](start_span)[span_27](end_span). |
| `NumberFormatException` | Non-numeric input is supplied to numeric fields (e.g., base salary, overtime hours)[span_28](start_span)[span_28](end_span). |
| `IOException` | File read/write issues occur during database persistence operations[span_29](start_span)[span_29](end_span). |

## Data Persistence

Data is saved as plain, comma-separated text in `hospital_employees.txt` in the root project directory (created automatically on first run)[span_30](start_span)[span_30](end_span):

```text
hospital_employees.txt
