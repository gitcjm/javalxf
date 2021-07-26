package com.str.service;

import com.str.entity.Order;
import com.str.entity.Book;
import com.str.entity.OrderItem;

public class OrderItemService extends AbstractService<OrderItem> {

    public OrderItem getOrderItemById(long id) {
        return getById(id);
    }

    public OrderItem addOrderItem(Order order, Book book, int number) {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setBook(book);
        orderItem.setNumber(number);
        return create(orderItem);
    }
}
