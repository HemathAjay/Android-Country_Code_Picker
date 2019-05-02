# Android-Country_Code_Picker
This application is used to pick a country code with country flag in a list.

1. Add CCP view to layout

2. Add AppCompatEditText view to layout

3. register the AppCompatEditText using registerPhoneNumberTextView(AppCompatEditText) we can use TextView instead of AppCompatEditText 
also.

4. Let the magic happens ;)

Here the more details step:

1. Add CCP to layout using the following:

```java
<github.hemathajay.com.picker.CountryCodePicker
      android:id="@+id/ccp"
      android:layout_width="wrap_content"
      android:layout_height="wrap_content" />
```

2. Add AppCompatEditText view to layout:

```java
<android.support.v7.widget.AppCompatEditText
       android:id="@+id/phone_number_edt"
       android:layout_width="match_parent"
       android:layout_height="wrap_content"
       android:hint="phone"
       android:inputType="phone"/>
```

3. register the AppCompatEditText with code:

```java
CountryCodePicker ccp;
AppCompatEditText edtPhoneNumber;

...

ccp = (CountryCodePicker) findViewById(R.id.ccp);
edtPhoneNumber = (AppCompatEditText) findViewById(R.id.phone_number_edt);

...

ccp.registerPhoneNumberTextView(edtPhoneNumber);
```

### Features

### 1. Default country

* Default country is the country where most of your target audience belong.

* The default country can be set through xml layout and programmatically as well.

### A. Through xml
Using country code name
Add app:ccp_defaultNameCode="US" (replace "US" with your default country name code) to xml layout. Refer List of countries for name codes.

```java
<github.hemathajay.com.picker.CountryCodePicker
       android:id="@+id/ccp"
       android:layout_width="wrap_content"
       android:layout_height="wrap_content"
       app:ccp_defaultNameCode="US"  />
```

Using phone code

add app:ccp_defaultCode="81" (replace 81 with your default country code) to xml layout.Refer List of countries for country codes.

Setting default country using phone code is not recommended. There are few cases where more than one countries have same phone code. Say
US and Canada have +1. Putting '1' will result in Canada even if you were intended for US. Use app:cpp_defaultNameCode or
app:cpp_countryPreference to overcome issue.

```java
<github.hemathajay.com.picker.CountryCodePicker
      android:id="@+id/ccp"
      android:layout_width="wrap_content"
      android:layout_height="wrap_content"
      app:ccp_defaultCode="81" />
      
app:ccp_defaultNameCode has higher priority than app:ccp_defaultCode.
```

### B. Programmatically

Using country name code

Use setDefaultCountryUsingNameCode() method.

Using phone code
To set default country programmatically, use setDefaultCountryUsingPhoneCode() method.

setDefaultCountryUsingNameCode() or setDefaultCountryUsingPhoneCode() will not set default country as selected country in CCP view. To 
set default country as selected country in CCP view, call resetToDefaultCountry() method.

resetToDefaultCountry() will set default country as selected country in CCP, it can be used at the time of form reset.

If you do not specify default country from xml, ID +91 (Indonesia) will be the default country until you update default country 
programmatically.

### 2. Choose and set country

Choosing and setting country will update selected country in CCP view.

Choose Country
1. In order to choose country, click on CCP view.
2. Then search country by country name or phone code or name code in dialog.
3. Click on county from list to choose

Set country programmatically

Using country code name
Country in CCP can be using setCountryForNameCode() method.

Using phone code

1. Country in CCP can be using setCountryForCode() method.

2. If specified country code / name code does not match with any country, default country will be set in to CCP.

How to listen change in selection? To get call back when country is changed, you need to add OnCountryChangeListener from code.

```java
ccp.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
 	 @Override
 	 public void onCountrySelected(Country selectedCountry) {
 	     Toast.makeText(getContext(), "Updated " + selectedCountry.getName(), Toast.LENGTH_SHORT).show();
      }
 });
```

### 3. Country preference

1. Library has list of countries in alphabetical order. It searches for country in same order. But preferred country/countries have 
higher priority than rest.

2. There are few cases where more than one countries have same code. For example, Canada, Puerto Rico and US have +1. When lilbrary will
try to find country with +1, it will always pick Canada as it's alphabetically first in (1)Canada-(2)Puerto Rico-(3)US.

3. If US is set in country preference, order for search will be (1)US-(2)Canada-(3)Puerto Rico, so it will pick US for +1.

4. Countries of preference will be listed at top in selection dialog. It is helpful when target audience is from a set of countries.

5. Any number of countries can be set in preference.

Set through xml

Add app:ccp_countryPreference="US,ID,NZ" (replace "US,ID,NZ" with your preference) to xml layout. Refer List of countries for name 
codes.

<github.hemathajay.com.picker.CountryCodePicker
          android:id="@+id/ccp"
          android:layout_width="wrap_content"
          android:layout_height="wrap_content"
          app:ccp_countryPreference="US,ID,NZ"  />
          
Programmatically

Use setCountryPreference() method.

### 4. Read selected country

Country's 3 properties (Country name, phone code and name code) can be read individually.

Read selected country phone code

1. To get selected country code as String type and without prefix “+”, use getSelectedCountryCode(); method. => “91”
2. To get selected country code as String type and with prefix “+”, use getSelectedCountryCodeWithPlus(); method. => “+91”
3. To get selected country code as int (Integer) type, use getSelectedCountryCodeAsInt(); method. => 91

Read selected country name

1. To get selected country’s name, use getSelectedCountryName(); => “Indonesia”

Read selected country name code

1. To get selected country’s name code, use getSelectedCountryNameCode(); => “ID”

### 5. Full number support

Full number is combination of country code and carrier number. for example, if country code is 91 and carrier number is 8866667722 then 
918866667722 or +918866667722 is the full number.

Register phoneNumberTextView

1. CarrierNumberEditText is the supplementary editText in which carrier number part of full number is entered.

2. A carrierNumberEditText must be registered in order to work with full number.

3. editText can be registered using registerPhoneNumberTextView().

Load full number

1. To load full number, use setFullNumber() method. In this method you need to pass the full number.

2. Prefix “+” is optional for full number so full number can be “91886667722” or “+918866667722”. Both will set same country and carrier
number."

3. This will detect country code from full number and set that county to ccp and carrier number ( remaining part of full number other
than country code) will be set as text of registered carrier editText.

4. If no valid country code is found in beginning part of full number, default country will be set to CCP and full number will be set as
text of registered carrier editText.

Get full number

1. Use getFullNumber(); for full number without “+” prefix.

2. Use getFullNumberWithPlus(); for full number with “+” prefix.

3. A phoneNumberTextView must be registered before any function call of full number like setFullNumber() or getFullNumber().

4. None of the above functions validate the number format of phone.



