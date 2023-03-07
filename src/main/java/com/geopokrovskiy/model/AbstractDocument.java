package com.geopokrovskiy.model;

import java.util.Objects;

public abstract class AbstractDocument {
    protected long printDuration;
    protected String type;
    protected Size size;
    protected static class Size{
        private int length;
        private int height;

        public Size(int length, int height) {
            this.length = length;
            this.height = height;
        }

        public int area(){
            return this.height * this.length;
        }

        @Override
        public String toString() {
            return "Size{" +
                    "length=" + length + " mm " +
                    ", height=" + height + " mm " +
                    ", area=" + area() + "mm^2" +
                    '}';
        }
    }

    public long getPrintDuration() {
        return printDuration;
    }

    public void setPrintDuration(long printDuration) {
        this.printDuration = printDuration;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public int getArea(){
        return this.size.area();
    }

    @Override
    public String toString() {
        return "AbstractDocument{" +
                "printDuration=" + printDuration +
                ", type='" + type + '\'' +
                ", size=" + size +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractDocument that = (AbstractDocument) o;
        return printDuration == that.printDuration && Objects.equals(type, that.type) && Objects.equals(size, that.size);
    }

    @Override
    public int hashCode() {
        return Objects.hash(printDuration, type, size);
    }
}
