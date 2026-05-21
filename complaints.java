package CareConnect;


import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class CareConnectG {

    static final String FILE_NAME = "complaints.txt";

    static ArrayList<String> complaintList = new ArrayList<>();

    static JTextArea area;

    static class GradientPanel extends JPanel {

        protected void paintComponent(Graphics g) {

            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;

            int width = getWidth();
            int height = getHeight();

            GradientPaint gp =
                    new GradientPaint(
                            0,
                            0,
                            new Color(15, 32, 39),
                            width,
                            height,
                            new Color(44, 83, 100)
                    );

            g2d.setPaint(gp);

            g2d.fillRect(0, 0, width, height);
        }
    }

    static class ModernButton extends JButton {

        Color normal;
        Color hover;

        ModernButton(
                String text,
                Color normal,
                Color hover
        ) {

            super(text);

            this.normal = normal;
            this.hover = hover;

            setFocusPainted(false);

            setForeground(Color.WHITE);

            setFont(
                    new Font(
                            "Segoe UI",
                            Font.BOLD,
                            14
                    )
            );

            setBackground(normal);

            setCursor(
                    new Cursor(
                            Cursor.HAND_CURSOR
                    )
            );

            setBorder(
                    new EmptyBorder(
                            10,
                            20,
                            10,
                            20
                    )
            );

            addMouseListener(new MouseAdapter() {

                public void mouseEntered(MouseEvent e) {

                    setBackground(hover);
                }

                public void mouseExited(MouseEvent e) {

                    setBackground(normal);
                }
            });
        }

        protected void paintComponent(Graphics g) {

            Graphics2D g2 =
                    (Graphics2D) g;

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            g2.setColor(getBackground());

            g2.fillRoundRect(
                    0,
                    0,
                    getWidth(),
                    getHeight(),
                    30,
                    30
            );

            super.paintComponent(g);
        }

        protected void paintBorder(Graphics g) {
        }

        public boolean isContentAreaFilled() {

            return false;
        }
    }

    public static void main(String[] args) {

        JFrame frame =
                new JFrame(
                        "Care Connect Dashboard"
                );

        frame.setSize(1000, 700);

        frame.setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        frame.setLocationRelativeTo(null);

        GradientPanel background =
                new GradientPanel();

        background.setLayout(null);

        frame.setContentPane(background);

        JPanel sidebar =
                new JPanel();

        sidebar.setBounds(0, 0, 220, 700);

        sidebar.setBackground(
                new Color(10, 20, 30)
        );

        sidebar.setLayout(null);

        JLabel logo =
                new JLabel("CARE CONNECT");

        logo.setBounds(20, 40, 200, 40);

        logo.setForeground(Color.WHITE);

        logo.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        24
                )
        );

        JLabel subtitle =
                new JLabel("Customer Support");

        subtitle.setBounds(20, 75, 200, 30);

        subtitle.setForeground(
                new Color(180, 180, 180)
        );

        sidebar.add(logo);
        sidebar.add(subtitle);

        JPanel mainPanel =
                new JPanel();

        mainPanel.setBounds(240, 40, 720, 580);

        mainPanel.setLayout(null);

        mainPanel.setBackground(
                new Color(255, 255, 255)
        );

        mainPanel.setBorder(
                new CompoundBorder(
                        new LineBorder(
                                new Color(0, 120, 215),
                                3,
                                true
                        ),
                        new EmptyBorder(
                                20,
                                20,
                                20,
                                20
                        )
                )
        );

        JLabel title =
                new JLabel(
                        "Complaint Management Dashboard"
                );

        title.setBounds(20, 10, 500, 40);

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        28
                )
        );

        title.setForeground(
                new Color(0, 51, 102)
        );

        JLabel nameLabel =
                new JLabel("Customer Name");

        nameLabel.setBounds(20, 70, 150, 25);

        JTextField nameField =
                createField(20, 100);

        nameField.addKeyListener(new KeyAdapter() {

            public void keyTyped(KeyEvent e) {

                char c = e.getKeyChar();

                if (
                        !Character.isLetter(c)
                        && c != ' '
                ) {

                    e.consume();

                    JOptionPane.showMessageDialog(
                            null,
                            "Only Alphabets Allowed In Name"
                    );
                }
            }
        });

        JLabel phoneLabel =
                new JLabel("Phone Number");

        phoneLabel.setBounds(250, 70, 150, 25);

        JTextField phoneField =
                createField(250, 100);

        phoneField.addKeyListener(new KeyAdapter() {

            public void keyTyped(KeyEvent e) {

                char c = e.getKeyChar();

                if (!Character.isDigit(c)) {

                    e.consume();

                    JOptionPane.showMessageDialog(
                            null,
                            "Only Numbers Allowed"
                    );
                }

                if (
                        phoneField.getText().length()
                                >= 10
                ) {

                    e.consume();

                    JOptionPane.showMessageDialog(
                            null,
                            "Phone Number Must Be 10 Digits Only"
                    );
                }
            }
        });

        JLabel addressLabel =
                new JLabel("Address");

        addressLabel.setBounds(480, 70, 150, 25);

        JTextField addressField =
                createField(480, 100);

        addressField.addKeyListener(new KeyAdapter() {

            public void keyTyped(KeyEvent e) {

                char c = e.getKeyChar();

                if (
                        !(Character.isLetterOrDigit(c)
                        || Character.isWhitespace(c)
                        || c == ','
                        || c == '-'
                        || c == '/')
                ) {

                    e.consume();

                    JOptionPane.showMessageDialog(
                            null,
                            "Special Symbols Not Allowed In Address"
                    );
                }
            }
        });

        JLabel complaintLabel =
                new JLabel("Complaint");

        complaintLabel.setBounds(20, 150, 150, 25);

        JTextField complaintField =
                createField(20, 180);

        complaintField.setSize(650, 40);

        ModernButton addButton =
                new ModernButton(
                        "Add Complaint",
                        new Color(0, 153, 76),
                        new Color(0, 200, 100)
                );

        addButton.setBounds(20, 250, 150, 45);

        ModernButton updateButton =
                new ModernButton(
                        "Update",
                        new Color(255, 153, 0),
                        new Color(255, 180, 50)
                );

        updateButton.setBounds(190, 250, 120, 45);

        ModernButton deleteButton =
                new ModernButton(
                        "Delete",
                        new Color(204, 0, 0),
                        new Color(255, 51, 51)
                );

        deleteButton.setBounds(330, 250, 120, 45);

        ModernButton viewButton =
                new ModernButton(
                        "View All",
                        new Color(0, 102, 204),
                        new Color(51, 153, 255)
                );

        viewButton.setBounds(470, 250, 120, 45);

        ModernButton clearButton =
                new ModernButton(
                        "Clear",
                        new Color(102, 0, 153),
                        new Color(153, 51, 255)
                );

        clearButton.setBounds(610, 250, 90, 45);

        area = new JTextArea();

        area.setEditable(false);

        area.setFont(
                new Font(
                        "Consolas",
                        Font.PLAIN,
                        14
                )
        );

        area.setBackground(
                new Color(245, 248, 255)
        );

        area.setBorder(
                new EmptyBorder(
                        10,
                        10,
                        10,
                        10
                )
        );

        JScrollPane scrollPane =
                new JScrollPane(area);

        scrollPane.setBounds(
                20,
                330,
                680,
                210
        );

        loadData();

        addButton.addActionListener(e -> {

            String name =
                    nameField.getText().trim();

            String phone =
                    phoneField.getText().trim();

            String address =
                    addressField.getText().trim();

            String complaint =
                    complaintField.getText().trim();

            if (
                    name.isEmpty()
                    || phone.isEmpty()
                    || complaint.isEmpty()
            ) {

                JOptionPane.showMessageDialog(
                        frame,
                        "Fill All Required Fields"
                );

                return;
            }

            if (!name.matches("[a-zA-Z ]+")) {

                JOptionPane.showMessageDialog(
                        frame,
                        "Invalid Name"
                );

                return;
            }

            if (!phone.matches("\\d{10}")) {

                JOptionPane.showMessageDialog(
                        frame,
                        "Phone Number Must Be 10 Digits"
                );

                return;
            }

            if (
                    !address.matches(
                            "[a-zA-Z0-9 ,/-]+"
                    )
            ) {

                JOptionPane.showMessageDialog(
                        frame,
                        "Invalid Address"
                );

                return;
            }

            Random random =
                    new Random();

            int complaintId =
                    1000 + random.nextInt(9000);

            String date =
                    new SimpleDateFormat(
                            "dd-MM-yyyy HH:mm:ss"
                    ).format(new Date());

            String receipt =
                    "\n====================================\n" +
                    "        CARE CONNECT RECEIPT        \n" +
                    "====================================\n" +
                    "Complaint ID   : CC" + complaintId + "\n" +
                    "Complaint Date : " + date + "\n\n" +
                    "Customer Name  : " + name + "\n" +
                    "Phone Number   : " + phone + "\n" +
                    "Address        : " + address + "\n" +
                    "Complaint      : " + complaint + "\n" +
                    "====================================\n";

            complaintList.add(receipt);

            area.append(receipt + "\n");

            saveData();

            JOptionPane.showMessageDialog(
                    frame,
                    "Complaint Added Successfully"
            );

            nameField.setText("");
            phoneField.setText("");
            addressField.setText("");
            complaintField.setText("");
        });

        viewButton.addActionListener(e -> {

            area.setText("");

            for (String data : complaintList) {

                area.append(data + "\n");
            }
        });

        deleteButton.addActionListener(e -> {

            String phone =
                    JOptionPane.showInputDialog(
                            frame,
                            "Enter Phone Number To Delete"
                    );

            boolean found = false;

            for (
                    int i = 0;
                    i < complaintList.size();
                    i++
            ) {

                if (
                        complaintList.get(i)
                                .contains(
                                        "Phone Number   : "
                                                + phone
                                )
                ) {

                    complaintList.remove(i);

                    found = true;

                    JOptionPane.showMessageDialog(
                            frame,
                            "Deleted Successfully"
                    );

                    break;
                }
            }

            if (!found) {

                JOptionPane.showMessageDialog(
                        frame,
                        "Complaint Not Found"
                );
            }

            saveData();

            area.setText("");

            for (String data : complaintList) {

                area.append(data + "\n");
            }
        });

        updateButton.addActionListener(e -> {

            String phone =
                    JOptionPane.showInputDialog(
                            frame,
                            "Enter Phone Number To Update"
                    );

            boolean found = false;

            for (
                    int i = 0;
                    i < complaintList.size();
                    i++
            ) {

                String oldData =
                        complaintList.get(i);

                if (
                        oldData.contains(
                                "Phone Number   : "
                                        + phone
                        )
                ) {

                    found = true;

                    String newComplaint =
                            JOptionPane.showInputDialog(
                                    frame,
                                    "Enter New Complaint"
                            );

                    String updated =
                            oldData.replaceAll(
                                    "(Complaint      : )(.*)",
                                    "Complaint      : "
                                            + newComplaint
                            );

                    complaintList.set(i, updated);

                    JOptionPane.showMessageDialog(
                            frame,
                            "Complaint Updated"
                    );

                    break;
                }
            }

            if (!found) {

                JOptionPane.showMessageDialog(
                        frame,
                        "Complaint Not Found"
                );
            }

            saveData();

            area.setText("");

            for (String data : complaintList) {

                area.append(data + "\n");
            }
        });

        clearButton.addActionListener(e -> {

            nameField.setText("");
            phoneField.setText("");
            addressField.setText("");
            complaintField.setText("");
            area.setText("");
        });

        mainPanel.add(title);

        mainPanel.add(nameLabel);
        mainPanel.add(nameField);

        mainPanel.add(phoneLabel);
        mainPanel.add(phoneField);

        mainPanel.add(addressLabel);
        mainPanel.add(addressField);

        mainPanel.add(complaintLabel);
        mainPanel.add(complaintField);

        mainPanel.add(addButton);
        mainPanel.add(updateButton);
        mainPanel.add(deleteButton);
        mainPanel.add(viewButton);
        mainPanel.add(clearButton);

        mainPanel.add(scrollPane);

        background.add(sidebar);
        background.add(mainPanel);

        frame.setVisible(true);
    }

    static JTextField createField(int x, int y) {

        JTextField field =
                new JTextField();

        field.setBounds(x, y, 190, 40);

        field.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        field.setBorder(
                new CompoundBorder(
                        new LineBorder(
                                new Color(200, 200, 200),
                                2,
                                true
                        ),
                        new EmptyBorder(
                                5,
                                10,
                                5,
                                10
                        )
                )
        );

        return field;
    }

    static void saveData() {

        try {

            BufferedWriter writer =
                    new BufferedWriter(
                            new FileWriter(FILE_NAME)
                    );

            for (String data : complaintList) {

                writer.write(data);

                writer.newLine();
            }

            writer.close();

        } catch (Exception e) {

            System.out.println(
                    "Error Saving File"
            );
        }
    }

    static void loadData() {

        try {

            File file =
                    new File(FILE_NAME);

            if (!file.exists()) {

                return;
            }

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader(file)
                    );

            StringBuilder record =
                    new StringBuilder();

            String line;

            while (
                    (line = reader.readLine()) != null
            ) {

                area.append(line + "\n");

                record.append(line)
                        .append("\n");

                if (
                        line.contains(
                                "===================================="
                        )
                ) {

                    complaintList.add(
                            record.toString()
                    );

                    record.setLength(0);
                }
            }

            reader.close();

        } catch (Exception e) {

            System.out.println(
                    "Error Loading File"
            );
        }
    }
}
