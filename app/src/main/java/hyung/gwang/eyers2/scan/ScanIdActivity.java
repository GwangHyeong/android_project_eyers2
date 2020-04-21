package hyung.gwang.eyers2.scan;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import hyung.gwang.eyers2.R;
import hyung.gwang.eyers2.request.ScanIdRequest;

public class ScanIdActivity extends AppCompatActivity {

    /**
     * 상단 선언부
     **/
    EditText studentnumberText;
    EditText nameText;
    Button scanidButton;
    private AlertDialog dialog;

    /**
     * 상단 선언부 종료
     **/
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_scanid);

        //아이디 선언부 호출
        findviewbyid();
        //상태바 색 변경
        actionbarcolor();
        //아이디 찾기 버튼 눌렀을 경우.
        scanidButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String user_studentnumber = studentnumberText.getText().toString();
                String user_name = nameText.getText().toString();

                Log.e("아이디찾기 입력값1", user_name);
                Log.e("아이디찾기 입력값2", user_studentnumber);
                //한칸이라도 빠뜨렸을 경우
                if (user_name.equals("") || user_studentnumber.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ScanIdActivity.this);
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
                                AlertDialog.Builder builder = new AlertDialog.Builder(ScanIdActivity.this);
                                dialog = builder.setMessage("찾는 아이디는 : " + success + " 입니다.")
                                        .setPositiveButton("확인", null)
                                        .create();
                                dialog.show();
                                Log.e(this.getClass().getName(), "id찾기성공");

                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(ScanIdActivity.this);
                                dialog = builder.setMessage("정보를 다시 확인하세요")
                                        .setNegativeButton("다시시도", null)
                                        .create();
                                dialog.show();
                                Log.e(this.getClass().getName(), "id찾기 실패");
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "id찾기 예외발생");
                        }
                    }
                };

                //Volley 라이브러리를 이용해서 실제 서버와 통신을 구현하는 부분
                ScanIdRequest scanIdRequest = new ScanIdRequest(user_name, user_studentnumber, responseListener);
                RequestQueue queue = Volley.newRequestQueue(ScanIdActivity.this);  //Request를 보낼 queue를 생성한다.
                queue.add(scanIdRequest);
            }

        });
    }


    //아이디 선언부
    private void findviewbyid() {
        studentnumberText = (EditText) findViewById(R.id.studentnumberText);
        nameText = (EditText) findViewById(R.id.nameText);
        scanidButton = (Button) findViewById(R.id.scanidButton);
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
