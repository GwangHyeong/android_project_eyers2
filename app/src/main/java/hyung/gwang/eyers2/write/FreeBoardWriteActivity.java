package hyung.gwang.eyers2.write;

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
import hyung.gwang.eyers2.request.FreeBoardWriteRequest;

public class FreeBoardWriteActivity extends AppCompatActivity {

    /**
     * 상단선언부
     **/
    String TAG = "FreeBoardWriteActivity";

    EditText titleText;
    EditText nameText;
    EditText contentText;
    EditText dateText;
    EditText seqText;
    Button button;


    /**
     * 상단선언부 종료
     **/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freeboard_write);

        Log.e(TAG, "FreeBoardWriteActivity 실행");
        final String getuser = getIntent().getStringExtra("key_id");
        Log.e("유저아이디 데이터 넘어왔나 확인2,",getuser);

        Toast.makeText(this, "관리자 모드(공지사항작성)", Toast.LENGTH_SHORT).show();


        //아이디 선언부 호출
        findviewbid();

        nameText.setText(getuser);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long now = System.currentTimeMillis();
                //Date date = new Date(now);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", java.util.Locale.getDefault());
                Date date = new Date();
                String strDate = dateFormat.format(date);



                dateText.setText(strDate);

                String freeboard_title = titleText.getText().toString();
                String freeboard_name = nameText.getText().toString();
                String freeboard_content = titleText.getText().toString();
                String freeboard_date = dateText.getText().toString();
                String freeboard_seq = seqText.getText().toString();


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
                                Toast.makeText(FreeBoardWriteActivity.this, "작성 완료", Toast.LENGTH_LONG).show();
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
                FreeBoardWriteRequest freeboardWriteRequest = new FreeBoardWriteRequest(freeboard_title, freeboard_name, freeboard_content, freeboard_date, freeboard_seq,responseListener);
                RequestQueue queue = Volley.newRequestQueue(FreeBoardWriteActivity.this);
                queue.add(freeboardWriteRequest);
            }
        });
    }

    //아이디 선언
    private void findviewbid() {

        titleText = findViewById(R.id.titleText);
        nameText = findViewById(R.id.nameText);
        contentText = findViewById(R.id.contentText);
        dateText = findViewById(R.id.dateText);
        seqText = findViewById(R.id.seqText);
        button = findViewById(R.id.loginButton);

    }
}
