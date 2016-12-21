package com.yitsol.quickmeal.utilities;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by User on 24-Mar-16.
 */
public class FontStyle {
    public static String openSansRegular = "opensans regular";
    public static String opensanscondensedBold = "OpenSans-CondBold";
    public static String BLENDE_SCRIPT = "blendscript";
    public Typeface typeFace_AmTypeWriter;
    Context context;

    public FontStyle(Context context) {
        this.context = context;
    }


    public void applyfontBasedOnSelection(View view, String type) {
        if (type.equalsIgnoreCase(openSansRegular)) {
            typeFace_AmTypeWriter = Typeface.createFromAsset(context.getAssets(), "OpenSans-Regular.ttf");

            ((TextView) view).setTypeface(typeFace_AmTypeWriter);
        } else if (type.equalsIgnoreCase(opensanscondensedBold)) {
            typeFace_AmTypeWriter = Typeface.createFromAsset(context.getAssets(), "OpenSans-CondBold.ttf");

            ((TextView) view).setTypeface(typeFace_AmTypeWriter);
        } else if (type.equalsIgnoreCase(BLENDE_SCRIPT)) {
            typeFace_AmTypeWriter = Typeface.createFromAsset(context.getAssets(), "BLENDA SCRIPT.OTF");

            ((TextView) view).setTypeface(typeFace_AmTypeWriter);
        }

    }

    public void applyfontToGroup(ViewGroup viewGroup, String type) {

        if (type.equalsIgnoreCase(openSansRegular)) {

            typeFace_AmTypeWriter = Typeface.createFromAsset(context.getAssets(), "OpenSans-Regular.ttf");

            setFont(viewGroup, typeFace_AmTypeWriter);
        } else if (type.equalsIgnoreCase(opensanscondensedBold)) {

            typeFace_AmTypeWriter = Typeface.createFromAsset(context.getAssets(), "OpenSans-CondBold.ttf");

            setFont(viewGroup, typeFace_AmTypeWriter);
        } else if (type.equalsIgnoreCase(BLENDE_SCRIPT)) {

            typeFace_AmTypeWriter = Typeface.createFromAsset(context.getAssets(), "BLENDA SCRIPT.OTF");

            setFont(viewGroup, typeFace_AmTypeWriter);
        }
    }

    public void applyfontToGroup(ViewGroup viewGroup) {


        typeFace_AmTypeWriter = Typeface.createFromAsset(context.getAssets(), "OpenSans-Regular.ttf");

        setFont(viewGroup, typeFace_AmTypeWriter);
    }

    public void applyfontToView(View view) {
        typeFace_AmTypeWriter = Typeface.createFromAsset(context.getAssets(), "OpenSans-Regular.ttf");

        ((TextView) view).setTypeface(typeFace_AmTypeWriter);
    }

    public void setFont(ViewGroup group, Typeface font) {
        int count = group.getChildCount();
        View v;
        for (int i = 0; i < count; i++) {
            v = group.getChildAt(i);
            if (v instanceof TextView) {
                ((TextView) v).setTypeface(font);
            } else if (v instanceof ViewGroup)
                setFont((ViewGroup) v, font);
        }
    }
}
