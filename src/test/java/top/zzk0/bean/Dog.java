package top.zzk0.bean;

public class Dog {

    private String name;
    private Bone bone;

    public Dog() {}

    public void say() {
        System.out.println("im " + name + ", wang~ i have a "
                + bone.getType() + " bone");
    }
}
