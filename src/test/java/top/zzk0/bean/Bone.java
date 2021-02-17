package top.zzk0.bean;

public class Bone implements IBone {

    private String type;
    private Dog owner;

    public Bone() {}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Dog getOwner() {
        return owner;
    }

    public void setOwner(Dog owner) {
        this.owner = owner;
    }
}
