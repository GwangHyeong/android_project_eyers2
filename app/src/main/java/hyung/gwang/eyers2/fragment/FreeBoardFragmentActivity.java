package hyung.gwang.eyers2.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import hyung.gwang.eyers2.R;

public class FreeBoardFragmentActivity extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.activity_fragment_freeboard_comment, container, false);
        //값 받기
      //  String user_id = getArguments().getString("key_id");

        return v;
    }
}
