package hyung.gwang.eyers2.request;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

//로그인
public class LoginRequest extends StringRequest {

  //  final static private String URL = "http://eyers1.iwinv.net/UserLogin.php";
  final static private String URL = "http://eyers1.iwinv.net/UserLogin.php";
    private Map<String, String> parameters;

    public LoginRequest(String user_id, String user_pw, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);//해당 URL에 POST방식으로 파마미터들을 전송함
        parameters = new HashMap<>();
        parameters.put("user_id", user_id); //user_id 이름으로 user_id값 던지기
        parameters.put("user_pw", user_pw); //user_pw 이름으로 user_pw값 던지기
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return parameters;
    }


}