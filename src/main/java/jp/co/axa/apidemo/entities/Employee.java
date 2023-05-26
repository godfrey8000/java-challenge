package jp.co.axa.apidemo.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="EMPLOYEE", indexes = {
        @Index(name="IDX_EMPLOYEE_ID", columnList="EMPLOYEE_NAME", unique = true)
})
public class Employee {

    @Getter
    @Setter
    @Id
    @Column(name="EMPLOYEE_ID")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(name="EMPLOYEE_NAME")
    private String name;

    @Getter
    @Setter
    @Column(name="EMPLOYEE_SALARY")
    private Integer salary;

    @Getter
    @Setter
    @Column(name="DEPARTMENT")
    private String department;

}
