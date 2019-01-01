package haoran.bwie.com.lianxi.view;

import haoran.bwie.com.lianxi.bean.MyBean;

public interface ViewImpl {
    void success(MyBean mybean);

    void error(String string);
}
