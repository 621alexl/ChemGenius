package com.example.alex.myapplication;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.googlecode.tesseract.android.TessBaseAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.alex.myapplication.R.id.CompoundLabel;

/**
 * Author: Alex Li
 * Defines the five main functions of ChemGenius
 * 1. Access the camera to take a photo of a chemical equation/compound and  balance it/find molar mass
 * 2. Open an alert dialog which allows users to manually type in an equation or compound to balance/find molar mass
 * 3. Shows the AP Chemistry formula sheet
 * 4. Unit Converter
 * 5. Ideal Gas Law calculator
 */

public class MainActivity extends AppCompatActivity {
    protected Button camerabtn;
    protected Button conversionbtn;
    protected Button pvnrtbtn;
    protected Button manual;
    protected Button formulabtn;
    protected String path;

    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Instantiate a RequestQueue, allowing Volley to send requests
        final RequestQueue queue = Volley.newRequestQueue(this);


        // Request a string response from the provided URL.
        conversionbtn = (Button) findViewById(R.id.conversionbtn);

        /*catergorymap is a hashmap used to create the Unit Conversion
        spinners: the keys are used to populate the category spinner
        and the value is an array used to populate the startspinner and endspinner
         */

        //This initializes the 5 buttons of the app
        camerabtn = (Button) findViewById(R.id.camerabtn);
        path = Environment.getExternalStorageDirectory() + "/images/make_machine_example.jpg";
        manual = (Button) findViewById(R.id.manualbtn);
        pvnrtbtn = (Button) findViewById(R.id.pvnrtbtn);
        formulabtn = (Button) findViewById(R.id.formulabtn);

        /*
        OnClick, will present an alertdialog of the xml file in R.layout.formula_alert
        Alertdialog contains a spinner and webview, letting user cycle through and
        display formulas. Uses MathScribe to turn html into math notation
         */
        formulabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Creates dialog using method in MainActivity called createDialog
                Second parameter indicates that this dialog does not contain a cancelbtn
                 */
                View promptsView = createDialog(R.layout.formula_alert, -1);
                //Dialog contains two webviews, one for formulas another for constants
                final WebView formulaweb = (WebView) promptsView.findViewById(R.id.formulaweb);
                final WebView constantweb = (WebView) promptsView.findViewById(R.id.constantweb);
                WebSettings formulawebSettings = formulaweb.getSettings();
                formulawebSettings.setJavaScriptEnabled(true);
                WebSettings constantwebSettings = constantweb.getSettings();
                constantwebSettings.setJavaScriptEnabled(true);
                //This loads the constant web with the html string in com.example.alex.myapplication.Formulas.java
                formulaweb.loadDataWithBaseURL("file:///android_asset/", Formulas.formula_atomic,
                        "text/html", "UTF-8", null);
                constantweb.loadDataWithBaseURL("file:///android_asset/", Formulas.constant_atomic,
                        "text/html", "UTF-8", null);


                //formulaspinner displays different formula categories I.E atomic structure, equilibrium, etc.
                final Spinner formulaspinner = (Spinner) promptsView.findViewById(R.id.formulaspinner);
                //When a new item is selected, formulaweb and constantweb are regenerated with new strings
                formulaspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        /*Formulas.getFormulas takes the spinnertext and returns an array, with the
                        0th index having the string for formulaweb, and the 1st index for constantweb
                        */
                        String[] texts = Formulas.getFormulas(formulaspinner.getSelectedItem().toString());
                        String formulas = texts[0];
                        String constants = texts[1];
                        formulaweb.loadDataWithBaseURL("file:///android_asset/", formulas,
                                "text/html", "UTF-8", null);
                        constantweb.loadDataWithBaseURL("file:///android_asset/",
                                constants, "text/html", "UTF-8", null);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }
        });

        pvnrtbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //PVnRT btn brings up an alert dialog of an interface which solves PVnRT equations
                //createDialog generates the alert dialog given the xmL file and id of cancel btn
                View promptsView = createDialog(R.layout.pvnrt_alert, R.id.cancelpvbtn);

                final LinearLayout pvnrtlayout = (LinearLayout) promptsView.findViewById(R.id.pvnrtlayout);

                //4 EditTexts, only input is a number
                final EditText pressurevalue = (EditText) promptsView.findViewById(R.id.pressurevalue);
                final EditText volumevalue = (EditText) promptsView.findViewById(R.id.volumevalue);
                final EditText molevalue = (EditText) promptsView.findViewById(R.id.molevalue);
                final EditText tempvalue = (EditText) promptsView.findViewById(R.id.tempvalue);

                //Button which, onClick, takes the inputs from textfields and calculates the unknown variable
                Button calcbtn = (Button) promptsView.findViewById(R.id.calcpvbtn);

                /*
                PVnRTTools.AddUnknown initializes an ArrayAdapter that takes the unit arrays
                used in the Conversion tool (see res/values/strings) and adds an "unknown" unit to array
                 */

                ArrayAdapter<String> pressureadapter = PVnRTTools.AddUnknown(R.array.pressurearray,
                        getApplicationContext());
                final ArrayAdapter<String> volumeadapter = PVnRTTools.AddUnknown(R.array.volumearray,
                        getApplicationContext());
                ArrayAdapter<String> tempadapter = PVnRTTools.AddUnknown(R.array.temparray,
                        getApplicationContext());
                ArrayAdapter<String> moleadapter = PVnRTTools.AddUnknown(R.array.molearray,
                        getApplicationContext());

                //Initializes spinners, which use the adapters from before
                final Spinner pressurespinner = (Spinner) promptsView.findViewById(R.id.pressurespinner);
                final Spinner volumespinner = (Spinner) promptsView.findViewById(R.id.volumespinner);
                final Spinner molespinner = (Spinner) promptsView.findViewById(R.id.molespinner);
                final Spinner tempspinner = (Spinner) promptsView.findViewById(R.id.tempspinner);

                pressurespinner.setAdapter(pressureadapter);
                volumespinner.setAdapter(volumeadapter);
                tempspinner.setAdapter(tempadapter);
                molespinner.setAdapter(moleadapter);

                //Runs onPVNRTSpinnerSelected
                pressurespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        onPVNRTSpinnerSelected(pressurespinner, pressurevalue, pvnrtlayout);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                volumespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        onPVNRTSpinnerSelected(volumespinner, volumevalue, pvnrtlayout);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {}
                });
                tempspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        onPVNRTSpinnerSelected(tempspinner, tempvalue, pvnrtlayout);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                molespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        onPVNRTSpinnerSelected(molespinner, molevalue, pvnrtlayout);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });


                calcbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (PVnRTTools.checkFields(pvnrtlayout)) {
                            if (!pressurevalue.isEnabled() || !volumevalue.isEnabled()
                                    || !molevalue.isEnabled() || !tempvalue.isEnabled()) {

                                double P = PVnRTTools.checkUnknown(pressurevalue);
                                String PUnits = GetUnit.getUnit(pressurespinner.getSelectedItem().toString());
                                double V = PVnRTTools.checkUnknown(volumevalue);
                                String VUnits = GetUnit.getUnit(volumespinner.getSelectedItem().toString());
                                double n = PVnRTTools.checkUnknown(molevalue);
                                String nUnits = GetUnit.getUnit(molespinner.getSelectedItem().toString());
                                double T = PVnRTTools.checkUnknown(tempvalue);
                                String TUnits = GetUnit.getUnit(tempspinner.getSelectedItem().toString());

                                if (!pressurevalue.isEnabled()) {
                                    pressurevalue.setText(Double.toString(PVNRT.
                                            pvnrt(P, PUnits, V, VUnits, n, nUnits, T, TUnits)));
                                    pressurespinner.setSelection(0);
                                    pressurevalue.setEnabled(true);
                                } else if (!volumevalue.isEnabled()) {
                                    volumevalue.setText(Double.toString(PVNRT.
                                            pvnrt(P, PUnits, V, VUnits, n, nUnits, T, TUnits)));
                                    volumespinner.setSelection(6);
                                    volumevalue.setEnabled(true);
                                } else if (!molevalue.isEnabled()) {
                                    molevalue.setText(Double.toString(PVNRT.
                                            pvnrt(P, PUnits, V, VUnits, n, nUnits, T, TUnits)));
                                    molespinner.setSelection(0);
                                    molevalue.setEnabled(true);
                                } else if (!tempvalue.isEnabled()) {
                                    tempvalue.setText(Double.toString(PVNRT.
                                            pvnrt(P, PUnits, V, VUnits, n, nUnits, T, TUnits)));
                                    tempspinner.setSelection(0);
                                    tempvalue.setEnabled(true);
                                } else {}
                            } else {
                                Toast.makeText(MainActivity.this, "One value must be set as unknown",
                                        Toast.LENGTH_SHORT).show();}
                        } else {Toast.makeText(MainActivity.this, "Fields not correctly filled in",
                                    Toast.LENGTH_SHORT).show();}}}); }
        });
        manual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final View promptsView = createDialog(R.layout.photo_analysis, R.id.cancelbtn);

                final EditText chemInput = (EditText) promptsView.findViewById(R.id.chemInput);
                Button readbtn = (Button) promptsView.findViewById(R.id.readbtn);
                Button searchbtn = (Button) promptsView.findViewById(R.id.search);
                final TextView answertext = (TextView) promptsView.findViewById(R.id.textView4);


                searchbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        //Scrubs input - replaces spaces, decapitalizes
                        String input = chemInput.getText().toString().toLowerCase().replaceAll("\\s+","");

                        //jsonurl = url that retrieves chemical properties in JSON form
                        String jsonurl = "https://pubchem.ncbi.nlm.nih.gov/rest/pug/compound/name/" +
                                input +
                                "/property/MolecularFormula,MolecularWeight,Charge/JSON";

                        //pngurl = url that retrieves chemical structure in png form
                        String pngurl = "https://pubchem.ncbi.nlm.nih.gov/rest/pug/compound/name/" +
                                input  + "/PNG";

                        //compoundname used to populate header of alert as title
                        final String compoundname = chemInput.getText().toString();

                        //View that holds the alert dialog
                        final View searchview = createDialog(R.layout.search_alert, -1);

                        //Listview holds properties from jsonurl's request
                        final ListView propertyLV = (ListView) searchview.findViewById(R.id.propertyLV);

                        //StructureImage is an ImageView for holding pngurl's request
                        final ImageView StructureImage = (ImageView) searchview.findViewById(R.id.StructureImage);

                        //Requests JSON File w/ properties, displayed in alert via PropertyListAdapter
                        StringRequest propertiesRequest = new StringRequest(Request.Method.GET, jsonurl,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {

                                        ArrayList<Property> propertylist = new ArrayList<Property>();
                                        try {
                                            //Retrives JSON Object that holds requested chemical properties
                                            JSONObject PropertyTable = new JSONObject(response);
                                            JSONObject  PropObj  = PropertyTable.getJSONObject("PropertyTable")
                                                    .getJSONArray("Properties").getJSONObject(0);

                                            /*Iterates through PropOBJ (which holds chem properties) to create
                                            Properties (See Property.java) that populate propertyLV via PropertyListAdapter
                                             */
                                            Iterator<String> Names = PropObj.keys();
                                            while (Names.hasNext()) {
                                                String propertytag  = Names.next();
                                                //Scrubs input - MolecularWeight becomes Molecular Weight
                                                String cleantag = propertytag.
                                                        replaceAll("\\d+", "").replaceAll("(.)([A-Z])", "$1 $2");
                                                //CID is always passed in the JSON object, we don't add it to propertyLV
                                                if (!propertytag.equals( "CID")) {
                                                    Property newproperty = new Property(cleantag,
                                                            PropObj.get(propertytag).toString());
                                                    propertylist.add(newproperty);}}}
                                        catch (JSONException e) {Log.v("Hey", "error");}

                                        PropertyListAdapter adapter =
                                                new PropertyListAdapter(MainActivity.this,
                                                        R.layout.adapter_view_layout,
                                                        propertylist);
                                        propertyLV.setAdapter(adapter);

                                        TextView CompoundLabel = (TextView) searchview.
                                                findViewById(R.id.CompoundLabel);
                                        String capname = Capitalize(compoundname);
                                        CompoundLabel.setText(capname);
                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {Toast.makeText(MainActivity.this, "Request didn't work", Toast.LENGTH_SHORT).show();
                            }
                        });

                        ImageRequest structureRequest = new ImageRequest(
                                pngurl, // Image URL
                                new Response.Listener<Bitmap>() { // Bitmap listener
                                    @Override
                                    public void onResponse(Bitmap response) {
                                        // Do something with response
                                        StructureImage.setImageBitmap(response);
                                    }
                                },
                                0, // Image width
                                0, // Image height
                                ImageView.ScaleType.CENTER_CROP, // Image scale type
                                Bitmap.Config.RGB_565, //Image decode configuration
                                new Response.ErrorListener() { // Error listener
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        // Do something with error response
                                        Log.v("Hey", "PNG  didn't work");
                                        Toast.makeText(MainActivity.this,
                                                "Chemical Structure not found", Toast.LENGTH_SHORT).show();}});

                        queue.add(propertiesRequest);
                        queue.add(structureRequest);

                    }
                });
                readbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {answertext.setText(ReadInput.readInput(chemInput.getText().toString()));
                    }
                });


            }
        });


        conversionbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                View promptsView = createDialog(R.layout.conversion_alert, R.id.cancelconversionbtn);
                final Spinner categoryspinner = (Spinner) promptsView.findViewById(R.id.categoryspinner);
                ArrayAdapter<CharSequence> categoryadapter = ArrayAdapter.createFromResource(MainActivity.this,
                        R.array.categoryarray, R.layout.spinner_item);
                //categoryadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                categoryspinner.setAdapter(categoryadapter);
                final Spinner startspinner = (Spinner) promptsView.findViewById(R.id.startspinner);
                final Spinner endspinner = (Spinner) promptsView.findViewById(R.id.endspinner);
                final ArrayAdapter<CharSequence> unitadapter = ArrayAdapter.createFromResource(MainActivity.this,
                        R.array.lengtharray, R.layout.spinner_item);
                startspinner.setAdapter(unitadapter);
                endspinner.setAdapter(unitadapter);
                categoryspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        String item = categoryspinner.getSelectedItem().toString();
                        ArrayAdapter<CharSequence> newadapter =
                                ArrayAdapter.createFromResource(MainActivity.this, UnitConversion.categorymap.get(item),
                                        R.layout.spinner_item);
                        startspinner.setAdapter(newadapter);
                        endspinner.setAdapter(newadapter);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                final EditText startvalue = (EditText) promptsView.findViewById(R.id.startvalue);
                Button calcbtn = (Button) promptsView.findViewById(R.id.calcbtn);
                final EditText endvalue = (EditText) promptsView.findViewById(R.id.endvalue);
                final LinearLayout conversionlayout = (LinearLayout) promptsView.findViewById(R.id.conversionlayout);
                calcbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (PVnRTTools.checkFields(conversionlayout)) {
                            String startunit = GetUnit.getUnit(startspinner.getSelectedItem().toString());
                            Log.v("Hey", startunit);

                            String endunit = GetUnit.getUnit(endspinner.getSelectedItem().toString());
                            Log.v("Hey", endunit);

                            double result = UnitConversion.unitConversion(startunit,
                                    Double.parseDouble(startvalue.getText().toString()), endunit,
                                    categoryspinner.getSelectedItem().toString());
                            Log.v("Hey", categoryspinner.getSelectedItem().toString());
                            endvalue.setText(Double.toString(result));
                        } else {
                            Toast.makeText(MainActivity.this, "Fields not correctly filled in",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });
        camerabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startCameraActivity();

            }
        });

    }



    //Starts camera
    protected  void startCameraActivity() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }

    }

    /*onActivityResult runs after taking a photo (startCameraActivity)
    requestCode = int which determines what task the result is from)
    resultCode = int which determines if task succeeded
    data = Intent which holds a bitmap of the photo
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap bitmap = (Bitmap) extras.get("data");

            //initialize Tesseract API. datapath/lang are used to access training data for tesseract
            String datapath = getFilesDir() + "/tesseract/";
            String lang = "eng";
            Tess tess = new Tess();
            tess.checkFile(new File(datapath + "tessdata/"), datapath, getApplicationContext());
            TessBaseAPI baseApi = new TessBaseAPI();
            // DATA_PATH = Path to the storage
            // lang = for which the language data exists, usually "eng"
            baseApi.init(datapath, lang);
            // Eg. baseApi.init("/mnt/sdcard/tesseract/tessdata/eng.traineddata", "eng");

            //After image is received, launch alert dialog with OCR'd text in the input field
            View promptsView = createDialog(R.layout.photo_analysis, R.id.cancelbtn);
            final EditText chemInput = (EditText) promptsView.findViewById(R.id.chemInput);
            Button readbtn = (Button) promptsView.findViewById(R.id.readbtn);
            final TextView answertext = (TextView) promptsView.findViewById(R.id.textView4);
            Bitmap manualBitmap = RotateBitmap(bitmap, 90);


            baseApi.setImage(toGrayscale(manualBitmap));
            String recognizedText = baseApi.getUTF8Text();
            chemInput.setText(recognizedText, TextView.BufferType.EDITABLE);
            baseApi.end();

            //Balance equation/calculate molar mass
            readbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    answertext.setText(ReadInput.readInput(chemInput.getText().toString()));

                }
            });

        }
    }


    public static Bitmap RotateBitmap(Bitmap source, float angle)
    {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }

    /*onPVNRTSpinnerSelected runs when a spinner item is selected.
    If the new item is selected is "Unknown (unknown)", then the EditText adjacent
    to spinner (EditText value) is disabled and cleared. And all other spinners
    set to "Unknown (unknown)" are set to the item @ the first index, and the adjacent editText is
    re-enabled
     */
    public void onPVNRTSpinnerSelected(Spinner spinner, EditText value, LinearLayout linearLayout) {
        if (spinner.getSelectedItem().toString().equals("Unknown (unknown)")) {
            /*This for loop iterates through each linear layout which contains
            an EditText - Spinner pair
             */
            for (int x = 0; x < linearLayout.getChildCount(); x++) {
                View horiz = linearLayout.getChildAt(x);
                if (horiz instanceof LinearLayout) {
                    //menu is an EditText @ horiz index 0, field is spinner @ horiz index 1
                    View menu = ((LinearLayout) horiz).getChildAt(1);
                    View field = ((LinearLayout) horiz).getChildAt(0);
                    if (menu instanceof Spinner) {
                        //If spinner says unknown, set spinner item to index 0
                        if (((Spinner) menu).getSelectedItem().toString().equals("Unknown (unknown)")) {
                            ((Spinner) menu).setSelection(0);
                            field.setEnabled(true);
                        }
                    }
                }
            }
            /*Because all spinners set to, "Unknown (unknown)" have been reset in the previous for loop
            the originally selected EditText (assigned as value) is disabled and cleared,
            and the spinner adjacent is reset to "Unknown (unknown)",
             */
            value.setEnabled(false);
            value.getText().clear();
            spinner.setSelection(spinner.getCount() - 1);

        } else {
            /*This else clause is in case a spinner goes from "Unknown (unknown)"
            to a different unit, to re-enable the EditText
             */

            value.setEnabled(true);

        }
    }


    /*createDialog takes 2 inputs - int layout = id of the xml file located in res/layout.
    int cancel id represents the id of the cancel btn present in all but 1 alert dialog file
     */
    public View createDialog(int layout, int cancelid) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                MainActivity.this);
        //Inflates dialog to View, which is passed to AlertDialog
        LayoutInflater li = LayoutInflater.from(MainActivity.this);
        View promptsView = li.inflate(layout, null);
        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);
        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
        /*-1 is not a valid id of a btn, so if cancelid != -1, then the btn is initialized
        and it's onclick is defined
         */

        if (cancelid != -1) {

            Button cancelbtn = (Button) promptsView.findViewById(cancelid);
            cancelbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialog.cancel();
                }
            });
        }
        return promptsView;
    }

    public String Capitalize(String input) {
        char[] chars = input.toCharArray();

        // all ways make first char a cap
        chars[0] = Character.toUpperCase(chars[0]);

        // then capitalize if space on left.
        for(int x=1; x<chars.length; x++) {
            if(chars[x-1] == ' '){
                chars[x] = Character.toUpperCase(chars[x]);
            }
        }

        return new String(chars);
    }
 

    public Bitmap toGrayscale(Bitmap bmpOriginal)
    {
        int width, height;
        height = bmpOriginal.getHeight();
        width = bmpOriginal.getWidth();

        Bitmap bmpGrayscale = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bmpGrayscale);
        Paint paint = new Paint();
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0);
        ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
        paint.setColorFilter(f);
        c.drawBitmap(bmpOriginal, 0, 0, paint);
        return bmpGrayscale;
    }


}