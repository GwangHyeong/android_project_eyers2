package hyung.gwang.eyers2.detail;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import androidx.appcompat.app.AppCompatActivity;
import hyung.gwang.eyers2.R;
import hyung.gwang.eyers2.request.BoardDetailRequest;
import hyung.gwang.eyers2.request.FreeBoardDetailRequest;
import hyung.gwang.eyers2.view.FreeBoardActivity;

public class FreeBoardDetailActivity extends AppCompatActivity {
    TextView txtText;
    TextView titleText;
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        setContentView(R.layout.activity_freeboard_detail);
        txtText = (TextView)findViewById(R.id.txtText);
        titleText = (TextView)findViewById(R.id.titleText) ;
        Button okBtn = (Button)findViewById(R.id.Button1);
        String key_id =(getIntent().getStringExtra("key_id"));
        Log.e("FreeBoardDetail--", String.valueOf(key_id));


        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e(this.getClass().getName(), response);
                Log.e(this.getClass().getName(), "리스폰 값이 이거래");

                try {
//                    JSONObject jsonResponse = new JSONObject(response); //json객체 생성..
//                    JSONObject jsonResponse2 = new JSONObject(response); //json객체 생성.

                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("response");
                    JSONObject object = jsonArray.getJSONObject(0);
                    String freeboard_content, freeboard_name, freeboard_date , freeboard_title;
                    int freeboard_seq;
                    int count = jsonArray.length();
                    freeboard_content = object.getString("freeboard_content");
                    freeboard_title = object.getString("freeboard_title");
//                    String success = jsonResponse.getString("freeboard_content"); //키값 success인거 가져오기
//                    String success2 = jsonResponse2.getString("freeboard_title"); //키값 success2인거 가져오기

                    Log.e(this.getClass().getName(), String.valueOf(response));
                    txtText.setText(freeboard_content);
                    titleText.setText(freeboard_title);

//                    //가져온 값이 false가 아닐경우.
//                    if (!success.equals("false")) {
//                        Log.e(this.getClass().getName(), "자유게시판찾기성공");
//
//                    } else {
//                        Log.e(this.getClass().getName(), "자유게시판 실패");
//                    }

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
    public void  mOk(View v){
        finish();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event){
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }
}

