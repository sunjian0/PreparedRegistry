package com.example.administrator.preparedregistry.net;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2016/7/8.
 */
public interface WebServiceListener {

    void getSuccess(ArrayList<HashMap<String, Object>> list);
    void getFailed(String s);
}
