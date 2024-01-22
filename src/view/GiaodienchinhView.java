package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.IconView;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

public class GiaodienchinhView extends JFrame {

	public GiaodienchinhView() {

		this.init();
	}

	static String ID;
	static String hovaten;
	static String ngaysinh;
	static String diachi;
	static String hocphidathu;
	static String gioitinh;
	static String tinhtrangnophocphi;

	private void init() {
		this.setTitle("QUẢN LÍ THU HỌC PHÍ CỦA TRUNG TÂM ĐÀO TẠO IELTS TM");
		this.setSize(1200, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		URL url_icon_them = GiaodienchinhView.class.getResource("tieude.png");
		Image img = Toolkit.getDefaultToolkit().createImage(url_icon_them);
		this.setIconImage(img);
		
		Font font_tieude = new Font("Arial", Font.BOLD, 50);
		Font font_nut = new Font("Arial", Font.BOLD, 15);

		JPanel jPanel_tendanhsach = new JPanel();

		JPanel jPanel_nuttxs = new JPanel();

		JLabel jLabel_tendanhsach = new JLabel("DANH SÁCH THU HỌC PHÍ (khóa 2023) ");
		jLabel_tendanhsach.setForeground(Color.blue);
		jLabel_tendanhsach.setFont(font_tieude);
		JLabel jLbel_bang = new JLabel();

		JButton jButton_Them = new JButton("Thêm");
		jButton_Them.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(GiaodienchinhView.class.getResource("icon_them.png"))));
		jLabel_tendanhsach.setBackground(Color.WHITE);
		jButton_Them.setFont(font_nut);
		JButton jButton_xoa = new JButton("Xoá");
		jButton_xoa.setBackground(Color.WHITE);
		jButton_xoa.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(GiaodienchinhView.class.getResource("icon_xoa.png"))));
		jButton_xoa.setFont(font_nut);
		JButton jButton_sua = new JButton("Sửa");
		jButton_sua.setBackground(Color.WHITE);
		jButton_sua.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(GiaodienchinhView.class.getResource("sua.png"))));
		jButton_sua.setFont(font_nut);
		JButton jButton_timkiem = new JButton("Tìm kiếm HV");
		jButton_timkiem.setBackground(Color.WHITE);
		jButton_timkiem.setFont(font_nut);
		jButton_timkiem.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(GiaodienchinhView.class.getResource("icon_timkiem.png"))));

		JButton jButton_lammoi = new JButton("Làm mới");
		jButton_lammoi.setBackground(Color.WHITE);
		jButton_lammoi.setFont(font_nut);
		jButton_lammoi.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(GiaodienchinhView.class.getResource("icon_lamlai.png"))));

		JLabel jLabel_khoang_trang = new JLabel("                ");

		jPanel_nuttxs.setLayout(new FlowLayout());

		jPanel_tendanhsach.add(jLabel_tendanhsach);

		jPanel_nuttxs.add(jButton_lammoi);
		jPanel_nuttxs.add(jButton_sua);
		jPanel_nuttxs.add(jButton_Them);
		jPanel_nuttxs.add(jButton_xoa);

		jPanel_nuttxs.add(jButton_timkiem);

		JTable jTable = new JTable();
		JScrollPane scb = new JScrollPane();

		scb.setViewportView(jTable);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/danhsachhocvien", "root", "");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from quanlithuchi");
			java.sql.ResultSetMetaData rsmt = rs.getMetaData();
			DefaultTableModel dtm = (DefaultTableModel) jTable.getModel();

			int cols = rsmt.getColumnCount();
			String[] colName = new String[cols];
			for (int i = 0; i < cols; i++) {
				colName[i] = rsmt.getColumnName(i + 1);

			}
			dtm.setColumnIdentifiers(colName);
			String id, hovaten, ngaysinh, diachi, hocphi, gioitinh, tinhtrangnophocphi;
			while (rs.next()) {
				id = rs.getString(1);
				hovaten = rs.getString(2);
				ngaysinh = rs.getString(3);
				diachi = rs.getString(4);
				hocphi = rs.getString(5);
				gioitinh = rs.getString(6);
				tinhtrangnophocphi = rs.getString(7);
				String[] row = { id, hovaten, ngaysinh, diachi, hocphi, gioitinh, tinhtrangnophocphi };
				dtm.addRow(row);
			}
			int[] columnWidths = { 10, 150, 100, 300, 100, 80, 10 };
			for (int i = 0; i < cols; i++) {
				jTable.getColumnModel().getColumn(i).setPreferredWidth(columnWidths[i]);
			}

			st.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		jButton_Them.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ThemView themview = new ThemView();
			}
		});

		jButton_timkiem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				tiemkiemView tiemkiemview = new tiemkiemView();

			}

		});
		jButton_lammoi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) jTable.getModel();
				model.setRowCount(0);
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/danhsachhocvien", "root",
							"");
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery("select * from quanlithuchi");
					java.sql.ResultSetMetaData rsmt = rs.getMetaData();
					DefaultTableModel dtm = (DefaultTableModel) jTable.getModel();

					int cols = rsmt.getColumnCount();
					String[] colName = new String[cols];
					for (int i = 0; i < cols; i++) {
						colName[i] = rsmt.getColumnName(i + 1);

					}
					dtm.setColumnIdentifiers(colName);
					String id, hovaten, ngaysinh, diachi, hocphi, gioitinh, tinhtrangnophocphi;
					while (rs.next()) {
						id = rs.getString(1);
						hovaten = rs.getString(2);
						ngaysinh = rs.getString(3);
						diachi = rs.getString(4);
						hocphi = rs.getString(5);
						gioitinh = rs.getString(6);
						tinhtrangnophocphi = rs.getString(7);
						String[] row = { id, hovaten, ngaysinh, diachi, hocphi, gioitinh, tinhtrangnophocphi };
						dtm.addRow(row);
					}
					int[] columnWidths = { 10, 150, 100, 300, 100, 80, 10 };
					for (int i = 0; i < cols; i++) {
						jTable.getColumnModel().getColumn(i).setPreferredWidth(columnWidths[i]);
					}
					st.close();
					con.close();

				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		jTable.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				int seclectRow = jTable.getSelectedRow();

				ID = jTable.getValueAt(seclectRow, 0).toString();
				hovaten = jTable.getValueAt(seclectRow, 1).toString();
				ngaysinh = jTable.getValueAt(seclectRow, 2).toString();

				diachi = jTable.getValueAt(seclectRow, 3).toString();

				hocphidathu = jTable.getValueAt(seclectRow, 4).toString();
				gioitinh = jTable.getValueAt(seclectRow, 5).toString();
				tinhtrangnophocphi = jTable.getValueAt(seclectRow, 6).toString();

			}
		});
		jButton_xoa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String id = ID;
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/danhsachhocvien", "root",
							"");
					Statement st = cn.createStatement();
					JLabel jLabel_xac_nhan_xoa = new JLabel("Xác nhận xóa ?");
					jLabel_xac_nhan_xoa.setForeground(Color.red);
					int ret = JOptionPane.showConfirmDialog(null, jLabel_xac_nhan_xoa, "Xóa",
							JOptionPane.YES_NO_OPTION);
					if (ret == JOptionPane.YES_OPTION) {
						st.executeUpdate("delete from quanlithuchi where ID = '" + id + "'");
						JLabel jLabel_xoa_thanh_cong = new JLabel("Xóa thành công");
						jLabel_xoa_thanh_cong.setForeground(Color.green);
						JOptionPane.showMessageDialog(null, jLabel_xoa_thanh_cong);

					}

				} catch (Exception e2) {
					e2.printStackTrace();
				}

			}
		});
		jButton_sua.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				suaView suaview = new suaView();
			}

		});

		this.setLayout(new BorderLayout());

		this.add(jPanel_tendanhsach, BorderLayout.NORTH);
		this.add(scb, BorderLayout.CENTER);
		this.add(jPanel_nuttxs, BorderLayout.SOUTH);
		jPanel_nuttxs.setBackground(Color.WHITE);
		this.setVisible(true);

	}

}
