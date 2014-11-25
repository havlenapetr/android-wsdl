/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JinosJFrame.java
 *
 * Created on May 2, 2012, 5:44:28 AM
 */

package org.jinouts.ui;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.jinouts.ui.biz.JinoutsFrameController;
import org.jinouts.util.AndroidWSClientGenUtil;

/**
 * @author asraf
 */
public class JinoutsJFrame extends javax.swing.JFrame {

    private JinoutsFrameController controller;
    private File cxfFile;
    private File dstDirectory;

    /**
     * Creates new form JinosJFrame
     */
    public JinoutsJFrame(String title) {
        super(title);

        controller = new JinoutsFrameController();
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed"
    // desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        clientGenPanel = new javax.swing.JPanel();
        wsdlLabel = new javax.swing.JLabel();
        wsdlTextField = new javax.swing.JTextField();
        destLabel = new javax.swing.JLabel();
        destTextField = new javax.swing.JTextField();
        destBrowseButton = new javax.swing.JButton();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        destLabel1 = new javax.swing.JLabel();
        cxfTextField = new javax.swing.JTextField();
        cxfBrowseButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        wsdlLabel.setFont(new java.awt.Font("Tahoma", 1, 12));
        wsdlLabel.setText("WSDL File URL :");

        wsdlTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wsdlTextFieldActionPerformed(evt);
            }
        });

        destLabel.setFont(new java.awt.Font("Tahoma", 1, 12));
        destLabel.setText("Choose Folder for Client Stub :");

        destTextField.setEditable(false);
        destTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                destTextFieldActionPerformed(evt);
            }
        });

        destBrowseButton.setText("Browse");
        destBrowseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                destBrowseButtonActionPerformed(evt);
            }
        });

        okButton.setText("Generate Stub");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtoneActionPerformed(evt);
            }
        });

        destLabel1.setFont(new java.awt.Font("Tahoma", 1, 12));
        destLabel1.setText("Choose CXF Bin dir ( if CXF_HOME not defined in your path ) :");

        cxfTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cxfTextFieldActionPerformed(evt);
            }
        });

        cxfBrowseButton.setText("Browse");
        cxfBrowseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cxfBrowseButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout clientGenPanelLayout = new javax.swing.GroupLayout(clientGenPanel);
        clientGenPanel.setLayout(clientGenPanelLayout);
        clientGenPanelLayout.setHorizontalGroup(clientGenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(clientGenPanelLayout.createSequentialGroup().addContainerGap().addGroup(clientGenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(clientGenPanelLayout.createSequentialGroup().addGap(2, 2, 2).addComponent(wsdlTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap()).addGroup(clientGenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(clientGenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(clientGenPanelLayout.createSequentialGroup().addComponent(wsdlLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap(493, Short.MAX_VALUE)).addGroup(clientGenPanelLayout.createSequentialGroup().addGroup(clientGenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(clientGenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, clientGenPanelLayout.createSequentialGroup().addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(destTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)).addGroup(clientGenPanelLayout.createSequentialGroup().addComponent(destLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 171, javax.swing.GroupLayout.PREFERRED_SIZE))).addComponent(cxfTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE).addGroup(clientGenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(destBrowseButton).addComponent(cxfBrowseButton)).addGap(66, 66, 66)).addGroup(clientGenPanelLayout.createSequentialGroup().addComponent(destLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap(212, Short.MAX_VALUE))).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, clientGenPanelLayout.createSequentialGroup().addComponent(okButton).addGap(46, 46, 46).addComponent(cancelButton).addGap(122, 122, 122))))));
        clientGenPanelLayout.setVerticalGroup(clientGenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(clientGenPanelLayout.createSequentialGroup().addContainerGap().addComponent(wsdlLabel).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(wsdlTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(27, 27, 27).addComponent(destLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18).addGroup(clientGenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(clientGenPanelLayout.createSequentialGroup().addComponent(destTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18).addComponent(destLabel1).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addGroup(clientGenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(cxfTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(cxfBrowseButton))).addComponent(destBrowseButton)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE).addGroup(clientGenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(okButton).addComponent(cancelButton)).addContainerGap()));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(clientGenPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(clientGenPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap(41, Short.MAX_VALUE)));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void wsdlTextFieldActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_wsdlTextFieldActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_wsdlTextFieldActionPerformed

    private void destTextFieldActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_destTextFieldActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_destTextFieldActionPerformed

    private void destBrowseButtonActionPerformed(java.awt.event.ActionEvent evt) {
        JFileChooser directoryChooser = new JFileChooser();
        directoryChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        switch (directoryChooser.showDialog(this, null)) {
            case JFileChooser.APPROVE_OPTION:
                File selectedFile = directoryChooser.getSelectedFile();
                if (selectedFile != null) {
                    if (selectedFile.isDirectory()) {
                        this.destTextField.setText(selectedFile.getAbsolutePath());
                        this.dstDirectory = selectedFile;
                    } else {
                        JOptionPane.showMessageDialog(this, "You chose a file, please choose a directory");
                    }
                }
                break;
            default:
                JOptionPane.showMessageDialog(this, "Please Choose a directory");
        }
    }// GEN-LAST:event_destBrowseButtonActionPerformed

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // check the wsdlurl
        String wsdlUrl = this.wsdlTextField.getText();

        if (wsdlUrl == null || wsdlUrl.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Give an entry of valid WSDL URL");
            return;
        }

        if (this.cxfFile == null) {
            this.cxfFile = getValidCXFBinDirFromTextField();
            if ((this.cxfFile == null) && !AndroidWSClientGenUtil.isCXFHOMEValid()) {
                JOptionPane.showMessageDialog(this, "Please choose a valid CXF bin directory or Set the CXF_HOME Path variable correctly !!");
                return;
            }

        }

        if (dstDirectory != null) {
            if (!dstDirectory.exists() || !dstDirectory.isDirectory()) {
                JOptionPane.showMessageDialog(this, "Please choose a valid directory");
                return;
            } else {
                File outputDirectory = controller.generateJavaFromWSDL(cxfFile, dstDirectory, wsdlTextField.getText());
                JOptionPane.showMessageDialog(this, "The WS Client Stub generated in the directory: \"" + outputDirectory + "\"");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please choose a directory");
            return;
        }
    }

    private void cancelButtoneActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cancelButtoneActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }// GEN-LAST:event_cancelButtoneActionPerformed

    private void cxfTextFieldActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cxfTextFieldActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_cxfTextFieldActionPerformed

    private void cxfBrowseButtonActionPerformed(java.awt.event.ActionEvent evt) {
        JFileChooser directoryChooser = new JFileChooser();
        directoryChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        switch (directoryChooser.showDialog(this, null)) {
            case JFileChooser.APPROVE_OPTION:
                File selectedFile = directoryChooser.getSelectedFile();
                if (selectedFile != null) {
                    if (selectedFile.isDirectory()) {
                        this.cxfTextField.setText(selectedFile.getAbsolutePath());
                        if (!AndroidWSClientGenUtil.isCXFBinValid(selectedFile)) {
                            this.cxfFile = null;
                            JOptionPane.showMessageDialog(this, "You chose a Wrong dir, Make sure you are choosing the right CXF Bin Dir, \n or Set the path variable \" CXF_HOME\" to avoid this dir selection!!");
                        } else {
                            this.cxfFile = selectedFile;
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "You chose a file, please choose a directory: ");
                    }
                }
                break;
            default:
                JOptionPane.showMessageDialog(this, "Please Choose a directory");
        }
    }

    private File getValidCXFBinDirFromTextField() {
        String binPath = this.cxfTextField.getText();
        if (binPath != null && !binPath.isEmpty()) {
            File binFile = new File(binPath);
            if (AndroidWSClientGenUtil.isCXFBinValid(binFile)) {
                return binFile;
            }
        }

        return null;

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JPanel clientGenPanel;
    private javax.swing.JButton cxfBrowseButton;
    private javax.swing.JTextField cxfTextField;
    private javax.swing.JButton destBrowseButton;
    private javax.swing.JLabel destLabel;
    private javax.swing.JLabel destLabel1;
    private javax.swing.JTextField destTextField;
    private javax.swing.JButton okButton;
    private javax.swing.JLabel wsdlLabel;
    private javax.swing.JTextField wsdlTextField;
    // End of variables declaration//GEN-END:variables

}