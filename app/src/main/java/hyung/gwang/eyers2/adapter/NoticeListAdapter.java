package hyung.gwang.eyers2.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import hyung.gwang.eyers2.R;
import hyung.gwang.eyers2.request.NoticeRequest;

public class NoticeListAdapter extends BaseAdapter {
    private Context context;
    private List<NoticeRequest> noticedList;
    public int result_seq;
    public NoticeListAdapter(Context context, List<NoticeRequest> noticedList){
        this.context = context;
        this.noticedList = noticedList;
    }
    @Override
    public int getCount() {
        return noticedList.size(); //리스트뷰의 총 갯수.
    }

    @Override
    public Object getItem(int position) {
        return noticedList.get(position); //해당 위치의 값을 리스트뷰에 뿌려줌.
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(context, R.layout.activity_notice,null);
        TextView noticeText = (TextView)v.findViewById(R.id.noticeText);
        TextView nameText = (TextView)v.findViewById(R.id.nameText);
        TextView dateText = (TextView)v.findViewById(R.id.dateText);
        TextView seqText = (TextView)v.findViewById(R.id.seqText);
        noticeText.setText(noticedList.get(position).getNotice());
        nameText.setText(noticedList.get(position).getName());
        dateText.setText(noticedList.get(position).getDate());
        seqText.setText(String.valueOf(noticedList.get(position).getSeq()));
        v.setTag(noticedList.get(position).getNotice());
        return v;
    }

}
