package com.example.keegz_000.opsc7311_assign1_15003770;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatDrawableManager;
import android.widget.Button;
import android.widget.GridLayout;

public class MemoryButton extends Button
{
    //Variables
    protected int row;
    protected int column;
    protected int frontDrawableId;

    protected Drawable front;
    protected Drawable back;

    protected boolean isFlipped = false;
    protected boolean isMatched = false;

    public MemoryButton(Context context, int r, int c, int frontImageDrawableId)
    {
        super(context);
        row = r;
        column = c;
        frontDrawableId = frontImageDrawableId;

        //Shows which picture will be used for the front and back of the button(picture)
        front = AppCompatDrawableManager.get().getDrawable(context, frontImageDrawableId);
        back = AppCompatDrawableManager.get().getDrawable(context, R.drawable.button_question);
        setBackground(back);


        //Creates parameters for the gridlayout and shapes the size of the pictures
        GridLayout.LayoutParams params = new GridLayout.LayoutParams(GridLayout.spec(r), GridLayout.spec(c));
        params.width = (int) getResources().getDisplayMetrics().density * 82;
        params.height = (int) getResources().getDisplayMetrics().density * 82;
        setLayoutParams(params);
    }
    //*************************************************************************************************
    public boolean isMatched() {
        return isMatched;
    }

    //*************************************************************************************************
    public void setMatched(boolean matched) {
        isMatched = matched;
    }

    //*************************************************************************************************
    public int getFrontDrawableId() {
        return frontDrawableId;
    }

    //*************************************************************************************************
    public void flipPic()
    {
        if(isMatched)
            return;

        if(isFlipped)
        {
            setBackground(back);
            isFlipped = false;
        }
        else
        {
            setBackground(front);
            isFlipped = true;
        }
    }
}
