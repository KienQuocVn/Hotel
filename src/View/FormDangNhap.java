/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Dao.DaoNhanVien;

import Model.NhanVien;
import Utils.DialogHelper;
import Utils.Encryption;
import Utils.Log4j;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.prefs.Preferences;
import javax.swing.ImageIcon;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author Admin
 */
public class FormDangNhap extends javax.swing.JFrame {

    public DaoNhanVien daoNhanVien;
    public NhanVien nhanVien = null;
    public Preferences pref = Preferences.userRoot().node("Rememberme");
    String key = FormNhanVien.key;
    private Encryption encryption = new Encryption(key);

    public FormDangNhap() {
        initComponents();
        RM();
        Setting();
        PropertyConfigurator.configure("D:\\HK4\\DUAN_1\\Duan1\\KSF\\src\\Log\\log4j.properties");
        SetIconImage();
    }
    public void openHuongDan() {
        try {
            Desktop.getDesktop().browse(new java.io.File("D:/HK4/DUAN_1/Duan1/Web/index.html").toURI());
        } catch (Exception e) {
            DialogHelper.alert(this, "Không tìm thấy file hướng dẫn!");
        }
    }
    public void Setting() {
        setLocationRelativeTo(null);
        txtTenDN.setBackground(new Color(0, 0, 0, 1));
        txtPass.setBackground(new Color(0, 0, 0, 1));
        btnSetting.setBackground(new Color(0, 0, 0, 90));
        IconShow.setVisible(false);
        daoNhanVien = new DaoNhanVien();
    }

    public void RM() {
        String urs = null;
        urs = pref.get("Username", urs);
        txtTenDN.setText(urs);
        String pss = null;
        pss = pref.get("Password", pss);
        txtPass.setText(pss);
        if (txtTenDN.getText().length() > 0 && txtPass.getPassword().length > 0) {
            checkboxcustom1.setSelected(true);
        } else {
            checkboxcustom1.setSelected(false);
        }
    }

    //save username and pass
    public void saveacount(String username, String password) {
        if (username == null || password == null) {
            return;
        } else {
            pref.put("Username", username);
            pref.put("Password", password);
        }   
    }

    public final void checked(boolean remember) {
        if (remember) {
            String pass = new String(txtPass.getPassword());
            saveacount(txtTenDN.getText(), pass);
        } else {
            saveacount("", "");
        }
    }

    public void SetIconImage() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Icon/IconHotel2.png")));

    }

   public void login() {
        String manv = txtTenDN.getText();
        String matKhau = new String(txtPass.getPassword());
        try {
            NhanVien nhanVien = daoNhanVien.FindByMa(manv);

            if (nhanVien != null) {
                String matKhau2 = new String(encryption.decrypt(nhanVien.getMatKhau().trim()));
                if (matKhau.equals(matKhau2.trim())) {
                    DialogHelper.alert(this, "Đăng Nhập Thành Công!");
                    FormMain main = new FormMain(nhanVien);
                    main.setVisible(true);

                    String vaitro;
                    if (nhanVien.getVaiTro() == 1) {
                        vaitro = "Trưởng Phòng";
                    } else {
                        vaitro = "Nhân Viên";
                    }
                    Log4j.logger.info("Đăng Nhập Thành Công| MaNV: " + nhanVien.getMaNV() + " Vai Trò: " + vaitro);
                    this.dispose();
                } else {
                    DialogHelper.alert(this, "Sai mật khẩu!");

                }
            } else {
                DialogHelper.alert(this, "Sai tên đăng nhập!");

            }
        } catch (Exception e) {

            System.out.println(e);
            DialogHelper.alert(this, "Lỗi truy vấn dữ liệu!");

        }
        if (checkboxcustom1.isSelected()) {
            checked(true);
        } else {
            checked(false);
        }
    }

    public void exit() {
        if (DialogHelper.confirm(this, "Bạn có muốn thoát khỏi ứng dụng không?")) {
            Log4j.logger.info("thoát khỏi ứng dụng Thành Công");
            System.exit(0);

        }
    }

    public boolean Check() {
        if (txtTenDN.getText().equals("")) {
            DialogHelper.alert(this, "Nhap Tai Khoan");
            txtTenDN.requestFocus();
            return false;
        }

        if (new String(txtPass.getPassword()).equals("")) {
            DialogHelper.alert(this, "Nhap Mat Khau");
            txtPass.requestFocus();
            return false;
        }

        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblDisc = new javax.swing.JLabel();
        lblExit = new javax.swing.JLabel();
        checkboxcustom1 = new Utils.Checkboxcustom();
        IconHide = new javax.swing.JLabel();
        IconShow = new javax.swing.JLabel();
        iconUser = new javax.swing.JLabel();
        iconKey = new javax.swing.JLabel();
        btnSetting = new Utils.ButtonTron();
        IconHotel2 = new javax.swing.JLabel();
        NameHotel = new javax.swing.JLabel();
        IconHotel1 = new javax.swing.JLabel();
        Name = new javax.swing.JLabel();
        lblQuenMk = new javax.swing.JLabel();
        lblDK = new javax.swing.JLabel();
        btnDangNhap = new Utils.Button();
        rach1 = new javax.swing.JLabel();
        txtTenDN = new javax.swing.JTextField();
        rach2 = new javax.swing.JLabel();
        txtPass = new javax.swing.JPasswordField();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblDisc.setBackground(new java.awt.Color(113, 113, 130));
        lblDisc.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblDisc.setForeground(new java.awt.Color(255, 255, 255));
        lblDisc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDisc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/IconDisc.png"))); // NOI18N
        lblDisc.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblDisc.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblDiscMouseMoved(evt);
            }
        });
        lblDisc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDiscMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblDiscMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblDiscMouseExited(evt);
            }
        });
        getContentPane().add(lblDisc, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 0, 30, 30));

        lblExit.setBackground(new java.awt.Color(255, 255, 255));
        lblExit.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblExit.setForeground(new java.awt.Color(255, 255, 255));
        lblExit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblExit.setText("X");
        lblExit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblExit.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblExitMouseMoved(evt);
            }
        });
        lblExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblExitMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblExitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblExitMouseExited(evt);
            }
        });
        getContentPane().add(lblExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 0, 30, 30));

        checkboxcustom1.setForeground(new java.awt.Color(255, 255, 255));
        checkboxcustom1.setText("Remember Me");
        checkboxcustom1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        getContentPane().add(checkboxcustom1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 190, -1, -1));

        IconHide.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/IconHide.png"))); // NOI18N
        IconHide.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                IconHideMouseClicked(evt);
            }
        });
        getContentPane().add(IconHide, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 120, 30, 60));

        IconShow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/IconShow.png"))); // NOI18N
        IconShow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                IconShowMouseClicked(evt);
            }
        });
        getContentPane().add(IconShow, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 120, 30, 60));

        iconUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/IconUser.png"))); // NOI18N
        getContentPane().add(iconUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 70, -1, -1));

        iconKey.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/IconKey.png"))); // NOI18N
        getContentPane().add(iconKey, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 140, -1, -1));

        btnSetting.setBackground(new java.awt.Color(0, 0, 0));
        btnSetting.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/IconSetting.png"))); // NOI18N
        btnSetting.setEffectColor(new java.awt.Color(255, 255, 255));
        getContentPane().add(btnSetting, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 70, 50));

        IconHotel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/IconHotel2.png"))); // NOI18N
        getContentPane().add(IconHotel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 10, -1, -1));

        NameHotel.setFont(new java.awt.Font("Bernard MT Condensed", 0, 24)); // NOI18N
        NameHotel.setForeground(new java.awt.Color(255, 255, 255));
        NameHotel.setText("KSF");
        getContentPane().add(NameHotel, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 30, -1, -1));

        IconHotel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/IconHotel.png"))); // NOI18N
        getContentPane().add(IconHotel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-30, 0, 300, -1));

        Name.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Name.setForeground(new java.awt.Color(255, 255, 255));
        Name.setText("[KSF]");
        getContentPane().add(Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, 0, 40, -1));

        lblQuenMk.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblQuenMk.setForeground(new java.awt.Color(153, 153, 153));
        lblQuenMk.setText("Quên mật khẩu");
        lblQuenMk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblQuenMk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblQuenMkMouseClicked(evt);
            }
        });
        getContentPane().add(lblQuenMk, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 290, -1, -1));

        lblDK.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDK.setForeground(new java.awt.Color(153, 153, 153));
        lblDK.setText("Hỗ trợ  |");
        lblDK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDKMouseClicked(evt);
            }
        });
        getContentPane().add(lblDK, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 290, -1, -1));

        btnDangNhap.setBackground(new java.awt.Color(69, 69, 242));
        btnDangNhap.setForeground(new java.awt.Color(255, 255, 255));
        btnDangNhap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/iconThunder.png"))); // NOI18N
        btnDangNhap.setText("Đăng Nhập");
        btnDangNhap.setBorderColor(new java.awt.Color(51, 51, 51));
        btnDangNhap.setColor(new java.awt.Color(69, 69, 242));
        btnDangNhap.setColorClick(new java.awt.Color(44, 44, 208));
        btnDangNhap.setColorOver(new java.awt.Color(44, 44, 208));
        btnDangNhap.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnDangNhap.setRadius(18);
        btnDangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangNhapActionPerformed(evt);
            }
        });
        getContentPane().add(btnDangNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 230, 230, 50));

        rach1.setForeground(new java.awt.Color(255, 255, 255));
        rach1.setText("________________________________");
        getContentPane().add(rach1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 80, 250, 50));

        txtTenDN.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtTenDN.setForeground(new java.awt.Color(255, 255, 255));
        txtTenDN.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        getContentPane().add(txtTenDN, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 70, 230, 40));

        rach2.setForeground(new java.awt.Color(255, 255, 255));
        rach2.setText("________________________________");
        getContentPane().add(rach2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 140, 260, 50));

        txtPass.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtPass.setForeground(new java.awt.Color(255, 255, 255));
        txtPass.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        getContentPane().add(txtPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 130, 230, 40));

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Ubackgroundlogin.png"))); // NOI18N
        getContentPane().add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 330));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void IconHideMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_IconHideMouseClicked
        //nhan vao nut icon thi mat cai nay mat cai kia kieu z
        IconShow.setVisible(true);
        IconHide.setVisible(false);
        //dua pass ve dang ki tu chu !!
        txtPass.setEchoChar((char) 0);
    }//GEN-LAST:event_IconHideMouseClicked

    private void IconShowMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_IconShowMouseClicked
        IconHide.setVisible(true);
        IconShow.setVisible(false);
        //dua pass ve dang ki tu chu !!
        txtPass.setEchoChar('*');
    }//GEN-LAST:event_IconShowMouseClicked

    private void btnDangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangNhapActionPerformed
        if (Check()) {
            login();
        }
    }//GEN-LAST:event_btnDangNhapActionPerformed

    private void lblExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExitMouseClicked
        System.exit(0);
    }//GEN-LAST:event_lblExitMouseClicked

    private void lblExitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExitMouseExited
        lblExit.setOpaque(false);
        lblExit.setBackground(new Color(2, 2, 2));
    }//GEN-LAST:event_lblExitMouseExited

    private void lblExitMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExitMouseMoved


    }//GEN-LAST:event_lblExitMouseMoved

    private void lblExitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExitMouseEntered

        lblExit.setOpaque(true);
        lblExit.setBackground(new Color(255, 51, 51));
    }//GEN-LAST:event_lblExitMouseEntered

    private void lblDiscMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDiscMouseExited
        lblDisc.setOpaque(false);
        lblDisc.setBackground(new Color(2, 2, 2));
    }//GEN-LAST:event_lblDiscMouseExited

    private void lblDiscMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDiscMouseEntered
        lblDisc.setOpaque(true);
        lblDisc.setBackground(new Color(113, 113, 130));
    }//GEN-LAST:event_lblDiscMouseEntered

    private void lblDiscMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDiscMouseClicked

    }//GEN-LAST:event_lblDiscMouseClicked

    private void lblDiscMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDiscMouseMoved

    }//GEN-LAST:event_lblDiscMouseMoved

    private void lblQuenMkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblQuenMkMouseClicked
        ForgotJD fjd = new ForgotJD(txtTenDN.getText());
        fjd.setVisible(true);
    }//GEN-LAST:event_lblQuenMkMouseClicked

    private void lblDKMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDKMouseClicked
        openHuongDan();
    }//GEN-LAST:event_lblDKMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormDangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormDangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormDangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormDangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormDangNhap().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel IconHide;
    private javax.swing.JLabel IconHotel1;
    private javax.swing.JLabel IconHotel2;
    private javax.swing.JLabel IconShow;
    private javax.swing.JLabel Name;
    private javax.swing.JLabel NameHotel;
    private javax.swing.JLabel background;
    private Utils.Button btnDangNhap;
    private Utils.ButtonTron btnSetting;
    private Utils.Checkboxcustom checkboxcustom1;
    private javax.swing.JLabel iconKey;
    private javax.swing.JLabel iconUser;
    private javax.swing.JLabel lblDK;
    private javax.swing.JLabel lblDisc;
    private javax.swing.JLabel lblExit;
    private javax.swing.JLabel lblQuenMk;
    private javax.swing.JLabel rach1;
    private javax.swing.JLabel rach2;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtTenDN;
    // End of variables declaration//GEN-END:variables
}
