package com.str.service;

import com.str.entity.Order;
import com.str.entity.User;
import com.str.util.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class OrderService extends AbstractService<Order> {

    public Order getOrderById(long id) {
        return getById(id);
    }

    public List<Order> getOrderList(long createdAt, Page page) {
        String selectCount = "select count(*) from Order r where createdAt > ?0";
        String select = "select r from Order r where createdAt > ?0 order by createdAt DESC";
        Long[] values = { createdAt };
        return queryForListHasPages(selectCount, select, values, page);
    }

    public Order newOrder(User user, int deliver, int payment, int state) {
        Order order = new Order();
        order.setName(user.getName());
        order.setAddress(user.getAddress());
        order.setMobile(user.getMobile());
        order.setDeliver(deliver);
        order.setPayment(payment);
        order.setState(state);
        // TODO: add orderItems
        return create(order);
    }

    // 传入非持久化对象
    public void updateOrder(User user, Order order) {
        // 获取数据库中的持久化对象
        Order order1 = getOrderById(order.getId());
        if (order1 == null) {
            throw new RuntimeException("Update failed.");
        }
        // 将非持久化对象的属性复制给持久化对象
        order1.setName(user.getName());
        order1.setAddress(user.getAddress());
        order1.setMobile(user.getMobile());
        order1.setDeliver(order.getDeliver());
        order1.setPayment(order.getPayment());
        order1.setState(order.getState());
        // TODO: add orderitems
        update(order1);
    }
}
