package cn.com.foton.Will;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.RequestBody;

import java.util.List;

import cn.com.foton.Color.MaterialColor;
import cn.com.foton.Dbhelper.jsonT;
import cn.com.foton.R;
import cn.com.foton.Util.LogUtil;
import cn.com.foton.Util.UserUtils;
import cn.com.foton.Util.myHttp2;
import cn.com.foton.base.HistoryBase;
import cn.com.foton.base.ResultHistory;
import cn.com.foton.base.userBase;
import cn.com.foton.data.App_url;

public class HistoryActivity extends AppCompatActivity {

    private RequestBody body;
    private String fcmCustomerId,fcmBusinessId;
    Context context=HistoryActivity.this;
    private List<HistoryBase.msgBean> list2;
    private ResultHistory.msg basss=null;
    private ExpandableListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        ((LinearLayout)findViewById(R.id.L_GONE2)).setBackgroundColor(MaterialColor.BLUE_GERY);

        lv = (ExpandableListView) findViewById(R.id.list_record);
        initBody();

        new myHttp2(body, App_url.resultHistory, h, 1,context).start();

    }
    private void initBody() {

        fcmCustomerId=getIntent().getStringExtra("fcmCustomerId");
        fcmBusinessId=getIntent().getStringExtra("fcmBusinessId");
        userBase user = UserUtils.getUserBase(this);
        // TODO Auto-generated method stub
        body = new FormEncodingBuilder()
                .add("fcmVisit.fcmUserId", user.getFcmUserId())
                .add("fcmVisit.fcmPositionId", user.getFcmPositionId())
                .add("fcmVisit.fcmCompanyCode", user.getFcmCompanyCode())
                .add("fcmVisit.fcmDealerCode", user.getFcmDealerCode())
                .add("fcmVisit.fcmCustomerId",fcmCustomerId)
                .add("fcmVisit.fcmBusinessId", fcmBusinessId)
                .build();

    }


    Handler h=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String jsonstring = (String) msg.obj;
            LogUtil.e("",jsonstring);
            if (!jsonstring.contains("没有此计划")) {


            LogUtil.e(context, jsonstring);
            jsonstring = jsonT.Getjson(jsonstring);
            Gson g = new Gson();
                HistoryBase base = g.fromJson(jsonstring, HistoryBase.class);
            list2 = base.msg;


            //Recordadapter adapter=new Recordadapter(list3);
            Adapter adapter = new Adapter(context, list2);
            lv.setAdapter(adapter);
             }
        }
    };
}
