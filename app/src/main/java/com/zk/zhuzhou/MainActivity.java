package com.zk.zhuzhou;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;
import android.provider.Settings.Secure;

import com.zk.zhuzhou.EventBus.EventComm;
import com.zk.zhuzhou.EventBus.MqttEvent;
import com.zk.zhuzhou.Utils.L;
import com.zk.zhuzhou.service.MqttService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Method;



public class MainActivity extends AppCompatActivity {


    private String mDeviceID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);

        initToolBar();

        mDeviceID = Secure.getString(this.getContentResolver(), Secure.ANDROID_ID);
        EventBus.getDefault().register(this);
      //  startService(new Intent(MainActivity.this, CommService.class));
        initMQTT();
    }

    /*
  初始化actionbar
  */
    private void initToolBar() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setLogo(R.drawable.mjlogo);
        toolbar.inflateMenu(R.menu.actionbar_menu);//设置右上角的填充菜单
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.action_settings:
                        startActivity(new Intent(MainActivity.this, SetConfigureActivity.class));
                        break;
                }
                return true;
            }
        });
    }



    /**
     * 初始化mqtt消息推送
     *
     * @author Yan jiepeng
     * @time 2016/7/14 8:51
     */

    private void initMQTT() {

        String[] topics = new String[]{"test"};
        Intent intent = new Intent(this , MqttService.class) ;
        intent.putExtra("topics" , topics) ;
        startService(intent);
    }




    /*
     收到MQTT消息回掉此处
     */
    @Subscribe (threadMode = ThreadMode.MAIN)
    public void GetMqttData(MqttEvent event) {
            if (event != null ){
                L.e(event.getMsg()+event.getTopic());
            }
    }

    /*
    处理串口数据
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void GetCommData(EventComm eventComm) {
        switch (eventComm.getCommId()) {
            case 1:
                Toast.makeText(MainActivity.this, new String(eventComm.getData()), Toast.LENGTH_SHORT).show();
                break;

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (menu != null) {
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
                try {
                    Method m = menu.getClass().getDeclaredMethod(
                            "setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {
                    Log.e(getClass().getSimpleName(), "onMenuOpened...unable to set icons for overflow menu", e);
                }
            }
        }
        return super.onPrepareOptionsMenu(menu);
    }


}
