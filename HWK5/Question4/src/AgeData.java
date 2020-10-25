public class AgeData implements Comparable<AgeData> {
    /**
     * age of someone
     */
    private Integer age = 0;
    /**
     * number of people of this age
     */
    private Integer count = 0;

    /**
     * One paramtere constrcutor that makes a node
     * @param age age to be assigned to and the counter is set to one
     */
    public AgeData(Integer age) {
        this.age = age;
        count = 1;
    }

    /**
     * returns the age
     * @return age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * returns the count
     * @return number of people of that age
     */
    public Integer getCount() {
        return count;
    }

    /**
     * increases the number of people with that age
     */
    public void increaseCount(){
        ++count;
    }

    /**
     * decreses the number of people with that age
     */
    public void decreaseCount(){
        if( count != 1 )
            --count;
    }
    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure
     * {@code sgn(x.compareTo(y)) == -sgn(y.compareTo(x))}
     * for all {@code x} and {@code y}.  (This
     * implies that {@code x.compareTo(y)} must throw an exception iff
     * {@code y.compareTo(x)} throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
     * {@code x.compareTo(z) > 0}.
     *
     * <p>Finally, the implementor must ensure that {@code x.compareTo(y)==0}
     * implies that {@code sgn(x.compareTo(z)) == sgn(y.compareTo(z))}, for
     * all {@code z}.
     *
     * <p>It is strongly recommended, but <i>not</i> strictly required that
     * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
     * class that implements the {@code Comparable} interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     *
     * <p>In the foregoing description, the notation
     * {@code sgn(}<i>expression</i>{@code )} designates the mathematical
     * <i>signum</i> function, which is defined to return one of {@code -1},
     * {@code 0}, or {@code 1} according to whether the value of
     * <i>expression</i> is negative, zero, or positive, respectively.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(AgeData o) {
        return age.compareTo(o.age);
    }

    /**
     * Overriden because of find method
     * @param obj obejct
     * @return true if ages are the same, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        AgeData age_obj = (AgeData) obj;
        if( age_obj != null )
            return this.age == age_obj.getAge();
        return false;
    }

    /**
     * format of string is AGE - COUNT
     * @return the string with AgeData info
     */
    @Override
    public String toString() {
        return age + " - " + count;
    }
}
