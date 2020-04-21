package hyung.gwang.eyers2.request;


import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


public class ValidateRequest extends StringRequest {

    final static private String URL = "http://eyers1.iwinv.net/UserValidate.php";
    private Map<String, String> parameters;

    public ValidateRequest(String user_id, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);//해당 URL에 POST방식으로 파마미터들을 전송함
        parameters = new HashMap<>();
        parameters.put("user_id", user_id);
        Log.e(this.getClass().getName(), "ValidateRequest 임");
        Log.e(this.getClass().getName(), String.valueOf(parameters));
        Log.e(this.getClass().getName(), user_id);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        Log.e(this.getClass().getName(), "ValidateRequest 파라미터 값임");
        Log.e(this.getClass().getName(), String.valueOf(parameters));
        return parameters;
    }
}