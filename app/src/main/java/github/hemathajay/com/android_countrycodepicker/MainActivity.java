package github.hemathajay.com.android_countrycodepicker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.*;

import github.hemathajay.com.picker.*;

public class MainActivity extends AppCompatActivity {

    CountryCodePicker ccp;
    AppCompatEditText edtPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ccp = (CountryCodePicker) findViewById(R.id.ccp);
        edtPhoneNumber = findViewById(R.id.phone_number_edt);

        ccp.registerPhoneNumberTextView(edtPhoneNumber);

    }
}
