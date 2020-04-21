package hyung.gwang.eyers2.request;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ScanIdRequest extends StringRequest {

    final static private String URL = "http://eyers1.iwinv.net/UserScanId.php";
    private Map<String, String> parameters;

    public ScanIdRequest(String user_name, String user_studentnumber, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);//해당 URL에 POST방식으로 파마미터들을 전송함
        parameters = new HashMap<>();
        parameters.put("user_name", user_name);
        parameters.put("user_studentnumber", user_studentnumber);
        Log.e(this.getClass().getName(), "scanIdRequest 임");
        Log.e(this.getClass().getName(), String.valueOf(parameters));
        Log.e(this.getClass().getName(), user_name);
        Log.e(this.getClass().getName(), user_studentnumber);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        Log.e(this.getClass().getName(), "scanIdRequest 파라미터 값임");
        Log.e(this.getClass().getName(), String.valueOf(parameters));
        return parameters;
    }
}
