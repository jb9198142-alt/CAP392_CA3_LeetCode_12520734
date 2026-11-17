import java.util.*;

class PeekingIterator implements Iterator<Integer> {
    private Iterator<Integer> iterator;
    private Integer savedValue;
    private boolean hasSavedValue;

    public PeekingIterator(Iterator<Integer> iterator) {
        this.iterator = iterator;
        hasSavedValue = false;
    }

    public Integer peek() {
        if (!hasSavedValue) {
            savedValue = iterator.next();
            hasSavedValue = true;
        }

        return savedValue;
    }

    @Override
    public Integer next() {
        if (!hasSavedValue) {
            return iterator.next();
        }

        Integer answer = savedValue;
        savedValue = null;
        hasSavedValue = false;
        return answer;
    }

    @Override
    public boolean hasNext() {
        return hasSavedValue || iterator.hasNext();
    }
}
