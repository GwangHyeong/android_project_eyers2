package hyung.gwang.eyers2.request;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class NoticeWriteRequest extends StringRequest {

    final static private String URL = "http://eyers1.iwinv.net/NoticeWrite.php";
    private Map<String, String> parameters;

    public NoticeWriteRequest(String notice_content, String notice_name, String notice_date, String notice_seq, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);//해당 URL에 POST방식으로 파마미터들을 전송함
        parameters = new HashMap<>();
        parameters.put("notice_content", notice_content); //키 이름주고 값던지기
        parameters.put("notice_name", notice_name);
        parameters.put("notice_date", String.valueOf(notice_date));
        parameters.put("notice_seq", notice_seq);
        Log.e(this.getClass().getName(), "NoticeWriteRequest 임");
        Log.e(this.getClass().getName(), notice_content);
        Log.e(this.getClass().getName(), notice_name);
        Log.e(this.getClass().getName(), String.valueOf(notice_date));
        Log.e(this.getClass().getName(), notice_seq);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return parameters;
    }


}
