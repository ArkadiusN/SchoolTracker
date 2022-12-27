package com.example.handlingformsubmission;

import java.util.List;

public class Query {
    private Object[][] data;
    private String[] columnNames;

    public Object[][] getData() {return data;}
    public String[] getColumnNames() {return columnNames;
    }
    public void setData(Object[][] data) {this.data = data;}
    public void setColumnNames(String[] columnNames) {this.columnNames = columnNames;}
}
