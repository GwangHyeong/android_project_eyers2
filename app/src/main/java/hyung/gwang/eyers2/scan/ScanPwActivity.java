package hyung.gwang.eyers2.scan;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import hyung.gwang.eyers2.R;
import hyung.gwang.eyers2.login.LoginActivity;
import hyung.gwang.eyers2.request.ScanPwRequest;
import hyung.gwang.eyers2.write.NewPwActivity;

public class ScanPwActivity extends AppCompatActivity {
    /**
     * 상단 선언부
     **/
    EditText studentnumberText;
    EditText nameText;
    EditText idText;
    Button scanpwButton;
    private AlertDialog dialog;
    String TAG = "ScanPwActivity";

    /**
     * 상단 선언부 종료
     **/
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_scanpw);

        //아이디 선언부 호출
        findviewbyid();
        //상태바 색 변경
        actionbarcolor();

        //패스워드 찾기 버튼 눌렀을때
        scanpwButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_studentnumber = studentnumberText.getText().toString();
                String user_name = nameText.getText().toString();
                String user_id = idText.getText().toString();

                Log.e(TAG, "비밀번호 찾기 입력값들 이름,학번,id 순");
                Log.e(TAG, user_name);
                Log.e(TAG, user_studentnumber);
                Log.e(TAG, user_id);

                //한칸이라도 빠뜨렸을 경우
                if (user_name.equals("") || user_studentnumber.equals("")|| user_id.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ScanPwActivity.this);
                    dialog = builder.setMessage("빈곳을 채워주세요")
                            .setNegativeButton("OK", null)
                            .create();
                    dialog.show();
                    return;
                }

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e(this.getClass().getName(), response);
                        Log.e(this.getClass().getName(), "리스폰 값이 이거래");
                        try {
                            JSONObject jsonResponse = new JSONObject(response); //json객체 생성.
                            String success = jsonResponse.getString("success"); //키값 success인거 가져오기
                            Log.e(this.getClass().getName(), String.valueOf(success));

                            //가져온 값이 false가 아닐경우.
                            if (!success.equals("false")) {
  //                              AlertDialog.Builder builder = new AlertDialog.Builder(ScanPwActivity.this);
                                String user_id = idText.getText().toString();
                                Log.e("pw찾기 보낸값",user_id);
                                Intent intent;
                                intent = new Intent(ScanPwActivity.this, NewPwActivity.class);
                                intent.putExtra("user_id",user_id);
                                ScanPwActivity.this.startActivity(intent);

//                                dialog = builder.setMessage("찾는 비밀번호는 : " + success + " 입니다.")
//                                        .setPositiveButton("확인", null)
//                                        .create();
//                                dialog.show();

                                Log.e(this.getClass().getName(), "pw찾기성공");

                                finish();

                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(ScanPwActivity.this);
                                dialog = builder.setMessage("정보를 다시 확인하세요")
                                        .setNegativeButton("다시시도", null)
                                        .create();
                                dialog.show();
                                Log.e(this.getClass().getName(), "pw찾기 실패");
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "pw찾기 예외발생");
                        }
                    }
                };

                //Volley 라이브러리를 이용해서 실제 서버와 통신을 구현하는 부분
                ScanPwRequest scanPwRequest = new ScanPwRequest(user_name, user_studentnumber,user_id, responseListener);
                RequestQueue queue = Volley.newRequestQueue(ScanPwActivity.this);  //Request를 보낼 queue를 생성한다.
                queue.add(scanPwRequest);
            }
        });
    }


    //아이디 선언부
    private void findviewbyid() {
        studentnumberText = (EditText) findViewById(R.id.studentnumberText);
        nameText = (EditText) findViewById(R.id.nameText);
        idText = (EditText) findViewById(R.id.idText) ;
        scanpwButton = (Button) findViewById(R.id.scanpwButton);
    }
    //상태바 색상 변경
    private void actionbarcolor() {
        //SoftDevelpmentKit 버전이 LOLLIPOP(21)과 같거나 더 높을때,
        //StatusBarColor 색상변경, 그밑에 버전들은 변경 안되는듯.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.parseColor("#8977ad"));
        }
    }

}
