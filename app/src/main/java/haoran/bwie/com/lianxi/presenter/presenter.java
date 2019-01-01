package haoran.bwie.com.lianxi.presenter;

import com.google.gson.Gson;

import java.util.HashMap;

import haoran.bwie.com.lianxi.bean.MyBean;
import haoran.bwie.com.lianxi.callback.MyCallback;
import haoran.bwie.com.lianxi.model.ModelImpl;
import haoran.bwie.com.lianxi.view.ViewImpl;

public class presenter {
    private ViewImpl view;
    private ModelImpl model;

    public presenter(ViewImpl view) {
        this.view = view;
        model = new ModelImpl();
    }

    public void login(HashMap<String,String>params){
        if (view!=null){
            model.Model(params, new MyCallback() {
                @Override
                public void success(String mybean) {
                    Gson gson = new Gson();
                    MyBean myBean = gson.fromJson(mybean, MyBean.class);
                    view.success(myBean);
                }

                @Override
                public void falure(String msg) {
                    view.error(msg);
                }
            });
        }
    }
}
