package lt.akademija.itacademymanager.repository;

import lt.akademija.itacademymanager.model.Stream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface StreamRepository extends JpaRepository<Stream, Integer> {

    @Query(value = "SELECT * FROM public.stream WHERE name= :name", nativeQuery = true)
    Stream getStreamByName(@Param("name") String name);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM public.stream WHERE name= :name", nativeQuery = true)
    void deleteByName(@Param("name") String name);
}
