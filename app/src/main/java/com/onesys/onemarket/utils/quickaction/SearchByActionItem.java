package com.onesys.onemarket.utils.quickaction;

import java.util.ArrayList;

public class SearchByActionItem {
    private ArrayList<OptionItem> arrayValues;
    private int currentValuePosition;
    private int id;
    private String title;

    public SearchByActionItem(int paramInt, String paramString)
    {
        this(paramInt, paramString, new ArrayList());
    }

    public SearchByActionItem(int id, String title, ArrayList<OptionItem> optionList)
    {
        this.id = id;
        this.title = title;
        this.arrayValues = optionList;
        this.currentValuePosition = (-1 + optionList.size());
    }

    public OptionItem getArrayValue(int paramInt)
    {
        if ((paramInt < 0) || (paramInt >= this.arrayValues.size()))
            return null;
        return (OptionItem)this.arrayValues.get(paramInt);
    }

    public ArrayList<OptionItem> getArrayValues()
    {
        return this.arrayValues;
    }

    public OptionItem getCurrentValue()
    {
        return getArrayValue(this.currentValuePosition);
    }

    public int getCurrentValuePosition()
    {
        return this.currentValuePosition;
    }

    public int getId()
    {
        return this.id;
    }

    public String getTitle()
    {
        return this.title;
    }

    public void setCurrentValuePosition(int paramInt)
    {
        this.currentValuePosition = paramInt;
    }

    public void setId(int paramInt)
    {
        this.id = paramInt;
    }

    public void setTitle(String paramString)
    {
        this.title = paramString;
    }

    public String toString()
    {
        return "SearchByActionItem [id=" + this.id + ", title=" + this.title + ", arrayValues=" + this.arrayValues + ", currentValuePosition=" + this.currentValuePosition + "]";
    }
}