/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.gov.turkiye.esignuidesk.view.pane;

import iaik.pkcs.pkcs11.TokenException;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import tr.gov.turkiye.esign.core.SmartCard;
import tr.gov.turkiye.esignuidesk.annotation.FocusOwner;
import tr.gov.turkiye.esignuidesk.config.Config;
import tr.gov.turkiye.esignuidesk.controller.LogicManager;
import tr.gov.turkiye.esignuidesk.data.UserData;
import tr.gov.turkiye.esignuidesk.props.ScreenProperties;
import tr.gov.turkiye.esignuidesk.support.Utils;

/**
 * Last Pane which from where user can leave application.
 * 
 * @author iakpolat
 */
public class DonePane extends javax.swing.JPanel {

    /**
     * Creates new form DonePane
     */
    public DonePane() {
        initComponents();
        backBtn.setVisible(false);
        setBounds(Config.DEF_PANEL_STARTING_X, Config.DEF_PANEL_STARTING_Y, Config.DEF_PANEL_WIDTH, Config.DEF_PANEL_HEIGHT);
    }

    @FocusOwner
    public JButton getQuitBtn() {
        return quitBtn;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        successLabel = new javax.swing.JLabel();
        userAgreementScroller = new javax.swing.JScrollPane();
        userAgreementArea = new javax.swing.JTextArea();
        quitBtn = new javax.swing.JButton();
        backBtn = new javax.swing.JButton();
        saveBtn = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setSize(new java.awt.Dimension(530, 250));
        setLayout(null);

        successLabel.setText(ScreenProperties.getValue("done_msg")
        );
        add(successLabel);
        successLabel.setBounds(135, 50, 250, 16);

        userAgreementScroller.setBorder(null);
        userAgreementScroller.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        userAgreementScroller.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        userAgreementArea.setEditable(false);
        userAgreementArea.setColumns(20);
        userAgreementArea.setForeground(new java.awt.Color(0, 51, 255));
        userAgreementArea.setLineWrap(true);
        userAgreementArea.setRows(5);
        userAgreementArea.setText(ScreenProperties.getValue("done_detailed_msg"));
        userAgreementArea.setWrapStyleWord(true);
        userAgreementArea.setBorder(null);
        userAgreementArea.setFocusable(false);
        userAgreementScroller.setViewportView(userAgreementArea);

        add(userAgreementScroller);
        userAgreementScroller.setBounds(80, 90, 370, 70);

        quitBtn.setText(ScreenProperties.getValue("quit"));
        quitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitBtnActionPerformed(evt);
            }
        });
        add(quitBtn);
        quitBtn.setBounds(270, 170, 60, 29);

        backBtn.setText(ScreenProperties.getValue("back"));
        backBtn.setEnabled(false);
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });
        add(backBtn);
        backBtn.setBounds(30, 170, 60, 29);

        saveBtn.setText(ScreenProperties.getValue("save"));
        saveBtn.setMaximumSize(new java.awt.Dimension(80, 29));
        saveBtn.setMinimumSize(new java.awt.Dimension(80, 29));
        saveBtn.setPreferredSize(new java.awt.Dimension(80, 29));
        saveBtn.setSize(new java.awt.Dimension(80, 29));
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });
        add(saveBtn);
        saveBtn.setBounds(190, 170, 80, 29);
    }// </editor-fold>//GEN-END:initComponents

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        LogicManager.cancel(Config.DONE_PANE_ID);
    }//GEN-LAST:event_backBtnActionPerformed

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.setSelectedFile(new File(chooser.getCurrentDirectory().getAbsolutePath()+File.separator+Config.defaultFileName+Utils.formatTime(new Date(System.currentTimeMillis()))));
        int result = chooser.showSaveDialog(this);
        if(result==JFileChooser.APPROVE_OPTION) {
            File file = new File(chooser.getCurrentDirectory().getAbsolutePath());
            if(file.canWrite()) {
                BufferedOutputStream writer = null;
                try {
                    writer = new BufferedOutputStream(new FileOutputStream(new File(chooser.getSelectedFile().getAbsolutePath())));
                    writer.write(UserData.signedUserAgreement, 0, UserData.signedUserAgreement.length);
                } catch (IOException ex) {
                    Logger.getLogger(DonePane.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        if(writer!=null)
                            writer.close();
                    } catch (IOException ex) {
                        Logger.getLogger(DonePane.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                //Error message permission denied for file write operation.
            }
            
        } else if(result==JFileChooser.ERROR_OPTION) {
            System.out.println("Error");
        } else if(result==JFileChooser.CANCEL_OPTION) {
            System.out.println("Cancel");
        } else {
            //New feature for file chooser probably. Should be implemented by new developer.
        }
    }//GEN-LAST:event_saveBtnActionPerformed

    private void quitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitBtnActionPerformed
        try {
            LogicManager.smartCard.finalizeModule();
        } catch (TokenException ex) {
            Logger.getLogger(DonePane.class.getName()).log(Level.SEVERE, null, ex);
        }
        LogicManager.done(Config.DONE_PANE_ID);
    }//GEN-LAST:event_quitBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBtn;
    private javax.swing.JButton quitBtn;
    private javax.swing.JButton saveBtn;
    private javax.swing.JLabel successLabel;
    private javax.swing.JTextArea userAgreementArea;
    private javax.swing.JScrollPane userAgreementScroller;
    // End of variables declaration//GEN-END:variables
}