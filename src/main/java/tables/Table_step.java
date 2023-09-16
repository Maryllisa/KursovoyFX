package tables;

public class Table_step {
    private String id;
    private String fio;
    private String group;
    private int col_pas;
    private float rating;
    private float amount;

    public Table_step(String id, String fio, String group, int col_pas, float rating, float amount) {
        this.id = id;
        this.fio = fio;
        this.group = group;
        this.col_pas = col_pas;
        this.rating = rating;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public String getFio() {
        return fio;
    }

    public String getGroup() {
        return group;
    }

    public int getCol_pas() {
        return col_pas;
    }

    public float getRating() {
        return rating;
    }

    public float getAmount() {
        return amount;
    }
}
