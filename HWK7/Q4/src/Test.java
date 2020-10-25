import software.system.gtu.*;

public class Test {
    public static void main(String[] args) {
        SoftwareSystemMenuDriven ssmd = new SoftwareSystemMenuDriven("System", "djuro", "pw", new BinarySearchTree(), new BinarySearchTree(), new BinarySearchTree());
        ssmd.startSystem();
    }
}
