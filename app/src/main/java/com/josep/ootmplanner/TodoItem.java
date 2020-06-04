package com.josep.ootmplanner;

import android.widget.Button;
import android.widget.CheckBox;

import java.io.Serializable;

public class TodoItem implements Serializable {
    private String todoTitle;
    private String todoDescription;
    private boolean checkBox;

    public TodoItem(String title, String description){
        todoTitle = title;
        todoDescription = description;
        checkBox = false;
    }

    public void setTodoTitle(String title){
        todoTitle = title;
    }
    public void setTodoDescription(String description){
        todoDescription = description;
    }

    public void setCheckBox(Boolean set){
        checkBox = set;
    }

    public String getTodoTitle(){
        return todoTitle;
    }
    public String getTodoDescription(){
        return todoDescription;
    }

    public Boolean getCheckBox(){
        return checkBox;
    }
}
