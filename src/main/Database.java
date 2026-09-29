import java.io.*;

import java.util.ArrayList;

import java.util.List;

import java.util.Scanner;

public class Database
    {
    private String fileName = "hospital_employees.txt";
    public Database()
        {
        try
            {
            File file = new File(fileName);
            if (!file.exists()) 
            {
                file.createNewFile();
            }
        } 
        catch (IOException e)
        {
            System.out.println("Error creating database file: " + e.getMessage());
        }
    }
    public void saveEmployee(Employee emp) throws IOException, InvalidSalaryException
        {
        List<Employee> list = readAllEmployees();
        for (Employee e : list)
        {
            if (e.getId().equalsIgnoreCase(emp.getId()))
            {
                throw new InvalidSalaryException("Employee ID " + emp.getId() + " already exists!");
            }
        }
        FileWriter fw = new FileWriter(fileName, true);
            
        PrintWriter pw = new PrintWriter(fw);
        pw.println(emp.toDataString());
        pw.close();
        fw.close();
    }
    public List<Employee> readAllEmployees()
        {
        List<Employee> list = new ArrayList<>();
        File file = new File(fileName);
        try
            {
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine())
                {
                String line = fileScanner.nextLine();
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",");
                if (parts.length < 7) continue;
                String role = parts[0];
                String id = parts[1];
                String name = parts[2];
                String dept = parts[3];
                double baseSalary = Double.parseDouble(parts[4]);
                double extra1 = Double.parseDouble(parts[5]);
                double extra2 = Double.parseDouble(parts[6]);
                if (role.equalsIgnoreCase("Doctor"))
                {
                    list.add(new Doctor(id, name, dept, baseSalary, extra1, (int) extra2));
                } 
                else if (role.equalsIgnoreCase("Nurse")) 
                {
                    list.add(new Nurse(id, name, dept, baseSalary, extra1, extra2));
                }
            }
            fileScanner.close();
        } catch (Exception e) 
        {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return list;
    }
    public Employee searchEmployee(String id) throws EmployeeNotFoundException
        {
        List<Employee> list = readAllEmployees();
        for (Employee emp : list) {
            if (emp.getId().equalsIgnoreCase(id)) 
            {
                return emp;
            }
        }
        throw new EmployeeNotFoundException("Employee with ID " + id + " not found!");
    }
    public void deleteEmployee(String id) throws IOException, EmployeeNotFoundException 
        {
        List<Employee> list = readAllEmployees();
        boolean found = false;
        for (int i = 0; i < list.size(); i++) 
        {
            if (list.get(i).getId().equalsIgnoreCase(id)) 
            {
                list.remove(i);
                found = true;
                break;
            }
        }
        if (!found)
        {
            throw new EmployeeNotFoundException("Employee with ID " + id + " not found!");
        }
        FileWriter fw = new FileWriter(fileName, false);
        PrintWriter pw = new PrintWriter(fw);
        for (Employee emp : list) 
        {
            pw.println(emp.toDataString());
        }
        pw.close();
        fw.close();
    }
}
