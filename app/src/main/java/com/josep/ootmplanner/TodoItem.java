package com.josep.ootmplanner;

public class TodoItem {
    private String todoTitle;
    private String todoDescription;

    public TodoItem(String title, String description){
        todoTitle = title;
        todoDescription = description;
    }

    public void setTodoTitle(String title){
        todoTitle = title;
    }
    public void setTodoDescription(String description){
        todoDescription = description;
    }

    public String getTodoTitle(){
        return todoTitle;
    }
    public String getTodoDescription(){
        return todoDescription;
    }
}
