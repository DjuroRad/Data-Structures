public interface SkipListInterface<T extends Comparable<? super T> > {
        public boolean remove(T target);
        public boolean insert(T data);
        public T find(T data);
}

