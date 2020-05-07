package hyung.gwang.eyers2.request;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class FreeBoardWriteRequest extends StringRequest {

    final static private String URL = "http://eyers1.iwinv.net/FreeboardWrite.php";
    private Map<String, String> parameters;

    public FreeBoardWriteRequest(String freeboard_title, String freeboard_name, String freeboard_content, String freeboard_date,String freeboard_seq, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);//해당 URL에 POST방식으로 파마미터들을 전송함
        parameters = new HashMap<>();
        parameters.put("freeboard_title", freeboard_title); //키 이름주고 값던지기
        parameters.put("freeboard_name", freeboard_name);
        parameters.put("freeboard_content", freeboard_content);
        parameters.put("freeboard_date", String.valueOf(freeboard_date));
        parameters.put("freeboard_seq", freeboard_seq);
        Log.e(this.getClass().getName(), "FreeBoardWriteRequest임");
        Log.e(this.getClass().getName(), freeboard_title);
        Log.e(this.getClass().getName(), freeboard_name);
        Log.e(this.getClass().getName(), freeboard_content);
        Log.e(this.getClass().getName(), String.valueOf(freeboard_date));
        Log.e(this.getClass().getName(), freeboard_seq);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return parameters;
    }


}
