package hyung.gwang.eyers2.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import hyung.gwang.eyers2.R;
import hyung.gwang.eyers2.detail.FreeBoardDetailActivity;
import hyung.gwang.eyers2.request.FreeBoardCommentRequest;
import hyung.gwang.eyers2.request.FreeBoardDetailRequest;

public class FreeBoardFragmentActivity extends Fragment {

    /**
     * 상단선언부
     */

    TextView commentText;
    TextView nameText;

    /**
     * 상단선언부 종료
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.activity_fragment_freeboard_comment, container, false);

        commentText = (TextView) v.findViewById(R.id.commentText);
        nameText = (TextView) v.findViewById(R.id.nameText);

        //값 받기
        String key_id = getArguments().getString("key_id");
        Log.e("key받기 테스트 게시글번호--", String.valueOf(key_id));
        String getuser = getArguments().getString("getuser");
        Log.e("key받기 테스트 글쓴이--", String.valueOf(getuser));


        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e(this.getClass().getName(), response);
                Log.e(this.getClass().getName(), "리스폰 값이 이거래");

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("response");

                    String fbcomment_content, fbcomment_name, freeboard_date, freeboard_title;
                    int freeboard_seq;
                    int count = 0;
                    while (count < jsonArray.length()) {
                    JSONObject object = jsonArray.getJSONObject(count); //length값주기
                    fbcomment_content = object.getString("fbcomment_content");
                    fbcomment_name = object.getString("fbcomment_name");

                    Log.e(this.getClass().getName(), String.valueOf(response));
                    commentText.setText(fbcomment_content);
                    nameText.setText(fbcomment_name);
                    count++;


                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "자유게시판찾기 예외발생");
                }
            }
        };

        //Volley 라이브러리를 이용해서 실제 서버와 통신을 구현하는 부분
        FreeBoardCommentRequest freeBoardCommentRequest = new FreeBoardCommentRequest(key_id, responseListener);
        RequestQueue queue = Volley.newRequestQueue(v.getContext());  //Request를 보낼 queue를 생성한다.
        queue.add(freeBoardCommentRequest);

        return v;
    }
}
