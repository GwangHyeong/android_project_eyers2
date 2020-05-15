package hyung.gwang.eyers2.view;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.captaindroid.tvg.Tvg;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import hyung.gwang.eyers2.R;
import hyung.gwang.eyers2.adapter.FAQAdapter;
import hyung.gwang.eyers2.detail.FAQDetailActivity;


public class FAQActivity extends AppCompatActivity {
    private ExpandableListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        Display newDisplay = getWindowManager().getDefaultDisplay();
        int width = newDisplay.getWidth();

        TextView titleView = (TextView)findViewById(R.id.titleView);
        //텍스트 그레디언트
        Tvg.change(titleView, Color.parseColor("#FFFFFF"), Color.parseColor("#3BA3F2"));
        Tvg.change((TextView) findViewById(R.id.titleView), new int[]{
                Color.parseColor("#F97C3C"),
                Color.parseColor("#FDB54E"),
                Color.parseColor("#FFFFFF"),
                Color.parseColor("#478AEA"),
                Color.parseColor("#8446CC"),
        });

        ArrayList<FAQDetailActivity> DataList = new ArrayList<FAQDetailActivity>();
        listView = (ExpandableListView) findViewById(R.id.mylist);
        FAQDetailActivity temp = new FAQDetailActivity("Q.1 App 오류사항이나 문의는 어디서 하나요?");
        temp.child.add("개발자 이메일 gh9383@daum.net 문의 하시면 됩니다.");
        DataList.add(temp);
        temp = new FAQDetailActivity("Q.2 무슨 App인지 궁금해요");
        temp.child.add("2020년 1학기 프로젝트로 진행된 학과 학술동아리 -EyErs- 의 커뮤니티 APP 입니다 ");
        DataList.add(temp);
        temp = new FAQDetailActivity("Q.3 -----------");
        temp.child.add("추가해야함");
        DataList.add(temp);

        FAQAdapter adapter = new FAQAdapter(getApplicationContext(), R.layout.group_row, R.layout.child_row, DataList);
        listView.setIndicatorBounds(width - 50, width); //이 코드를 지우면 화살표 위치가 바뀐다.
        listView.setAdapter(adapter);
    }
}