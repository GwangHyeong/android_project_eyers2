package hyung.gwang.eyers2.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import hyung.gwang.eyers2.R;
import hyung.gwang.eyers2.request.FreeBoardCommentListRequest;
import hyung.gwang.eyers2.request.FreeBoardRequest;

public class FreeBoardCommentListAdapter extends BaseAdapter {
    private Context context;
    private List<FreeBoardCommentListRequest> freeboardcommentList;
    public int result_seq;
    public  FreeBoardCommentListAdapter(Context context, List<FreeBoardCommentListRequest> freeboardcommentList){
        this.context = context;
        this.freeboardcommentList = freeboardcommentList;
    }
    @Override
    public int getCount() {
        return freeboardcommentList.size(); //리스트뷰의 총 갯수.
    }

    @Override
    public Object getItem(int position) {
        return freeboardcommentList.get(position); //해당 위치의 값을 리스트뷰에 뿌려줌.
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(context, R.layout.activity_fbcomment,null);
        TextView freeboardText = (TextView)v.findViewById(R.id.freeboardText);
        TextView nameText = (TextView)v.findViewById(R.id.nameText);
        TextView dateText = (TextView)v.findViewById(R.id.dateText);
        TextView seqText = (TextView)v.findViewById(R.id.seqText);
        TextView commentText = (TextView)v.findViewById(R.id.commentText);
        freeboardText.setText(freeboardcommentList.get(position).getContent());
        nameText.setText(freeboardcommentList.get(position).getName());
        seqText.setText(String.valueOf(freeboardcommentList.get(position).getSeq()));
        commentText.setText(freeboardcommentList.get(position).getContent());

        v.setTag(freeboardcommentList.get(position).getContent());
        return v;
    }

}