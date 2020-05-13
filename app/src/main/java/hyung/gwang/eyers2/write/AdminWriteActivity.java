package hyung.gwang.eyers2.write;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;
import hyung.gwang.eyers2.R;
import hyung.gwang.eyers2.request.NoticeWriteRequest;

public class AdminWriteActivity extends AppCompatActivity {

    /**
     * 상단선언부
     **/
    String TAG = "AdminWriteActivity";
    public static Context mContext;
    EditText editText;
    EditText editText2;
    EditText editText3;
    EditText editText4;
    Button button;


    /**
     * 상단선언부 종료
     **/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_write);
        mContext = this;
        Log.e(TAG, "AdminWriteActivity 실행");

        Toast.makeText(this, "관리자 모드(공지사항작성)", Toast.LENGTH_SHORT).show();


        //아이디 선언부 호출
        findviewbid();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                realtimenotice(); //리스트뷰 뿌리는 메서드 만들기.

            }


        });
    }

    public void realtimenotice(){
        long now = System.currentTimeMillis();
        //Date date = new Date(now);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", java.util.Locale.getDefault());
        Date date = new Date();
        String strDate = dateFormat.format(date);



        editText3.setText(strDate);

        String notice_content = editText.getText().toString();
        String notice_name = editText2.getText().toString();
        String notice_date = editText3.getText().toString();
        String notice_seq = editText4.getText().toString();
        //공지사항 작성 시작
        Response.Listener<String> responseListener = new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e(this.getClass().getName(), response);
                Log.e(this.getClass().getName(), "리스폰 값이 이거래");
                Log.e(this.getClass().getName(), "공지사항 작성 시작");
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    Log.e(this.getClass().getName(), String.valueOf(success));

                    if (success) {//사용할 수 있는 아이디라면
                        Toast.makeText(AdminWriteActivity.this, "작성 완료", Toast.LENGTH_LONG).show();
                        Log.e(this.getClass().getName(), "작성성공");
                        Log.e(this.getClass().getName(), String.valueOf(jsonResponse));

                        finish();//액티비티를 종료시킴(공지사항 작성창을 닫음)
                    } else {//사용할 수 없는 아이디라면
                        Log.e(this.getClass().getName(), "작성실패");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "예외발생");

                }
            }
        };//Response.Listener 완료

        //Volley 라이브러리를 이용해서 실제 서버와 통신을 구현하는 부분
        NoticeWriteRequest noticeWriteRequest = new NoticeWriteRequest(notice_content, notice_name, notice_date, notice_seq, responseListener);
        RequestQueue queue = Volley.newRequestQueue(AdminWriteActivity.this);
        queue.add(noticeWriteRequest);
    }

    //아이디 선언
    private void findviewbid() {

        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.nameText);
        editText3 = findViewById(R.id.dateText);
        editText4 = findViewById(R.id.seqText);
        button = findViewById(R.id.button2);

    }
}
