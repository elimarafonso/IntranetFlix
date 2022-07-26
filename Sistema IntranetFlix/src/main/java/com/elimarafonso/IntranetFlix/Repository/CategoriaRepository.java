package com.elimarafonso.IntranetFlix.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.elimarafonso.IntranetFlix.Entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

	Categoria findByTitulo(String titulo);

}
