import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
public class HospitalSalaryGUI extends JFrame {

    private final Database db = new Database();
    private final JTextField txtId = new JTextField(18);
    private final JTextField txtName = new JTextField(18);
    private final JTextField txtDept = new JTextField(18);
    private final JTextField txtBaseSalary = new JTextField(18);
    private final JTextField txtExtra1 = new JTextField(18);
    private final JTextField txtExtra2 = new JTextField(18);
    private final JComboBox<String> cmbRole = new JComboBox<>(new String[]{"Doctor", "Nurse"});
    private final JLabel lblExtra1 = new JLabel("Consultation Fee ($):");
    private final JLabel lblExtra2 = new JLabel("Patients Treated:");
    private DefaultTableModel tableModel;
    private JTable table;
    private final JTextArea txtReport = new JTextArea();
    public HospitalSalaryGUI() {
        setTitle("Hospital Payroll Management System");
        setSize(820, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBackground(new Color(245, 247, 250));

        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 15));
        headerPanel.setBackground(new Color(30, 41, 59));
        JLabel titleLabel = new JLabel("Hospital Payroll System");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);
        contentPane.add(headerPanel, BorderLayout.NORTH);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        tabbedPane.addTab("Register Staff", createFormPanel());
        tabbedPane.addTab("Staff Directory", createTablePanel());
        tabbedPane.addTab("Payroll Report", createReportPanel());

        contentPane.add(tabbedPane, BorderLayout.CENTER);
        add(contentPane);

        refreshTableData();
    }

    private JPanel createFormPanel() {
        JPanel outerPanel = new JPanel(new BorderLayout());
        outerPanel.setBorder(new EmptyBorder(25, 40, 25, 40));
        outerPanel.setBackground(new Color(245, 247, 250));

        JPanel formCard = new JPanel(new GridBagLayout());
        formCard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(226, 232, 240), 1),
                new EmptyBorder(20, 25, 20, 25)
        ));
        formCard.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font labelFont = new Font("Segoe UI", Font.BOLD, 12);
        gbc.gridx = 0; gbc.gridy = 0;
        JLabel lblRole = new JLabel("Role:"); lblRole.setFont(labelFont);
        formCard.add(lblRole, gbc);
        gbc.gridx = 1;
        formCard.add(cmbRole, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        JLabel lblId = new JLabel("Employee ID:"); lblId.setFont(labelFont);
        formCard.add(lblId, gbc);
        gbc.gridx = 1;
        formCard.add(txtId, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        JLabel lblName = new JLabel("Full Name:"); lblName.setFont(labelFont);
        formCard.add(lblName, gbc);
        gbc.gridx = 1;
        formCard.add(txtName, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        JLabel lblDept = new JLabel("Department:"); lblDept.setFont(labelFont);
        formCard.add(lblDept, gbc);
        gbc.gridx = 1;
        formCard.add(txtDept, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        JLabel lblBase = new JLabel("Base Salary ($):"); lblBase.setFont(labelFont);
        formCard.add(lblBase, gbc);
        gbc.gridx = 1;
        formCard.add(txtBaseSalary, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        lblExtra1.setFont(labelFont);
        formCard.add(lblExtra1, gbc);
        gbc.gridx = 1;
        formCard.add(txtExtra1, gbc);

        gbc.gridx = 0; gbc.gridy = 6;
        lblExtra2.setFont(labelFont);
        formCard.add(lblExtra2, gbc);
        gbc.gridx = 1;
        formCard.add(txtExtra2, gbc);

        cmbRole.addActionListener(e -> {
            if ("Doctor".equals(cmbRole.getSelectedItem())) {
                lblExtra1.setText("Consultation Fee ($):");
                lblExtra2.setText("Patients Treated:");
            } else {
                lblExtra1.setText("Overtime Hours:");
                lblExtra2.setText("Overtime Rate ($/hr):");
            }
        });
        JButton btnSave = new JButton("Save Record");
        btnSave.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnSave.setBackground(new Color(15, 118, 110));
        btnSave.setForeground(Color.WHITE);
        btnSave.setFocusPainted(false);
        btnSave.setPreferredSize(new Dimension(140, 36));
        btnSave.addActionListener(e -> saveEmployee());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(new EmptyBorder(15, 0, 0, 0));
        buttonPanel.add(btnSave);

        outerPanel.add(formCard, BorderLayout.CENTER);
        outerPanel.add(buttonPanel, BorderLayout.SOUTH);

        return outerPanel;
    }
    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));
        panel.setBackground(new Color(245, 247, 250));
        String[] headers = {"ID", "Name", "Role", "Department", "Base Salary", "Total Salary"};
        tableModel = new DefaultTableModel(headers, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        table.setRowHeight(26);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        table.getTableHeader().setBackground(new Color(241, 245, 249));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(Color.WHITE);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 5));
        btnPanel.setOpaque(false);

        JButton btnRefresh = createStyledButton("Refresh Table", new Color(71, 85, 105));
        JButton btnSearch = createStyledButton("Search ID", new Color(30, 58, 138));
        JButton btnDelete = createStyledButton("Delete Selected", new Color(185, 28, 28));

        btnRefresh.addActionListener(e -> refreshTableData());
        btnSearch.addActionListener(e -> searchEmployee());
        btnDelete.addActionListener(e -> deleteEmployee());

        btnPanel.add(btnRefresh);
        btnPanel.add(btnSearch);
        btnPanel.add(btnDelete);

        panel.add(btnPanel, BorderLayout.SOUTH);
        return panel;
    }
    private JPanel createReportPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));
        panel.setBackground(new Color(245, 247, 250));

        txtReport.setEditable(false);
        txtReport.setFont(new Font("Consolas", Font.PLAIN, 13));
        txtReport.setBorder(new EmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(txtReport);
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton btnGenerate = createStyledButton("Generate Summary Report", new Color(15, 118, 110));
        btnGenerate.addActionListener(e -> generateReport());

        JPanel buttonContainer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonContainer.setOpaque(false);
        buttonContainer.add(btnGenerate);

        panel.add(buttonContainer, BorderLayout.SOUTH);
        return panel;
    }
    private JButton createStyledButton(String text, Color bg) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setPreferredSize(new Dimension(150, 32));
        return btn;
    }
    private void saveEmployee() {
        try {
            String id = txtId.getText().trim();
            String name = txtName.getText().trim();
            String dept = txtDept.getText().trim();

            if (id.isEmpty() || name.isEmpty() || dept.isEmpty() ||
                    txtBaseSalary.getText().trim().isEmpty() ||
                    txtExtra1.getText().trim().isEmpty() ||
                    txtExtra2.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(this, "Please fill in all form fields.", "Missing Input", JOptionPane.WARNING_MESSAGE);
                return;
            }
            double base = Double.parseDouble(txtBaseSalary.getText().trim());
            double field1 = Double.parseDouble(txtExtra1.getText().trim());
            double field2 = Double.parseDouble(txtExtra2.getText().trim())
            String role = (String) cmbRole.getSelectedItem();
            Employee emp;
            if ("Doctor".equals(role)) {
                emp = new Doctor(id, name, dept, base, field1, (int) field2);
            } else {
                emp = new Nurse(id, name, dept, base, field1, field2);
            }
            db.saveEmployee(emp);
            JOptionPane.showMessageDialog(this, "Employee record created successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearForm();
            refreshTableData();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values for salary and numeric attributes.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        } catch (InvalidSalaryException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Validation Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error saving record: " + ex.getMessage(), "System Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void refreshTableData() {
        tableModel.setRowCount(0);
        List<Employee> list = db.readAllEmployees();
        for (Employee emp : list) {
            tableModel.addRow(new Object[]{
                    emp.getId(),
                    emp.getName(),
                    emp.getRole(),
                    emp.getDepartment(),
                    String.format("$%.2f", emp.getBaseSalary()),
                    String.format("$%.2f", emp.calculateSalary())
            });
        }
    }
    private void searchEmployee() {
        String id = JOptionPane.showInputDialog(this, "Enter Employee ID to search:");
        if (id != null && !id.trim().isEmpty()) {
            try {
                Employee emp = db.searchEmployee(id.trim());
                String info = String.format(
                        "Employee Details:\n\nID: %s\nName: %s\nRole: %s\nDepartment: %s\nBase Salary: $%.2f\nCalculated Monthly Salary: $%.2f",
                        emp.getId(), emp.getName(), emp.getRole(), emp.getDepartment(), emp.getBaseSalary(), emp.calculateSalary()
                );
                JOptionPane.showMessageDialog(this, info, "Search Result", JOptionPane.INFORMATION_MESSAGE);
            } catch (EmployeeNotFoundException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Not Found", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    private void deleteEmployee() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an employee row to delete.", "Selection Required", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String id = (String) tableModel.getValueAt(selectedRow, 0);
        int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to remove record ID: " + id + "?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            try {
                db.deleteEmployee(id);
                refreshTableData();
                JOptionPane.showMessageDialog(this, "Record successfully removed.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Deletion failed: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private void generateReport() {
        List<Employee> list = db.readAllEmployees();
        if (list.isEmpty()) {
            txtReport.setText("No staff records available.");
            return;
        }
        double totalPayroll = 0;
        Employee highestPaid = list.get(0);
        StringBuilder sb = new StringBuilder();
        sb.append("=====================================================================\n");
        sb.append("                   HOSPITAL PAYROLL SUMMARY REPORT                   \n");
        sb.append("=====================================================================\n\n");
        for (Employee emp : list) {
            double salary = emp.calculateSalary();
            totalPayroll += salary;

            if (salary > highestPaid.calculateSalary()) {
                highestPaid = emp;
            }
            sb.append(String.format("ID: %-8s | Name: %-18s | Role: %-8s | Total: $%.2f\n",
                    emp.getId(), emp.getName(), emp.getRole(), salary));
        }
        sb.append("\n---------------------------------------------------------------------\n");
        sb.append(String.format("Total Employees Registered : %d\n", list.size()));
        sb.append(String.format("Total Monthly Expense      : $%.2f\n", totalPayroll));
        sb.append(String.format("Average Employee Pay       : $%.2f\n", totalPayroll / list.size()));
        sb.append(String.format("Highest Paid Staff Member  : %s ($%.2f)\n", highestPaid.getName(), highestPaid.calculateSalary()));
        sb.append("=====================================================================\n");

        txtReport.setText(sb.toString());
    }
    private void clearForm() {
        txtId.setText("");
        txtName.setText("");
        txtDept.setText("");
        txtBaseSalary.setText("");
        txtExtra1.setText("");
        txtExtra2.setText("");
    }
}