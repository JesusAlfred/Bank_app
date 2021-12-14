package com.example.bank;

public class TransactionElement {
    private int id;
    private String date;
    private String type;
    private float mount;
    private String comment;

    public TransactionElement(){

    }

    public TransactionElement(int id, String date, String type, float mount, String comment) {
        this.id = id;
        this.date = date;
        this.type = type;
        this.mount = mount;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getMount() {
        return mount;
    }

    public void setMount(float mount) {
        this.mount = mount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
