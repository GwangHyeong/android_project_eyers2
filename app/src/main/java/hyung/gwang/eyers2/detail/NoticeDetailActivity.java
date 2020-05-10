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

import org.json.JSONObject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import hyung.gwang.eyers2.R;
import hyung.gwang.eyers2.request.BoardDetailRequest;
import hyung.gwang.eyers2.request.ScanIdRequest;
import hyung.gwang.eyers2.scan.ScanIdActivity;

public class NoticeDetailActivity extends AppCompatActivity {
    TextView txtText;
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        setContentView(R.layout.activity_notice_detail);
        txtText = (TextView)findViewById(R.id.txtText);
        Button okBtn = (Button)findViewById(R.id.Button1);
        String key_id =(getIntent().getStringExtra("key_id"));
        Log.e("NoticeDetailActivity--", String.valueOf(key_id));


        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e(this.getClass().getName(), response);
                Log.e(this.getClass().getName(), "리스폰 값이 이거래");

                try {
                    JSONObject jsonResponse = new JSONObject(response); //json객체 생성.
                    String success = jsonResponse.getString("success"); //키값 success인거 가져오기
                    Log.e(this.getClass().getName(), String.valueOf(success));
                    txtText.setText(success);
                    //가져온 값이 false가 아닐경우.
                    if (!success.equals("false")) {
                        Log.e(this.getClass().getName(), "공지사항찾기성공");

                    } else {
                        Log.e(this.getClass().getName(), "공지사항찾기 실패");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "공지사항찾기 예외발생");
                }
            }
        };

        //Volley 라이브러리를 이용해서 실제 서버와 통신을 구현하는 부분
        BoardDetailRequest boardDetailRequest = new BoardDetailRequest(key_id, responseListener);
        RequestQueue queue = Volley.newRequestQueue(NoticeDetailActivity.this);  //Request를 보낼 queue를 생성한다.
        queue.add(boardDetailRequest);


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
