package tables;

public class Table_Request {
    private String id_request;
    private float scholarship;
    private float f_scholarship;
    private String status;

    public Table_Request(String id_request, float scholarship, float f_scholarship, String status) {
        this.id_request = id_request;
        this.scholarship = scholarship;
        this.f_scholarship = f_scholarship;
        this.status = status;
    }

    public String getId_request() {
        return id_request;
    }

    public float getScholarship() {
        return scholarship;
    }

    public float getF_scholarship() {
        return f_scholarship;
    }

    public String getStatus() {
        return status;
    }
}
