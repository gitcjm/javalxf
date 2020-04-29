package com.str.designpatterns.mediator;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 把UI组件画出来
 */
public class OrderFrame extends JFrame {

    public OrderFrame(String... names) {
        setTitle("Order");
        setSize(430, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container c = getContentPane();
        c.setLayout(new FlowLayout(FlowLayout.LEADING, 20, 20));
        c.add(new JLabel("Use Mediator Pattern"));
        List<JCheckBox> checkBoxList = addCheckBox(names);
        JButton selectAll = addButton("Select All");
        JButton selectNone = addButton("Select None");
        selectNone.setEnabled(false);
        JButton selectInverse = addButton("Inverse Select");

        // 中介模式把用户界面和交互逻辑分离了
        new Mediator(checkBoxList, selectAll, selectNone, selectInverse);

        setVisible(true);
    }

    private List<JCheckBox> addCheckBox(String[] names) {
        JPanel panel = new JPanel();
        panel.add(new JLabel("菜单: "));
        List<JCheckBox> list = new ArrayList<>();
        for (String name : names) {
            JCheckBox checkBox = new JCheckBox(name);
            list.add(checkBox);
            panel.add(checkBox);
        }
        getContentPane().add(panel);

        return list;
    }

    private JButton addButton(String label) {
        JButton button = new JButton(label);
        getContentPane().add(button);

        return button;
    }
}
