/*
 * SbApp: Open Source software for novelists and authors.
 * Original idea 2008 - 2012 Martin Mustun
 * Copyrigth (C) Favdb
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 */
package storybook.ui.dialog.edit;

import storybook.model.hbn.entity.ItemLink;
import storybook.toolkit.I18N;
import static storybook.ui.dialog.edit.CommonBox.findItem;
import static storybook.ui.dialog.edit.CommonBox.findLocation;
import static storybook.ui.dialog.edit.CommonBox.findPerson;
import static storybook.ui.dialog.edit.CommonBox.findScene;
import static storybook.ui.dialog.edit.CommonBox.loadCbItems;
import static storybook.ui.dialog.edit.CommonBox.loadCbLocations;
import static storybook.ui.dialog.edit.CommonBox.loadCbPersons;
import static storybook.ui.dialog.edit.CommonBox.loadCbScenes;
import static storybook.ui.dialog.edit.DlgErrorMessage.mandatoryField;

/**
 *
 * @author favdb
 */
public class EditItemLinks extends javax.swing.JPanel {
	Editor parent;
	ItemLink itemLink;

	/**
	 * Creates new form EditItemLinks
	 */
	public EditItemLinks() {
		initComponents();
	}

	EditItemLinks(Editor e, ItemLink o) {
		initComponents();
		parent=e;
		itemLink=o;
		initUi();
	}

	/**
	 * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
	 * content of this method is always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cbPerson = new javax.swing.JComboBox();
        cbLocation = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cbItem = new javax.swing.JComboBox();
        cbStartScene = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbEndScene = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        lbId = new javax.swing.JLabel();

        cbPerson.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbLocation.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("storybook/msg/messages"); // NOI18N
        jLabel1.setText(bundle.getString("msg.common.item")); // NOI18N

        jLabel3.setText(bundle.getString("msg.common.location")); // NOI18N

        cbItem.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbStartScene.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setText(bundle.getString("msg.common.person")); // NOI18N

        jLabel4.setText(bundle.getString("msg.tag.start.scene")); // NOI18N

        cbEndScene.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setText(bundle.getString("msg.tag.end.scene")); // NOI18N

        txtId.setEditable(false);

        lbId.setText(bundle.getString("msg.common.id")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbId)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtId, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                        .addGap(213, 213, 213))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbItem, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbPerson, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbLocation, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbStartScene, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbEndScene, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbPerson, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbStartScene, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cbEndScene, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cbEndScene;
    private javax.swing.JComboBox cbItem;
    private javax.swing.JComboBox cbLocation;
    private javax.swing.JComboBox cbPerson;
    private javax.swing.JComboBox cbStartScene;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel lbId;
    private javax.swing.JTextField txtId;
    // End of variables declaration//GEN-END:variables

	private void initUi() {
		txtId.setText(Long.toString(itemLink.getId()));
		loadCbItems(parent.mainFrame, cbItem, itemLink.getItem());
		loadCbPersons(parent.mainFrame, cbPerson, itemLink.getPerson());
		loadCbLocations(parent.mainFrame, cbLocation, itemLink.getLocation());
		loadCbScenes(parent.mainFrame, cbStartScene, itemLink.getStartScene());
		loadCbScenes(parent.mainFrame, cbEndScene, itemLink.getEndScene());
	}
    private ItemLink createNewItemLink() {
        ItemLink i = new ItemLink();
        i.setId(-1L);
        return (i);
    }

    boolean isModified() {
        if (!cbItem.getSelectedItem().equals(itemLink.getItem().getName())) return(true);
        if (!cbPerson.getSelectedItem().equals(itemLink.getPerson().getFullName())) return(true);
        if (!cbLocation.getSelectedItem().equals(itemLink.getLocation().getFullName())) return(true);
        if (!cbStartScene.getSelectedItem().equals(itemLink.getStartScene().getFullTitle())) return(true);
        if (!cbEndScene.getSelectedItem().equals(itemLink.getEndScene().getFullTitle())) return(true);
		return(false);
    }

    public String saveData() {
        String rt = ctrlData();
        if ("".equals(rt)) {
            itemLink.setItem(findItem(parent.mainFrame, cbItem.getSelectedItem().toString()));
            itemLink.setPerson(findPerson(parent.mainFrame, cbPerson.getSelectedItem().toString()));
            itemLink.setLocation(findLocation(parent.mainFrame, cbLocation.getSelectedItem().toString()));
            itemLink.setStartScene(findScene(parent.mainFrame, cbStartScene.getSelectedItem().toString()));
            itemLink.setEndScene(findScene(parent.mainFrame, cbEndScene.getSelectedItem().toString()));
        }
        return (rt);
    }

    private String ctrlData() {
        if (cbItem.getSelectedIndex()==-1) return (mandatoryField(I18N.getMsg("msg.common.title")));
        return ("");
    }
}
