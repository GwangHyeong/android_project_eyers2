package hyung.gwang.eyers2.login;

import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.captaindroid.tvg.Tvg;

import org.json.JSONObject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import hyung.gwang.eyers2.MainActivity;
import hyung.gwang.eyers2.R;
import hyung.gwang.eyers2.request.LoginRequest;
import hyung.gwang.eyers2.scan.ScanIdActivity;
import hyung.gwang.eyers2.scan.ScanPwActivity;
import hyung.gwang.eyers2.thread.HttpMgrThread;
import hyung.gwang.eyers2.view.AdminActivity;

public class LoginActivity extends AppCompatActivity {

    private AlertDialog dialog;
    public static int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //네트워크 연결됬는지 확인
        networkconnected();

        //액션바 색상 변경
        actionbarcolor();

        HttpMgrThread httpMgrThread = new HttpMgrThread();
        httpMgrThread.start();

        //선언
        TextView registerButton = (TextView) findViewById(R.id.registerButton);
        TextView idButton = (TextView) findViewById(R.id.idButton);
        TextView pwButton = (TextView) findViewById(R.id.pwButton);
        TextView titleView = (TextView)findViewById(R.id.txtDYPatil);

        Tvg.change(titleView, Color.parseColor("#FFFF00"), Color.parseColor("#3BA3F2"));
        Tvg.change((TextView) findViewById(R.id.titleView), new int[]{
                Color.parseColor("#FFFF00"),
                Color.parseColor("#FDB54E"),
                Color.parseColor("#64B678"),
                Color.parseColor("#478AEA"),
                Color.parseColor("#8446CC"),
        });

        //회원가입 버튼이 눌리면 회원가입으로
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });
        //아이디 찾기 버튼 눌렀을때.
        idButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent idIntent = new Intent(LoginActivity.this, ScanIdActivity.class);
                LoginActivity.this.startActivity(idIntent);
            }
        });
        //패스워드 찾기 버튼 눌렀을때.
        pwButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pwIntent = new Intent(LoginActivity.this, ScanPwActivity.class);
                startActivity(pwIntent);
            }
        });

        //선언
        final EditText idText = (EditText) findViewById(R.id.idText);
        final EditText passwordText = (EditText) findViewById(R.id.passwordText);
        final Button loginButton = (Button) findViewById(R.id.loginButton);

        /***로그인 버튼을 눌렀을 경우.***/
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**텍스트에 입력된 값 가져와서 String 에 넣기..**/
                final String user_id = idText.getText().toString();
                String user_pw = passwordText.getText().toString();
                Log.e(this.getClass().getName(), "로그인버튼 입력");

                if(user_id.equals("admin")){
                    if(user_pw.equals("123456")){
                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                        dialog = builder.setMessage("관리자 모드")
                                .setPositiveButton("확인", null)
                                .create();
                        dialog.show();
                        Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
                        LoginActivity.this.startActivity(intent);
                        finish();
                    }
                }

                Response.Listener<String> responseLisner = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response); //제이슨객채 생성
                            boolean success = jsonResponse.getBoolean("success"); // boolean 타입으로 키가 success인 값 가져오기

                            //로그인성공시
                            if (success) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                dialog = builder.setMessage("로그인에 성공했습니다")
                                        .setPositiveButton("확인", null)
                                        .create();
                                dialog.show();
                                Log.e(this.getClass().getName(), "로그인성공");

                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                LoginActivity.this.startActivity(intent);
                                intent.putExtra("key_id",user_id);
                                Log.e(this.getClass().getName(), "아이디 넘기는부분 login 할떄 -------"+user_id);
                                finish();
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                dialog = builder.setMessage("계정을 다시 확인하세요")
                                        .setNegativeButton("다시시도", null)
                                        .create();
                                dialog.show();
                                Log.e(this.getClass().getName(), "로그인실패");
                            }

                            //예외발생시
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "로그인 예외발생");
                        }
                    }
                };

                //Volley라이브러리 사용해서 값 전달하기
                LoginRequest loginRequest = new LoginRequest(user_id, user_pw, responseLisner);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);

            }
        });

    }

    //네트워크 연결됬는지 확인 해주는 함수.
    private void networkconnected() {
        //데이터 연결 여부확인해보자!!!
        if (false == isConnected()) {
            Toast.makeText(this, "네트워크가 연결되지 않았습니다.", Toast.LENGTH_LONG).show();
            return;
        }
        switch (getNetworkType()) {
            case ConnectivityManager.TYPE_WIFI:
                Toast.makeText(this, "WiFi에 연결 됐습니다.", Toast.LENGTH_LONG).show();

                HttpMgrThread httpThread = new HttpMgrThread();
                httpThread.start();
                break;

            case ConnectivityManager.TYPE_MOBILE:
                Toast.makeText(this, "모바일 네트워크에 연결됐습니다.", Toast.LENGTH_LONG).show();
                break;

            default:
                break;
        }
    }

    private int getNetworkType() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetWork = cm.getActiveNetworkInfo();

        return activeNetWork.getType();
    }

    //네트워크 연결 됬나 안됬나.
    private boolean isConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetWork = cm.getActiveNetworkInfo();

        boolean isConnected = activeNetWork != null && activeNetWork.isConnectedOrConnecting();

        return isConnected;
    }

    //액션바 색상 변경
    private void actionbarcolor() {
        //SoftDevelpmentKit 버전이 LOLLIPOP(21)과 같거나 더 높을때,
        //StatusBarColor 색상변경, 그밑에 버전들은 변경 안되는듯.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.parseColor("#8977ad"));
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (dialog != null) {//다이얼로그가 켜져있을때 함부로 종료가 되지 않게함
            dialog.dismiss();
            dialog = null;
        }
    }


}
