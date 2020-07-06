package hyung.gwang.eyers2.login;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import hyung.gwang.eyers2.R;
import hyung.gwang.eyers2.request.RegisterRequest;
import hyung.gwang.eyers2.request.ValidateRequest;

public class RegisterActivity extends AppCompatActivity {

    private ArrayAdapter adapter;
    private Spinner spinner;
    private String user_id;
    private String user_name;
    private String user_nickname;
    private String user_pw;
    private String user_onpw;
    private String user_seq;
    private String user_studentnumber;
    private AlertDialog dialog;
    private boolean validate = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //액션바 색상 변경
        actionbarcolor();

        //선언
        final EditText idText = (EditText) findViewById(R.id.idText);
        final EditText passwordText = (EditText) findViewById(R.id.passwordText);
        final EditText onpasswordText = (EditText) findViewById(R.id.onpasswordText);
        final EditText nameText = (EditText) findViewById(R.id.nameText);
        final EditText nicknameText = (EditText) findViewById(R.id.nicknameText);
        final EditText studentnumberText = (EditText) findViewById(R.id.studentnumberText);
        final EditText seqText = (EditText) findViewById(R.id.seqText);


        //회원가입시 아이디가 사용가능한지 검증하는 부분
        final Button validateButton = (Button) findViewById(R.id.validateButton);


        //비밀번호와 비밀번호재입력이 같을경우,
//        if(user_pw.equals(user_onpw)){
//            passwordText.setEnabled(false);//아이디값을 바꿀 수 없도록 함
//            onpasswordText.setEnabled(false);//아이디값을 바꿀 수 없도록 함
//            passwordText.setBackgroundColor(getResources().getColor(R.color.colorGray));
//            onpasswordText.setBackgroundColor(getResources().getColor(R.color.colorGray));
//        }


        //CHECKID(validateButton) 눌렸을때,
        validateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_id = idText.getText().toString(); // Edittext 안에 있는 텍스트내용을 getText()
                // getText로 텍스트를 가져오면 Charsequence 형태? 이기때문에 toString로 string형태로 바꿔줌.

                /************************/
                Log.e(this.getClass().getName(), user_id);
                Log.e(this.getClass().getName(), "유저 id 값.");

                final Button validateButton = (Button) findViewById(R.id.validateButton);


                if (validate) {

                    return;//검증 완료
                }
                //ID 값을 입력하지 않았다면
                if (user_id.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    dialog = builder.setMessage("ID is empty")
                            .setPositiveButton("OK", null)
                            .create();
                    Log.e(this.getClass().getName(), "아이디 비어있을때 부분.");
                    dialog.show();
                    return;
                }


                //검증시작
                Response.Listener<String> responseListener = new Response.Listener<String>() {


                    @Override
                    public void onResponse(String response) {

                        Log.e(this.getClass().getName(), response);
                        Log.e(this.getClass().getName(), "리스폰 값이 이거래");


                        try {
                            //Toast.makeText(RegisterActivity.this, response, Toast.LENGTH_LONG).show();
                            JSONObject jsonResponse = new JSONObject(response); //서버로 부터 받는 데이터는 JSON타입의 객체이다.

                            Log.e(this.getClass().getName(), String.valueOf(jsonResponse));
                            Log.e(this.getClass().getName(), "jsonResponse 값이 이거래");

                            //그중 Key값이 "success" 인 것을 가져온다.
                            boolean success = jsonResponse.getBoolean("success"); // befor name = success

                            Log.e(this.getClass().getName(), String.valueOf(success));
                            Log.e(this.getClass().getName(), "success에 해당되는 value 값 ");

                            if (success) {//사용할 수 있는 아이디라면
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                dialog = builder.setMessage("가입 가능한 아이디")
                                        .setPositiveButton("확인", null)
                                        .create();
                                dialog.show();

                                Log.e(this.getClass().getName(), "onResponse if 절");
                                Log.e(this.getClass().getName(), String.valueOf(true));

                                idText.setEnabled(false);//아이디값을 바꿀 수 없도록 함
                                validate = true;//검증완료
                                idText.setBackgroundColor(getResources().getColor(R.color.colorGray));
                                validateButton.setBackgroundColor(getResources().getColor(R.color.colorGray));
                            } else {//사용할 수 없는 아이디라면
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                dialog = builder.setMessage("이미 가입된 아이디")
                                        .setNegativeButton("다시 시도", null)
                                        .create();
                                dialog.show();
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };//Response.Listener 완료

                //Volley 라이브러리를 이용해서 실제 서버와 통신을 구현하는 부분
                ValidateRequest validateRequest = new ValidateRequest(user_id, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);  //Request를 보낼 queue를 생성한다.
                queue.add(validateRequest);
            }
        });


        //회원 가입 버튼이 눌렸을때
        Button registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_seq = seqText.getText().toString();
                String user_id = idText.getText().toString();
                String user_pw = passwordText.getText().toString();
                String user_onpw = onpasswordText.getText().toString();
                String user_name = nameText.getText().toString();
                String user_studentnumber = studentnumberText.getText().toString();
                String user_nickname = nicknameText.getText().toString();
                String user_role = "user";
                //ID 중복체크를 했는지 확인함
                if (!validate) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    dialog = builder.setMessage("First Check ID plz")
                            .setNegativeButton("OK", null)
                            .create();
                    dialog.show();
                    return;
                }

                //비밀번호 입력 잘 못했을 경우.
                if (!user_pw.equals(user_onpw)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    onpasswordText.setBackgroundColor(getResources().getColor(R.color.colorWarning));
                    dialog = builder.setMessage("비밀번호를 정확히 입력해주세요")
                            .setNegativeButton("OK", null)
                            .create();
                    dialog.show();
                    return;
                }

                //한칸이라도 빠뜨렸을 경우(user_seq)제외
                if (user_id.equals("") || user_pw.equals("") || user_name.equals("") || user_studentnumber.equals("") || user_nickname.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    dialog = builder.setMessage("빈곳을 채워주세요")
                            .setNegativeButton("OK", null)
                            .create();
                    dialog.show();
                    return;
                }

                //회원가입 시작
                Response.Listener<String> responseListener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Log.e(this.getClass().getName(), "가입시작");
                        Log.e(this.getClass().getName(), response);
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            Log.e(this.getClass().getName(), String.valueOf(jsonResponse));

                            if (success) {//사용할 수 있는 아이디라면
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                dialog = builder.setMessage("Register Your ID")
                                        .setPositiveButton("OK", null)
                                        .create();
                                dialog.show();
                                Log.e(this.getClass().getName(), "가입성공");
                                Log.e(this.getClass().getName(), String.valueOf(jsonResponse));

                                finish();//액티비티를 종료시킴(회원등록 창을 닫음)
                            } else {//사용할 수 없는 아이디라면
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                dialog = builder.setMessage("Register fail")
                                        .setNegativeButton("OK", null)
                                        .create();
                                dialog.show();
                                Log.e(this.getClass().getName(), "가입실패");
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "예외발생");

                        }
                    }
                };//Response.Listener 완료

                //Volley 라이브러리를 이용해서 실제 서버와 통신을 구현하는 부분
                RegisterRequest registerRequest = new RegisterRequest(user_seq, user_id, user_pw, user_name, user_studentnumber, user_nickname, user_role, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);


            }
        });
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
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }
}
