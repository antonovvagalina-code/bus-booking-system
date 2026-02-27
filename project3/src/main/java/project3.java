import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

class JobApplication {
    String name, email, jobTitle, qualification;

    JobApplication(String name, String email, String jobTitle, String qualification) {
        this.name = name;
        this.email = email;
        this.jobTitle = jobTitle;
        this.qualification = qualification;
    }
}

public class project3 extends JFrame {
    private JTextField tfName, tfEmail, tfJobTitle, tfQualification;
    private JTable table;
    private DefaultTableModel model;
    private ArrayList<JobApplication> applications = new ArrayList<>();

    public project3() {
        setTitle("Job Portal System");
        setSize(900, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel heading = new JLabel("Online Job Portal", JLabel.CENTER);
        heading.setFont(new Font("Segoe UI", Font.BOLD, 26));
        heading.setOpaque(true);
        heading.setBackground(new Color(33, 47, 61));
        heading.setForeground(Color.white);
        heading.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        add(heading, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 12, 12));
        formPanel.setBorder(BorderFactory.createTitledBorder("Apply for a Job"));
        formPanel.setBackground(new Color(245, 245, 245));

        tfName = new JTextField();
        tfEmail = new JTextField();
        tfJobTitle = new JTextField();
        tfQualification = new JTextField();

        formPanel.add(new JLabel("Applicant Name:"));
        formPanel.add(tfName);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(tfEmail);
        formPanel.add(new JLabel("Job Title:"));
        formPanel.add(tfJobTitle);
        formPanel.add(new JLabel("Qualification:"));
        formPanel.add(tfQualification);

        JButton btnApply = new JButton("Apply");
        JButton btnClear = new JButton("Clear");
        styleButton(btnApply);
        styleButton(btnClear);
        formPanel.add(btnApply);
        formPanel.add(btnClear);
        add(formPanel, BorderLayout.WEST);

        model = new DefaultTableModel(new String[]{"Name", "Email", "Job Title", "Qualification"}, 0);
        table = new JTable(model);
        table.setRowHeight(22);
        table.setFont(new Font("SansSerif", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        JScrollPane tablePane = new JScrollPane(table);
        add(tablePane, BorderLayout.CENTER);

        btnApply.addActionListener(e -> applyJob());
        btnClear.addActionListener(e -> clearForm());

        // Example data
        applications.add(new JobApplication("John Doe", "john@mail.com", "Software Developer", "B.Tech"));
        applications.add(new JobApplication("Jane Smith", "jane@mail.com", "Graphic Designer", "BFA"));
        updateTable();
    }

    private void applyJob() {
        String name = tfName.getText().trim();
        String email = tfEmail.getText().trim();
        String jobTitle = tfJobTitle.getText().trim();
        String qualification = tfQualification.getText().trim();

        if (name.isEmpty() || email.isEmpty() || jobTitle.isEmpty() || qualification.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        applications.add(new JobApplication(name, email, jobTitle, qualification));
        updateTable();
        clearForm();
    }

    private void updateTable() {
        model.setRowCount(0);
        for (JobApplication ja : applications) {
            model.addRow(new Object[]{ja.name, ja.email, ja.jobTitle, ja.qualification});
        }
    }

    private void clearForm() {
        tfName.setText("");
        tfEmail.setText("");
        tfJobTitle.setText("");
        tfQualification.setText("");
    }

    private void styleButton(JButton button) {
        button.setBackground(new Color(41, 128, 185));
        button.setForeground(Color.white);
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setFocusPainted(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new project3().setVisible(true));
    }
}