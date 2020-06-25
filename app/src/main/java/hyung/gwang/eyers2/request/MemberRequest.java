package hyung.gwang.eyers2.request;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class MemberRequest extends StringRequest {

    final static private String URL = "http://eyers1.iwinv.net/MemberWrite.php";
    private Map<String, String> parameters;
    public MemberRequest(String member_name, String member_email, String member_img, String member_seq, String member_skill, String member_homepage, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);//해당 URL에 POST방식으로 파마미터들을 전송함
        parameters = new HashMap<>();
        parameters.put("member_name", member_name); //키 이름주고 값던지기
        parameters.put("member_email", member_email);
        parameters.put("member_img", member_img);
        parameters.put("member_seq", member_seq);
        parameters.put("member_skill", member_skill);
        parameters.put("member_homepage", member_homepage);
        Log.e(this.getClass().getName(), "MemberRequest 임");
        Log.e(this.getClass().getName(), member_name);
        Log.e(this.getClass().getName(), member_email);
        Log.e(this.getClass().getName(), member_img);
        Log.e(this.getClass().getName(), member_seq);
        Log.e(this.getClass().getName(), member_skill);
        Log.e(this.getClass().getName(), member_homepage);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return parameters;
    }


}
