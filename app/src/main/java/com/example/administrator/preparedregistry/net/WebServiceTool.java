package com.example.administrator.preparedregistry.net;

import android.os.AsyncTask;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sunjian on 2016/7/8.
 * <p>
 * 自己封装的异步任务请求
 */
public class WebServiceTool {


    private String result;
    private String nameSpace;
    private String endPoint;
    private String methodName;
    private String soapAction;

    private Map<String, Object> requestDatas;
    WebServiceListener webServiceListener;
    HashMap<String, Object> map;
    ArrayList<HashMap<String, Object>> list;

    public WebServiceTool(String methodName, Map<String, Object> RequestDatas) {
        super();
        this.methodName = methodName;
        this.requestDatas = RequestDatas;
        this.webServiceListener = webServiceListener;
    }

    public void setWebServiceListener(WebServiceListener webServiceListener){
        this.webServiceListener=webServiceListener;
        setData();
    }

    public void setData() {

        list = new ArrayList<>();  // 传递信息的list

        QueryAddressTask queryAddressTask = new QueryAddressTask();
        queryAddressTask.execute();

    }


    public ArrayList<HashMap<String, Object>> getRemoteInfo() {
        // 命名空间
        nameSpace = "http://tempuri.org/";
        // 调用的方法名称

        // EndPoint
        endPoint = "http://192.168.0.40:9100/ws.asmx";
        // SOAP Action
        soapAction = "http://tempuri.org/" + methodName;

        // 指定WebService的命名空间和调用的方法名
        SoapObject rpc = new SoapObject(nameSpace, methodName);

        if (requestDatas != null && !requestDatas.isEmpty()) {
            for (Map.Entry<String, Object> entry : requestDatas.entrySet()) {
                rpc.addProperty(entry.getKey(), entry.getValue());
            }
        }


        // 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
        SoapSerializationEnvelope envelope =
                new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.bodyOut = rpc;//由于是发送请求，所以是设置bodyOut
        // 设置是否调用的是dotNet开发的WebService
        envelope.dotNet = true;

        // 等价于envelope.bodyOut = rpc;
//        envelope.setOutputSoapObject(rpc);

        HttpTransportSE transport = new HttpTransportSE(endPoint);

        try {
            // 调用WebService
            transport.call(soapAction, envelope);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

//        Log.d("sss", envelope.bodyIn.toString());
//        // 获取返回的数据
//        SoapObject object = (SoapObject) envelope.bodyIn;
//        // 获取返回的结果
//        result = object.getProperty(0).toString();
//        Log.i("sss", "获取内容" + result);
////        // 将WebService返回的结果显示在TextView中
////        resultView.setText(result);


        SoapObject result = null;
        try {
            result = (SoapObject) envelope.getResponse();

        } catch (SoapFault soapFault) {
            soapFault.printStackTrace();
        }

        if (methodName.equals("GetPCS")) {
            result = (SoapObject) result.getProperty(1);
            result = (SoapObject) result.getProperty(0);
            for (int i = 0; i < result.getPropertyCount(); i++) {
                map = new HashMap<String, Object>();
                SoapObject soap = (SoapObject) result.getProperty(i);
                String PCSM = soap.getProperty("PCSM").toString();
                String PCSSM = soap.getProperty("PCSSM").toString();
                String XQ = soap.getProperty("XQ").toString();
                map.put("PCSM", PCSM);
                map.put("PCSSM", PCSSM);
                map.put("XQ", XQ);


                list.add(map);
            }
        }


        return list;
    }


    class QueryAddressTask extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... params) {
            // 查询手机号码（段）信息*/
            try {

                list = getRemoteInfo();


                webServiceListener.getSuccess(list);

            } catch (Exception e) {
                e.printStackTrace();
            }
            //将结果返回给onPostExecute方法
            return result;
        }

    }


}
