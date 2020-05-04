package sw2.clase04ej2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sw2.clase04ej2.dto.EmpleadosRegionDto;
import sw2.clase04ej2.entity.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
 @Query(value="select r.regionDescription as regiondescription, count(e.employeeid) as cantidadempleados\n" +
         "FROM region r\n" +
         "inner join territories t on (r.regionid = t.regionid)\n" +
         "inner join employeeterritories et on (t.territoryid = et.territoryid)\n" +
         "inner join employees e on (e.employeeid = et.employeeid)\n" +
         "group by r.regionid\n",nativeQuery = true)
 List<EmpleadosRegionDto> obtenerEmpleadosPorRegion();
 ///////////////////////
///SI QUISIERA UN DTO CON PARAMETROS, cambiaría el query con ?1 y el sería el mismo método pero con parámetros
 @Query(value="select r.regionDescription as regiondescription, count(e.employeeid) as cantidadempleados\n" +
         "FROM region r\n" +
         "inner join territories t on (r.regionid = t.regionid)\n" +
         "inner join employeeterritories et on (t.territoryid = et.territoryid)\n" +
         "inner join employees e on (e.employeeid = et.employeeid)\n where id=?1" +
         "group by r.regionid\n",nativeQuery = true)
 List<EmpleadosRegionDto> obtenerEmpleadosPorRegion(int idPais);
}

