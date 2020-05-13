package hyung.gwang.eyers2.view;

import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;

import com.captaindroid.tvg.Tvg;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import hyung.gwang.eyers2.R;
import hyung.gwang.eyers2.adapter.MemberAdapter;
import hyung.gwang.eyers2.request.EyersMemberActivity;

public class MemberActivity extends AppCompatActivity {

    ArrayList<EyersMemberActivity> al = new ArrayList<EyersMemberActivity>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);

        // ListView 로 만들기
        //    1. 다량의 데이터
        //    2. Adapter (데이터와 view의 연결 관계를 정의)
        //    3. AdapterView (ListView)
        EyersMemberActivity m1 = new EyersMemberActivity();
        m1.img = R.drawable.logo;
        m1.name = "이름 : 이광형 학번 : 201562042";
        m1.skill = "전공 : 스마트미디어 기술분야 : Android" ;
        m1.homepage="홈페이지 : https://github.com/GwangHyeong";
        m1.email="이메일 : rhkdgud61@naver.com";
        al.add(m1);
        al.add(new EyersMemberActivity(R.drawable.logo_vi2,"이름 : 김우재 학번 : 201512014","전공 : 스마트미디어 기술분야 : Web","홈페이지 : www.naver.com","이메일 : kwj1270@naver.com"));

        TextView titleView = (TextView)findViewById(R.id.titleView);
        //텍스트 그레디언트
        Tvg.change(titleView, Color.parseColor("#800CDD"), Color.parseColor("#3BA3F2"));
        Tvg.change((TextView) findViewById(R.id.titleView), new int[]{
                Color.parseColor("#F97C3C"),
                Color.parseColor("#FDB54E"),
                Color.parseColor("#64B678"),
                Color.parseColor("#478AEA"),
                Color.parseColor("#8446CC"),
        });

        //adapter
        MemberAdapter adapter = new MemberAdapter(getApplicationContext(),R.layout.member_list_row,al);

        ListView lv = (ListView)findViewById(R.id.listView1);
        lv.setAdapter(adapter);
    }
}