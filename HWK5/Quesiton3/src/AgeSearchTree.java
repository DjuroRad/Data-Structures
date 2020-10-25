public class AgeSearchTree<T>
        extends BinarySearchTree<AgeData> {
    /**
     * adds an element if it does not already exits
     * if it exists than the counter will be incrementet by one
     * @param item to be inserted
     * @return
     */
    @Override
    public boolean add(AgeData item) {//basically always returns true
        AgeData element = find(item);
        if( element == null )
            return super.add(item);
        else
            element.increaseCount();

        return true;
    }

    /**
     * removes an element from the tree
     * if there is more than just one person with a targeted age the counter will be decremented by one
     * @param target value to be deleted
     * @return return tree on deletion and false otherwise
     */
    @Override
    public boolean remove(AgeData target) {
        AgeData item = find(target);
        if( item == null )
            return false;
        else{
            if( item.getCount() == 1 )
                super.remove(target);
            else
                item.decreaseCount();
        }
        return true;
    }

    /**
     * number of people younger than some age
     * @param age age for comparison
     * @return number of people younger than the comparison age
     */
    public int youngerThan(int age){
        return youngerThan(root, age);
    }

    /**
     * number of people younger than age( private method called in the public one
     * @param localTree current tree node
     * @param age age to be compared with
     * @return number of people younger than that age
     */
    private int youngerThan( Node<AgeData> localTree, int age ){
        if( localTree == null )
            return 0;
        else if( localTree.data.getAge() < age ){
            return localTree.data.getCount() + youngerThan(localTree.left, age) + youngerThan(localTree.right, age);
        }
        else
            return youngerThan(localTree.left,age);
    }

    /**
     * counts the number of people that are older than a certain age
     * @param age age for comparison
     * @return number of people older than that age
     */
    public int olderThan(int age){
        return olderThan(root, age);
    }

    /**
     * counts the number of people younger than ceratin age
     * @param localTree current node
     * @param age age to be compared with
     * @return number of people older than the age
     */
    private int olderThan( Node<AgeData> localTree, int age ){
        if( localTree == null )
            return 0;
        else if( localTree.data.getAge() > age ){
            return localTree.data.getCount() + olderThan(localTree.left, age) + olderThan(localTree.right, age);
        }
        else
            return olderThan(localTree.right,age);
    }

    /**
     * Returns a reference to the data in the node that is equal to target. If
     * no such node is found, returns nul 1.
     * The target object must implement
     * the Comparabl e interface.
     * @param target searched element that is Comparable
     * @return reference to el if found, null otherwise
     */
    @Override
    public AgeData find(AgeData target) {
        return super.find(target);//just works the same as in the biinary tree( it is more about overloading toCompare in AgeData than doing anything inside this method
    }
}