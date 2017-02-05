package enghack2017.ca.sparkoflife;

import android.app.Activity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {

    public final static String TAG = "MAIN_ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.prompt_button)
    public void onPromptClicked() {
        EmergencyCallUtils.openEmergencyPrompt(this);
    }
}
