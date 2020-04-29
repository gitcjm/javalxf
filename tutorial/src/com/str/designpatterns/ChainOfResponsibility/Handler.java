package com.str.designpatterns.ChainOfResponsibility;

import java.math.BigDecimal;

/**
 * Manager: 只能审核1000元以下的报销;
 * Director：只能审核10000元以下的报销;
 * CEO：可以审核任意额度;
 */

public interface Handler {
    // 返回Boolean.TRUE = 成功
    // 返回Boolean.FALSE = 拒绝
    // 返回 null = 交下一个处理
    Boolean process(Request request);
}

class ManagerHandler implements Handler {

    public Boolean process(Request request) {
        if (request.getAmount().compareTo(BigDecimal.valueOf(1000)) > 0) {
            return null;
        }
        // 对Bob有偏见
        return !request.getName().equalsIgnoreCase("Bob");
    }

}

class DirectorHandler implements Handler {

    public Boolean process(Request request) {
        if (request.getAmount().compareTo(BigDecimal.valueOf(10000)) > 0) {
            return null;
        }
        return true;
    }
}

class CEOHandler implements Handler {

    public Boolean process(Request request) {
        // 对Trump有偏见
        return !request.getName().equalsIgnoreCase("trump");
    }
}
