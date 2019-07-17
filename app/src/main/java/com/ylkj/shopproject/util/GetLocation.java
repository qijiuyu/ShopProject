package com.ylkj.shopproject.util;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.ylkj.shopproject.application.APPAplication;
import com.zxdc.utils.library.util.SPUtil;

/**
 * 定位
 * Created by Administrator on 2017/3/15 0015.
 */
public class GetLocation {

    private static GetLocation getLocation;
    private LocationClient mLocClient;
    public MyLocationListenner myListener = new MyLocationListenner();
    public static GetLocation getInstance() {
        if (null == getLocation) {
            getLocation = new GetLocation();
        }
        return getLocation;
    }

    /**
     * 设置定位
     */
    public void setLocation(Context mContext) {
        mLocClient = new LocationClient(mContext.getApplicationContext());
        mLocClient.registerLocationListener(myListener);

        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置火星坐标
        option.setScanSpan(10000);
        option.setIsNeedAddress(true);
        mLocClient.setLocOption(option);
        mLocClient.start();
    }


    /**
     * 定位SDK监听函数
     */
    public class MyLocationListenner implements BDLocationListener {
        public void onReceiveLocation(BDLocation location) {
            Message message = new Message();
            //GPS定位成功、网络定位成功、离线定位成功
            if (location.getLocType() == BDLocation.TypeGpsLocation ||
                    location.getLocType() == BDLocation.TypeNetWorkLocation ||
                    location.getLocType() == BDLocation.TypeOffLineLocation) {
                MyLocationData locData = new MyLocationData.Builder()
                        .accuracy(0)//0：去掉蓝色小图标
                        // 此处设置开发者获取到的方向信息，顺时针0-360
                        .latitude(location.getLatitude())
                        .longitude(location.getLongitude()).build();

                final Double longtitude = location.getLongitude();
                final Double latitude = location.getLatitude();
                final LatLng latLng=new LatLng(latitude,longtitude);
                SPUtil.getInstance(APPAplication.application).addObject(SPUtil.LATLNG,latLng);
            }
            stopLocation();
        }

        public void onReceivePoi(BDLocation poiLocation) {
        }
    }


    /**
     * 停止定位
     */
    public void stopLocation() {
        if (null != mLocClient) {
            mLocClient.unRegisterLocationListener(myListener);
            mLocClient.stop();
            mLocClient=null;
        }
    }
}
