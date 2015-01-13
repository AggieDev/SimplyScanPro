package appuccino.simplyscanpro;

import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    private TextView mainText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainText = (TextView)findViewById(R.id.mainText);
        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Light.ttf");
        mainText.setTypeface(type);

        button = (Button)findViewById(R.id.button);
        button.setTypeface(type);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        SpannableString s = new SpannableString("SimplyScan Pro Key");
        s.setSpan(new TypefaceSpan(this, "Dancing-Script.ttf"), 0, s.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Update the action bar title with the TypefaceSpan instance
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(s);
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.blue)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!freeVersionInstalled()){
            setViewsForFreeNotInstalled();
        } else {
            setViewsForFreeInstalled();
        }
    }

    private void setViewsForFreeNotInstalled(){
        if(mainText != null){
            mainText.setText(getResources().getString(R.string.main_message_not_installed));
        }
        if(button != null){
            button.setVisibility(View.VISIBLE);
        }
    }

    private void setViewsForFreeInstalled(){
        if(mainText != null){
            mainText.setText(getResources().getString(R.string.main_message));
        }
        if(button != null){
            button.setVisibility(View.GONE);
        }
    }

    private boolean freeVersionInstalled() {
        PackageManager pm = getPackageManager();
        boolean app_installed = false;
        try {
            pm.getPackageInfo("appuccino.simplyscan", PackageManager.GET_ACTIVITIES);
            app_installed = true;
        }
        catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
