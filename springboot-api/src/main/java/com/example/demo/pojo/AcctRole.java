package com.example.demo.pojo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class AcctRole implements Serializable {


    private Set<AcctAuthority> permissions = new HashSet<>();

    public Set<AcctAuthority> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<AcctAuthority> permissions) {
        this.permissions = permissions;
    }

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column acct_role.id
     *
     * @mbg.generated Sun Sep 29 10:58:18 CST 2019
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column acct_role.name
     *
     * @mbg.generated Sun Sep 29 10:58:18 CST 2019
     */
    private String name;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column acct_role.id
     *
     * @return the value of acct_role.id
     *
     * @mbg.generated Sun Sep 29 10:58:18 CST 2019
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column acct_role.id
     *
     * @param id the value for acct_role.id
     *
     * @mbg.generated Sun Sep 29 10:58:18 CST 2019
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column acct_role.name
     *
     * @return the value of acct_role.name
     *
     * @mbg.generated Sun Sep 29 10:58:18 CST 2019
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column acct_role.name
     *
     * @param name the value for acct_role.name
     *
     * @mbg.generated Sun Sep 29 10:58:18 CST 2019
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table acct_role
     *
     * @mbg.generated Sun Sep 29 10:58:18 CST 2019
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append("]");
        return sb.toString();
    }
}