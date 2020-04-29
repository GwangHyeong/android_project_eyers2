package hyung.gwang.eyers2.view;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

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
import hyung.gwang.eyers2.adapter.NoticeListAdapter;
import hyung.gwang.eyers2.request.NoticeRequest;

public class PageTestOne extends AppCompatActivity {

    private ListView noticeListView;
    private NoticeListAdapter adapter;
    private List<NoticeRequest> noticedList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notic_board);

        noticeListView = (ListView)findViewById(R.id.noticeListView);
        noticedList = new ArrayList<NoticeRequest>();

        adapter = new NoticeListAdapter(getApplicationContext(), noticedList);
        noticeListView.setAdapter(adapter);


        new BackgroundTask().execute();
    }

    //PHP서버에 접속해서 JSON타입으로 데이터를 가져옴
    class BackgroundTask extends AsyncTask<Void, Void, String> {
        String target;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            target = "http://eyers1.iwinv.net/NoticeList.php";

        }

        //실제 데이터를 가져오는 부분임
        @Override
        protected String doInBackground(Void... voids) {
            try {

                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;//결과 값을 여기에 저장함
                StringBuilder stringBuilder = new StringBuilder();

                //버퍼생성후 한줄씩 가져옴
                while ((temp = bufferedReader.readLine()) != null) {
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
                String notice_content, notice_name, notice_date;
                /************************/
                Log.e(this.getClass().getName(), "jsonarray.length값");
                Log.e(this.getClass().getName(), String.valueOf(jsonArray.length()));
                Log.e(this.getClass().getName(), String.valueOf(count));
                /************************/
                //json타입의 값을 하나씩 빼서 NoticeRequest 객체에 저장후 리스트에 추가하는 부분
                while (count < jsonArray.length()) {
                    Log.e(this.getClass().getName(), "여기 반복문 실행 되는중인가?");

                    JSONObject object = jsonArray.getJSONObject(count);

                    notice_content = object.getString("notice_content");
                    notice_name = object.getString("notice_name");
                    notice_date = object.getString("notice_date");
                    /* 안되는 이유가 뭐니 진짜 */

                    Log.e(this.getClass().getName(), String.valueOf(count));
                    Log.e(this.getClass().getName(), String.valueOf(notice_content));
                    Log.e(this.getClass().getName(), String.valueOf(notice_name));
                    Log.e(this.getClass().getName(), String.valueOf(notice_date));

                    NoticeRequest notice = new NoticeRequest(notice_content,notice_name , notice_date);
                    Log.e(this.getClass().getName(), String.valueOf(notice));

                    noticedList.add(notice);
                    //????????? 이거 넣었더니 됨 어뎁터를 위에 선언했었는데 흠...//
                    adapter = new NoticeListAdapter(getApplicationContext(), noticedList);
                    noticeListView.setAdapter(adapter);

                    count++;

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
