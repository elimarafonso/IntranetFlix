package com.elimarafonso.IntranetFlix.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.elimarafonso.IntranetFlix.Entity.Categoria;
import com.elimarafonso.IntranetFlix.Entity.Video;
import com.elimarafonso.IntranetFlix.Repository.CategoriaRepository;
import com.elimarafonso.IntranetFlix.Repository.VideoRepository;
import com.elimarafonso.IntranetFlix.VO.CategoriaVO;
import com.elimarafonso.IntranetFlix.VO.VideoVO;

@Service
public class CategoriaService {

	public final CategoriaRepository categoriaRepository;
	public final VideoRepository videoRepository;

	@Autowired
	public CategoriaService(CategoriaRepository categoriaRepository, VideoRepository videoRepository) {
		this.categoriaRepository = categoriaRepository;
		this.videoRepository = videoRepository;
		
	}

	public List<CategoriaVO> listaTodas() {
		List<Categoria> categorias = categoriaRepository.findAll();

		return CategoriaVO.converteListaDeCategorias(categorias);

	}

	public ResponseEntity<CategoriaVO> findById(Long id) {
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		// se está vazia retorna NAO ENCONTRADO
		if (categoria.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		// SE NAO RETORNA A CATEGORIA
		return ResponseEntity.ok(new CategoriaVO(categoria.get()));

	}

	public ResponseEntity<CategoriaVO> cadastraCategoria(CategoriaVO categoriaVO) {

		if (categoriaVO.verificaCampos(categoriaVO.getTitulo(), categoriaVO.getCor())) {
			// se os campos sao invalidos retorna true
			return new ResponseEntity<CategoriaVO>(HttpStatus.FORBIDDEN);

			/*
			 * "Campos inválidos [Título: "+categoriaVO.getTitulo()+
			 * " Cor: "+categoriaVO.getCor()+"]"
			 */
		}
		// se nao estao invalidos ele salva no BD
		Categoria categoriaConvertida = Categoria.converte(categoriaVO);
		Categoria categoriaSalva = categoriaRepository.save(categoriaConvertida);

		return new ResponseEntity<>(new CategoriaVO(categoriaSalva), HttpStatus.CREATED);

	}

	public ResponseEntity<CategoriaVO> alteraCategoria(long id, CategoriaVO categoriaVO) {

		Optional<Categoria> existeCategoria = categoriaRepository.findById(id);

		if (existeCategoria.isPresent()) {
			Categoria novaCategoria = categoriaVO.alteraCategoria(id, categoriaRepository);
			return new ResponseEntity<>(new CategoriaVO(categoriaVO.converteToCategoriaVO(novaCategoria)), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<Object> deletaCategoria(Long id) {
		Optional<Categoria> existeCategoria = categoriaRepository.findById(id);

		if (existeCategoria.isPresent()) {

			categoriaRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

	}


	
	public ResponseEntity<List<VideoVO>> buscaVideos(Long idCategoria) {
		// busca os filmes da base de dados com o IdCategoria

		Optional<Categoria> categoria = categoriaRepository.findById(idCategoria);
		List<Video> videos = videoRepository.findByCategoria(categoria.get());

		if (videos.isEmpty()) {
			// Caso a lista estiver vazia retorna notFund
			return  ResponseEntity.notFound().build();
		}
		List<VideoVO> videosVO = VideoVO.converteListaDeVideos(videos);
		return  new ResponseEntity<List<VideoVO>>(videosVO, HttpStatus.OK);
	}
	

}
