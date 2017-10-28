package com.example.keegz_000.opsc7311_assign1_15003770;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;

import java.util.Random;

public class Game8x5VeryHard extends AppCompatActivity implements View.OnClickListener
{

    //Variables
    private int numberOfPictures;
    private MemoryButtonVeryHard[] buttons;
    private int[] buttonGraphicLocation;
    private int[] buttonGraphics;

    private MemoryButtonVeryHard selectedPicture1;
    private MemoryButtonVeryHard selectedPicture2;

    private boolean isBusy = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game8x5_very_hard);

        //Gets the gridlayout
        GridLayout gridLayout = (GridLayout)findViewById(R.id.grid_layout_8x5);

        int numColumns = gridLayout.getColumnCount();
        int numRows = gridLayout.getRowCount();

        numberOfPictures = numColumns * numRows;
        buttons = new MemoryButtonVeryHard[numberOfPictures];
        buttonGraphics = new int[numberOfPictures / 2];

        buttonGraphics[0] = R.drawable.bunny;
        buttonGraphics[1] = R.drawable.hippo;
        buttonGraphics[2] = R.drawable.cow;
        buttonGraphics[3] = R.drawable.leopard;
        buttonGraphics[4] = R.drawable.pig;
        buttonGraphics[5] = R.drawable.sheep;
        buttonGraphics[6] = R.drawable.turtle;
        buttonGraphics[7] = R.drawable.zebra;
        buttonGraphics[8] = R.drawable.dino;
        buttonGraphics[9] = R.drawable.goat;
        buttonGraphics[10] = R.drawable.hedgehog;
        buttonGraphics[11] = R.drawable.koala;
        buttonGraphics[12] = R.drawable.dog;
        buttonGraphics[13] = R.drawable.llama;
        buttonGraphics[14] = R.drawable.monkey;
        buttonGraphics[15] = R.drawable.eagle;
        buttonGraphics[16] = R.drawable.raindeer;
        buttonGraphics[17] = R.drawable.elephant;
        buttonGraphics[18] = R.drawable.horns;
        buttonGraphics[19] = R.drawable.polarbear;


        buttonGraphicLocation = new int[numberOfPictures];

        shuffleButtons();

        for(int r = 0; r < numRows; r++)
        {
            for(int c = 0; c < numColumns; c++)
            {
                MemoryButtonVeryHard picButton = new MemoryButtonVeryHard(this, r, c, buttonGraphics[ buttonGraphicLocation[r * numColumns + c] ]);
                picButton.setId(View.generateViewId());
                picButton.setOnClickListener(this);
                buttons[r * numColumns + c] = picButton;
                gridLayout.addView(picButton);
            }
        }
    }
    //*************************************************************************************************
    //Randomly shuffles the pictures around so they not in the same place every time
    protected void shuffleButtons()
    {
        Random ran = new Random();

        for(int i = 0; i < numberOfPictures; i++)
        {
            buttonGraphicLocation[i] = i % (numberOfPictures / 2);
        }

        for(int i = 0; i < numberOfPictures; i++)
        {
            int position = buttonGraphicLocation[i];
            int swapPic = ran.nextInt(40);
            buttonGraphicLocation[i] = buttonGraphicLocation[swapPic];
            buttonGraphicLocation[swapPic] = position;
        }
    }
    //*************************************************************************************************
    @Override
    public void onClick(View view) {

        if(isBusy)
            return;

        MemoryButtonVeryHard button = (MemoryButtonVeryHard) view;

        if(button.isMatched)
            return;

        if(selectedPicture1 == null)
        {
            selectedPicture1 = button;
            selectedPicture1.flipPic();
            return;
        }

        if(selectedPicture1.getId() == button.getId())
        {
            return;
        }

        //Checks to see if the pictures are the same and uses a handler to delay the pictures in place for a certain amount of time
        if(selectedPicture1.getFrontDrawableId() == button.getFrontDrawableId())
        {
            button.flipPic();
            button.setMatched(true);
            selectedPicture1.setMatched(true);
            selectedPicture1.setEnabled(false);
            button.setEnabled(false);
            selectedPicture1 = null;
        }
        else
        {
            selectedPicture2 = button;
            selectedPicture2.flipPic();
            isBusy = true;

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    selectedPicture2.flipPic();
                    selectedPicture1.flipPic();
                    selectedPicture1 = null;
                    selectedPicture2 = null;
                    isBusy = false;
                }
            }, 500);

        }
    }
}
