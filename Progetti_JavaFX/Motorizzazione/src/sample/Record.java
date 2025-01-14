package sample;

import java.util.Objects;

public class Record {
    protected String field1;
    protected String field2;
    protected int field3;

    public Record(){
    }

    public Record(String field1, String field2, int field3) {
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Record record = (Record) o;
        return field3 == record.field3 &&
                Objects.equals(field1, record.field1) &&
                Objects.equals(field2, record.field2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(field1, field2, field3);
    }
}
