package hyung.gwang.eyers2.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import hyung.gwang.eyers2.R;
import hyung.gwang.eyers2.request.FreeBoardRequest;


public class FreeBoardListAdapter extends BaseAdapter {
    private Context context;
    private List<FreeBoardRequest> freeboardList;
    public int result_seq;
    public  FreeBoardListAdapter(Context context, List<FreeBoardRequest> freeboardList){
        this.context = context;
        this.freeboardList = freeboardList;
    }
    @Override
    public int getCount() {
        return freeboardList.size(); //리스트뷰의 총 갯수.
    }

    @Override
    public Object getItem(int position) {
        return freeboardList.get(position); //해당 위치의 값을 리스트뷰에 뿌려줌.
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(context, R.layout.activity_freeboard1,null);
        TextView freeboardText = (TextView)v.findViewById(R.id.freeboardText);
        TextView nameText = (TextView)v.findViewById(R.id.nameText);
        TextView dateText = (TextView)v.findViewById(R.id.dateText);
        TextView seqText = (TextView)v.findViewById(R.id.seqText);
        TextView titleText = (TextView)v.findViewById(R.id.titleText);
        freeboardText.setText(freeboardList.get(position).getContent());
        nameText.setText(freeboardList.get(position).getName());
        dateText.setText(freeboardList.get(position).getDate());
        seqText.setText(String.valueOf(freeboardList.get(position).getSeq()));
        titleText.setText(freeboardList.get(position).getTitle());
        v.setTag(freeboardList.get(position).getContent());
        return v;
    }

}