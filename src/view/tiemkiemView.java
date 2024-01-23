package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

public class tiemkiemView extends JFrame {
	public tiemkiemView() {
		this.init();
	}

	private void init() {
		this.setTitle("Tìm kiếm ");
		this.setSize(1200, 500);
		this.setLocationRelativeTo(null);
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel jPanel_tren = new JPanel();
		JPanel jPanel_giua = new JPanel();

		JLabel jLabel_timkiem = new JLabel("(ID hoặc Tên)");
		jLabel_timkiem.setForeground(Color.blue);
		JTextField jTextFiled_o_timkiem = new JTextField(30);
		JButton jButton_timkiem = new JButton("Tìm kiếm");
		JButton jButton_trolai = new JButton("Trở về");

		jPanel_tren.setLayout(new FlowLayout());
		jPanel_tren.add(jLabel_timkiem);
		jPanel_tren.add(jTextFiled_o_timkiem);
		jPanel_tren.add(jButton_timkiem);
		jPanel_tren.add(jButton_trolai);

		JTable jTable_bang = new JTable();
		JScrollPane scp = new JScrollPane(jTable_bang);
		jButton_timkiem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					DefaultTableModel dtm = (DefaultTableModel) jTable_bang.getModel();
					dtm.setRowCount(0);

					Class.forName("com.mysql.jdbc.Driver");
					Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/danhsachhocvien", "root",
							"");
					Statement st = cn.createStatement();
					ResultSet rs = st
							.executeQuery("select * from quanlithuchi where ID = '" + jTextFiled_o_timkiem.getText()
									+ "' or `Họ và Tên`= '" + jTextFiled_o_timkiem.getText() + "'");

					java.sql.ResultSetMetaData rsmd = rs.getMetaData();
					DefaultTableModel dtmm = (DefaultTableModel) jTable_bang.getModel();

					int cols = rsmd.getColumnCount();
					String[] colName = new String[cols];
					for (int i = 0; i < cols; i++)
						colName[i] = rsmd.getColumnName(i + 1);

					dtmm.setColumnIdentifiers(colName);
					String id, hovaten, ngaysinh, diachi, hocphi, gioitinh, tinhTrangNopHocPhi;
					while (rs.next()) {
						id = rs.getString(1);
						hovaten = rs.getString(2);
						ngaysinh = rs.getString(3);
						diachi = rs.getString(4);
						hocphi = rs.getString(5);
						gioitinh = rs.getString(6);
						tinhTrangNopHocPhi = rs.getString(7);

						String[] row = { id, hovaten, ngaysinh, diachi, hocphi, gioitinh, tinhTrangNopHocPhi };
						dtmm.addRow(row);
						
						 int[] columnWidths = { 150, 200, 100, 300, 100, 80, 80 };
		                    for (int i = 0; i < cols; i++) {
		                        jTable_bang.getColumnModel().getColumn(i).setPreferredWidth(columnWidths[i]);
		                    }
					}
					st.close();
					cn.close();

				} catch (Exception e2) {
					e2.printStackTrace();

				}
			}

		});
		jButton_trolai.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				dispose();

			}

		});

		this.setLayout(new BorderLayout());

		this.add(jPanel_tren, BorderLayout.NORTH);
		this.add(scp, BorderLayout.CENTER);

		this.setVisible(true);

	}

}
