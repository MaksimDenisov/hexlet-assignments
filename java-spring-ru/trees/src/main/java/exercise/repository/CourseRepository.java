package exercise.repository;

import exercise.model.Course;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {

    Course findById(long id);

    @Query( "select c from Course c where c.id in :ids" )
    List<Course> getPrevious(@Param("ids") List<Long> ids);
}
