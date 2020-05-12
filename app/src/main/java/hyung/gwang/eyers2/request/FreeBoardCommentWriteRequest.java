package hyung.gwang.eyers2.request;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class FreeBoardCommentWriteRequest extends StringRequest {

    final static private String URL = "http://eyers1.iwinv.net/FreeBoardCommentWrtie.php";
    private Map<String, String> parameters;
    public FreeBoardCommentWriteRequest(String freeboardcomment_content, String freeboardcomment_name,String freeboardcomment_freeboard, String freeboardcomment_seq, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);//해당 URL에 POST방식으로 파마미터들을 전송함
        parameters = new HashMap<>();
        parameters.put("freeboardcomment_content", freeboardcomment_content); //키 이름주고 값던지기
        parameters.put("freeboardcomment_name", freeboardcomment_name);
        parameters.put("freeboardcomment_seq", freeboardcomment_seq);
        parameters.put("freeboardcomment_freeboard", String.valueOf(freeboardcomment_freeboard));

        Log.e(this.getClass().getName(), "FreeBoardCommentWriteRequest임");
        Log.e(this.getClass().getName(), freeboardcomment_content);
        Log.e(this.getClass().getName(), freeboardcomment_name);
        Log.e(this.getClass().getName(), freeboardcomment_seq);
        Log.e(this.getClass().getName(), String.valueOf(freeboardcomment_freeboard));
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return parameters;
    }


}
