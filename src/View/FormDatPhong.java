/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Dao.DaoDichVu;
import Dao.DaoHoaDonPhong;
import Dao.DaoKhachHang;
import Dao.DaoPhong;
import Extendent.ValidateClass;
import Model.DichVu;
import Model.HoaDonPhong;
import Model.KhachHang;
import Model.NhanVien;
import Model.Phong;
import Model.Tuple;
import Utils.DateHelper;
import Utils.DialogHelper;
import Utils.Log4j;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author User
 */
public class FormDatPhong extends javax.swing.JFrame {

    DaoDichVu daoDichVu;
    DaoKhachHang daoKhachHang;
    DaoHoaDonPhong daoHoaDonPhong;
    DaoPhong daoPhong;
    Phong Phong;
    NhanVien nhanvien;
    public List<Tuple<String, Integer, Date>> listOfTuple = new ArrayList<>();

    private String column1[] = {"Tầng", "Số Phòng", "Loại Phòng", "Giá Ngày", "Giá Giờ", "Mô Tả"};
    public DefaultTableModel tblModel1 = new DefaultTableModel(column1, 0);

    private String column2[] = {"Tầng", "Số Phòng", "Loại Dịch Vụ", "Mã Dịch Vụ", "Phí Dịch Vụ", "Số Lượng", "Thời Gian Gọi"};
    public DefaultTableModel tblModel2 = new DefaultTableModel(column2, 0);

    private String column3[] = {"Tầng", "Số Phòng", "Loại Phòng", "Giá"};
    public DefaultTableModel tblModel3 = new DefaultTableModel(column3, 0);

    public FormDatPhong(Phong phong, NhanVien NhanVien) {
        initComponents();
        setLocationRelativeTo(null);
        PropertyConfigurator.configure("D:\\HK4\\DUAN_1\\Duan1\\KSF\\src\\Log\\log4j.properties");
        lblSoPhong.setText("PHÒNG " + phong.getMaPhong());
        Phong = phong;
        nhanvien = NhanVien;
        table();
        loadcbo();
        dataTable(Phong);
    }

    public void table() {
        tblThongtinphong.setModel(tblModel1);
        tblCTphongDV.setModel(tblModel2);
        tblChiTietPhong.setModel(tblModel3);
    }

    public void dataTable(Phong phong) {
        try {
            DecimalFormat formatter = new DecimalFormat("###,###,###");
            tblModel1.setRowCount(0);
            Vector data = new Vector();
            data.add(phong.getMaTang());
            data.add(phong.getMaPhong());
            data.add(phong.getTenPhong());
            data.add(formatter.format(phong.getGia()) + " đ");
            data.add(formatter.format((phong.getGia() * 5) / 100) + " đ");
            data.add(phong.getMoTa());
            tblModel1.addRow(data);

            tblModel3.setRowCount(0);
            Vector data2 = new Vector();
            data2.add(phong.getMaTang());
            data2.add(phong.getMaPhong());
            data2.add(phong.getTenPhong());
            data2.add(formatter.format(phong.getGia()) + " đ");
            tblModel3.addRow(data2);

        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }
    }

    public void loadcbo() {
        List<DichVu> list = new ArrayList<>();
        daoDichVu = new DaoDichVu();
        daoKhachHang = new DaoKhachHang();
        daoHoaDonPhong = new DaoHoaDonPhong(this);
        daoPhong = new DaoPhong();
        daoHoaDonPhong.fillHoaDon(Phong.getMaPhong());
        list = daoDichVu.selectAll();
        cboDV.removeAllItems();
        cboDV.addItem("Dịch Vụ");
        for (int i = 0; i < list.size(); i++) {

            cboDV.addItem(list.get(i).getTenDichVu());
        }
    }

    public void fillDichVu() {
        try {
            int count = 1;
            DecimalFormat formatter = new DecimalFormat("###,###,###");
            DichVu dv = new DichVu();
            int index = cboDV.getSelectedIndex();
            dv = daoDichVu.selectbyName(cboDV.getItemAt(index));
            Vector data = new Vector();
            data.add(Phong.getMaTang());
            data.add(Phong.getMaPhong());
            data.add(dv.getTenDichVu());
            data.add(dv.getMaDichVu());
            data.add(formatter.format(dv.getDonGia()) + " đ");
            data.add(1);
            data.add(DateHelper.now());

            Tuple a = new Tuple();
            a.setMaDV(dv.getMaDichVu());
            a.setSoLuong(count);
            a.setThoiGianGoi(DateHelper.now());

            if (listOfTuple.size() == 0) {
                listOfTuple.add(a);
            } else {
                listOfTuple.add(a);
                for (int i = 0; i < listOfTuple.size() - 1; i++) {
                    for (int j = i + 1; j < listOfTuple.size(); j++) {
                        if (listOfTuple.get(i).getMaDV().equals(listOfTuple.get(j).getMaDV())) {
                            count++;
                            listOfTuple.get(i).setSoLuong(count);
                            listOfTuple.remove(listOfTuple.size() - 1);
                        }
                    }
                }
            }
            tblModel2.addRow(data);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel3 = new javax.swing.JPanel();
        lblSoPhong = new javax.swing.JLabel();
        btnHuy = new Utils.Button();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtHoTenKH = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtCCCD = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        jdateNgaySinh = new com.toedter.calendar.JDateChooser();
        scrollPaneWin114 = new Utils.ScrollPaneWin11();
        txtDiaChi = new javax.swing.JTextArea();
        jLabel17 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        rdoGio = new javax.swing.JRadioButton();
        rdoNgay = new javax.swing.JRadioButton();
        txtPhuThu = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtGiamGia = new javax.swing.JTextField();
        txtTraTruoc = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jdateNgayNhanPhong = new com.toedter.calendar.JDateChooser();
        jLabel14 = new javax.swing.JLabel();
        jdateNgayTraPhong = new com.toedter.calendar.JDateChooser();
        jLabel15 = new javax.swing.JLabel();
        cboThanhToan = new javax.swing.JComboBox<>();
        scrollPaneWin111 = new Utils.ScrollPaneWin11();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblChiTietPhong = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        scrollPaneWin112 = new Utils.ScrollPaneWin11();
        tblCTphongDV = new javax.swing.JTable();
        button2 = new Utils.Button();
        btnXoaDV = new Utils.Button();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        scrollPaneWin113 = new Utils.ScrollPaneWin11();
        tblThongtinphong = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        cboDV = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        btnThem = new Utils.Button();
        jdateThoiGianDV = new com.toedter.calendar.JDateChooser();
        btnTra = new Utils.Button();
        btnDat = new Utils.Button();
        btnSua = new Utils.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Dat Phong");

        jPanel3.setBackground(new java.awt.Color(0, 102, 102));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblSoPhong.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblSoPhong.setForeground(new java.awt.Color(255, 255, 255));
        lblSoPhong.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSoPhong.setText("PHÒNG 101");

        btnHuy.setBackground(new java.awt.Color(255, 51, 51));
        btnHuy.setForeground(new java.awt.Color(51, 51, 51));
        btnHuy.setText("HỦY");
        btnHuy.setColorClick(new java.awt.Color(255, 153, 153));
        btnHuy.setColorOver(new java.awt.Color(255, 153, 153));
        btnHuy.setRippleColor(new java.awt.Color(102, 0, 102));

        jPanel1.setBackground(new java.awt.Color(255, 255, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông Tin Khách Hàng"));
        jPanel1.setToolTipText("");

        jLabel1.setText("Tên Khách Hàng:");

        jLabel2.setText("Số CCCD/CMND:");

        jLabel3.setText("SĐT:");

        jLabel7.setText("Giới Tính:");

        buttonGroup1.add(rdoNam);
        rdoNam.setSelected(true);
        rdoNam.setText("Nam");
        rdoNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoNamActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoNu);
        rdoNu.setText("Nữ");
        rdoNu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoNuActionPerformed(evt);
            }
        });

        jLabel8.setText("Ngày Sinh:");

        jdateNgaySinh.setDateFormatString("yyyy-MM-dd");

        txtDiaChi.setColumns(20);
        txtDiaChi.setRows(5);
        scrollPaneWin114.setViewportView(txtDiaChi);

        jLabel17.setText("Địa Chỉ:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(scrollPaneWin114, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtHoTenKH))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jdateNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(rdoNam)
                                        .addGap(18, 18, 18)
                                        .addComponent(rdoNu)
                                        .addGap(0, 128, Short.MAX_VALUE))
                                    .addComponent(txtSDT))))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jdateNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txtHoTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(rdoNam)
                            .addComponent(rdoNu))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(4, 4, 4)))
                .addComponent(scrollPaneWin114, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Chi Tiết Thuê Phòng"));

        jLabel9.setText("Hình Thức Thuê:");

        buttonGroup2.add(rdoGio);
        rdoGio.setSelected(true);
        rdoGio.setText("Giờ");
        rdoGio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoGioActionPerformed(evt);
            }
        });

        buttonGroup2.add(rdoNgay);
        rdoNgay.setText("Ngày");
        rdoNgay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoNgayActionPerformed(evt);
            }
        });

        txtPhuThu.setText("0");
        txtPhuThu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPhuThuActionPerformed(evt);
            }
        });

        jLabel10.setText("Phụ Thu:");

        jLabel11.setText("Giảm Giá:");

        jLabel12.setText("Trả Trước:");

        txtGiamGia.setText("0");
        txtGiamGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiamGiaActionPerformed(evt);
            }
        });

        txtTraTruoc.setText("0");
        txtTraTruoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTraTruocActionPerformed(evt);
            }
        });

        jLabel13.setText("Ngày Nhận Phòng:");

        jdateNgayNhanPhong.setDateFormatString("yyyy-MM-dd hh:mm:ss");
        jdateNgayNhanPhong.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jdateNgayNhanPhongAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jLabel14.setText("Ngày Trả Phòng:");

        jdateNgayTraPhong.setDateFormatString("yyyy-MM-dd hh:mm:ss");
        jdateNgayTraPhong.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jdateNgayTraPhongAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jLabel15.setText("Thanh Toán:");

        cboThanhToan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tiền Mặt", "Thẻ", "Chuyển Khoản" }));

        tblChiTietPhong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblChiTietPhong);

        scrollPaneWin111.setViewportView(jScrollPane1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPaneWin111, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel10)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cboThanhToan, 0, 129, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTraTruoc, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(txtPhuThu, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel15)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(rdoNgay)
                                .addGap(29, 29, 29)
                                .addComponent(rdoGio))
                            .addComponent(jdateNgayTraPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jdateNgayNhanPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(rdoNgay)
                    .addComponent(rdoGio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jdateNgayNhanPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jdateNgayTraPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtPhuThu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtTraTruoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(scrollPaneWin111, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(102, 255, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Chi Tiết Phòng Và Dịch Vụ"));

        tblCTphongDV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        scrollPaneWin112.setViewportView(tblCTphongDV);

        button2.setText("Xóa Dịch Vụ");

        btnXoaDV.setBackground(new java.awt.Color(153, 153, 255));
        btnXoaDV.setForeground(new java.awt.Color(51, 51, 51));
        btnXoaDV.setText("Xóa");
        btnXoaDV.setColorClick(new java.awt.Color(153, 153, 255));
        btnXoaDV.setColorOver(new java.awt.Color(153, 153, 255));
        btnXoaDV.setRippleColor(new java.awt.Color(102, 0, 102));
        btnXoaDV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaDVActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPaneWin112, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(button2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXoaDV, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(scrollPaneWin112, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnXoaDV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(204, 204, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Thêm Dịch Vụ Cho Khách Hàng"));

        jPanel6.setBackground(new java.awt.Color(204, 0, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông Tin Phòng Được Chọn"));

        tblThongtinphong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        scrollPaneWin113.setViewportView(tblThongtinphong);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPaneWin113, javax.swing.GroupLayout.DEFAULT_SIZE, 698, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(scrollPaneWin113, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel7.setBackground(new java.awt.Color(0, 204, 204));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Chọn Dịch Vụ"));

        cboDV.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel16.setText("Thời Gian");

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        jdateThoiGianDV.setDateFormatString("hh.mm MM-dd-yyyy");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(cboDV, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel16)
                    .addComponent(jdateThoiGianDV, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboDV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jdateThoiGianDV, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        btnTra.setBackground(new java.awt.Color(255, 255, 153));
        btnTra.setForeground(new java.awt.Color(51, 51, 51));
        btnTra.setText("TRẢ");
        btnTra.setColorClick(new java.awt.Color(255, 255, 153));
        btnTra.setColorOver(new java.awt.Color(255, 255, 204));
        btnTra.setRippleColor(new java.awt.Color(102, 0, 102));
        btnTra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTraActionPerformed(evt);
            }
        });

        btnDat.setBackground(new java.awt.Color(153, 255, 153));
        btnDat.setForeground(new java.awt.Color(51, 51, 51));
        btnDat.setText("ĐẶT");
        btnDat.setRippleColor(new java.awt.Color(102, 0, 102));
        btnDat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDatActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(102, 255, 204));
        btnSua.setForeground(new java.awt.Color(51, 51, 51));
        btnSua.setText("SỬA");
        btnSua.setBorderColor(new java.awt.Color(102, 255, 204));
        btnSua.setColorClick(new java.awt.Color(153, 255, 204));
        btnSua.setColorOver(new java.awt.Color(51, 255, 204));
        btnSua.setRippleColor(new java.awt.Color(102, 0, 102));
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnDat, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTra, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(36, Short.MAX_VALUE))
            .addComponent(lblSoPhong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(lblSoPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rdoNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoNamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoNamActionPerformed

    private void rdoNuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoNuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoNuActionPerformed

    private void rdoGioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoGioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoGioActionPerformed

    private void rdoNgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoNgayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoNgayActionPerformed

    private void txtPhuThuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPhuThuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPhuThuActionPerformed

    private void txtGiamGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiamGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiamGiaActionPerformed

    private void txtTraTruocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTraTruocActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTraTruocActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if (cboDV.getSelectedIndex() == 0) {
            DialogHelper.alert(this, "Chọn dịch vụ");
        } else {
            fillDichVu();
        }
        jdateThoiGianDV.setDate(DateHelper.now());
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaDVActionPerformed
        int row = tblCTphongDV.getSelectedRow();
        if (row < 0) {
            DialogHelper.alert(this, "chưa Chọn Dòng Nào");
            return;
        } else {
            int count;
            listOfTuple.remove(row);
            String MaDV = (String) tblModel2.getValueAt(row, 3);
            for (int i = 0; i < listOfTuple.size() - 1; i++) {
                for (int j = i + 1; j < listOfTuple.size(); j++) {
                    if (listOfTuple.get(i).getMaDV().equals(MaDV) && listOfTuple.get(i).SoLuong > 1) {
                        count = listOfTuple.get(i).SoLuong;
                        count--;
                        listOfTuple.get(i).setSoLuong(count);
                    }

                }
            }

        }

        tblModel2.removeRow(row);


    }//GEN-LAST:event_btnXoaDVActionPerformed

    private void btnDatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDatActionPerformed
        try {
            if (check()) {
                KhachHang khachHang = new KhachHang();
                khachHang.setHoTen(txtHoTenKH.getText());
                khachHang.setNgaySinh(jdateNgaySinh.getDate());
                khachHang.setSoCCCD(txtCCCD.getText());
                khachHang.setSoDT(txtSDT.getText());
                khachHang.setGioiTinh(rdoNam.isSelected() ? 1 : 0);
                khachHang.setDiaChi(txtDiaChi.getText());

                HoaDonPhong hoaDonPhong = new HoaDonPhong();
                hoaDonPhong.setMaNV(nhanvien.getMaNV());
                hoaDonPhong.setMaPhong(Phong.getMaPhong());
                hoaDonPhong.setNgayNhanPhong(jdateNgayNhanPhong.getDate());
                hoaDonPhong.setNgayTraPhong(jdateNgayTraPhong.getDate());
                hoaDonPhong.setNgayXuatHoaDon(DateHelper.now());
                hoaDonPhong.setHinhThucThue(rdoNgay.isSelected() ? rdoNgay.getText() : rdoGio.getText());
                hoaDonPhong.setPhuThu(Integer.parseInt(txtPhuThu.getText()));
                hoaDonPhong.setGiamGia(Integer.parseInt(txtGiamGia.getText()));
                int index = cboThanhToan.getSelectedIndex();
                hoaDonPhong.setThanhToan(cboThanhToan.getItemAt(index));
                hoaDonPhong.setTraTruoc(Float.parseFloat(txtTraTruoc.getText()));
                hoaDonPhong.setGhiChu("Chưa Thanh Toán");

                DialogHelper.alert(this, "Đặt Phòng Thành Công");
                daoKhachHang.insert(khachHang);
                daoHoaDonPhong.insert(hoaDonPhong);
                
                if (listOfTuple.size() != 0) {
                    for (int i = 0; i < listOfTuple.size(); i++) {
                        daoDichVu.insertpdv(listOfTuple.get(i).getMaDV(), listOfTuple.get(i).SoLuong, listOfTuple.get(i).ThoiGianGoi);
                    }
                }
                daoPhong.UpdateTrangThai("Đã Đặt", Phong.getMaPhong());
                daoHoaDonPhong.setGhiChu("Chưa Thanh Toán", Phong.getMaPhong());
               
                Log4j.logger.info("Nhân Viên: " + nhanvien.getMaNV() + " Đã Đặt Phòng" + Phong.getMaPhong() + " Thành Công");

            }

        } catch (Exception e) {
            System.out.println(e);
        }


    }//GEN-LAST:event_btnDatActionPerformed

    public boolean check() {
        if (txtHoTenKH.getText().equals("")) {
            DialogHelper.alert(this, "Nhập Tên Khách Hàng");
            return false;
        }

        if (jdateNgaySinh.getDate().equals("")) {
            DialogHelper.alert(this, "Nhập Ngày Sinh ");
            return false;
        } else {
            try {
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(jdateNgaySinh.getDate());
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

                if (calendar.get(Calendar.YEAR) > 2005) {
                    DialogHelper.alert(this, "Nhập Ngày Sinh Không Hợp Lệ ");
                    return false;
                }
            } catch (Exception e) {
                System.out.println(e);
                DialogHelper.alert(this, "Nhập Ngày Sinh Không Hợp Lệ ");
                return false;
            }
        }

        if (txtCCCD.getText().equals("")) {
            DialogHelper.alert(this, "Nhập CCCD Khách Hàng");
            return false;
        } else {
            try {
                long SoCC = Long.parseLong(txtCCCD.getText());
                if (SoCC < 0) {
                    DialogHelper.alert(this, "Số căn cước không đúng định dạng\n");
                } else if (txtCCCD.getText().length() != 12) {
                    DialogHelper.alert(this, "Số căn cước phải đúng 12 số\n");
                }
            } catch (Exception e) {
                DialogHelper.alert(this, "Số căn cước không đúng định dạng\n");
            }
        }

        if (txtSDT.getText().equals("")) {
            DialogHelper.alert(this, "Nhập SDT Khách Hàng");
            return false;
        } else {
            if (!ValidateClass.isValidPhoneNumber(txtSDT.getText())) {
                DialogHelper.alert(this, "SĐT không đúng định dạng");
            }
        }
        if (txtDiaChi.getText().equals("")) {
            DialogHelper.alert(this, "Nhập Địa Chỉ Khách Hàng");
            return false;
        }

        if (!rdoNam.isSelected() && !rdoNu.isSelected()) {
            DialogHelper.alert(this, "Chọn Giới Tính Khách Hàng");
            return false;
        }

        if (!rdoNgay.isSelected() && !rdoGio.isSelected()) {
            DialogHelper.alert(this, "Chọn Hình Thức Thanh Toán");
            return false;
        }

        if (jdateNgayNhanPhong.getDate().equals("")) {
            DialogHelper.alert(this, "Chọn Ngày Nhận Phòng ");
            return false;
        } else {
            try {
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(jdateNgayNhanPhong.getDate());
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

                if (calendar.get(Calendar.YEAR) < 2023) {
                    DialogHelper.alert(this, "Nhập Thời Gian Không Hợp Lệ ");
                    return false;
                }
            } catch (Exception e) {
                DialogHelper.alert(this, "Nhập Thời Gian Không Hợp Lệ ");
                return false;
            }
        }

        if (jdateNgayTraPhong.getDate().equals("")) {
            DialogHelper.alert(this, "Chọn Ngày Trả Phòng ");
            return false;
        } else {
            try {
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(jdateNgayTraPhong.getDate());
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

                if (calendar.get(Calendar.YEAR) < 2023) {
                    DialogHelper.alert(this, "Nhập Thời Gian Không Hợp Lệ ");
                    return false;
                } 
            } catch (Exception e) {
                DialogHelper.alert(this, "Nhập Thời Gian Không Hợp Lệ 2 ");
                return false;
            }
        }

        if (!txtPhuThu.getText().equals("")) {
            try {
                int phuthu = Integer.parseInt(txtPhuThu.getText());

            } catch (Exception e) {
                DialogHelper.alert(this, "Nhập phụ thu Không Hợp Lệ ");
                return false;
            }
        }

        if (!txtGiamGia.getText().equals("")) {
            try {
                int phuthu = Integer.parseInt(txtGiamGia.getText());

            } catch (Exception e) {
                DialogHelper.alert(this, "Nhập giảm giá Không Hợp Lệ ");
                return false;
            }
        }

        if (!txtTraTruoc.getText().equals("")) {
            try {
                int phuthu = Integer.parseInt(txtTraTruoc.getText());

            } catch (Exception e) {
                DialogHelper.alert(this, "Nhập trả trước Không Hợp Lệ ");
                return false;
            }
        }

        return true;
    }


    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        if (check()) {
            daoKhachHang.UpdateKH(Phong.getMaPhong(), txtHoTenKH.getText(), rdoNam.isSelected() ? 1 : 0, txtSDT.getText(), txtCCCD.getText(), jdateNgaySinh.getDate(), txtDiaChi.getText());
            daoHoaDonPhong.UpdateHD(Phong.getMaPhong(), jdateNgayNhanPhong.getDate(), jdateNgayTraPhong.getDate(), rdoGio.isSelected() ? "Giờ" : "Ngày", Integer.parseInt(txtPhuThu.getText()), Integer.parseInt(txtGiamGia.getText()), (String) cboThanhToan.getSelectedItem(), Integer.parseInt(txtTraTruoc.getText()));
            daoDichVu.Deltedv(Phong.getMaPhong());

            if (listOfTuple.size() != 0) {
                for (int i = 0; i < listOfTuple.size(); i++) {

                    daoDichVu.Indv(Phong.getMaPhong(), listOfTuple.get(i).getMaDV(), listOfTuple.get(i).SoLuong, listOfTuple.get(i).ThoiGianGoi);
                }
            }
            Log4j.logger.info("Nhân Viên: " + nhanvien.getMaNV() + " Đã Sửa Phòng " + Phong.getMaPhong() + " Thành Công");
            DialogHelper.alert(this, "Sửa Phòng Thành Công");
            this.dispose();
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnTraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTraActionPerformed
//        daoPhong.UpdateTrangThai("Trống", Phong.getMaPhong());
        int option = JOptionPane.showConfirmDialog(this, "Bạn có muốn trả phòng ??",
                "Warning", JOptionPane.WARNING_MESSAGE);
        if (option == JOptionPane.YES_OPTION) {
            FormHoaDon hoaDonJF = new FormHoaDon(Phong);
            hoaDonJF.setVisible(true);

            new Thread() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            if (hoaDonJF.isVisible()) {
                                while (true) {
                                    Thread.sleep(100);
                                    if (!hoaDonJF.isVisible()) {
                                        setFormVi();
                                        break;
                                    }
                                }
                            }

                        }

                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }.start();
        }

    }//GEN-LAST:event_btnTraActionPerformed

    private void jdateNgayNhanPhongAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jdateNgayNhanPhongAncestorAdded
      
    }//GEN-LAST:event_jdateNgayNhanPhongAncestorAdded

    private void jdateNgayTraPhongAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jdateNgayTraPhongAncestorAdded
      
    }//GEN-LAST:event_jdateNgayTraPhongAncestorAdded

    public void setFormVi() {
        this.setVisible(false);
    }

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Utils.Button btnDat;
    private Utils.Button btnHuy;
    private Utils.Button btnSua;
    private Utils.Button btnThem;
    private Utils.Button btnTra;
    private Utils.Button btnXoaDV;
    private Utils.Button button2;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    public javax.swing.JComboBox<String> cboDV;
    public javax.swing.JComboBox<String> cboThanhToan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    public com.toedter.calendar.JDateChooser jdateNgayNhanPhong;
    public com.toedter.calendar.JDateChooser jdateNgaySinh;
    public com.toedter.calendar.JDateChooser jdateNgayTraPhong;
    public com.toedter.calendar.JDateChooser jdateThoiGianDV;
    private javax.swing.JLabel lblSoPhong;
    public javax.swing.JRadioButton rdoGio;
    public javax.swing.JRadioButton rdoNam;
    public javax.swing.JRadioButton rdoNgay;
    public javax.swing.JRadioButton rdoNu;
    private Utils.ScrollPaneWin11 scrollPaneWin111;
    private Utils.ScrollPaneWin11 scrollPaneWin112;
    private Utils.ScrollPaneWin11 scrollPaneWin113;
    private Utils.ScrollPaneWin11 scrollPaneWin114;
    public javax.swing.JTable tblCTphongDV;
    public javax.swing.JTable tblChiTietPhong;
    public javax.swing.JTable tblThongtinphong;
    public javax.swing.JTextField txtCCCD;
    public javax.swing.JTextArea txtDiaChi;
    public javax.swing.JTextField txtGiamGia;
    public javax.swing.JTextField txtHoTenKH;
    public javax.swing.JTextField txtPhuThu;
    public javax.swing.JTextField txtSDT;
    public javax.swing.JTextField txtTraTruoc;
    // End of variables declaration//GEN-END:variables
}
