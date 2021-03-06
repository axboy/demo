/**
 * This class is generated by jOOQ
 */
package cn.axboy.demo.mixdb.jooq.tables.records;


import cn.axboy.demo.mixdb.jooq.tables.DemoCategory;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record7;
import org.jooq.Row7;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.8.2"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DemoCategoryRecord extends UpdatableRecordImpl<DemoCategoryRecord> implements Record7<Long, String, Long, Integer, String, Long, String> {

    private static final long serialVersionUID = 1941269585;

    /**
     * Setter for <code>demo_category.id</code>.
     */
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>demo_category.id</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>demo_category.created_by</code>.
     */
    public void setCreatedBy(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>demo_category.created_by</code>.
     */
    public String getCreatedBy() {
        return (String) get(1);
    }

    /**
     * Setter for <code>demo_category.created_time</code>.
     */
    public void setCreatedTime(Long value) {
        set(2, value);
    }

    /**
     * Getter for <code>demo_category.created_time</code>.
     */
    public Long getCreatedTime() {
        return (Long) get(2);
    }

    /**
     * Setter for <code>demo_category.flag</code>.
     */
    public void setFlag(Integer value) {
        set(3, value);
    }

    /**
     * Getter for <code>demo_category.flag</code>.
     */
    public Integer getFlag() {
        return (Integer) get(3);
    }

    /**
     * Setter for <code>demo_category.last_modified_by</code>.
     */
    public void setLastModifiedBy(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>demo_category.last_modified_by</code>.
     */
    public String getLastModifiedBy() {
        return (String) get(4);
    }

    /**
     * Setter for <code>demo_category.last_modified_time</code>.
     */
    public void setLastModifiedTime(Long value) {
        set(5, value);
    }

    /**
     * Getter for <code>demo_category.last_modified_time</code>.
     */
    public Long getLastModifiedTime() {
        return (Long) get(5);
    }

    /**
     * Setter for <code>demo_category.title</code>.
     */
    public void setTitle(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>demo_category.title</code>.
     */
    public String getTitle() {
        return (String) get(6);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record7 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row7<Long, String, Long, Integer, String, Long, String> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row7<Long, String, Long, Integer, String, Long, String> valuesRow() {
        return (Row7) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field1() {
        return DemoCategory.DEMO_CATEGORY.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return DemoCategory.DEMO_CATEGORY.CREATED_BY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field3() {
        return DemoCategory.DEMO_CATEGORY.CREATED_TIME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field4() {
        return DemoCategory.DEMO_CATEGORY.FLAG;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field5() {
        return DemoCategory.DEMO_CATEGORY.LAST_MODIFIED_BY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field6() {
        return DemoCategory.DEMO_CATEGORY.LAST_MODIFIED_TIME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field7() {
        return DemoCategory.DEMO_CATEGORY.TITLE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getCreatedBy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value3() {
        return getCreatedTime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value4() {
        return getFlag();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value5() {
        return getLastModifiedBy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value6() {
        return getLastModifiedTime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value7() {
        return getTitle();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DemoCategoryRecord value1(Long value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DemoCategoryRecord value2(String value) {
        setCreatedBy(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DemoCategoryRecord value3(Long value) {
        setCreatedTime(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DemoCategoryRecord value4(Integer value) {
        setFlag(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DemoCategoryRecord value5(String value) {
        setLastModifiedBy(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DemoCategoryRecord value6(Long value) {
        setLastModifiedTime(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DemoCategoryRecord value7(String value) {
        setTitle(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DemoCategoryRecord values(Long value1, String value2, Long value3, Integer value4, String value5, Long value6, String value7) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached DemoCategoryRecord
     */
    public DemoCategoryRecord() {
        super(DemoCategory.DEMO_CATEGORY);
    }

    /**
     * Create a detached, initialised DemoCategoryRecord
     */
    public DemoCategoryRecord(Long id, String createdBy, Long createdTime, Integer flag, String lastModifiedBy, Long lastModifiedTime, String title) {
        super(DemoCategory.DEMO_CATEGORY);

        set(0, id);
        set(1, createdBy);
        set(2, createdTime);
        set(3, flag);
        set(4, lastModifiedBy);
        set(5, lastModifiedTime);
        set(6, title);
    }
}
