package com.elimarafonso.IntranetFlix.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.elimarafonso.IntranetFlix.Entity.Categoria;
import com.elimarafonso.IntranetFlix.Entity.Video;

public interface VideoRepository extends JpaRepository<Video, Long> {

	Video findByTitulo(String titulo);

	Page<Video> findByCategoria(Categoria categoria, Pageable pageable);

	Page<Video> findByTituloContaining(String titulo,Pageable pageable);

}
