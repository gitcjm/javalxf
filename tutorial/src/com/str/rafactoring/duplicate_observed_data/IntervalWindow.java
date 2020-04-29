package com.str.rafactoring.duplicate_observed_data;

import java.awt.*;

/**
 * 画出UI窗口
 */
public class IntervalWindow extends Frame {

    public IntervalWindow() {
        // 三个文本框
        TextField startField = new TextField("0", 20);
        TextField endField = new TextField("0", 20);
        TextField lengthField = new TextField("0", 20);

        // 设置窗口，添加控件
        this.setBounds(700, 400, 250, 180);
        this.setLayout(new FlowLayout());
        this.add(new Label("start  "));
        this.add(startField);
        this.add(new Label("end    "));
        this.add(endField);
        this.add(new Label("length"));
        this.add(lengthField);
        // 绑定事件
        new EventMediator(startField, endField, lengthField, this);

        this.setVisible(true);
    }
}
