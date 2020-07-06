package hyung.gwang.eyers2.request;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {

    final static private String URL = "http://eyers1.iwinv.net/UserRegister.php";
    private Map<String, String> parameters;

    public RegisterRequest(String user_seq, String user_id, String user_pw, String user_name, String user_studentnumber,
                           String user_nickname, String user_role, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);//해당 URL에 POST방식으로 파마미터들을 전송함
        parameters = new HashMap<>();
        parameters.put("user_seq", user_seq);
        parameters.put("user_id", user_id);
        parameters.put("user_pw", user_pw);
        parameters.put("user_name", user_name);
        parameters.put("user_studentnumber", user_studentnumber);
        parameters.put("user_nickname", user_nickname);
        parameters.put("user_role", user_role);
        Log.e(this.getClass().getName(), "RegisterRequest 임");
        Log.e(this.getClass().getName(), user_seq);
        Log.e(this.getClass().getName(), user_id);
        Log.e(this.getClass().getName(), user_pw);
        Log.e(this.getClass().getName(), user_name);
        Log.e(this.getClass().getName(), user_studentnumber);
        Log.e(this.getClass().getName(), user_nickname);
        Log.e(this.getClass().getName(), user_role);

    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return parameters;
    }
}
