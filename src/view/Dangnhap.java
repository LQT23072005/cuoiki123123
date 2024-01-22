package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Dangnhap extends JFrame {
    public Dangnhap() {
        this.init();
    }

    private void init() {
        this.setTitle("ĐĂNG NHẬP");
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        URL url_background = Dangnhap.class.getResource("nen.png");
        Image img = Toolkit.getDefaultToolkit().createImage(url_background);

        Font font_TIEUDE = new Font("Arial", Font.BOLD, 40);

        JLabel jLabel_tenTieuDe = new JLabel("Đăng nhập hệ thống quản lý");
        jLabel_tenTieuDe.setForeground(Color.blue);
        jLabel_tenTieuDe.setFont(font_TIEUDE);

        JLabel jLabel_username = new JLabel("Tài khoản  :");
        jLabel_username.setFont(new Font("Arial", Font.BOLD, 20));
        jLabel_username.setIcon(new ImageIcon(
                Toolkit.getDefaultToolkit().createImage(Dangnhap.class.getResource("icon_man.png"))));

        JLabel jLabel_password = new JLabel("Mật khẩu :");
        jLabel_password.setFont(new Font("Arial", Font.BOLD, 20));
        jLabel_password.setIcon(new ImageIcon(
                Toolkit.getDefaultToolkit().createImage(Dangnhap.class.getResource("icon_lock.png"))));

        JTextField jTextField = new JTextField(50);
        jTextField.setFont(new Font("Arial", Font.BOLD, 20));

        JPasswordField jPasswordField_password = new JPasswordField(50);
        jPasswordField_password.setFont(new Font("Arial", Font.BOLD, 20));

        JButton jButton_login = new JButton("ĐĂNG NHẬP");
        jButton_login.setBackground(Color.DARK_GRAY);
        jButton_login.setForeground(Color.WHITE);

        jButton_login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/danhsachhocvien", "root", "");
                    Statement st = cn.createStatement();
                    ResultSet rs = st.executeQuery("select * from login where Taikhoan ='" + jTextField.getText()
                            + "'and Matkhau ='" + jPasswordField_password.getText() + "'");

                    if (rs.next()) {
                        GiaodienchinhView giaodienchinhView = new GiaodienchinhView();
                        giaodienchinhView.setVisible(true);

                    } else {
                        JOptionPane.showMessageDialog(null, "Tài khoản hoặc mật khẩu không đúng", "Lỗi đăng nhập",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                    st.close();
                    cn.close();

                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });

        // Create the background panel with a custom paint method
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw the background image
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };

        backgroundPanel.setLayout(new BorderLayout());

        // Create panels for title, inputs, and button
        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        titlePanel.add(jLabel_tenTieuDe);

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 15, 15));
        inputPanel.setOpaque(false);
        inputPanel.add(jLabel_username);
        inputPanel.add(jTextField);
        inputPanel.add(jLabel_password);
        inputPanel.add(jPasswordField_password);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.add(jButton_login);

        
        backgroundPanel.add(titlePanel, BorderLayout.NORTH);
        backgroundPanel.add(inputPanel, BorderLayout.CENTER);
        backgroundPanel.add(buttonPanel, BorderLayout.SOUTH);

        
        setContentPane(backgroundPanel);

        
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Dangnhap();
        });
    }
}