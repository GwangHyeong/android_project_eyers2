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
import android.app.AlertDialog;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;
import hyung.gwang.eyers2.R;
import hyung.gwang.eyers2.detail.FreeBoardDetailActivity;
import hyung.gwang.eyers2.request.FreeBoardCommentWriteRequest;
import hyung.gwang.eyers2.write.AdminWriteActivity;
import hyung.gwang.eyers2.request.FreeBoardWriteRequest;

public class FreeBoardCommentWriteActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_freeboard_comment_write);


        Log.e(TAG, "FreeBoardCommentWriteActivity 실행");
        final String key_id = getIntent().getStringExtra("key_id");
        final String getuser = getIntent().getStringExtra("getuser");
        Log.e("유저아이디 데이터 넘어왔나 확인2,",key_id);
        Log.e("유저아이디 데이터 넘어왔나 확인2,",getuser);

        Toast.makeText(this, "댓글작성", Toast.LENGTH_SHORT).show();


        //아이디 선언부 호출.
        findviewbyidset();

        freeboardText.setText(key_id);
        nameText.setText(getuser);
        //freeboardText.setText(key_)


       //adminWriteActivity.realtimecomment();
    }
    public void  mOk(View v){

        String freeboardcomment_content = contentText.getText().toString();
        String freeboardcomment_name = nameText.getText().toString();
        String freeboardcomment_freeboard = freeboardText.getText().toString();
        String freeboardcomment_seq = seqText.getText().toString();


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
                        Toast.makeText(FreeBoardCommentWriteActivity.this, "작성 완료", Toast.LENGTH_LONG).show();
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
        };//Response.Listener 완료.

        //Volley 라이브러리를 이용해서 실제 서버와 통신을 구현하는 부분
        FreeBoardCommentWriteRequest freeboardCommentWriteRequest = new FreeBoardCommentWriteRequest(freeboardcomment_content, freeboardcomment_name, freeboardcomment_freeboard, freeboardcomment_seq,responseListener);
        RequestQueue queue = Volley.newRequestQueue(FreeBoardCommentWriteActivity.this);
        queue.add(freeboardCommentWriteRequest);

        //기존액티비티를 종료하고 댓글작성시 다시 액티비티를 여는 방법
        //이방법이 맞는지는 모르겠다.
        Intent intent;
        intent = new Intent(FreeBoardCommentWriteActivity.this, FreeBoardDetailActivity.class);

        intent.putExtra("key_id",freeboardcomment_freeboard);
        intent.putExtra("getuser",freeboardcomment_name);
        Log.e("댓글작성할때 값 전달 --",freeboardcomment_freeboard + "키아이디");
        Log.e("댓글작성할때 값 전달 --",freeboardcomment_name+"겟유저");
        startActivity(intent);


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
