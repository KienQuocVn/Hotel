/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package View;

import DAO.LoaiDichVuDao;
import Model.LoaiDichVu;
import Utils.DialogHelper;
import Utils.Tableheader;

import java.awt.Color;
import java.awt.Component;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ngomi
 */
public class FormQuanLiLoaiDichVu extends javax.swing.JDialog {

    LoaiDichVuDao ldvDao = new LoaiDichVuDao();
    int index = 0;
    DefaultTableModel model;
    private StringBuilder error = new StringBuilder();
    /**
     * Creates new form QuanLiDichVuJDialog
     */
    public FormQuanLiLoaiDichVu(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.white);
        tblLoaiDV.setShowVerticalLines(false);
        tblLoaiDV.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Tableheader header = new Tableheader(value + "");
                return header;
            }
        });
        init();
    }

    public void init() {
        fillTable();
        setStatus(true);
    }

    public void fillTable() {
        model = (DefaultTableModel) tblLoaiDV.getModel();
        model.setRowCount(0);
        try {
            List<LoaiDichVu> list = (List<Model.LoaiDichVu>) ldvDao.selectAll();
            for (LoaiDichVu ldv : list) {
                Object rows[] = {
                    ldv.getMaLoaiDichVu(),
                    ldv.getTenLoaiDichVu()
                };
                model.addRow(rows);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setModel(LoaiDichVu ldv) {
        txtMaLoai.setText(String.valueOf(ldv.getMaLoaiDichVu()));
        txtTenLoai.setText(ldv.getTenLoaiDichVu());
    }

    public LoaiDichVu getModel() {
        LoaiDichVu ldv = new LoaiDichVu();
        ldv.setMaLoaiDichVu(Integer.parseInt(txtMaLoai.getText()));
        ldv.setTenLoaiDichVu(txtTenLoai.getText());
        return ldv;
    }

    public void setStatus(boolean bl) {
        btnThem.setEnabled(bl);
        btnSua.setEnabled(!bl);
        btnXoa.setEnabled(!bl);
    }

    public void edit() {
        try {
            int maKH = (int) tblLoaiDV.getValueAt(index, 0);
            LoaiDichVu tn = ldvDao.selectbyID(maKH);
            if (tn != null) {
                setModel(tn);
                setStatus(false);
                tab.setSelectedIndex(0);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void clear() {
        setModel(new LoaiDichVu());
        setStatus(true);
    }

    public boolean validates() {

        if (txtMaLoai.getText().trim().isEmpty()) {
            error.append("Chưa nhập mã loại dịch vụ\n");
            txtMaLoai.requestFocus();
            return false;
        }

        try {
            int maLoai = Integer.parseInt(txtMaLoai.getText());
        } catch (Exception e) {
            DialogHelper.alert(this, "mã loại dịch vụ không hợp lệ");
            txtMaLoai.requestFocus();
            return false;
        }

        if (txtTenLoai.getText().trim().isEmpty()) {
            error.append("Chưa nhập tên loại dịch vụ\n");
            txtTenLoai.requestFocus();
            return false;
        }

        return true;
    }

    public void insert() {
        if (validates()) {
            int maloai = Integer.valueOf(txtMaLoai.getText());
            LoaiDichVu cd = ldvDao.selectbyID(maloai);
            if (cd != null) {
                DialogHelper.alert(this, "Mã loại dịch vụ đã tồn tại");
            } else {
                LoaiDichVu model = getModel();
                try {
                    ldvDao.insert(model);
                    DialogHelper.alert(this, "Thêm dịch vụ thành công");
                    fillTable();
                } catch (Exception e) {
                    DialogHelper.alert(this, "Thêm dịch vụ that bai");
                }
            }
        }
    }

    public void update() {
        if (validates()) {
            LoaiDichVu ldv = getModel();
            try {
                ldvDao.update(ldv);
                DialogHelper.alert(this, "Cập nhật dịch vụ Thành công");
                fillTable();
            } catch (Exception e) {
                DialogHelper.alert(this, "Cập nhật dịch vụ thát bại");
            }
        }
    }

    public void delete() {
        if (DialogHelper.confirm(this, "Bạn có chắc chắn muốn xóa dịch vụ này")) {
            int maLoai = Integer.valueOf(txtMaLoai.getText());
            try {
                ldvDao.delete(maLoai);
                DialogHelper.alert(this, "Xóa dịch vụ thành công");
                fillTable();
                this.clear();
            } catch (Exception e) {
                DialogHelper.alert(this, "Xóa dịch vụ thất bại");
                throw new RuntimeException(e);

            }
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        tab = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        txtMaLoai = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtTenLoai = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblLoaiDV = new javax.swing.JTable();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Loại Dịch Vụ");

        tab.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setText("Mã Loại");

        jLabel4.setText("Tên Loại");

        btnThem.setBackground(new java.awt.Color(242, 242, 242));
        btnThem.setText("Thêm");
        btnThem.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(242, 242, 242)));
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(242, 242, 242));
        btnXoa.setText("Xóa");
        btnXoa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(242, 242, 242)));
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(242, 242, 242));
        btnSua.setText("Sữa");
        btnSua.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(242, 242, 242)));
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnMoi.setBackground(new java.awt.Color(242, 242, 242));
        btnMoi.setText("Mới");
        btnMoi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(242, 242, 242)));
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMaLoai, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                    .addComponent(txtTenLoai))
                .addGap(43, 43, 43))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 22, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtMaLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtTenLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        tab.addTab("Chi tiết", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        tblLoaiDV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Mã loại dịch vụ", "Tên dịch vụ"
            }
        ));
        tblLoaiDV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblLoaiDVMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tblLoaiDV);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        tab.addTab("Thông tin", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tab)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tab, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblLoaiDVMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLoaiDVMousePressed
        if (index >= 0) {
            index = tblLoaiDV.getSelectedRow();
            edit();
        }
    }//GEN-LAST:event_tblLoaiDVMousePressed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        clear();
    }//GEN-LAST:event_btnMoiActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        insert();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        delete();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        update();
    }//GEN-LAST:event_btnSuaActionPerformed

 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTabbedPane tab;
    private javax.swing.JTable tblLoaiDV;
    private javax.swing.JTextField txtMaLoai;
    private javax.swing.JTextField txtTenLoai;
    // End of variables declaration//GEN-END:variables
}
