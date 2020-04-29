package com.str.rafactoring.duplicate_observed_data;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;

/**
 * 交互中介类(响应各种事件)
 */
public class EventMediator implements Observer {
    private Interval subject;

    private TextField startField;
    private TextField endField;
    private TextField lengthField;

    public EventMediator(TextField startField, TextField endField,
                         TextField lengthField, Frame frame) {
        this.startField = startField;
        this.endField = endField;
        this.lengthField = lengthField;
        // 绑定事件
        frame.addWindowListener(new CloseWindowAdapter());
        startField.addFocusListener(new SymFocus());
        endField.addFocusListener(new SymFocus());
        lengthField.addFocusListener(new SymFocus());
        // 注册观察者
        subject = new Interval();
        subject.addObserver((Observer) this);
        update(subject, null);
    }

    // window listener
    class CloseWindowAdapter extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }
    }

    // focus listener
    class SymFocus extends java.awt.event.FocusAdapter {
        public void focusLost (java.awt.event.FocusEvent event) {
            Object object = event.getSource();
            if (object == startField)
                StartField_FocusLost(event);
            else if (object == endField)
                EndField_FocusLost(event);
            else if (object == lengthField)
                LengthField_FocusLost(event);
        }
    }

    private void StartField_FocusLost(java.awt.event.FocusEvent event) {
        setStart(startField.getText());
        if (isNotInteger(getStart()))
            setStart("0");
        subject.calculateLength();
    }

    private void EndField_FocusLost(java.awt.event.FocusEvent event) {
        setEnd(endField.getText());
        if (isNotInteger(getEnd()))
            setEnd("0");
        subject.calculateLength();
    }

    private void LengthField_FocusLost(java.awt.event.FocusEvent event) {
        setLength(lengthField.getText());
        if (isNotInteger(getLength()))
            setLength("0");
        subject.calculateEnd();
    }

    private boolean isNotInteger(String text) {
        try {
            Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return true;
        }
        return false;
    }

    /**
     * 通过producer获取或设置文本框的值
     */
    @Override
    public void update(Observable o, Object arg) {
        startField.setText(subject.getStart());
        endField.setText(subject.getEnd());
        lengthField.setText(subject.getLength());
    }

    public String getStart() {
        return subject.getStart();
    }

    public void setStart(String arg) {
        subject.setStart(arg);
    }

    public String getEnd() {
        return subject.getEnd();
    }

    public void setEnd(String arg) {
        subject.setEnd(arg);
    }

    public String getLength() {
        return subject.getLength();
    }

    public void setLength(String arg) {
        subject.setLength(arg);
    }

}
