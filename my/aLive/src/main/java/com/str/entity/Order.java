package com.str.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="t_order")
public class Order extends AbstractEntity {

    public boolean canCancel() { return state <= STATE_WAITTING_FOR_HANDLE; }

    // 订单状态
    public static final int STATE_WAITTING_FOR_PAY = 0;
    public static final int STATE_WAITTING_FOR_HANDLE = 1;
    public static final int STATE_PREPARE = 2;
    public static final int STATE_DELIVER = 3;
    public static final int STATE_COMPLETE = 4;
    public static final int STATE_CANCELLED = 5;
    private static final String[] STATES = {
            "等待付款", "等待处理", "正在配货", "正在发送", "处理完毕", "已经取消"
    };

    // 送货方式
    public static final int DELIVER_HOME = 0;
    public static final int DELIVER_POST = 1;
    public static final int DELIVER_EMS = 2;
    private static final String[] DELIVERS = {
            "送货上门", "普通快递", "特快转递"
    };

    // 付款方式
    public static final int PAYMENT_WEICHAT = 0;
    public static final int PAYMENT_ALIPAY = 1;
    public static final int PAYMENT_BANKCARD = 2;
    private static final String[] PAYMENTS = {
            "微信", "支付宝", "银行卡"
    };

    /* 常规属性 */
    private User user;    // FK
    private List<OrderItem> orderItems;

    // default value should be copied from user;
    private String address;
    private String name;
    private String mobile;

    private Integer deliver;   // 送货方式
    private Integer payment;   // 付款方式
    private Integer state;     // 订单状态

    @ManyToOne
    @JoinColumn(nullable = false, updatable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @OneToMany(targetEntity = OrderItem.class, mappedBy = "order")
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    @Column(nullable = false)
    public Integer getDeliver() {
        return deliver;
    }

    public void setDeliver(Integer deliver) {
        this.deliver = deliver;
    }

    @Column(nullable = false)
    public Integer getPayment() {
        return payment;
    }

    public void setPayment(Integer payment) {
        this.payment = payment;
    }

    @Column(nullable = false)
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Column(nullable = true)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(nullable = false, length = 100)
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Column(nullable = false, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
