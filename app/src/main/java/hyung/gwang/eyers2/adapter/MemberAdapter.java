package hyung.gwang.eyers2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import hyung.gwang.eyers2.R;
import hyung.gwang.eyers2.request.EyersMemberActivity;

public class MemberAdapter extends BaseAdapter {
    Context context;     // 현재 화면의 제어권자
    int layout;              // 한행을 그려줄 layout
    ArrayList<EyersMemberActivity> al;     // 다량의 데이터
    LayoutInflater inf; // 화면을 그려줄 때 필요
    public MemberAdapter(Context context, int layout, ArrayList<EyersMemberActivity> al) {
        this.context = context;
        this.layout = layout;
        this.al = al;
        this.inf = (LayoutInflater)context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }



    @Override
    public int getCount() { // 총 데이터의 개수를 리턴
        return al.size();
    }
    @Override
    public Object getItem(int position) { // 해당번째의 데이터 값
        return al.get(position);
    }
    @Override
    public long getItemId(int position) { // 해당번째의 고유한 id 값
        return position;
    }
    @Override // 해당번째의 행에 내용을 셋팅(데이터와 레이아웃의 연결관계 정의)
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = inf.inflate(layout, null);

        ImageView iv = (ImageView)convertView.findViewById(R.id.imageView1);
        TextView tvName=(TextView)convertView.findViewById(R.id.tvName);
        TextView tvSkill=(TextView)convertView.findViewById(R.id.tvSkill);
        TextView tvHomepage=(TextView)convertView.findViewById(R.id.tvHomepage);
        TextView tvEmail=(TextView)convertView.findViewById(R.id.tvEmail);

        EyersMemberActivity m = al.get(position);

        iv.setImageResource(m.img);

        tvName.setText(m.name);
        tvSkill.setText(m.skill);
        tvHomepage.setText(m.homepage);
        tvEmail.setText(m.email);
        return convertView;
    }
}
