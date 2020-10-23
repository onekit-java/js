package cn.onekit.js.core;

public abstract class Iterator<I> implements java.util.Iterator<Iterator.Item<I>>,Iterable<Iterator.Item<I>> {


    public static class Item<I>  {

        private I _value;
        private boolean _done;

        public Item(I value, boolean done) {
            _value = value;
            _done = done;
        }

        @Override
        public String toString() {
            return String.format("Object { value: %s, done: %b }", getValue(), getDone());
        }

        public I getValue() {
            return _value;
        }

        public boolean getDone() {
            return _done;
        }

    }
    ///////////////////////////////

    public Iterator(java.util.Iterator iterator) {
        _iterator = iterator;
    }
    public abstract I getValue(Object value);
    private java.util.Iterator _iterator;

    ///////////////////////////////


    @Override
    public boolean hasNext() {
        return _iterator.hasNext();
    }

    @Override
    public Item<I> next() {
        boolean done = !_iterator.hasNext();
        Object value = done?null:_iterator.next();
        return new Item(getValue(value), done);
    }

    @Override
    public java.util.Iterator<Item<I>> iterator() {
        return this;
    }




}
