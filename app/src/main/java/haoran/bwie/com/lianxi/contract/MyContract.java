package haoran.bwie.com.lianxi.contract;

import java.util.HashMap;

import haoran.bwie.com.lianxi.callback.MyCallback;

//契约类
public interface MyContract {

    //model层的接口
    interface MyModel {
        void Model(HashMap<String, String> params, MyCallback myCallback);
    }



    ;


}
