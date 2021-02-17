package top.zzk0.bean;

public class Dog implements Animal {

    private String name;

    public Dog() {}

    public void say() {
        System.out.println("im " + name + ", wang~ i have a " + " bone");
    }

    // ---------------------------- 由 IDEA 生成 ----------------------------

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
