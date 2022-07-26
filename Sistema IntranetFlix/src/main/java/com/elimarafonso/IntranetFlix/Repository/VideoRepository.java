package com.elimarafonso.IntranetFlix.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elimarafonso.IntranetFlix.Entity.Categoria;
import com.elimarafonso.IntranetFlix.Entity.Video;

public interface VideoRepository extends JpaRepository<Video,Long> {

	Video findByTitulo(String titulo);

	
	List<Video> findByCategoria(Categoria categoria);

	List<Video> findByTituloContaining(String titulo);


}
