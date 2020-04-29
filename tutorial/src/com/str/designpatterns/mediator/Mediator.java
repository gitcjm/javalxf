package com.str.designpatterns.mediator;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.util.List;

/**
 * 中介负责逻辑交互
 */
public class Mediator {
    // 引用UI组件
    private List<JCheckBox> checkBoxList;
    private JButton selectAll;
    private JButton selectNone;
    private JButton selectInverse;

    public Mediator(List<JCheckBox> checkBoxList, JButton selectAll,
                    JButton selectNone, JButton selectInverse) {
        this.checkBoxList = checkBoxList;
        this.selectAll = selectAll;
        this.selectNone = selectNone;
        this.selectInverse = selectInverse;
        // 绷定事件
        for (JCheckBox checkBox : checkBoxList) {
            checkBox.addChangeListener(new CheckBoxChangeListener());
        }
        this.selectAll.addActionListener(this::onSelectAllClicked);
        this.selectNone.addActionListener(this::onSelectNoneClicked);
        this.selectInverse.addActionListener(this::onSelectInverseClicked);
    }

    // 通过事件类绷定事件比较麻烦，采用lambda表达式就简介多了
    class CheckBoxChangeListener implements ChangeListener {
        public void stateChanged(ChangeEvent e) {
            onCheckBoxChanged(e);
        }
    }

    // 当checkbox的状态变化时
    public void onCheckBoxChanged(ChangeEvent event) {
        boolean allChecked = true;
        boolean allUnchecked = true;
        for (JCheckBox checkBox : checkBoxList) {
            if (checkBox.isSelected()) {
                allUnchecked = false;
            } else {
                allChecked = false;
            }
        }
        selectAll.setEnabled(!allChecked);
        selectNone.setEnabled(!allUnchecked);
    }

    // 点击select all
    public void onSelectAllClicked(ActionEvent event) {
        for (JCheckBox checkBox : checkBoxList) {
            checkBox.setSelected(true);
        }
        selectAll.setEnabled(false);
        selectNone.setEnabled(true);
    }

    // 点击select none
    public void onSelectNoneClicked(ActionEvent event) {
        checkBoxList.forEach(checkBox -> checkBox.setSelected(false));
        selectAll.setEnabled(true);
        selectNone.setEnabled(false);
    }

    // 点击反选
    public void onSelectInverseClicked(ActionEvent event) {
        checkBoxList.forEach(checkBox ->
                checkBox.setSelected(!checkBox.isSelected()));
        onCheckBoxChanged(null);
    }
}
