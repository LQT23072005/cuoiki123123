package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class suaView extends JFrame {
	public suaView() {
		this.init();
	}

	private void init() {
		this.setTitle("Sửa thông tin học viên");
		this.setSize(1200, 650);
		this.setLocationRelativeTo(null);
		this.setLocationRelativeTo(null);

		Font font_tren = new Font("Arial", Font.BOLD, 35);
		Font font_giua = new Font("Arial", Font.BOLD, 20);

		JPanel jPanel_tren = new JPanel();
		JPanel jPanel_giua = new JPanel();
		JPanel jPanel_duoi = new JPanel();

		JButton jButton_troVe = new JButton("Trở về");
		jButton_troVe.setFont(font_giua);
		JLabel jLabel_suaHocvien = new JLabel("Sửa thông tin học viên");
		jLabel_suaHocvien.setFont(font_tren);

		JLabel jLabel_id = new JLabel("ID học viên :");
		jLabel_id.setFont(font_tren);
		JLabel jLabel_ten = new JLabel("Tên học viên :");
		jLabel_ten.setFont(font_tren);
		JLabel jLabel_ngaysinh = new JLabel("Ngày sinh (Năm-Tháng -Ngày):");
		jLabel_ngaysinh.setFont(font_tren);
		JLabel jLabel_diachi = new JLabel("Địa chỉ :");
		jLabel_diachi.setFont(font_tren);
		JLabel jLabel_hocphi = new JLabel("Học phí đã đóng :");
		jLabel_hocphi.setFont(font_tren);
		JLabel jLabel_gioitinh = new JLabel("Giới tính :");
		jLabel_gioitinh.setFont(font_tren);
		JLabel jLabel_tinhtrangnophocphi = new JLabel("Tình Trạng học Phí :");
		jLabel_tinhtrangnophocphi.setFont(font_tren);

		JTextField jTextFiled_ID = new JTextField(10);
		jTextFiled_ID.setFont(font_giua);
		JTextField jTextFiled_ten = new JTextField(10);
		jTextFiled_ten.setFont(font_giua);
		JTextField jTextFiled_ngaysinh = new JTextField(10);
		jTextFiled_ngaysinh.setFont(font_giua);
		JTextField jTextFiled_diachi = new JTextField(10);
		jTextFiled_diachi.setFont(font_giua);
		JTextField jTextFiled_hocphi = new JTextField(10);
		jTextFiled_hocphi.setFont(font_giua);
		String[] gioiTinhOptions = { "Nam", "Nữ" };
		JComboBox<String> jComboBox_gioitinh = new JComboBox<>(gioiTinhOptions);
		jComboBox_gioitinh.setFont(font_giua);
		String[] tinhTrangNopHocPhiOptions = { "Đủ", "Chưa" };
		JComboBox<String> jComboBox_tinhTrangNopHocPhi = new JComboBox<>(tinhTrangNopHocPhiOptions);
		jComboBox_gioitinh.setFont(font_giua);

		JButton jButton_sua = new JButton("Sửa");
		jButton_sua.setFont(font_giua);

		jPanel_tren.add(jButton_troVe);
		jPanel_tren.add(jLabel_suaHocvien);
		jPanel_tren.setLayout(new FlowLayout());

		jPanel_giua.add(jLabel_id);
		jPanel_giua.add(jTextFiled_ID);
		jPanel_giua.add(jLabel_ten);
		jPanel_giua.add(jTextFiled_ten);
		jPanel_giua.add(jLabel_ngaysinh);
		jPanel_giua.add(jTextFiled_ngaysinh);
		jPanel_giua.add(jLabel_diachi);
		jPanel_giua.add(jTextFiled_diachi);
		jPanel_giua.add(jLabel_hocphi);
		jPanel_giua.add(jTextFiled_hocphi);
		jPanel_giua.add(jLabel_gioitinh);
		jPanel_giua.add(jComboBox_gioitinh);
		jPanel_giua.add(jLabel_tinhtrangnophocphi);
		jPanel_giua.add(jComboBox_tinhTrangNopHocPhi);

		jPanel_giua.setLayout(new GridLayout(8, 2, 5, 5));

		jPanel_duoi.add(jButton_sua);

		jButton_troVe.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				dispose();

			}

		});
		jTextFiled_ID.setText(GiaodienchinhView.ID);
		jTextFiled_ten.setText(GiaodienchinhView.hovaten);
		jTextFiled_ngaysinh.setText(GiaodienchinhView.ngaysinh);
		jTextFiled_diachi.setText(GiaodienchinhView.diachi);
		jTextFiled_hocphi.setText(GiaodienchinhView.hocphidathu);
		jComboBox_gioitinh.setSelectedItem(GiaodienchinhView.gioitinh);
		jComboBox_tinhTrangNopHocPhi.setSelectedItem(GiaodienchinhView.tinhtrangnophocphi);

		jButton_sua.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String id = jTextFiled_ID.getText();
				String ten = jTextFiled_ten.getText();
				String ngaysinh = jTextFiled_ngaysinh.getText();
				String diachi = jTextFiled_diachi.getText();
				String hocphi = jTextFiled_hocphi.getText();
				String gioitinh = (String) jComboBox_gioitinh.getSelectedItem();
				String tinhTrangNopHocPhi = (String) jComboBox_tinhTrangNopHocPhi.getSelectedItem();

				if (!id.isEmpty() && !ten.isEmpty() && !ngaysinh.isEmpty() && !diachi.isEmpty() && !hocphi.isEmpty()
						&& !gioitinh.isEmpty() && !tinhTrangNopHocPhi.isEmpty()) {
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/danhsachhocvien",
								"root", "");
						Statement st = cn.createStatement();

						st.executeUpdate("UPDATE quanlithuchi SET ID = '" + jTextFiled_ID.getText()
								+ "',`Họ và Tên` = '" + jTextFiled_ten.getText() + "', `Ngày sinh` ='"
								+ jTextFiled_ngaysinh.getText() + "',`Địa chỉ`='" + jTextFiled_diachi.getText()
								+ "',`Học phí đã nộp` ='" + jTextFiled_hocphi.getText() + "', `Giới tính` = '"
								+ gioitinh + "',`Tình trạng học phí`='" + tinhTrangNopHocPhi + "' where ID =" + id);

						JOptionPane.showMessageDialog(null, "Đã sửa thành công");

						st.close();
						cn.close();

					} catch (ClassNotFoundException | SQLException e1) {

						JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng ID và Ngày sinh");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ");
				}

			}

		});

		this.setLayout(new BorderLayout());
		this.add(jPanel_tren, BorderLayout.NORTH);
		this.add(jPanel_giua, BorderLayout.CENTER);
		this.add(jPanel_duoi, BorderLayout.SOUTH);

		this.setVisible(true);
	}

}
