package br.com.ada.adalocate.repository;


import br.com.ada.adalocate.model.Aluguel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.validation.ObjectError;

@Repository
public interface AluguelRepository extends JpaRepository<Aluguel, Long> {

}
