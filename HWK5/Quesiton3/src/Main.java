public class Main {
    public static void main(String[] args) {
        AgeSearchTree<AgeData> ageTree = new AgeSearchTree<AgeData>();
        System.out.println("Adding 10(2times),20,5,15 and 10 to the tree");
        ageTree.add(new AgeData(10));
        ageTree.add(new AgeData(20));
        ageTree.add(new AgeData(5));
        ageTree.add(new AgeData(15));
        ageTree.add(new AgeData(10));
        System.out.println(ageTree);
        System.out.println("Number of people younger than 15 is:" + ageTree.youngerThan(15));
        System.out.println("Number of people older than 15 is:" + ageTree.olderThan(15));

        System.out.println("Removing 15 from the tree");
        ageTree.remove(new AgeData(15));
        System.out.println(ageTree);

        System.out.println("Removing 10 from the tree");
        ageTree.remove( new AgeData(10) );
        System.out.println(ageTree);

        System.out.println("Number of people younger than 15 is:" + ageTree.youngerThan(15));
        ageTree.remove(new AgeData(20));
        System.out.println("Number of people older than 15 is:" + ageTree.olderThan(15));

        System.out.println(ageTree);

        System.out.println("Find method for age 5");
        System.out.println(ageTree.find(new AgeData(5)));
        System.out.println("Find method for age 15(should be null)");
        System.out.println(ageTree.find(new AgeData(15)));

    }
}
