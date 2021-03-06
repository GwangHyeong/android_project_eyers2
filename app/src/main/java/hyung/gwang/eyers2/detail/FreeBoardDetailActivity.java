package hyung.gwang.eyers2.detail;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONObject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import hyung.gwang.eyers2.R;
import hyung.gwang.eyers2.fragment.FreeBoardFragmentActivity;
import hyung.gwang.eyers2.request.BoardDetailRequest;
import hyung.gwang.eyers2.request.FreeBoardDetailRequest;
import hyung.gwang.eyers2.view.FreeBoardActivity;
import hyung.gwang.eyers2.write.FreeBoardCommentWriteActivity;
import hyung.gwang.eyers2.write.FreeBoardWriteActivity;

public class FreeBoardDetailActivity extends AppCompatActivity {
    /**
     * 상단선언부
     */
    TextView txtText;
    TextView titleText;
    TextView testText;
    Button button11;
    Button button22;
    Button button33;
    //프래그먼트들
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private FreeBoardFragmentActivity FreeBoardFragmentActivity;

    /**
     * 상단선언부 종료
     */
    @Override
    protected void onCreate(Bundle bundle) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(bundle);

        setContentView(R.layout.activity_freeboard_detail);

        FloatingActionButton fab = findViewById(R.id.fab);
        final String key_id = (getIntent().getStringExtra("key_id"));
        Log.e("FreeBoardDetail--", String.valueOf(key_id));
        final String getuser = (getIntent().getStringExtra("getuser"));
        Log.e("FreeBoardDetail--", String.valueOf(getuser));

        //프래그먼트
        fragmentManager = getSupportFragmentManager();
        FreeBoardFragmentActivity = new FreeBoardFragmentActivity();
        transaction = fragmentManager.beginTransaction();
        //transaction.hide(FreeBoardFragmentActivity);
        transaction.replace(R.id.frameLayout, FreeBoardFragmentActivity).commitAllowingStateLoss();
        //아이디선언부 호출
        findViewByIdset();
        //프래그먼트 값 전달.
        Bundle bundle1 = new Bundle();
        bundle1.putString("key_id", key_id);
        bundle1.putString("getuser", getuser);
        FreeBoardFragmentActivity.setArguments(bundle1);
        //Bundle bundle2 = new Bundle();
        //FreeBoardFragmentActivity.setArguments(bundle2);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent;
                //팝업 띄우기
                intent = new Intent(FreeBoardDetailActivity.this, FreeBoardCommentWriteActivity.class);

                intent.putExtra("key_id",key_id);
                intent.putExtra("getuser",getuser);
                Log.e("댓글작성할때 값 전달 --",key_id + "키아이디");
                Log.e("댓글작성할때 값 전달 --",getuser+"겟유저");
                startActivity(intent);
                finish();
            }
        });

        //안드로이드 값 전달> php 통신 > DB > php > 안드로이드에서 값 수신.
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e(this.getClass().getName(), response);
                Log.e(this.getClass().getName(), "리스폰 값이 이거래");

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("response");
                    JSONObject object = jsonArray.getJSONObject(0); //어차피 한줄이니까 0값주자~
                    String freeboard_content, freeboard_name, freeboard_date, freeboard_title;
                    int freeboard_seq;
                    int count = jsonArray.length();
                    freeboard_content = object.getString("freeboard_content");
                    freeboard_title = object.getString("freeboard_title");

                    Log.e(this.getClass().getName(), String.valueOf(response));
                    txtText.setText(freeboard_content);
                    titleText.setText(freeboard_title);


                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "자유게시판찾기 예외발생");
                }
            }
        };

        //Volley 라이브러리를 이용해서 실제 서버와 통신을 구현하는 부분
        FreeBoardDetailRequest freeboardDetailRequest = new FreeBoardDetailRequest(key_id, responseListener);
        RequestQueue queue = Volley.newRequestQueue(FreeBoardDetailActivity.this);  //Request를 보낼 queue를 생성한다.
        queue.add(freeboardDetailRequest);


    }

    private void findViewByIdset() {
        txtText = (TextView) findViewById(R.id.txtText);
        titleText = (TextView) findViewById(R.id.titleText);
        //testText = (TextView) findViewById(R.id.testtext);
        button11 = (Button) findViewById(R.id.button11);

    }

    //프래그먼트 클릭
    public void clickHandler(View view) {
        transaction = fragmentManager.beginTransaction();
        transaction.show(FreeBoardFragmentActivity);
        Log.e("buttontest", "프리보드프래그먼트");


    }

    public void mOk(View v) {
        finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
            return false;
        }
        return true;
    }
}

