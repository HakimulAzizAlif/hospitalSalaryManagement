import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                HospitalSalaryGUI frame = new HospitalSalaryGUI();
                frame.setVisible(true);
            }
        });
    }
}
