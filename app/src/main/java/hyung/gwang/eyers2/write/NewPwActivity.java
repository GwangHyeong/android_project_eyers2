package hyung.gwang.eyers2.write;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import androidx.appcompat.app.AppCompatActivity;
import hyung.gwang.eyers2.R;
import hyung.gwang.eyers2.detail.FreeBoardDetailActivity;
import hyung.gwang.eyers2.request.FreeBoardCommentWriteRequest;
import hyung.gwang.eyers2.request.NewPwRequest;

public class NewPwActivity extends AppCompatActivity {

    /**
     * 상단선언부
     **/
    String TAG = "FreeBoardCommentWriteActivity";

    //AdminWriteActivity adminWriteActivity;
    TextView titleText;
    TextView nameText;
    EditText contentText;
    TextView freeboardText;
    TextView dateText;
    TextView seqText;
    Button button;
    Button okBtn;
    TextView txtText;

    /**
     * 상단선언부 종료
     **/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        // Popup의 Title을 제거
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_new_pw);





        Toast.makeText(this, "새로운 비밀번호를 입력하세요", Toast.LENGTH_SHORT).show();


        //아이디 선언부 호출.
        findviewbyidset();

        String getuser = getIntent().getStringExtra("user_id");
        Log.e("유저아이디 데이터 넘어왔나 확인,",getuser);
        nameText.setText(getuser);

        //adminWriteActivity.realtimecomment();
    }
    public void  mOk(View v){

        String newpw_content = contentText.getText().toString();
        String newpw_getuser = nameText.getText().toString();

        //공지사항 작성 시작
        Response.Listener<String> responseListener = new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e(this.getClass().getName(), response);
                Log.e(this.getClass().getName(), "리스폰 값이 이거래");
                Log.e(this.getClass().getName(), "게시판댓글 작성 시작");
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    Log.e(this.getClass().getName(), String.valueOf(success));

                    if (success) {//사용할 수 있는 아이디라면
                        Toast.makeText(NewPwActivity.this, "변경 완료", Toast.LENGTH_LONG).show();
                        Log.e(this.getClass().getName(), "변경 성공");
                        Log.e(this.getClass().getName(), String.valueOf(jsonResponse));

                        finish();//액티비티를 종료시킴(공지사항 작성창을 닫음)
                    } else {//사용할 수 없는 아이디라면
                        Log.e(this.getClass().getName(), "변경 실패");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "예외발생");

                }

            }
        };//Response.Listener 완료.

        //Volley 라이브러리를 이용해서 실제 서버와 통신을 구현하는 부분
        NewPwRequest newPwRequest = new NewPwRequest(newpw_content,newpw_getuser,responseListener);
        RequestQueue queue = Volley.newRequestQueue(NewPwActivity.this);
        queue.add(newPwRequest);


        finish();


    }

    //아이디 선언
    private void findviewbyidset() {

        nameText = findViewById(R.id.nameText);
        contentText = findViewById(R.id.contentText);
        seqText = findViewById(R.id.seqText);
        freeboardText = findViewById(R.id.freeboardText);
        button = findViewById(R.id.Button1);

    }

}