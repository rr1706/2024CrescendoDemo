package com.frc1706.a2024crescendodemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

// Up to date tablets
//
// all

public class MainActivity extends AppCompatActivity {
    Spinner endGame, selectedTabNum, trapScore;
    Button preload, update, imageGrabber, backPre, speakerPlus, speakerMinus, ampPlus, ampMinus, feedPlus, feedMinus, submit, notesSubmit, noShow, redAlliance, blueAlliance, continueBtn, grayBox, autoTele, sameScouter, backbtn, testBtn, note1, note2, note3, note4, note5, note6, note7, note8;
    Switch robotError;
    TextView ampTxt, speakerTxt, feedTxt, allianceTxt, infoDisplay, endgameTxt, roboTxt, roberTxt, speaktxt, amptxt, note1txt, note2txt, note3txt, note4txt, note5txt, note6txt, note7txt, note8txt;
    EditText nameInput, matchNumber, teamNumber, notes;
    CheckBox autofillTeam;
    ImageView rrLogo, spike;
    int teleAmp;
    int teleSpeaker;
    int telefeed;
    int autoAmp;
    int autoSpeaker;
    int tabletnumber;
    int roundfill = 1;
    int timesPressed = 0;
    int woah = 0;
    int totalOrder = 0;
    int note1_order = 0;
    int note2_order = 0;
    int note3_order = 0;
    int note4_order = 0;
    int note5_order = 0;
    int note6_order = 0;
    int note7_order = 0;
    int note8_order = 0;
    int seconds = 0;
    String note1_val = "null";
    String note2_val = "null";
    String note3_val = "null";
    String note4_val = "null";
    String note5_val = "null";
    String note6_val = "null";
    String note7_val = "null";
    String note8_val = "null";
    String preload_val = "null";
    String team = "";
    String alliance = "none";
    String tabletName;
    String scouterName;
    Boolean countDown;
    Boolean autoclicked = false;
    String teamArray = "8719,3397,1288,3814,9428,2978\n" +
            "1732,1178,4766,2531,4522,3284\n" +
            "6391,2408,7417,5176,6237,7452\n" +
            "1756,4187,1329,5045,7117,4256\n" +
            "9031,1706,4931,3843,2783,5147\n" +
            "1939,931,5060,7472,9401,5583\n" +
            "4329,7662,8069,3937,8077,7496\n" +
            "6391,4330,6817,8719,1208,6744\n" +
            "4766,7417,1329,1178,1288,9428\n" +
            "6237,5147,3814,2531,2783,4187\n" +
            "5045,3284,5583,1939,2978,5176\n" +
            "7452,4931,7117,3937,931,8069\n" +
            "4330,4329,4522,3843,6817,3397\n" +
            "5060,7662,1756,9031,1732,9401\n" +
            "7496,6744,2408,1706,7472,4256\n" +
            "2531,2978,8077,1208,6391,5176\n" +
            "1939,5045,1178,2783,7117,1288\n" +
            "6237,4931,4329,4522,3397,5583\n" +
            "6817,3814,9031,7417,5147,3937\n" +
            "4187,8069,8719,1706,5060,9401\n" +
            "2408,1208,9428,6744,1329,7662\n" +
            "7472,7496,3843,1756,8077,1732\n" +
            "4256,3284,4766,931,4330,7452\n" +
            "1288,7417,5176,3937,4931,2783\n" +
            "9031,4329,2978,1178,6391,7117\n" +
            "8069,9428,2531,5583,6744,4187\n" +
            "6237,5060,1208,1756,1706,5045\n" +
            "7472,1732,4330,4766,7452,3814\n" +
            "1329,7496,3284,8077,6817,9401\n" +
            "4522,4256,5147,7662,1939,3397\n" +
            "7117,3843,2408,8719,931,9428\n" +
            "4931,8069,5583,6744,1756,1178\n" +
            "5060,2531,7472,4330,7417,1706\n" +
            "4187,3814,6391,9401,3937,3284\n" +
            "3397,8077,5147,7496,5176,5045\n" +
            "6817,4766,931,2783,7662,2408\n" +
            "4256,1732,6237,4329,1288,1208\n" +
            "1939,8719,4522,1329,9031,3843\n" +
            "7452,5583,1706,7117,4187,2978\n" +
            "5147,1756,4330,3284,6391,6744\n" +
            "7496,3397,1178,5176,3814,7662\n" +
            "7472,3937,1208,9428,9401,4931\n" +
            "9031,2531,8719,5045,4766,4329\n" +
            "6817,2978,7417,3843,6237,1288\n" +
            "8069,1939,1732,2408,4256,931\n" +
            "8077,2783,5060,1329,7452,4522\n" +
            "9428,5583,3937,5147,1208,3284\n" +
            "7662,9031,4187,6391,4329,2531\n" +
            "9401,3843,4330,2978,6237,1178\n" +
            "8719,7117,1732,4931,1939,4256\n" +
            "4522,7496,1288,5060,7452,2408\n" +
            "3397,5045,7472,8069,6817,2783\n" +
            "7417,6744,931,8077,1329,3814\n" +
            "1756,5176,3937,4766,1706,3843\n" +
            "7117,2531,7662,1732,4931,2978\n" +
            "9428,4256,7452,1178,5147,9031\n" +
            "2783,8719,6391,5583,5060,7496\n" +
            "6817,1329,1939,4330,8069,6237\n" +
            "3814,9401,5045,4522,2408,1706\n" +
            "1288,1756,931,5176,4329,6744\n" +
            "3397,4187,4766,1208,7417,8077\n" +
            "7662,5147,7472,3284,1178,8719\n" +
            "2978,5583,2783,3937,4256,4330\n" +
            "7117,9428,1706,2531,6817,6237\n" +
            "931,3843,4329,3814,5060,8069\n" +
            "9401,6744,1939,7417,7452,3397\n" +
            "4931,5045,1208,7496,4766,6391\n" +
            "2408,1288,8077,3284,9031,1756\n" +
            "1329,7472,5176,4187,1732,4522\n" +
            "1706,8719,6817,1178,5583,7662\n" +
            "5060,3937,7117,6744,2531,3397\n" +
            "3814,1208,4256,7417,5045,3843\n" +
            "7452,2783,4329,9401,2408,1756\n" +
            "8077,4766,4931,6237,3284,1939\n" +
            "1288,2978,1329,6391,7472,8069\n" +
            "4187,4330,7496,931,4522,9031\n"+
            "1732,5176,5060,5147,9428,6817\n" +
            "2408,1178,2531,1208,3397,1756\n" +
            "1706,3814,3937,5583,8077,1939\n" +
            "3843,3284,7452,4329,1329,8719\n" +
            "5045,1288,4187,7662,4330,4931\n" +
            "8069,7496,9401,5147,7117,4766\n" +
            "2783,4522,7417,1732,6391,9428\n" +
            "6744,6237,9031,2978,931,7472\n" +
            "5176,1178,8077,4256,8719,5060\n" +
            "7452,7662,5045,5583,3843,3814\n" +
            "4330,2408,1329,4931,4187,5147\n" +
            "9428,1939,7417,4329,1756,7496\n" +
            "3397,9031,6391,931,1706,1732\n" +
            "3284,5176,2783,1208,8069,7117\n" +
            "4256,9401,2531,1288,7472,6817\n" +
            "2978,3937,6744,4766,4522,6237\n";


    ConstraintLayout notesBox, preMenu, background, autoPaths, teleLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        ampPlus = findViewById(R.id.ampPlus);
        ampMinus = findViewById(R.id.ampMinus);
        speakerPlus = findViewById(R.id.speakerPlus);
        speakerMinus = findViewById(R.id.speakerMinus);
        feedPlus = findViewById(R.id.feedPlus);
        feedMinus = findViewById(R.id.feedMinus);
        submit = findViewById(R.id.submit);
        endGame = findViewById(R.id.endGame);
        ampTxt = findViewById(R.id.ampValue);
        speakerTxt = findViewById(R.id.speakerValue);
        feedTxt = findViewById(R.id.feederScore);
        notesBox = findViewById(R.id.notesBox);
        notesSubmit = findViewById(R.id.noteSubmit);
        nameInput = findViewById(R.id.nameInput);
        matchNumber = findViewById(R.id.matchNumber);
        teamNumber = findViewById(R.id.teamInput);
        autofillTeam = findViewById(R.id.autoFill);
        noShow = findViewById(R.id.noShow);
        redAlliance = findViewById(R.id.redAlliance);
        blueAlliance = findViewById(R.id.blueAlliance);
        continueBtn = findViewById(R.id.continueBtn);
        preMenu = findViewById(R.id.preMenu);
        allianceTxt = findViewById(R.id.allianceTxt);
        background = findViewById(R.id.Background);
        infoDisplay = findViewById(R.id.InfoDisplay);
        grayBox = findViewById(R.id.the_return_of_gray_box);
        notes = findViewById(R.id.essayPrompt);
        autoTele = findViewById(R.id.autoTele);
        sameScouter = findViewById(R.id.sameScouter);
        robotError = findViewById(R.id.robotError);
        backbtn = findViewById(R.id.backBtn);
        testBtn = findViewById(R.id.testbutton_Leave_In);
        rrLogo = findViewById(R.id.RR_logo);
        endgameTxt = findViewById(R.id.endgameTxt);
        matchNumber.setText(String.valueOf(roundfill));
        backPre = findViewById(R.id.backPre);
        roboTxt = findViewById(R.id.roboErrorTxt);
        imageGrabber = findViewById(R.id.imageGrabber);
        spike = findViewById(R.id.spike);
        selectedTabNum = findViewById(R.id.TabletNumborFoRealNoCapNoCapNoCap);
        update = findViewById(R.id.updateBtn);
        roberTxt = findViewById(R.id.roboerrorTxt);
        speaktxt = findViewById(R.id.speaktxt);
        amptxt = findViewById(R.id.amptxt_abpistgiwdsb);
        autoPaths = findViewById(R.id.autoLayout);
        note1 = findViewById(R.id.autoNote1);
        note2 = findViewById(R.id.autoNote2);
        note3 = findViewById(R.id.autoNote3);
        note4 = findViewById(R.id.autoNote4);
        note5 = findViewById(R.id.autoNote5);
        note6 = findViewById(R.id.autoNote6);
        note7 = findViewById(R.id.autoNote7);
        note8 = findViewById(R.id.autoNote8);
        note1txt = findViewById(R.id.note1txt);
        note2txt = findViewById(R.id.note2text);
        note3txt = findViewById(R.id.note3txt);
        note4txt = findViewById(R.id.note4txt);
        note5txt = findViewById(R.id.note5txt);
        note6txt = findViewById(R.id.note6txt);
        note7txt = findViewById(R.id.note7txt);
        note8txt = findViewById(R.id.note8txt);
        teleLayout = findViewById(R.id.teleLayout);
        trapScore = findViewById(R.id.trapScore);
        preload = findViewById(R.id.preloaded);

        Animation rotate = AnimationUtils.loadAnimation(this, R.anim.rotate_picture);
        Animation flip = AnimationUtils.loadAnimation(this, R.anim.flip);
        Animation fall = AnimationUtils.loadAnimation(this, R.anim.fall);

        tabletName = Settings.Secure.getString(getContentResolver(), "bluetooth_name");

        switch (tabletName) {
            case "1706's Blue 1":

                tabletnumber = 4;
                selectedTabNum.setSelection(3);
                break;
            case "1706's Blue 2":
                tabletnumber = 5;
                selectedTabNum.setSelection(4);
                break;
            case "1706's Blue 3":
                tabletnumber = 6;
                selectedTabNum.setSelection(5);
                break;
            case "1706's Red 1":
                tabletnumber = 1;
                selectedTabNum.setSelection(0);
                break;
            case "1706's Red 2":
                tabletnumber = 2;
                selectedTabNum.setSelection(1);
                break;
            case "1706's Red 3":
                tabletnumber = 3;
                selectedTabNum.setSelection(2);
                break;
        }




        update.setOnClickListener(V->{
            if (selectedTabNum.getSelectedItem().toString().equals("red 1")) {
                tabletnumber = 1;
            } else if (selectedTabNum.getSelectedItem().toString().equals("red 2")) {
                tabletnumber = 2;
            } else if (selectedTabNum.getSelectedItem().toString().equals("red 3")) {
                tabletnumber = 3;
            } else if (selectedTabNum.getSelectedItem().toString().equals("blue 1")) {
                tabletnumber = 4;
            } else if (selectedTabNum.getSelectedItem().toString().equals("blue 2")) {
                tabletnumber = 5;
            } else if (selectedTabNum.getSelectedItem().toString().equals("blue 3")) {
                tabletnumber = 6;
            } else {
                Toast.makeText(this, "No item selected", Toast.LENGTH_SHORT).show();
            }
            update.setText(Settings.Secure.getString(getContentResolver(), "bluetooth_name"));
            teamAuto();
        });



        teamAuto();

        imageGrabber.setOnClickListener(v->{
            if (spike.getVisibility() == View.INVISIBLE) {
                spike.setVisibility(View.VISIBLE);
                String teamNumber = team;

                File directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
                File image = new File(directory + "/RobotPictures/" + teamNumber + ".jpg");
                if (image.exists()){
                    Bitmap imgBitmap = BitmapFactory.decodeFile(image.getAbsolutePath());
                    spike.setImageBitmap(imgBitmap);
                }else{
                    spike.setImageDrawable(getResources().getDrawable(getResources().getIdentifier("placeholder", "drawable", getPackageName())));
                    Toast.makeText(getApplicationContext(), "No Robot Image Found.  Check Team Number or Talk with the Scouting Lead", Toast.LENGTH_LONG).show();
                }
            } else {
                spike.setVisibility(View.INVISIBLE);
            }
        });

        autofillTeam.setOnClickListener(v->{
            if (autofillTeam.isChecked()) {
                teamAuto();
            }
        });

        //PREGAME

        blueAlliance.setOnClickListener(v -> {
            background.setBackgroundColor(Color.argb(127, 127, 127, 247));
            preMenu.setBackgroundColor(Color.argb(255, 127, 127, 247));
            alliance = "blue";
        });
        //This sets the alliance red and sets the background color red.
        redAlliance.setOnClickListener(v -> {
            background.setBackgroundColor(Color.argb(127, 247, 127, 127));
            preMenu.setBackgroundColor(Color.argb(255, 247, 127, 127));
            alliance = "red";
        });

        continueBtn.setOnClickListener(view -> {
            String closeError = "";
            if (teamNumber.getText().toString().equals("")) {
                closeError += " No Team,";
                teamNumber.setBackgroundColor(Color.argb(255, 255, 255, 0));
            }
            if (matchNumber.getText().toString().equals("")) {
                closeError += " No Match,";
                matchNumber.setBackgroundColor(Color.argb(255, 255, 255, 0));
            }
            if (alliance.equals("none")) {
                closeError += " No Alliance,";
                allianceTxt.setBackgroundColor(Color.argb(255, 255, 255, 0));
            }
            if (nameInput.getText().toString().equals("")) {
                closeError += " No Name,";
                nameInput.setBackgroundColor(Color.argb(255, 255, 255, 0));
            }
            if (!closeError.equals("")) {
                closeError = closeError.substring(0, closeError.length() - 1) + ".";
            }
            if (!(closeError.equals(""))) {
                Toast.makeText(getApplicationContext(), "Submit Error:" + closeError, Toast.LENGTH_LONG).show();

            } else {
                preMenu.setVisibility(View.INVISIBLE);
                allianceTxt.setBackgroundColor(Color.TRANSPARENT);
                matchNumber.setVisibility(View.INVISIBLE);
                grayBox.setVisibility(View.INVISIBLE);
                teamNumber.setVisibility(View.INVISIBLE);
                nameInput.setVisibility(View.INVISIBLE);
                infoDisplay.setText("Team #: " + teamNumber.getText()+ ", Match #: " + matchNumber.getText());
                autoTele.setText("Auto");
                autoTele.setText("Auto");
                feedTxt.setText("Notes fed: " + telefeed);
                team = teamNumber.getText().toString();
                ampTxt.setText("amp: 0");
                speakerTxt.setText("speaker: 0");
                teleLayout.setVisibility(View.INVISIBLE);
                autoPaths.setVisibility(View.VISIBLE);


                if (team.equals("1706")) {
                    background.startAnimation(rotate);
                }
                if (team.equals("9401")) {
                    Toast.makeText(this, "Midas YAY", Toast.LENGTH_SHORT).show();
                    ampMinus.setBackgroundColor(Color.argb(127, 255, 215, 0));
                    ampPlus.setBackgroundColor(Color.argb(127, 255, 215, 0));
                    speakerMinus.setBackgroundColor(Color.argb(127, 255, 215, 0));
                    speakerPlus.setBackgroundColor(Color.argb(127, 255, 215, 0));
                    feedMinus.setBackgroundColor(Color.argb(127, 255, 215, 0));
                    feedPlus.setBackgroundColor(Color.argb(127, 255, 215, 0));
                    submit.setBackgroundColor(Color.argb(127, 255, 215, 0));
                }


                if (nameInput.getText().toString().equalsIgnoreCase("paximus prime")) {
                    background.startAnimation(flip);
                }
                if (matchNumber.getText().toString().equals("69")) {
                    //nice
                    Toast.makeText(this, "nice", Toast.LENGTH_SHORT).show();
                }
                if (nameInput.getText().toString().equalsIgnoreCase("cooper fleming")) {
                    background.startAnimation(fall);
                }
                if (nameInput.getText().toString().equalsIgnoreCase("jc")) {
                    autoTele.startAnimation(flip);
                    speakerPlus.startAnimation(flip);
                    speakerMinus.startAnimation(flip);
                    speakerTxt.startAnimation(flip);
                    ampMinus.startAnimation(flip);
                    ampPlus.startAnimation(flip);
                    ampTxt.startAnimation(flip);
                    backPre.startAnimation(flip);
                    imageGrabber.startAnimation(flip);
                    infoDisplay.startAnimation(flip);
                    rrLogo.startAnimation(flip);
                    amptxt.startAnimation(flip);
                    speaktxt.startAnimation(flip);
                }
                int random = new Random().nextInt(101);
                if (nameInput.getText().toString().equalsIgnoreCase("rainbow")) {
                    ampMinus.setBackgroundColor(Color.argb(new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256)));
                    ampPlus.setBackgroundColor(Color.argb(new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256)));
                    speakerPlus.setBackgroundColor(Color.argb(new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256)));
                    speakerMinus.setBackgroundColor(Color.argb(new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256)));
                    feedMinus.setBackgroundColor(Color.argb(new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256)));
                    feedPlus.setBackgroundColor(Color.argb(new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256)));
                    submit.setBackgroundColor(Color.argb(new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256)));
                    autoTele.setBackgroundColor(Color.argb(new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256)));
                }
                if (!nameInput.getText().toString().equalsIgnoreCase("rainbow") && !team.equals("9401")) {
                    ampMinus.setBackgroundColor(Color.argb(255, 156, 39, 176));
                    ampPlus.setBackgroundColor(Color.argb(255, 156, 39, 176));
                    speakerPlus.setBackgroundColor(Color.argb(255, 156, 39, 176));
                    speakerMinus.setBackgroundColor(Color.argb(255, 156, 39, 176));
                    feedMinus.setBackgroundColor(Color.argb(255, 156, 39, 176));
                    feedPlus.setBackgroundColor(Color.argb(255, 156, 39, 176));
                    submit.setBackgroundColor(Color.argb(255, 156, 39, 176));
                    autoTele.setBackgroundColor(Color.argb(255, 156, 39, 176));
                }
            }
        });

        final DialogInterface.OnClickListener NoShowDialog = (dialog, which) -> {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE: //If the yes button is clicked for no show, this executes

                    resetVars();
                    teamAuto();

                    if (!autofillTeam.isChecked()) {
                        teamNumber.setText("");
                    }
                case DialogInterface.BUTTON_NEGATIVE:
                    break;
            }
        };

        final DialogInterface.OnClickListener areyousureurinautospeaker = (dialog, which) -> {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE: //If the yes button is clicked for no show, this executes
                    Toast.makeText(this, "wow good for them", Toast.LENGTH_SHORT).show();
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    autoSpeaker -= 1;
                    speakerTxt.setText("(Auto) speaker: " + autoSpeaker);
            }
        };

        final DialogInterface.OnClickListener areyousureurinautoamp = (dialog, which) -> {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE: //If the yes button is clicked for no show, this executes
                    Toast.makeText(this, "wow good for them", Toast.LENGTH_SHORT).show();
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    autoAmp -= 1;
                    ampTxt.setText("(Auto) amp: " + autoAmp);
            }
        };
        final DialogInterface.OnClickListener areyousureurinauto3and3 = (dialog, which) -> {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE: //If the yes button is clicked for no show, this executes
                    Toast.makeText(this, "wow good for them", Toast.LENGTH_SHORT).show();
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    break;
            }
        };

        backPre.setOnClickListener(v -> {
            preMenu.setVisibility(View.VISIBLE);
            matchNumber.setVisibility(View.VISIBLE);
            nameInput.setVisibility(View.VISIBLE);
            teamNumber.setVisibility(View.VISIBLE);
            grayBox.setVisibility(View.VISIBLE);
        });

        noShow.setOnClickListener(v -> {
            String submitError = "";
            //Special handling
            if (alliance.equals("none")) {
                submitError += " No Alliance,";
            }
            if (nameInput.getText().toString().equals("")) {
                submitError += " No Name,";
            }
            if (teamNumber.getText().toString().equals("")) {
                submitError += " No Team#,";
            }
            if (matchNumber.getText().toString().equals("")) {
                submitError += " No Round#,";
            }
            if (!submitError.equals("")) {
                submitError = submitError.substring(0, submitError.length() - 1) + ".";
            }
            if (!(submitError.equals(""))) {
                Toast.makeText(getApplicationContext(), "Submit Error:" + submitError, Toast.LENGTH_LONG).show();
            } else {
                builder.setMessage("Are you sure the team is a no show?")
                        .setPositiveButton("Yes", NoShowDialog)
                        .setNegativeButton("No", NoShowDialog)
                        .show();
            }
        });

        //lose focus

        matchNumber.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                hideKeyboard(v);
                teamAuto();
            }
        });

        teamNumber.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                hideKeyboard(v);
                teamAuto();
            }
        });

        nameInput.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                hideKeyboard(v);
                if (nameInput.getText().toString().equals("1706admin")) {
                    selectedTabNum.setVisibility(View.VISIBLE);
                    update.setVisibility(View.VISIBLE);
                } else {
                    selectedTabNum.setVisibility(View.INVISIBLE);
                    update.setVisibility(View.INVISIBLE);
                }
            }
        });

        notes.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                hideKeyboard(v);
            }
        });


        testBtn.setOnClickListener(v -> {
            timesPressed += 1;
            if (timesPressed == 5) {
                Toast.makeText(getApplicationContext(), "woah how did this happen" , Toast.LENGTH_LONG).show();
                testBtn.setVisibility(View.INVISIBLE);
                rrLogo.startAnimation(rotate);
                timesPressed = 0;
                testBtn.setVisibility(View.VISIBLE);

            }
        });

        robotError.setOnClickListener(v -> {
            if (woah == 0) {
                Toast.makeText(this, "Don't forget to put the error in the notes!", Toast.LENGTH_SHORT).show();
                roboTxt.setVisibility(View.VISIBLE);
                woah = 1;
            } else {
                roboTxt.setVisibility(View.INVISIBLE);
                woah = 0;
            }
        });

        //Auto stuff
        autoTele.setOnClickListener(v -> {
            if (alliance.equals("blue")) {
                background.setBackgroundColor(Color.argb(127, 127, 127, 247));
            }
            if (alliance.equals("red")) {
                background.setBackgroundColor(Color.argb(127, 247, 127, 127));
            }
            if (autoTele.getText() == "Teleop") {
                autoTele.setText("Auto");
                teleLayout.setVisibility(View.INVISIBLE);
                autoPaths.setVisibility(View.VISIBLE);
            } else {
                autoTele.setText("Teleop");
                teleLayout.setVisibility(View.VISIBLE);
                autoPaths.setVisibility(View.INVISIBLE);

            }
        });

        //TELEOP
        speakerPlus.setOnClickListener(v -> {
            if (autoTele.getText() == "Teleop") {
                teleSpeaker += 1;
                speakerTxt.setText("speaker: " + teleSpeaker);
            }
        });


        speakerMinus.setOnClickListener(v -> {
            if (autoTele.getText() == "Teleop") {
                if (teleSpeaker > 0) {
                    teleSpeaker -= 1;
                    speakerTxt.setText("speaker: " + teleSpeaker);
                }
            } else {
                if (autoSpeaker > 0) {
                    autoSpeaker -= 1;
                    speakerTxt.setText("(Auto) speaker: " + autoSpeaker);
                }

            }

        });

        ampPlus.setOnClickListener(v -> {
            if (autoTele.getText() == "Teleop") {
                teleAmp += 1;
                ampTxt.setText("amp: " + teleAmp);
            }
        });

        ampMinus.setOnClickListener(v -> {
            if (autoTele.getText() == "Teleop") {
                if (teleAmp > 0) {
                    teleAmp -= 1;
                    ampTxt.setText("amp: " + (teleAmp));
                }
            } else {
                if (autoAmp > 0) {
                    autoAmp -= 1;
                    ampTxt.setText("(Auto) amp: " + (autoAmp));
                }
            }
        });

        feedPlus.setOnClickListener(v -> {
                telefeed += 1;
            feedTxt.setText("Notes fed: " + telefeed);
        });

        feedMinus.setOnClickListener(v -> {
            if (telefeed > 0) {
                telefeed -= 1;
            }
            feedTxt.setText("Notes fed: " + telefeed);
        });


        note1.setOnClickListener(v -> {
            if (note1_val == "null") {
                note1_val = "Make";
                note1.setBackgroundColor(Color.GREEN);
                autoSpeaker += 1;
                totalOrder += 1;
                note1_order = totalOrder;
            } else if (note1_val == "Make") {
                autoSpeaker -= 1;
                note1_val = "Miss";
                note1.setBackgroundColor(Color.RED);
            } else {
                note1_val = "null";
                note1.setBackgroundColor(Color.LTGRAY);
                totalOrder -= 1;
                idiotchecker(note1_order);
                note1_order = 0;
            }
            updatenotes();
        });

        note2.setOnClickListener(v -> {
            if (note2_val == "null") {
                note2_val = "Make";
                note2.setBackgroundColor(Color.GREEN);
                totalOrder += 1;
                note2_order = totalOrder;
                autoSpeaker += 1;
            } else if (note2_val == "Make") {
                autoSpeaker -= 1;
                note2_val = "Miss";
                note2.setBackgroundColor(Color.RED);
            } else {
                note2_val = "null";
                note2.setBackgroundColor(Color.LTGRAY);
                totalOrder -= 1;
                idiotchecker(note2_order);
                note2_order = 0;
            }
            updatenotes();
        });

        note3.setOnClickListener(v -> {
            if (note3_val == "null") {
                note3_val = "Make";
                note3.setBackgroundColor(Color.GREEN);
                totalOrder += 1;
                note3_order = totalOrder;
                autoSpeaker += 1;
            } else if (note3_val == "Make") {
                autoSpeaker -= 1;
                note3_val = "Miss";
                note3.setBackgroundColor(Color.RED);
            } else {
                note3_val = "null";
                note3.setBackgroundColor(Color.LTGRAY);
                totalOrder -= 1;
                idiotchecker(note3_order);
                note3_order = 0;
            }
            updatenotes();
        });

        note4.setOnClickListener(v -> {
            if (note4_val == "null") {
                note4_val = "Make";
                note4.setBackgroundColor(Color.GREEN);
                totalOrder += 1;
                autoSpeaker += 1;
                note4_order = totalOrder;
            } else if (note4_val == "Make") {
                autoSpeaker -= 1;
                note4_val = "Miss";
                note4.setBackgroundColor(Color.RED);
            } else {
                note4_val = "null";
                note4.setBackgroundColor(Color.LTGRAY);
                totalOrder -= 1;
                idiotchecker(note4_order);
                note4_order = 0;
            }
            updatenotes();
        });

        note5.setOnClickListener(v -> {
            if (note5_val == "null") {
                note5_val = "Make";
                note5.setBackgroundColor(Color.GREEN);
                totalOrder += 1;
                note5_order = totalOrder;
                autoSpeaker += 1;
            } else if (note5_val == "Make") {
                autoSpeaker -= 1;
                note5_val = "Miss";
                note5.setBackgroundColor(Color.RED);
            } else {
                note5_val = "null";
                note5.setBackgroundColor(Color.LTGRAY);
                totalOrder -= 1;
                idiotchecker(note5_order);
                note5_order = 0;
            }
            updatenotes();
        });

        note6.setOnClickListener(v -> {
            if (note6_val == "null") {
                note6_val = "Make";
                note6.setBackgroundColor(Color.GREEN);
                totalOrder += 1;
                note6_order = totalOrder;
                autoSpeaker += 1;
            } else if (note6_val == "Make") {
                autoSpeaker -= 1;
                note6_val = "Miss";
                note6.setBackgroundColor(Color.RED);
            } else {
                note6_val = "null";
                note6.setBackgroundColor(Color.LTGRAY);
                totalOrder -= 1;
                idiotchecker(note6_order);
                note6_order = 0;
            }
            updatenotes();
        });

        note7.setOnClickListener(v -> {
            if (note7_val == "null") {
                note7_val = "Make";
                note7.setBackgroundColor(Color.GREEN);
                totalOrder += 1;
                note7_order = totalOrder;
                autoSpeaker += 1;
            } else if (note7_val == "Make") {
                autoSpeaker -= 1;
                note7_val = "Miss";
                note7.setBackgroundColor(Color.RED);
            } else {
                note7_val = "null";
                note7.setBackgroundColor(Color.LTGRAY);
                totalOrder -= 1;
                idiotchecker(note7_order);
                note7_order = 0;
            }
            updatenotes();
        });

        note8.setOnClickListener(v -> {
            if (note8_val == "null") {
                note8_val = "Make";
                note8.setBackgroundColor(Color.GREEN);
                totalOrder += 1;
                note8_order = totalOrder;
                autoSpeaker += 1;
            } else if (note8_val == "Make") {
                autoSpeaker -= 1;
                note8_val = "Miss";
                note8.setBackgroundColor(Color.RED);
            } else {
                note8_val = "null";
                note8.setBackgroundColor(Color.LTGRAY);
                totalOrder -= 1;
                idiotchecker(note8_order);
                note8_order = 0;
            }
            updatenotes();
        });

        preload.setOnClickListener(v -> {
            if (preload_val == "null") {
                preload_val = "Make";
                preload.setBackgroundColor(Color.GREEN);
                autoSpeaker += 1;
            } else if (preload_val == "Make") {
                autoSpeaker -= 1;
                preload_val = "Miss";
                preload.setBackgroundColor(Color.RED);
            } else {
                preload_val = "null";
                preload.setBackgroundColor(Color.LTGRAY);
            }
        });


        submit.setOnClickListener(v -> {
            notesBox.setVisibility(android.view.View.VISIBLE);
            grayBox.setVisibility(View.VISIBLE);
            notes.setVisibility(View.VISIBLE);
            notesSubmit.setVisibility(View.VISIBLE);
            backbtn.setVisibility(View.VISIBLE);
        });

        backbtn.setOnClickListener(v -> {
            notesBox.setVisibility(android.view.View.INVISIBLE);
            grayBox.setVisibility(View.INVISIBLE);
        });

        sameScouter.setOnClickListener(v -> {
            nameInput.setText(scouterName);
        });

        //finalSub
        notesSubmit.setOnClickListener(v -> {
            spike.setVisibility(View.INVISIBLE);
            String submitError = "";
            SimpleDateFormat time = new SimpleDateFormat("dd-HHmmss", Locale.getDefault());
            int team;
            int round;
            //Special handling
            if (teamNumber.getText().toString().equals("")) {
                team = -1;
            } else {
                team = Integer.parseInt(teamNumber.getText().toString());
            }
            if (matchNumber.getText().toString().equals("")) {
                round = -1;
            } else {
                round = Integer.parseInt(matchNumber.getText().toString());
            }
            //These are telling the toast what to put in the error field, and it changes the color to yellow.
            if (alliance.equals("none")) {
                submitError += " No Alliance,";
                allianceTxt.setBackgroundColor(Color.argb(255, 255, 255, 0));
                preMenu.setVisibility(View.VISIBLE);
            }
            if (nameInput.getText().toString().equals("")) {
                submitError += " No Name,";
                nameInput.setBackgroundColor(Color.argb(255, 255, 255, 0));
                preMenu.setVisibility(View.VISIBLE);
            }
            if (matchNumber.getText().toString().equals("420")) {
                submitError += " No. Its not even funny,";
                matchNumber.setBackgroundColor(Color.argb(255, 255, 255, 0));
                preMenu.setVisibility(View.VISIBLE);
            }
            if (endGame.getSelectedItem().toString().equals("No Input")) {
                submitError += " No Endgame Choice,";
                preMenu.setVisibility(View.INVISIBLE);
            }
            if (teamNumber.getText().toString().equals("")) {
                submitError += " No Team#,";
                teamNumber.setBackgroundColor(Color.argb(255, 255, 255, 0));
                preMenu.setVisibility(View.VISIBLE);
            }
            if (matchNumber.getText().toString().equals("")) {
                submitError += " No Round#,";
                matchNumber.setBackgroundColor(Color.argb(255, 255, 255, 0));
                preMenu.setVisibility(View.VISIBLE);
            }
            if (!submitError.equals("")) {
                submitError = submitError.substring(0, submitError.length() - 1) + ".";
            }
            //If any of the above are true, the thing returns a submit error.
            if (!(submitError.equals(""))) {
                Toast.makeText(getApplicationContext(), "Submit Error:" + submitError, Toast.LENGTH_LONG).show();
                //Place an X if incorrect

                if (preMenu.getVisibility() != View.VISIBLE) {
                    grayBox.setVisibility(View.INVISIBLE);
                }

            } else {
                //This is what happens whenever all is correct
                allianceTxt.setBackgroundColor(Color.TRANSPARENT);
                preMenu.setVisibility(View.VISIBLE);
                grayBox.setVisibility(View.VISIBLE);


                //Save data for transfer
                File dir = getDataDirectory();
                File dir2 = getDataDirectory2();
                //This creates a file
                try {
                    File myFile = new File(dir, team + "_" + round + "_" + time.format(new Date()) + ".txt");
                    FileOutputStream fOut = new FileOutputStream(myFile, true);
                    PrintWriter myOutWriter = new PrintWriter(new OutputStreamWriter(fOut));

                    //This prints all of the lines into the file for transfer.
                    myOutWriter.println("Scouter: " + nameInput.getText());
                    myOutWriter.println("teamNumber: " + team);
                    myOutWriter.println("Timestamp: " + time.format(new Date()));
                    myOutWriter.println("matchNumber: " + round);
                    myOutWriter.println("Alliance: " + alliance);

                    myOutWriter.println("teleSpeaker: " + teleSpeaker);
                    myOutWriter.println("teleAmp: " + teleAmp);
                    myOutWriter.println("autoSpeaker: " + autoSpeaker);
                    myOutWriter.println("autoAmp: " + autoAmp);
                    myOutWriter.println("teleTrap: " + trapScore.getSelectedItem().toString());
                    myOutWriter.println("climb: " + endGame.getSelectedItem());

                    myOutWriter.println("teleFeeds: " + telefeed);

                    myOutWriter.println("autoClose1: " + note1_val);
                    myOutWriter.println("autoClose1Order: " + note1_order);

                    myOutWriter.println("autoClose2: " + note2_val);
                    myOutWriter.println("autoClose2Order: " + note2_order);

                    myOutWriter.println("autoClose3: " + note3_val);
                    myOutWriter.println("autoClose3Order: " + note3_order);

                    myOutWriter.println("autoCenter1: " + note4_val);
                    myOutWriter.println("autoCenter1Order: " + note4_order);

                    myOutWriter.println("autoCenter2: " + note5_val);
                    myOutWriter.println("autoCenter2Order: " + note5_order);

                    myOutWriter.println("autoCenter3: " + note6_val);
                    myOutWriter.println("autoCenter3Order: " + note6_order);

                    myOutWriter.println("autoCenter4: " + note7_val);
                    myOutWriter.println("autoCenter4Order: " + note7_order);

                    myOutWriter.println("autoCenter5: " + note8_val);
                    myOutWriter.println("autoCenter5Order: " + note8_order);

                    myOutWriter.println("autoPreload: " + preload_val);

                    myOutWriter.println("Comment: " + notes.getText());

                    myOutWriter.flush();
                    myOutWriter.close();
                    fOut.close();

                    File myFile2 = new File(dir2, nameInput.getText() + ".txt");
                    FileOutputStream fOut2 = new FileOutputStream(myFile2, true);
                    PrintWriter myOutWriter2 = new PrintWriter(new OutputStreamWriter(fOut2));
                    //This prints all of the lines into the file for transfer.
                    Date date = new Date();
                    myOutWriter2.println(nameInput.getText() + ", " + round + ", " + date);

                    myOutWriter2.flush();
                    myOutWriter2.close();
                    fOut2.close();
                    Toast.makeText(getApplicationContext(), "Data Submitted!", Toast.LENGTH_SHORT).show();

                } catch (IOException e) {
                    //If anything goes wrong, it throws an error instead of crashing
                    Toast.makeText(getApplicationContext(), "Data Submission Failed! (TELL SCOUTING)" + e, Toast.LENGTH_SHORT).show();
                    Log.e("Exception", "File write failed: " + e);
                }
                //Reset Everything
                resetVars();
                teamAuto();
            }
        });
    }

    private String getTeams() {

        StringBuilder text = new StringBuilder();
        try {
            File documents = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
            File file;
            file = new File(documents + "/ScoutingTeams.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                text.append(line).append("\n");
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
            //text.append(teamArray).append("\n");
        }
        Log.e("log", text.toString());
        return text.toString();
    }

    private void teamAuto() {
        if (autofillTeam.isChecked() && !Objects.equals(getTeams(), "") && !matchNumber.getText().toString().equals("")) {
            int tabletnumbercomp = tabletnumber - 1;
            String[] tempIntArr;
            String[] splittempIntArr;
            tempIntArr = getTeams().split("\n");
            roundfill = Integer.parseInt(matchNumber.getText().toString());
            try {
                splittempIntArr = tempIntArr[roundfill - 1].split(",");
                teamNumber.setText(splittempIntArr[tabletnumbercomp]);
            } catch (ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }

    }

    private void runtimer() {
        if (autoclicked == false) {
            final Handler handler = new Handler();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    if (seconds >= 60) {
                        //change mode
                        if (autoTele.getText().equals("Auto")) {
                            autoTele.setText("Teleop");
                            teleLayout.setVisibility(View.VISIBLE);
                            countDown = false;
                            seconds = 0;

                        }
                    }
                    if (autoTele.getText().equals("Auto")) {
                        if (seconds >= 45 && seconds % 2 == 0) {
                            background.setBackgroundColor(Color.YELLOW);
                        }

                        if (seconds >= 45 && seconds % 2 == 1) {
                            if (alliance.equals("blue")) {
                                background.setBackgroundColor(Color.argb(127, 127, 127, 247));
                            }
                            if (alliance.equals("red")) {
                                background.setBackgroundColor(Color.argb(127, 247, 127, 127));
                            }
                        }

                        if (countDown == true) {
                            seconds = seconds + 1;
                        }

                        handler.postDelayed(this, 250);

                    }
                }
            });
        }
    }
    private void resetVars() {
        roundfill = Integer.parseInt(matchNumber.getText().toString());
        roundfill++;

        notes.setText("");
        scouterName = nameInput.getText().toString();
        nameInput.setText("");
        matchNumber.setText(String.valueOf(roundfill));
        teamNumber.setText("");
        endGame.setSelection(0);
        trapScore.setSelection(0);
        robotError.setChecked(false);
        preMenu.setVisibility(View.VISIBLE);
        grayBox.setVisibility(View.VISIBLE);
        nameInput.setVisibility(View.VISIBLE);
        matchNumber.setVisibility(View.VISIBLE);
        teamNumber.setVisibility(View.VISIBLE);
        sameScouter.setVisibility(View.VISIBLE);
        notes.setVisibility(View.INVISIBLE);
        notesBox.setVisibility(View.INVISIBLE);
        notesSubmit.setVisibility(View.INVISIBLE);
        roberTxt.setVisibility(View.INVISIBLE);
        telefeed = 0;
        teleAmp = 0;
        teleSpeaker = 0;
        autoAmp = 0;
        autoSpeaker = 0;
        timesPressed = 0;

        totalOrder = 0;

        note1_order = 0;
        note1_val = "null";
        note1.setBackgroundColor(Color.LTGRAY);
        note2_order = 0;
        note2_val = "null";
        note2.setBackgroundColor(Color.LTGRAY);
        note3_order = 0;
        note3_val = "null";
        note3.setBackgroundColor(Color.LTGRAY);
        note4_order = 0;
        note4_val = "null";
        note4.setBackgroundColor(Color.LTGRAY);
        note5_order = 0;
        note5_val = "null";
        note5.setBackgroundColor(Color.LTGRAY);
        note6_order = 0;
        note6_val = "null";
        note6.setBackgroundColor(Color.LTGRAY);
        note7_order = 0;
        note7_val = "null";
        note7.setBackgroundColor(Color.LTGRAY);
        note8_order = 0;
        note8_val = "null";
        note8.setBackgroundColor(Color.LTGRAY);

        preload_val = "null";
        preload.setBackgroundColor(Color.LTGRAY);

        updatenotes();

        if (roundfill > 1) {
            sameScouter.setVisibility(View.VISIBLE);
        }
        if (!autofillTeam.isChecked()) {
            teamNumber.setText("");
        }
    }

    @Override
    public void onBackPressed() {}
    private File getDataDirectory() {
        File directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        File myDir = new File(directory + "/ScoutingData");
        myDir.mkdirs();
        return myDir;
    }
    private File getDataDirectory2() {
        File directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        File myDir = new File(directory + "/ScoutingTimes");
        myDir.mkdirs();
        return myDir;
    }
    public void hideKeyboard(View v) {
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(),0);
    }
    public void idiotchecker(int x) {
        if (note1_order > x) {
            note1_order -= 1;
        }

        if (note2_order > x) {
            note2_order -= 1;
        }

        if (note3_order > x) {
            note3_order -= 1;
        }

        if (note4_order > x) {
            note4_order -= 1;
        }
        if (note5_order > x) {
            note5_order -= 1;
        }

        if (note6_order > x) {
            note6_order -= 1;
        }

        if (note7_order > x) {
            note7_order -= 1;
        }

        if (note8_order > x) {
            note8_order -= 1;
        }
    }

    public void updatenotes() {
        note2txt.setText(Integer.toString(note2_order));
        note1txt.setText(Integer.toString(note1_order));
        note3txt.setText(Integer.toString(note3_order));
        note4txt.setText(Integer.toString(note4_order));
        note5txt.setText(Integer.toString(note5_order));
        note6txt.setText(Integer.toString(note6_order));
        note7txt.setText(Integer.toString(note7_order));
        note8txt.setText(Integer.toString(note8_order));

    }
}