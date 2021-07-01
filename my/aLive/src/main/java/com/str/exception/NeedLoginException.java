package com.str.exception;

/**
 * 自定义操作异常
 * 注意：如果继承的是Exception类，那么Spring的事务管理将会失效，
 * 只有继承RuntimeException类才使Spring的事务管理不会失效
 *  */
public class NeedLoginException extends RuntimeException {


}
