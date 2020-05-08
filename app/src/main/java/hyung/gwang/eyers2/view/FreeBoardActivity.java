package hyung.gwang.eyers2.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.captaindroid.tvg.Tvg;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

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
import hyung.gwang.eyers2.adapter.FreeBoardListAdapter;
import hyung.gwang.eyers2.detail.FreeBoardDetailActivity;
import hyung.gwang.eyers2.detail.NoticeDetailActivity;
import hyung.gwang.eyers2.request.FreeBoardRequest;
import hyung.gwang.eyers2.write.FreeBoardWriteActivity;

public class FreeBoardActivity extends AppCompatActivity {

    private ListView freeboardListView;
    private FreeBoardListAdapter adapter;
    private List<FreeBoardRequest> freeboardList;
    String result_seq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freeboard_main);

        final String getuser = getIntent().getStringExtra("key_id");
        Log.e("유저아이디 데이터 넘어왔나 확인2,",getuser);

        freeboardListView = (ListView)findViewById(R.id.freeboardListView);
        freeboardList = new ArrayList<FreeBoardRequest>();
        FloatingActionButton fab = findViewById(R.id.fab);

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

        //클릭
        freeboardListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("test", "아이템클릭, postion : " + position +
                        ", id : " + id);
                Toast.makeText(getApplicationContext(), " 상세 보기", Toast.LENGTH_SHORT).show();
                Log.e("ListenerTest","L_TEST"+position);


                TextView seqText = (TextView)view.findViewById(R.id.seqText);
                String result_seq = (seqText.getText().toString());
                Log.e("result_seq", String.valueOf(result_seq));

                Intent intent = new Intent(FreeBoardActivity.this, FreeBoardDetailActivity.class);
                //Error 드디어찾은곳. Integer을 형변환 하지않고 String 에 뿌려서 그런듯;?
                intent.putExtra("key_id",String.valueOf(result_seq)); //값 전달하기.

                //값잘넘겻는지 로그캣 확인
                int listentest = position;
                Log.e("ListenerTest", String.valueOf(listentest));
                startActivity(intent);
            }
        });



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent;
                intent = new Intent(FreeBoardActivity.this, FreeBoardWriteActivity.class);
                intent.putExtra("key_id",getuser);
                startActivity(intent);
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        adapter = new FreeBoardListAdapter(getApplicationContext(), freeboardList);
        freeboardListView.setAdapter(adapter);

        new BackgroundTask().execute();
    }



    //PHP서버에 접속해서 JSON타입으로 데이터를 가져옴
    public class BackgroundTask extends AsyncTask<Void, Void, String> {


        String target;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            target = "http://eyers1.iwinv.net/FreeboardList.php";

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

        //여기서는 가져온 데이터를 freeboard객체에 넣은뒤 리스트뷰 출력을 위한 List객체에 넣어주는 부분
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.e("데이터가져오는거 테스트", String.valueOf(result)+"스트링");
            try {

                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                int count = 0;
                String freeboard_content, freeboard_name, freeboard_date , freeboard_title;
                int freeboard_seq;
                /************************/
                Log.e(this.getClass().getName(), "jsonarray.length값");
                Log.e(this.getClass().getName(), String.valueOf(jsonArray.length()));
                Log.e(this.getClass().getName(), String.valueOf(count));
                /************************/
                //json타입의 값을 하나씩 빼서 freeboardRequest 객체에 저장후 리스트에 추가하는 부분
                while (count < jsonArray.length()) {
                    Log.e(this.getClass().getName(), "여기 반복문 실행 되는중인가?");

                    JSONObject object = jsonArray.getJSONObject(count);

                    freeboard_content = object.getString("freeboard_content");
                    freeboard_name = object.getString("freeboard_name");
                    freeboard_date = object.getString("freeboard_date");
                    freeboard_seq = object.getInt("freeboard_seq");
                    freeboard_title = object.getString("freeboard_title");
                    /* 안되는 이유가 뭐니 진짜 */

                    Log.e(this.getClass().getName(), String.valueOf(count));
                    Log.e(this.getClass().getName(), String.valueOf(freeboard_content));
                    Log.e(this.getClass().getName(), String.valueOf(freeboard_name));
                    Log.e(this.getClass().getName(), String.valueOf(freeboard_date));
                    Log.e(this.getClass().getName(), "seq테스트"+String.valueOf(freeboard_seq));
                    Log.e(this.getClass().getName(), String.valueOf(freeboard_title));

                    FreeBoardRequest freeboard = new FreeBoardRequest(freeboard_content,freeboard_name ,freeboard_date, freeboard_seq,freeboard_title);
                    Log.e(this.getClass().getName(), String.valueOf(freeboard));

                    freeboardList.add(freeboard);
                    //????????? 이거 넣었더니 됨 어뎁터를 위에 선언했었는데 흠...//
                    adapter = new FreeBoardListAdapter(getApplicationContext(), freeboardList);
                    freeboardListView.setAdapter(adapter);



                    Log.e("리스트뷰클릭TEST44444444", String.valueOf(freeboard_seq));
                    count++;


                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}

