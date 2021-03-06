package ru.dfsystems.spring.tutorial.dao;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import ru.dfsystems.spring.tutorial.generated.Sequences;
import ru.dfsystems.spring.tutorial.generated.tables.daos.StudentDao;
import ru.dfsystems.spring.tutorial.generated.tables.pojos.Student;

import java.time.LocalDateTime;
import java.util.List;

import static ru.dfsystems.spring.tutorial.generated.tables.Student.STUDENT;
import static ru.dfsystems.spring.tutorial.generated.tables.StudentsToCourse.STUDENTS_TO_COURSE;

@Repository
public class StudentDaoImpl extends StudentDao implements BaseDao<Student> {
    private final DSLContext jooq;

    public StudentDaoImpl(DSLContext jooq) {
        super(jooq.configuration());
        this.jooq = jooq;
    }

    @Override
    public Student create(Student student) {
        student.setId(jooq.nextval(Sequences.STUDENT_ID_SEQ));
        if (student.getIdd() == null) {
            student.setIdd(student.getId());
        }
        student.setCreatedAt(LocalDateTime.now());
        super.insert(student);
        return student;
    }

    @Override
    public Student getActiveByIdd(Integer idd) {
        return jooq.select(STUDENT.fields())
                .from(STUDENT)
                .where(STUDENT.IDD.eq(idd).and(STUDENT.DELETED_AT.isNull()))
                .fetchOneInto(Student.class);
    }

    public List<Student> getHistory(Integer idd) {
        return jooq.selectFrom(STUDENT)
                .where(STUDENT.IDD.eq(idd))
                .fetchInto(Student.class);
    }

    public List<Student> getStudentsByCourseIdd(Integer idd) {
        return jooq.select(STUDENT.fields())
                .from(STUDENT)
                .join(STUDENTS_TO_COURSE)
                    .on(STUDENT.IDD.
                            eq(STUDENTS_TO_COURSE.STUDENT_IDD))
                .where(STUDENTS_TO_COURSE.COURSE_IDD.eq(idd))
                .fetchInto(Student.class);
    }
}
