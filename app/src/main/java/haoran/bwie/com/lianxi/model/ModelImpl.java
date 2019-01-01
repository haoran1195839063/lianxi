package haoran.bwie.com.lianxi.model;

import java.util.HashMap;

import haoran.bwie.com.lianxi.api.MyApi;
import haoran.bwie.com.lianxi.callback.MyCallback;
import haoran.bwie.com.lianxi.contract.MyContract;
import haoran.bwie.com.lianxi.util.OkhttpUtil;

public class ModelImpl implements MyContract.MyModel {

    @Override
    public void Model(HashMap<String, String> params, MyCallback myCallback) {
        OkhttpUtil okhttpUtil = new OkhttpUtil();
        //调用okhttp
        okhttpUtil.doPost(MyApi.lujing,params,myCallback);
    }

}
