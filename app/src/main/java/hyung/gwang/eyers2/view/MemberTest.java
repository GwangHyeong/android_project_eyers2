package hyung.gwang.eyers2.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.captaindroid.tvg.Tvg;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import hyung.gwang.eyers2.R;
import hyung.gwang.eyers2.adapter.MemberListAdapter;
import hyung.gwang.eyers2.adapter.NoticeListAdapter;
import hyung.gwang.eyers2.detail.NoticeDetailActivity;
import hyung.gwang.eyers2.request.MemberRequest;
import hyung.gwang.eyers2.request.MemberTestRequest;
import hyung.gwang.eyers2.request.NoticeRequest;

public class MemberTest extends AppCompatActivity {


    private ListView memberListView;
    private MemberListAdapter adapter;
    private List<MemberTestRequest> memberList;
    ImageView imageView;
    String result_seq;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);

        memberListView = (ListView)findViewById(R.id.memberListView);
        memberList = new ArrayList<MemberTestRequest>();

        imageView = (ImageView) findViewById(R.id.imageView1);
        TextView titleView = (TextView)findViewById(R.id.titleView);
        //텍스트 그레디언트

        Tvg.change(titleView, Color.parseColor("#800CDD"), Color.parseColor("#3BA3F2"));
        Tvg.change((TextView) findViewById(R.id.titleView), new int[]{
                Color.parseColor("#F97C3C"),
                Color.parseColor("#FDB54E"),
                Color.parseColor("#64B678"),
                Color.parseColor("#478AEA"),
                Color.parseColor("#8446CC"),
        });

        adapter = new MemberListAdapter(getApplicationContext(), memberList);
        memberListView.setAdapter(adapter);

        new BackgroundTask().execute();

        //Glide.with(MemberTest.this).load(member_img).into(imageView);


    }


    //PHP서버에 접속해서 JSON타입으로 데이터를 가져옴
    public class BackgroundTask extends AsyncTask<Void, Void, String> {


        String target;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            target = "http://eyers1.iwinv.net/MemberList.php";

        }

        //실제 데이터를 가져오는 부분임
        @Override
        protected String doInBackground(Void... voids) {
            try {

                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                Log.e("데이터가져오는거 테스트", String.valueOf(inputStream)+"인풋스트림");
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                Log.e("데이터가져오는거 테스트", String.valueOf(bufferedReader)+"버퍼리더");
                String temp;//결과 값을 여기에 저장함
                StringBuilder stringBuilder = new StringBuilder();
                Log.e("데이터가져오는거 테스트", String.valueOf(stringBuilder)+"스트링빌더");
                //버퍼생성후 한줄씩 가져옴
                while ((temp = bufferedReader.readLine()) != null) {
                    Log.e("데이터가져오는거 테스트", String.valueOf(temp)+"스트링");

                    stringBuilder.append(temp + "\n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return stringBuilder.toString().trim();//결과값이 여기에 리턴되면 이 값이 onPostExcute의 파라미터로 넘어감

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        //여기서는 가져온 데이터를 Notice객체에 넣은뒤 리스트뷰 출력을 위한 List객체에 넣어주는 부분
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try {

                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                int count = 0;
                String member_name, member_skill, member_email,member_homepage,member_img;
                int member_seq;
                /************************/
                Log.e(this.getClass().getName(), "jsonarray.length값");
                Log.e(this.getClass().getName(), String.valueOf(jsonArray.length()));
                Log.e(this.getClass().getName(), String.valueOf(count));
                /************************/

                //json타입의 값을 하나씩 빼서 NoticeRequest 객체에 저장후 리스트에 추가하는 부분
                while (count < jsonArray.length()) {
                    Log.e(this.getClass().getName(), "여기 반복문 실행 되는중인가?");

                    JSONObject object = jsonArray.getJSONObject(count);

                    member_img = object.getString("member_img");
                    member_name = object.getString("member_name");
                    member_skill = object.getString("member_skill");
                    member_email = object.getString("member_email");
                    member_homepage = object.getString("member_homepage");
                    member_seq = object.getInt("member_seq");
                    /* 안되는 이유가 뭐니 진짜 */




                    Log.e(this.getClass().getName(), String.valueOf(count));
                    Log.e(this.getClass().getName(), String.valueOf(member_img));
                    Log.e(this.getClass().getName(), String.valueOf(member_name));
                    Log.e(this.getClass().getName(), String.valueOf(member_skill));
                    Log.e(this.getClass().getName(), String.valueOf(member_email));
                    Log.e(this.getClass().getName(), String.valueOf(member_homepage));
                    Log.e(this.getClass().getName(), "seq테스트"+String.valueOf(member_seq));

                    MemberTestRequest notice = new MemberTestRequest(member_img,member_name,member_skill , member_email,member_homepage, member_seq);
                    Log.e(this.getClass().getName(), String.valueOf(notice));
                    memberList.add(notice);
                    //????????? 이거 넣었더니 됨 어뎁터를 위에 선언했었는데 흠...//
                    adapter = new MemberListAdapter(getApplicationContext(), memberList);
                    memberListView.setAdapter(adapter);



                    Log.e("리스트뷰클릭TEST55555", String.valueOf(member_seq));
                    count++;


                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}