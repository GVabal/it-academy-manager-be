package lt.akademija.itacademymanager.repository;

import lt.akademija.itacademymanager.model.Stream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface StreamRepository extends JpaRepository<Stream, Integer> {

    @Query(value = "SELECT * FROM public.stream WHERE name= :name", nativeQuery = true)
    Stream getStreamByName(@Param("name") String name);

}
