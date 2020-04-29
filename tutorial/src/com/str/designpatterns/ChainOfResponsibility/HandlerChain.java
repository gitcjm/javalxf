package com.str.designpatterns.ChainOfResponsibility;

import java.util.ArrayList;
import java.util.List;

public class HandlerChain {
    // 持有所有Handler
    private List<Handler> handlers = new ArrayList<>();

    public void addHandler(Handler handler) {
        this.handlers.add(handler);
    }

    public boolean process(Request request) {
        // 依次调用每个Handler
        for (Handler handler : handlers) {
            Boolean result = handler.process(request);
            if ( result != null) {
                System.out.println(request.getName() + " " +
                        (result ? "Approved by: " : "Denied by") +
                        handler.getClass().getSimpleName());
                return result;
            }
        }
        throw new RuntimeException("Could not handle request: " + request);
    }
}
