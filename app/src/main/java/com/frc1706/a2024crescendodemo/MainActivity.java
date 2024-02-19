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
    Spinner endGame, selectedTabNum;
    Button update, imageGrabber, backPre, speakerPlus, speakerMinus, ampPlus, ampMinus, trapPlus, trapMinus, submit, notesSubmit, noShow, redAlliance, blueAlliance, continueBtn, grayBox, autoTele, sameScouter, backbtn, testBtn;
    Switch defense, robotError;
    TextView ampTxt, speakerTxt, trapTxt, allianceTxt, infoDisplay, endgameTxt, roboTxt, roberTxt, defenTxt, oobydooby, oobydooby2;
    EditText nameInput, matchNumber, teamNumber, notes;
    CheckBox autofillTeam;
    ImageView rrLogo, spike;
    int teleAmp;
    int teleSpeaker;
    int teleTrap;
    int autoAmp;
    int autoSpeaker;
    int tabletnumber;
    int roundfill = 1;
    int timesPressed = 0;
    int woah = 0;
    String team = "";
    String alliance = "none";
    String tabletName;
    String scouterName;


    ConstraintLayout notesBox, preMenu, background;

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
        trapPlus = findViewById(R.id.trapPlus);
        trapMinus = findViewById(R.id.trapMinus);
        submit = findViewById(R.id.submit);
        endGame = findViewById(R.id.endGame);
        ampTxt = findViewById(R.id.ampValue);
        speakerTxt = findViewById(R.id.speakerValue);
        trapTxt = findViewById(R.id.trapScore);
        defense = findViewById(R.id.defense);
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
        defenTxt = findViewById(R.id.defenceTxt);
        oobydooby = findViewById(R.id.speaktxt);
        oobydooby2 = findViewById(R.id.textviewongodiwilldosomebad);

        Animation rotate = AnimationUtils.loadAnimation(this, R.anim.rotate_picture);
        Animation flip = AnimationUtils.loadAnimation(this, R.anim.flip);
        Animation fall = AnimationUtils.loadAnimation(this, R.anim.fall);

        tabletName = Settings.Secure.getString(getContentResolver(), "bluetooth_name");

        switch (tabletName) {
            case "1706's 1st Fire":
                tabletnumber = 4;
                selectedTabNum.setSelection(3);
                break;
            case "1706's 2nd Fire":
                tabletnumber = 5;
                selectedTabNum.setSelection(4);
                break;
            case "1706's 3rd Fire":
                tabletnumber = 6;
                selectedTabNum.setSelection(5);
                break;
            case "1706's 5th Fire":
                tabletnumber = 1;
                selectedTabNum.setSelection(0);
                break;
            case "1706's 6th Fire":
                tabletnumber = 2;
                selectedTabNum.setSelection(1);
                break;
            case "1706's 7th Fire":
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
                trapMinus.setVisibility(View.INVISIBLE);
                trapPlus.setVisibility(View.INVISIBLE);
                trapTxt.setVisibility(View.INVISIBLE);
                endGame.setVisibility(View.INVISIBLE);
                defense.setVisibility(View.INVISIBLE);
                submit.setVisibility(View.INVISIBLE);
                endgameTxt.setVisibility(View.INVISIBLE);
                ampTxt.setText("(Auto) amp: " + autoAmp);
                trapTxt.setText("trap: " + teleTrap);
                speakerTxt.setText("(Auto) speaker: " + autoSpeaker);
                team = teamNumber.getText().toString();
                robotError.setVisibility(View.INVISIBLE);
                roberTxt.setVisibility(View.INVISIBLE);
                defenTxt.setVisibility(View.INVISIBLE);


                if (alliance.equals("blue")) {
                    endGame.setBackgroundColor(Color.argb(0, 127, 127, 247));
                } else {
                    endGame.setBackgroundColor(Color.argb(0, 247, 127, 127));
                }

                if (team.equals("1706")) {
                    background.startAnimation(rotate);
                }
                if (team.equals("9401")) {
                    Toast.makeText(this, "Midas YAY", Toast.LENGTH_SHORT).show();
                    ampMinus.setBackgroundColor(Color.argb(127, 255, 215, 0));
                    ampPlus.setBackgroundColor(Color.argb(127, 255, 215, 0));
                    speakerMinus.setBackgroundColor(Color.argb(127, 255, 215, 0));
                    speakerPlus.setBackgroundColor(Color.argb(127, 255, 215, 0));
                    trapMinus.setBackgroundColor(Color.argb(127, 255, 215, 0));
                    trapPlus.setBackgroundColor(Color.argb(127, 255, 215, 0));
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
                    oobydooby2.startAnimation(flip);
                    oobydooby.startAnimation(flip);
                }
                int random = new Random().nextInt(101);
                if (nameInput.getText().toString().equalsIgnoreCase("rainbow")) {
                    ampMinus.setBackgroundColor(Color.argb(new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256)));
                    ampPlus.setBackgroundColor(Color.argb(new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256)));
                    speakerPlus.setBackgroundColor(Color.argb(new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256)));
                    speakerMinus.setBackgroundColor(Color.argb(new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256)));
                    trapMinus.setBackgroundColor(Color.argb(new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256)));
                    trapPlus.setBackgroundColor(Color.argb(new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256)));
                    submit.setBackgroundColor(Color.argb(new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256)));
                    autoTele.setBackgroundColor(Color.argb(new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256)));
                }
                if (!nameInput.getText().toString().equalsIgnoreCase("rainbow") && !team.equals("9401")) {
                    ampMinus.setBackgroundColor(Color.argb(255, 156, 39, 176));
                    ampPlus.setBackgroundColor(Color.argb(255, 156, 39, 176));
                    speakerPlus.setBackgroundColor(Color.argb(255, 156, 39, 176));
                    speakerMinus.setBackgroundColor(Color.argb(255, 156, 39, 176));
                    trapMinus.setBackgroundColor(Color.argb(255, 156, 39, 176));
                    trapPlus.setBackgroundColor(Color.argb(255, 156, 39, 176));
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
                if (nameInput.getText().toString().equals("admin1706")) {
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
            if (autoTele.getText() == "Teleop") {
                autoTele.setText("Auto");
                trapMinus.setVisibility(View.INVISIBLE);
                trapPlus.setVisibility(View.INVISIBLE);
                trapTxt.setVisibility(View.INVISIBLE);
                endGame.setVisibility(View.INVISIBLE);
                defense.setVisibility(View.INVISIBLE);
                submit.setVisibility(View.INVISIBLE);
                endGame.setVisibility(View.INVISIBLE);
                endgameTxt.setVisibility(View.INVISIBLE);
                ampTxt.setText("(Auto) amp: " + autoAmp);
                speakerTxt.setText("(Auto) speaker: " + autoSpeaker);
                robotError.setVisibility(View.INVISIBLE);
                roboTxt.setVisibility(View.INVISIBLE);
                roberTxt.setVisibility(View.INVISIBLE);
                defenTxt.setVisibility(View.INVISIBLE);
            } else {
                autoTele.setText("Teleop");
                trapMinus.setVisibility(View.VISIBLE);
                trapPlus.setVisibility(View.VISIBLE);
                trapTxt.setVisibility(View.VISIBLE);
                endGame.setVisibility(View.VISIBLE);
                defense.setVisibility(View.VISIBLE);
                submit.setVisibility(View.VISIBLE);
                endgameTxt.setVisibility(View.VISIBLE);
                robotError.setVisibility(View.VISIBLE);
                ampTxt.setText("(Teleop) amp: " + teleAmp);
                speakerTxt.setText("(Teleop) speaker: " + teleSpeaker);
                roberTxt.setVisibility(View.VISIBLE);
                defenTxt.setVisibility(View.VISIBLE);

            }
        });

        //TELEOP
        speakerPlus.setOnClickListener(v -> {
            if (autoTele.getText() == "Teleop" && teleSpeaker <= 40) {
                teleSpeaker += 1;
                speakerTxt.setText("(Teleop) speaker: " + teleSpeaker);
            } else {
                autoSpeaker += 1;
                speakerTxt.setText("(Auto) speaker: " + autoSpeaker);
                if (autoSpeaker >= 9) {
                    builder.setMessage("Are you supposed to be in auto, that's a lot of points?")
                            .setPositiveButton("Yes", areyousureurinautospeaker)
                            .setNegativeButton("No", areyousureurinautospeaker)
                            .show();
                }
                if (autoSpeaker > 3 && autoAmp > 3) {
                    builder.setMessage("Are you supposed to be in auto, they scored more than 3 amp and more than 3 speaker?")
                            .setPositiveButton("Yes", areyousureurinauto3and3)
                            .setNegativeButton("No", areyousureurinauto3and3)
                            .show();
                }
            }
        });

        //weeboo dweeboo gweeboo

        speakerMinus.setOnClickListener(v -> {
            if (autoTele.getText() == "Teleop") {
                if (teleSpeaker > 0) {
                    teleSpeaker -= 1;
                    speakerTxt.setText("(Teleop) speaker: " + teleSpeaker);
                }
            } else {
                if (autoSpeaker > 0) {
                    autoSpeaker -= 1;
                    speakerTxt.setText("(Auto) speaker: " + autoSpeaker);
                }

            }

        });

        ampPlus.setOnClickListener(v -> {
            if (autoTele.getText() == "Teleop" && teleAmp <= 40) {
                teleAmp += 1;
                ampTxt.setText("(Teleop) amp: " + teleAmp);
            } else {
                autoAmp += 1;
                ampTxt.setText("(Auto) amp: " + autoAmp);
                if (autoAmp >= 5) {
                    builder.setMessage("Are you supposed to be in auto, that's a lot of points?")
                            .setPositiveButton("Yes", areyousureurinautoamp)
                            .setNegativeButton("No", areyousureurinautoamp)
                            .show();
                }
                if (autoSpeaker > 3 && autoAmp > 3) {
                    builder.setMessage("Are you supposed to be in auto, they scored more than 3 amp and more than 3 speaker?")
                            .setPositiveButton("Yes", areyousureurinauto3and3)
                            .setNegativeButton("No", areyousureurinauto3and3)
                            .show();
                }
            }
        });

        ampMinus.setOnClickListener(v -> {
            if (autoTele.getText() == "Teleop") {
                if (teleAmp > 0) {
                    teleAmp -= 1;
                    ampTxt.setText("(Teleop) amp: " + (teleAmp));
                }
            } else {
                if (autoAmp > 0) {
                    autoAmp -= 1;
                    ampTxt.setText("(Auto) amp: " + (autoAmp));
                }
            }
        });

        trapPlus.setOnClickListener(v -> {
            if (teleTrap < 3) {
                teleTrap += 1;
            }
            trapTxt.setText("trap: " + teleTrap);
        });

        trapMinus.setOnClickListener(v -> {
            if (teleTrap > 0) {
                teleTrap -= 1;
            }
            trapTxt.setText("trap: " + teleTrap);
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
                    myOutWriter.println("teleTrap: " + teleTrap);
                    myOutWriter.println("climb: " + endGame.getSelectedItem());

                    if (defense.isChecked()) {
                        myOutWriter.println("PlayedDefense: yes");
                    } else {
                        myOutWriter.println("PlayedDefense: no");
                    }

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
                Toast.makeText(this, splittempIntArr[tabletnumbercomp], Toast.LENGTH_SHORT).show();

            } catch (ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
            }
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
        submit.setVisibility(View.INVISIBLE);
        endGame.setVisibility(View.INVISIBLE);
        robotError.setChecked(false);
        defense.setChecked(false);
        preMenu.setVisibility(View.VISIBLE);
        grayBox.setVisibility(View.VISIBLE);
        nameInput.setVisibility(View.VISIBLE);
        matchNumber.setVisibility(View.VISIBLE);
        teamNumber.setVisibility(View.VISIBLE);
        sameScouter.setVisibility(View.VISIBLE);
        notes.setVisibility(View.INVISIBLE);
        notesBox.setVisibility(View.INVISIBLE);
        notesSubmit.setVisibility(View.INVISIBLE);
        teleTrap = 0;
        teleAmp = 0;
        teleSpeaker = 0;
        autoAmp = 0;
        autoSpeaker = 0;
        timesPressed = 0;

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
}