/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package storybook.export;

import java.awt.event.ItemEvent;

/**
 *
 * @author favdb
 */
public class DlgExportOptions extends javax.swing.JDialog {
	DlgExport parent;
	ParamExport param;

	/**
	 * Creates new form DlgExportOptions
	 */
	public DlgExportOptions(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	public DlgExportOptions(DlgExport parent, boolean modal) {
		super(parent, modal);
		initComponents();
		this.parent=parent;
		this.param=parent.paramExport;
		initUI();
	}

	@SuppressWarnings("unchecked")
	private void initUI() {
		csvSingleQuotes.setSelected(param.csvSingleQuotes);
		csvDoubleQuotes.setSelected(param.csvDoubleQuotes);
		csvNoQuotes.setSelected(param.csvNoQuotes);
		csvComma.setSelected(param.csvComma);
		if (!param.csvComma) csvSemicolon.setSelected(true);
		txtTab.setSelected(param.txtTab);
		if (!param.txtTab) {
			txtSeparator.setText(param.txtSeparator);
			txtSeparator.setVisible(true);
		} else {
			txtSeparator.setVisible(false);
		}
		htmlUseCss.setSelected(param.htmlUseCss);
		if (!param.htmlUseCss) {
			htmlCssFile.setText(parent.paramExport.htmlCssFile);
			htmlCssFile.setVisible(true);
			btCssDirectory.setVisible(true);
		} else {
			htmlCssFile.setVisible(false);
			btCssDirectory.setVisible(false);
		}
		if (param.htmlBookMulti) {
			htmlBookOneFile.setSelected(false);
			htmlBookMultiFile.setSelected(true);
		} else {
			htmlBookOneFile.setSelected(true);
			htmlBookMultiFile.setSelected(false);
		}
		ckExportChapterNumbers.setSelected(param.isExportChapterNumbers);
		ckExportChapterNumbersRoman.setSelected(param.isExportChapterNumbersRoman);
		ckExportChapterTitles.setSelected(param.isExportChapterTitles);
		ckExportChapterDatesLocations.setSelected(param.isExportChapterDatesLocs);
		ckExportSceneTitles.setSelected(param.isExportSceneTitles);
		ckExportSceneSeparator.setSelected(param.isExportSceneSeparator);
		String pageSize[]={"A0","A1","A2","A3","A4"};
		for (String p : pageSize) {
			pdfPageSize.addItem(p);
		}
		pdfPageSize.setSelectedItem(param.pdfPageSize);
		pdfPortrait.setSelected(true);
		pdfLandscape.setSelected(param.pdfLandscape);	
	}
	/**
	 * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
	 * content of this method is always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        csvGroupQuotes = new javax.swing.ButtonGroup();
        csvGroupComma = new javax.swing.ButtonGroup();
        txtGroupTab = new javax.swing.ButtonGroup();
        pdfGroupOrientation = new javax.swing.ButtonGroup();
        htmlBookGroup = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        paneCSV = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        csvSingleQuotes = new javax.swing.JRadioButton();
        csvDoubleQuotes = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        csvComma = new javax.swing.JRadioButton();
        csvSemicolon = new javax.swing.JRadioButton();
        csvNoQuotes = new javax.swing.JRadioButton();
        paneTXT = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtTab = new javax.swing.JRadioButton();
        txtOther = new javax.swing.JRadioButton();
        txtSeparator = new javax.swing.JTextField();
        paneHTML = new javax.swing.JPanel();
        htmlUseCss = new javax.swing.JCheckBox();
        htmlCssFile = new javax.swing.JTextField();
        btCssDirectory = new javax.swing.JButton();
        panePDF = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        pdfPageSize = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        pdfPortrait = new javax.swing.JRadioButton();
        pdfLandscape = new javax.swing.JRadioButton();
        paneBook = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        htmlBookMultiFile = new javax.swing.JRadioButton();
        htmlBookOneFile = new javax.swing.JRadioButton();
        ckExportChapterNumbers = new javax.swing.JCheckBox();
        ckExportChapterNumbersRoman = new javax.swing.JCheckBox();
        ckExportChapterTitles = new javax.swing.JCheckBox();
        ckExportChapterDatesLocations = new javax.swing.JCheckBox();
        ckExportSceneTitles = new javax.swing.JCheckBox();
        ckExportSceneSeparator = new javax.swing.JCheckBox();
        btCancel = new javax.swing.JButton();
        btOK = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        paneCSV.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("storybook/msg/messages"); // NOI18N
        jLabel2.setText(bundle.getString("msg.export.options.csv.quoted")); // NOI18N

        csvGroupQuotes.add(csvSingleQuotes);
        csvSingleQuotes.setText(bundle.getString("msg.export.options.csv.quoted.single")); // NOI18N

        csvGroupQuotes.add(csvDoubleQuotes);
        csvDoubleQuotes.setText(bundle.getString("msg.export.options.csv.quoted.double")); // NOI18N

        jLabel3.setText(bundle.getString("msg.export.options.csv.separate")); // NOI18N

        csvGroupComma.add(csvComma);
        csvComma.setText(bundle.getString("msg.export.options.csv.separate.comma")); // NOI18N

        csvGroupComma.add(csvSemicolon);
        csvSemicolon.setText(bundle.getString("msg.export.options.csv.separate.semicolon")); // NOI18N

        csvGroupQuotes.add(csvNoQuotes);
        csvNoQuotes.setText(bundle.getString("msg.common.none")); // NOI18N

        javax.swing.GroupLayout paneCSVLayout = new javax.swing.GroupLayout(paneCSV);
        paneCSV.setLayout(paneCSVLayout);
        paneCSVLayout.setHorizontalGroup(
            paneCSVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneCSVLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paneCSVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(paneCSVLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(csvSingleQuotes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(csvDoubleQuotes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(csvNoQuotes))
                    .addGroup(paneCSVLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(csvComma)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(csvSemicolon)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        paneCSVLayout.setVerticalGroup(
            paneCSVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneCSVLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paneCSVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(csvSingleQuotes)
                    .addComponent(csvDoubleQuotes)
                    .addComponent(csvNoQuotes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(paneCSVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(csvComma)
                    .addComponent(csvSemicolon))
                .addContainerGap(160, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("CSV", paneCSV);

        paneTXT.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel1.setText(bundle.getString("msg.export.options.csv.separate")); // NOI18N

        txtGroupTab.add(txtTab);
        txtTab.setText("tab");

        txtGroupTab.add(txtOther);
        txtOther.setText(bundle.getString("msg.common.other")); // NOI18N
        txtOther.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                txtOtherItemStateChanged(evt);
            }
        });

        txtSeparator.setAutoscrolls(false);

        javax.swing.GroupLayout paneTXTLayout = new javax.swing.GroupLayout(paneTXT);
        paneTXT.setLayout(paneTXTLayout);
        paneTXTLayout.setHorizontalGroup(
            paneTXTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneTXTLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTab)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtOther)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(224, Short.MAX_VALUE))
        );
        paneTXTLayout.setVerticalGroup(
            paneTXTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneTXTLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paneTXTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTab)
                    .addComponent(txtOther)
                    .addComponent(txtSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(190, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("TXT", paneTXT);

        paneHTML.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        htmlUseCss.setText(bundle.getString("msg.export.options.html.css")); // NOI18N
        htmlUseCss.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                htmlUseCssItemStateChanged(evt);
            }
        });

        htmlCssFile.setEnabled(false);

        btCssDirectory.setText("...");
        btCssDirectory.setActionCommand("");
        btCssDirectory.setEnabled(false);

        javax.swing.GroupLayout paneHTMLLayout = new javax.swing.GroupLayout(paneHTML);
        paneHTML.setLayout(paneHTMLLayout);
        paneHTMLLayout.setHorizontalGroup(
            paneHTMLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneHTMLLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paneHTMLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(paneHTMLLayout.createSequentialGroup()
                        .addComponent(htmlCssFile)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btCssDirectory))
                    .addGroup(paneHTMLLayout.createSequentialGroup()
                        .addComponent(htmlUseCss)
                        .addGap(0, 318, Short.MAX_VALUE)))
                .addContainerGap())
        );
        paneHTMLLayout.setVerticalGroup(
            paneHTMLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneHTMLLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(htmlUseCss)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(paneHTMLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(htmlCssFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btCssDirectory))
                .addContainerGap(158, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("HTML", paneHTML);

        panePDF.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel4.setText(bundle.getString("msg.export.options.pdf.pagesize")); // NOI18N

        jLabel5.setText(bundle.getString("msg.export.options.pdf.orientation")); // NOI18N

        pdfGroupOrientation.add(pdfPortrait);
        pdfPortrait.setSelected(true);
        pdfPortrait.setText(bundle.getString("msg.export.options.pdf.orientation.portrait")); // NOI18N

        pdfGroupOrientation.add(pdfLandscape);
        pdfLandscape.setText(bundle.getString("msg.export.options.pdf.orientation.landscape")); // NOI18N

        javax.swing.GroupLayout panePDFLayout = new javax.swing.GroupLayout(panePDF);
        panePDF.setLayout(panePDFLayout);
        panePDFLayout.setHorizontalGroup(
            panePDFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panePDFLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panePDFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panePDFLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pdfPageSize, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panePDFLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pdfPortrait)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pdfLandscape)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panePDFLayout.setVerticalGroup(
            panePDFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panePDFLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panePDFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(pdfPageSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panePDFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(pdfPortrait)
                    .addComponent(pdfLandscape))
                .addContainerGap(160, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("PDF", panePDF);

        jLabel6.setText(bundle.getString("msg.export.book.htmloption")); // NOI18N

        htmlBookGroup.add(htmlBookMultiFile);
        htmlBookMultiFile.setText(bundle.getString("msg.export.book.htmloption.multifile")); // NOI18N

        htmlBookGroup.add(htmlBookOneFile);
        htmlBookOneFile.setText(bundle.getString("msg.export.book.htmloption.onefile")); // NOI18N

        ckExportChapterNumbers.setText(bundle.getString("msg.export.chapter.numbers")); // NOI18N

        ckExportChapterNumbersRoman.setText(bundle.getString("msg.export.roman.numerals")); // NOI18N

        ckExportChapterTitles.setText(bundle.getString("msg.export.chapter.titles")); // NOI18N

        ckExportChapterDatesLocations.setText(bundle.getString("msg.export.chapter.dates.locations")); // NOI18N

        ckExportSceneTitles.setText(bundle.getString("msg.export.scene.titles")); // NOI18N

        ckExportSceneSeparator.setText(bundle.getString("msg.export.scene.separator")); // NOI18N

        javax.swing.GroupLayout paneBookLayout = new javax.swing.GroupLayout(paneBook);
        paneBook.setLayout(paneBookLayout);
        paneBookLayout.setHorizontalGroup(
            paneBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneBookLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paneBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(paneBookLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(htmlBookOneFile)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(htmlBookMultiFile))
                    .addComponent(ckExportChapterNumbersRoman)
                    .addComponent(ckExportChapterTitles)
                    .addComponent(ckExportChapterDatesLocations)
                    .addComponent(ckExportChapterNumbers)
                    .addComponent(ckExportSceneTitles)
                    .addComponent(ckExportSceneSeparator))
                .addContainerGap(147, Short.MAX_VALUE))
        );
        paneBookLayout.setVerticalGroup(
            paneBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneBookLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paneBookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(htmlBookOneFile)
                    .addComponent(htmlBookMultiFile))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ckExportChapterNumbers)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ckExportChapterNumbersRoman)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ckExportChapterTitles)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ckExportChapterDatesLocations)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ckExportSceneTitles)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ckExportSceneSeparator)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(bundle.getString("msg.export.book.text"), paneBook); // NOI18N

        btCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/storybook/resources/icons/16x16/cancel.png"))); // NOI18N
        btCancel.setText(bundle.getString("msg.common.cancel")); // NOI18N
        btCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelActionPerformed(evt);
            }
        });

        btOK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/storybook/resources/icons/16x16/ok.png"))); // NOI18N
        btOK.setText(bundle.getString("msg.common.ok")); // NOI18N
        btOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btOKActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btOK, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btCancel)
                    .addComponent(btOK))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void htmlUseCssItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_htmlUseCssItemStateChanged
		if (evt.getStateChange()==ItemEvent.SELECTED) {
			htmlCssFile.setVisible(true);
			btCssDirectory.setVisible(true);
		} else {
			htmlCssFile.setVisible(false);
			btCssDirectory.setVisible(false);
		}
    }//GEN-LAST:event_htmlUseCssItemStateChanged

    private void txtOtherItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_txtOtherItemStateChanged
        if (evt.getStateChange()==ItemEvent.SELECTED) {
			txtSeparator.setVisible(true);
		} else {
			txtSeparator.setVisible(false);
		}
    }//GEN-LAST:event_txtOtherItemStateChanged

    private void btCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelActionPerformed
		dispose();
    }//GEN-LAST:event_btCancelActionPerformed

    private void btOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btOKActionPerformed
        param.csvSingleQuotes=csvSingleQuotes.isSelected();
        param.csvDoubleQuotes=csvDoubleQuotes.isSelected();
        param.csvNoQuotes=csvNoQuotes.isSelected();
        param.csvComma=csvComma.isSelected();
        param.txtTab=txtTab.isSelected();
		if (!param.txtTab) param.txtSeparator=txtSeparator.getText();
        param.htmlUseCss=htmlUseCss.isSelected();
		if (!param.htmlUseCss) param.htmlCssFile=htmlCssFile.getText();
		param.isExportChapterNumbers=ckExportChapterNumbers.isSelected();
		param.isExportChapterNumbersRoman=ckExportChapterNumbersRoman.isSelected();
		param.isExportChapterTitles=ckExportChapterTitles.isSelected();
		param.isExportSceneTitles=ckExportSceneTitles.isSelected();
		param.isExportSceneSeparator=ckExportSceneSeparator.isSelected();
		param.htmlBookMulti=htmlBookMultiFile.isSelected();
		param.pdfPageSize=pdfPageSize.getSelectedItem().toString();
		param.pdfLandscape=pdfLandscape.isSelected();
		param.save();
		dispose();
    }//GEN-LAST:event_btOKActionPerformed

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
			java.util.logging.Logger.getLogger(DlgExportOptions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(DlgExportOptions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(DlgExportOptions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(DlgExportOptions.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
        //</editor-fold>

		/* Create and display the dialog */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				DlgExportOptions dialog = new DlgExportOptions(new javax.swing.JFrame(), true);
				dialog.addWindowListener(new java.awt.event.WindowAdapter() {
					@Override
					public void windowClosing(java.awt.event.WindowEvent e) {
						System.exit(0);
					}
				});
				dialog.setVisible(true);
			}
		});
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCancel;
    private javax.swing.JButton btCssDirectory;
    private javax.swing.JButton btOK;
    private javax.swing.JCheckBox ckExportChapterDatesLocations;
    private javax.swing.JCheckBox ckExportChapterNumbers;
    private javax.swing.JCheckBox ckExportChapterNumbersRoman;
    private javax.swing.JCheckBox ckExportChapterTitles;
    private javax.swing.JCheckBox ckExportSceneSeparator;
    private javax.swing.JCheckBox ckExportSceneTitles;
    private javax.swing.JRadioButton csvComma;
    private javax.swing.JRadioButton csvDoubleQuotes;
    private javax.swing.ButtonGroup csvGroupComma;
    private javax.swing.ButtonGroup csvGroupQuotes;
    private javax.swing.JRadioButton csvNoQuotes;
    private javax.swing.JRadioButton csvSemicolon;
    private javax.swing.JRadioButton csvSingleQuotes;
    private javax.swing.ButtonGroup htmlBookGroup;
    private javax.swing.JRadioButton htmlBookMultiFile;
    private javax.swing.JRadioButton htmlBookOneFile;
    private javax.swing.JTextField htmlCssFile;
    private javax.swing.JCheckBox htmlUseCss;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel paneBook;
    private javax.swing.JPanel paneCSV;
    private javax.swing.JPanel paneHTML;
    private javax.swing.JPanel panePDF;
    private javax.swing.JPanel paneTXT;
    private javax.swing.ButtonGroup pdfGroupOrientation;
    private javax.swing.JRadioButton pdfLandscape;
    private javax.swing.JComboBox pdfPageSize;
    private javax.swing.JRadioButton pdfPortrait;
    private javax.swing.ButtonGroup txtGroupTab;
    private javax.swing.JRadioButton txtOther;
    private javax.swing.JTextField txtSeparator;
    private javax.swing.JRadioButton txtTab;
    // End of variables declaration//GEN-END:variables
}
