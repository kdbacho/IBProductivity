package com.wes.ibproductivity;

import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Rational;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TabHost;
import android.widget.RelativeLayout;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.jeevan.calculator.CalculatorFunctions;
import com.jeevan.calculator.RationalNumber;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    TabHost tabHost;
    EditText x1,x2,x3,y1,y2,y3,z1,z2,z3,c1,c2,c3;
    EditText v1,v2,v3,u1,u2,u3;
    Button rip;
    TextView r, v;
    boolean isCross;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TabHost host = (TabHost) findViewById(R.id.tabHost);
        host.setup();


        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("Tab One");
        spec.setContent(R.id.tab1);
        spec.setIndicator("", getResources().getDrawable(R.drawable.math));
        host.addTab(spec);
        WebView browser = (WebView) findViewById(R.id.webView1);
        browser.getSettings().setJavaScriptEnabled(true);
        browser.getSettings().setSupportZoom(true);
        String pdf = "http://www.edukraft.in/documents/Math%20HL%20Formulae%20IB.pdf";
        browser.loadUrl("http://drive.google.com/viewerng/viewer?embedded=true&url=" + pdf);


        //Tab 2
        spec = host.newTabSpec("Tab Two");
        spec.setContent(R.id.tab2);
        spec.setIndicator("", getResources().getDrawable(R.drawable.chem));
        host.addTab(spec);
        browser = (WebView) findViewById(R.id.webView2);
        browser.getSettings().setJavaScriptEnabled(true);
        browser.getSettings().setSupportZoom(true);
        pdf = "http://www.ibchem.com/root_pdf/data_booklet_2016.pdf";
        browser.loadUrl("http://drive.google.com/viewerng/viewer?embedded=true&url=" + pdf);

        //Tab 3
        spec = host.newTabSpec("Tab Three");
        spec.setContent(R.id.tab3);
        spec.setIndicator("", getResources().getDrawable(R.drawable.physics));
        host.addTab(spec);
        browser = (WebView) findViewById(R.id.webView3);
        browser.getSettings().setJavaScriptEnabled(true);
        browser.getSettings().setSupportZoom(true);
        pdf = "http://schools.cms.k12.nc.us/myersparkHS/Documents/Physics%20Data%20Booklet.pdf";
        browser.loadUrl("http://drive.google.com/viewerng/viewer?embedded=true&url=" + pdf);

        //Tab 4
        spec = host.newTabSpec("Tab Four");
        spec.setContent(R.id.tab4);
        spec.setIndicator("", getResources().getDrawable(R.drawable.calc));
        host.addTab(spec);

        //Set fields
        x1 = (EditText)findViewById(R.id.x1T);
        x2 = (EditText)findViewById(R.id.x2T);
        x3 = (EditText)findViewById(R.id.x3T);

        y1 = (EditText)findViewById(R.id.y1T);
        y2 = (EditText)findViewById(R.id.y2T);
        y3 = (EditText)findViewById(R.id.y3T);

        z1 = (EditText)findViewById(R.id.z1T);
        z2 = (EditText)findViewById(R.id.z2T);
        z3 = (EditText)findViewById(R.id.z3T);

        c1 = (EditText)findViewById(R.id.c1T);
        c2 = (EditText)findViewById(R.id.c2T);
        c3 = (EditText)findViewById(R.id.c3T);

        v1 = (EditText)findViewById(R.id.vec1x);
        v2 = (EditText)findViewById(R.id.vec1y);
        v3 = (EditText)findViewById(R.id.vec1z);

        u1 = (EditText)findViewById(R.id.vec2x);
        u2 = (EditText)findViewById(R.id.vec2y);
        u3 = (EditText)findViewById(R.id.vec2z);

        r = (TextView)findViewById(R.id.resultLabel);
        v = (TextView)findViewById(R.id.vecResult);
        rip = (Button)findViewById(R.id.productButton);

        Switch s= (Switch) findViewById(R.id.productToggle);
        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isCross = b;
                if(b)
                    rip.setText("x⃗ × y⃗");
                else
                    rip.setText("x⃗ · y⃗");
            }
        });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void solveButtonClick(View w)
    {

        try {
            //Set textfield
            RationalNumber[][] coefficients = new RationalNumber[3][3];
            RationalNumber[] scalars = new RationalNumber[3];
            //eq1
            coefficients[0][0] = RationalNumber.parseRational(x1.getText().toString());
            coefficients[0][1] = RationalNumber.parseRational(y1.getText().toString());
            coefficients[0][2] = RationalNumber.parseRational(z1.getText().toString());
            scalars[0] = RationalNumber.parseRational(c1.getText().toString());
            //eq2
            coefficients[1][0] = RationalNumber.parseRational(x2.getText().toString());
            coefficients[1][1] = RationalNumber.parseRational(y2.getText().toString());
            coefficients[1][2] = RationalNumber.parseRational(z2.getText().toString());
            scalars[1] = RationalNumber.parseRational(c2.getText().toString());
            //eq3
            coefficients[2][0] = RationalNumber.parseRational(x3.getText().toString());
            coefficients[2][1] = RationalNumber.parseRational(y3.getText().toString());
            coefficients[2][2] = RationalNumber.parseRational(z3.getText().toString());
            scalars[2] = RationalNumber.parseRational(c3.getText().toString());
            try {
                RationalNumber[] solutions = CalculatorFunctions.gaussian(coefficients, scalars);
                r.setText("(x,y,z) = (" + solutions[0] + ", " + solutions[1] + ", " + solutions[2] + ")");
            } catch (RuntimeException e) {
                r.setText("the system has no solution");
            }
        }catch(Exception e){};
    }

    public void vecEvalButtonClick(View w)
    {
        RationalNumber a,b,c,x,y,z;
        try {
            a = RationalNumber.parseRational(v1.getText().toString());
            b = RationalNumber.parseRational(v2.getText().toString());
            c = RationalNumber.parseRational(v3.getText().toString());
            x = RationalNumber.parseRational(u1.getText().toString());
            y = RationalNumber.parseRational(u2.getText().toString());
            z = RationalNumber.parseRational(u3.getText().toString());
            if (isCross) {
                RationalNumber[] result = CalculatorFunctions.crossProduct(a, b, c, x, y, z);
                v.setText("(" + result[0] + ", " + result[1] + ", " + result[2] + ")");
            } else {
                v.setText(" " + CalculatorFunctions.dotProduct(a, b, c, x, y, z).toString());
            }
        }catch (Exception e){};

    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.wes.ibproductivity/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.wes.ibproductivity/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
