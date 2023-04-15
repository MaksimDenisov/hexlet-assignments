package exercise.repository;

import exercise.model.City;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {

    // BEGIN
    List<City> findAllByNameStartsWithIgnoreCase(String str);

    @Query("SELECT c FROM City c ORDER BY c.name")
    List<City> getALL();

    @Query("SELECT c FROM City c WHERE c.id =:id")
    City getById(@Param("id") Long id);
    // END
}
