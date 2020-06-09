package hyung.gwang.eyers2.request;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class FreeBoardDeleteRequest extends StringRequest {

    final static private String URL = "http://eyers1.iwinv.net/FreeBoardDelete.php";
    private Map<String, String> parameters;

    public FreeBoardDeleteRequest(String freeboard_seq,Response.Listener<String> listener) {
        super(Request.Method.POST, URL, listener, null);//해당 URL에 POST방식으로 파마미터들을 전송함
        parameters = new HashMap<>();
        parameters.put("freeboard_seq", freeboard_seq); //키 이름주고 값던지기

        Log.e(this.getClass().getName(), "FreeBoardWriteRequest임");
        Log.e(this.getClass().getName(), freeboard_seq);

    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return parameters;
    }


}