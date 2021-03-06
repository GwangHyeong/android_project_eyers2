package hyung.gwang.eyers2.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import hyung.gwang.eyers2.R;
import hyung.gwang.eyers2.write.AdminWriteActivity;
import hyung.gwang.eyers2.write.MemberWriteActivity;

public class AdminActivity  extends AppCompatActivity {
    /**상단 선언부**/
    Button button;
    Button button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        button = (Button)findViewById(R.id.button);
        button2 = (Button)findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AdminActivity.this, AdminWriteActivity.class);
                AdminActivity.this.startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, MemberWriteActivity.class);
                AdminActivity.this.startActivity(intent);
            }
        });



    }
}
