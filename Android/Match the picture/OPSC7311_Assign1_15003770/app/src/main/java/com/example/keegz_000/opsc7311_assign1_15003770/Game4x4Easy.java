package com.example.keegz_000.opsc7311_assign1_15003770;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;

import java.util.Random;

public class Game4x4Easy extends AppCompatActivity implements View.OnClickListener
{

    //Variables
    private int numberOfPictures;
    private MemoryButton[] buttons;
    private int[] buttonGraphicLocation;
    private int[] buttonGraphics;

    private MemoryButton selectedPicture1;
    private MemoryButton selectedPicture2;

    private boolean isBusy = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game4x4_easy);

        //Gets the gridlayout
        GridLayout gridLayout = (GridLayout)findViewById(R.id.grid_layout_4x4);

        int numColumns = gridLayout.getColumnCount();
        int numRows = gridLayout.getRowCount();

        numberOfPictures = numColumns * numRows;
        buttons = new MemoryButton[numberOfPictures];
        buttonGraphics = new int[numberOfPictures / 2];

        buttonGraphics[0] = R.drawable.bunny;
        buttonGraphics[1] = R.drawable.hippo;
        buttonGraphics[2] = R.drawable.kangaroo;
        buttonGraphics[3] = R.drawable.leopard;
        buttonGraphics[4] = R.drawable.pig;
        buttonGraphics[5] = R.drawable.sheep;
        buttonGraphics[6] = R.drawable.squirrel;
        buttonGraphics[7] = R.drawable.zebra;

        buttonGraphicLocation = new int[numberOfPictures];

        shuffleButtons();

        for(int r = 0; r < numRows; r++)
        {
            for(int c = 0; c < numColumns; c++)
            {
                MemoryButton picButton = new MemoryButton(this, r, c, buttonGraphics[ buttonGraphicLocation[r * numColumns + c] ]);
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
            int swapPic = ran.nextInt(16);
            buttonGraphicLocation[i] = buttonGraphicLocation[swapPic];
            buttonGraphicLocation[swapPic] = position;
        }
    }
    //*************************************************************************************************
    @Override
    public void onClick(View view) {

        if(isBusy)
            return;

        MemoryButton button = (MemoryButton) view;

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
