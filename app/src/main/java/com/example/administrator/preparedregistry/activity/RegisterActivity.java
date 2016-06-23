package com.example.administrator.preparedregistry.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.preparedregistry.R;
import com.example.administrator.preparedregistry.XmlParserHandler;
import com.example.administrator.preparedregistry.adapter.ArrayWheelAdapter;
import com.example.administrator.preparedregistry.base.BaseActivity;
import com.example.administrator.preparedregistry.base.BaseApplication;
import com.example.administrator.preparedregistry.model.CityModel;
import com.example.administrator.preparedregistry.model.DistrictModel;
import com.example.administrator.preparedregistry.model.ProvinceModel;
import com.example.administrator.preparedregistry.widget.OnWheelChangedListener;
import com.example.administrator.preparedregistry.widget.WheelView;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by Administrator on 2016/6/17.
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener, OnWheelChangedListener {

    private Spinner spiIfHaveHome;
    private RelativeLayout layoutHomeCard, layoutHomePlace;
    private Button button;
    private ImageView ivBack;
    private TextView tvCity;
    private LinearLayout layoutCity;

    private WheelView mViewProvince;
    private WheelView mViewCity;
    private WheelView mViewDistrict;

    private Button btnCity;

    protected String[] mProvinceDatas;

    protected Map<String, String[]> mCitisDatasMap = new HashMap<String, String[]>();

    protected Map<String, String[]> mDistrictDatasMap = new HashMap<String, String[]>();

    protected Map<String, String> mZipcodeDatasMap = new HashMap<String, String>();

    protected String mCurrentProviceName;

    protected String mCurrentCityName;

    protected String mCurrentDistrictName ="";

    protected String mCurrentZipCode ="";


    @Override
    protected int initLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        spiIfHaveHome = bindView(R.id.register_spinner_ifhavehome);
        layoutHomeCard = bindView(R.id.register_layoutHomeCardNumber);
        layoutHomePlace = bindView(R.id.register_layoutHomePlace);
        button=bindView(R.id.register_btn);
        ivBack=bindView(R.id.register_back);
        layoutCity=bindView(R.id.register_layout_city);
        tvCity=bindView(R.id.register_text_city);
    }

    @Override
    protected void initData() {
        showDialog();

        // 是否有房产
        spiIfHaveHome.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] ifhavehome = getResources().getStringArray(R.array.ifhavehome);
                //切换为有时
                if (ifhavehome[position].equals("有")) {
                    layoutHomeCard.setVisibility(View.VISIBLE);
                    layoutHomePlace.setVisibility(View.VISIBLE);
                }
                if (ifhavehome[position].equals("无")){
                    layoutHomeCard.setVisibility(View.GONE);
                    layoutHomePlace.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ivBack.setOnClickListener(this);
        button.setOnClickListener(this);
        layoutCity.setOnClickListener(this);
    }

    public void showDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });


        View view = getLayoutInflater().inflate(R.layout.dialog_register, null);

        builder.setView(view);
        builder.setCancelable(false);
        builder.show();


    }

    public void cityDialog(){
        AlertDialog.Builder cityBuilder = new AlertDialog.Builder(this);
        View cityView = getLayoutInflater().inflate(R.layout.dialog_city, null);


        mViewProvince= (WheelView) cityView.findViewById(R.id.dialog_city_province);
        mViewCity= (WheelView) cityView.findViewById(R.id.dialog_city_city);
        mViewDistrict= (WheelView) cityView.findViewById(R.id.dialog_city_district);


        mViewProvince.addChangingListener(this);
        mViewCity.addChangingListener(this);
        mViewDistrict.addChangingListener(this);
        setUpData();

        cityBuilder.setView(cityView);

        cityBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tvCity.setText(mCurrentProviceName+mCurrentCityName+mCurrentDistrictName);
                tvCity.setTextColor(Color.parseColor("#323232"));
            }
        });
        cityBuilder.setCancelable(false);
        cityBuilder.show();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register_btn:

                // TODO  一系列发送数据到服务器的操作
                Intent intent=new Intent(RegisterActivity.this,RegisterFatherActivity.class);
                startActivity(intent);

                break;
            case R.id.register_back:
                finish();
                break;
            case R.id.register_layout_city:
                cityDialog();
                break;

        }
    }

    private void setUpData() {
        initProvinceDatas();
        mViewProvince.setViewAdapter(new ArrayWheelAdapter<String>(RegisterActivity.this, mProvinceDatas));
        mViewProvince.setVisibleItems(7);
        mViewCity.setVisibleItems(7);
        mViewDistrict.setVisibleItems(7);
        updateCities();
        updateAreas();
    }
    private void updateAreas() {
        int pCurrent = mViewCity.getCurrentItem();
        mCurrentCityName = mCitisDatasMap.get(mCurrentProviceName)[pCurrent];
        String[] areas = mDistrictDatasMap.get(mCurrentCityName);

        if (areas == null) {
            areas = new String[] { "" };
        }
        mViewDistrict.setViewAdapter(new ArrayWheelAdapter<String>(this, areas));
        mViewDistrict.setCurrentItem(0);

        //我做的修改 得到当前区名 并加载
        String [] district=mDistrictDatasMap.get(mCurrentCityName);
        if (district == null) {
            district = new String[] { "" };
        }
        mCurrentDistrictName=district[0];

    }
    private void updateCities() {
        int pCurrent = mViewProvince.getCurrentItem();
        mCurrentProviceName = mProvinceDatas[pCurrent];
        String[] cities = mCitisDatasMap.get(mCurrentProviceName);
        if (cities == null) {
            cities = new String[] { "" };
        }
        mViewCity.setViewAdapter(new ArrayWheelAdapter<String>(this, cities));
        mViewCity.setCurrentItem(0);
        updateAreas();
    }
    protected void initProvinceDatas()
    {
        List<ProvinceModel> provinceList = null;
//        AssetManager asset = getAssets();
        try {
//            InputStream input = asset.open("province_data.xml");
            InputStream input = BaseApplication.getContext().getClass().getClassLoader().getResourceAsStream("assets/"+"province_data.xml");
            // 创建一个解析xml的工厂对象
            SAXParserFactory spf = SAXParserFactory.newInstance();
            // 解析xml
            SAXParser parser = spf.newSAXParser();
            XmlParserHandler handler = new XmlParserHandler();
            parser.parse(input, handler);
            input.close();
            // 获取解析出来的数据
            provinceList = handler.getDataList();
            //*/ 初始化默认选中的省、市、区
            if (provinceList!= null && !provinceList.isEmpty()) {
                mCurrentProviceName = provinceList.get(0).getName();
                List<CityModel> cityList = provinceList.get(0).getCityList();
                if (cityList!= null && !cityList.isEmpty()) {
                    mCurrentCityName = cityList.get(0).getName();
                    List<DistrictModel> districtList = cityList.get(0).getDistrictList();
                    mCurrentDistrictName = districtList.get(0).getName();
                    mCurrentZipCode = districtList.get(0).getZipcode();
                }
            }

            //*/
            mProvinceDatas = new String[provinceList.size()];
            for (int i=0; i< provinceList.size(); i++) {
                mProvinceDatas[i] = provinceList.get(i).getName();
                List<CityModel> cityList = provinceList.get(i).getCityList();
                String[] cityNames = new String[cityList.size()];
                for (int j=0; j< cityList.size(); j++) {
                    cityNames[j] = cityList.get(j).getName();
                    List<DistrictModel> districtList = cityList.get(j).getDistrictList();
                    String[] distrinctNameArray = new String[districtList.size()];
                    DistrictModel[] distrinctArray = new DistrictModel[districtList.size()];
                    for (int k=0; k<districtList.size(); k++) {
                        DistrictModel districtModel = new DistrictModel(districtList.get(k).getName(), districtList.get(k).getZipcode());
                        mZipcodeDatasMap.put(districtList.get(k).getName(), districtList.get(k).getZipcode());
                        distrinctArray[k] = districtModel;
                        distrinctNameArray[k] = districtModel.getName();
                    }
                    mDistrictDatasMap.put(cityNames[j], distrinctNameArray);
                }
                mCitisDatasMap.put(provinceList.get(i).getName(), cityNames);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {

        }
    }

    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        if (wheel == mViewProvince) {
            updateCities();
        } else if (wheel == mViewCity) {
            updateAreas();
        } else if (wheel == mViewDistrict) {
            mCurrentDistrictName = mDistrictDatasMap.get(mCurrentCityName)[newValue];
            mCurrentZipCode = mZipcodeDatasMap.get(mCurrentDistrictName);
        }
    }
}
