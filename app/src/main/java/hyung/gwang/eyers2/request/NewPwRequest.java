package hyung.gwang.eyers2.request;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class NewPwRequest  extends StringRequest {

    final static private String URL = "http://eyers1.iwinv.net/NewPw.php";
    private Map<String, String> parameters;

    public NewPwRequest(String newpw_content,String newpw_getuser, Response.Listener<String> listener) {
        super(Request.Method.POST, URL, listener, null);//해당 URL에 POST방식으로 파마미터들을 전송함
        parameters = new HashMap<>();
        parameters.put("newpw_content", newpw_content); //키 이름주고 값던지기
        parameters.put("newpw_getuser", newpw_getuser); //키 이름주고 값던지기

        Log.e(this.getClass().getName(), "newpw_Request임");
        Log.e(this.getClass().getName(), newpw_content);
        Log.e(this.getClass().getName(), newpw_getuser);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        Log.e("NewPwRequest","newpwrequest"+parameters);
        return parameters;
    }


}
