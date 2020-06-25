package hyung.gwang.eyers2.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import hyung.gwang.eyers2.R;
import hyung.gwang.eyers2.request.MemberRequest;
import hyung.gwang.eyers2.request.MemberTestRequest;
import hyung.gwang.eyers2.request.NoticeRequest;

public class MemberListAdapter extends BaseAdapter {
    private Context context;
    private List<MemberTestRequest> memberList;
    public int result_seq;
    public MemberListAdapter(Context context, List<MemberTestRequest> memberList){
        this.context = context;
        this.memberList = memberList;
    }
    @Override
    public int getCount() {
        return memberList.size(); //리스트뷰의 총 갯수.
    }

    @Override
    public Object getItem(int position) {
        return memberList.get(position); //해당 위치의 값을 리스트뷰에 뿌려줌.
    }

    @Override
    public long getItemId(int position)   {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(context, R.layout.member_list_row,null);
        ImageView imageView1 = (ImageView)v.findViewById(R.id.imageView1);
        TextView tvName=(TextView)convertView.findViewById(R.id.tvName);
        TextView tvSkill=(TextView)convertView.findViewById(R.id.tvSkill);
        TextView tvHomepage=(TextView)convertView.findViewById(R.id.tvHomepage);
        TextView tvEmail=(TextView)convertView.findViewById(R.id.tvEmail);
        TextView tvSeq=(TextView)convertView.findViewById(R.id.tvSeq);
        tvName.setText(memberList.get(position).getName());
        tvSkill.setText(memberList.get(position).getSkill());
        tvHomepage.setText(memberList.get(position).getHomepage());
        tvEmail.setText(String.valueOf(memberList.get(position).getEmail()));
        tvSeq.setText(String.valueOf(memberList.get(position).getSeq()));
        v.setTag(memberList.get(position).getName());
        return v;
    }

}
