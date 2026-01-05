package Model;

public class Manager {
    private int mang_id;
    private String name;
    private String password;

    public Manager(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public int getMang_id() {
        return mang_id;
    }

    public void setMang_id(int mang_id) {
        this.mang_id = mang_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
