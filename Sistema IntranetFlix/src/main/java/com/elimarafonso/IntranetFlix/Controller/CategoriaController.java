package com.elimarafonso.IntranetFlix.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elimarafonso.IntranetFlix.Service.CategoriaService;
import com.elimarafonso.IntranetFlix.VO.CategoriaVO;
import com.elimarafonso.IntranetFlix.VO.VideoVO;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
	
	public final CategoriaService categoriaService;
	
	@Autowired
	public CategoriaController(CategoriaService categoriaService) {
		this.categoriaService = categoriaService;
	}



	/* LISTA TODAS AS CATEGORIAS */
	@GetMapping("/")
	public List<CategoriaVO> listaTodasCategorias() {
		List<CategoriaVO> listaTodas = categoriaService.listaTodas();

		return listaTodas;
	}
	
	
	/* LISTA UMA CATEGORIA POR IDENTIFICAÇÃO */
	@GetMapping("/{id}/")
	public ResponseEntity<CategoriaVO> listaCategoria(@PathVariable Long id){
		
		ResponseEntity<CategoriaVO> categoria = categoriaService.findById(id);
		
		return categoria;
	}
	

	/* CRIA UMA CATEGORIA E SALVA NO BANCO */
	@PostMapping
	public ResponseEntity<CategoriaVO> cadastrarCategoria(@RequestBody CategoriaVO categoriaVO) {

		ResponseEntity<CategoriaVO> novaCategoria = categoriaService.cadastraCategoria(categoriaVO);

		return novaCategoria;

	}
	
	
	/*ATUALIZA UMA CATEGORIA*/
	@PatchMapping("/{id}")
	public ResponseEntity<CategoriaVO> atualizaCategoria(@PathVariable Long id, @RequestBody @Validated CategoriaVO categoriaVO){
		
		return categoriaService.alteraCategoria(id,categoriaVO);
	}	
	
	
	/*DELETA UMA CATEGORIA*/
	@DeleteMapping("/{id}/")
	public ResponseEntity<Object> deletaCategoria (@PathVariable Long id){
		return categoriaService.deletaCategoria(id);
	}
 	
	
	
	/*EXIBE VIDEOS POR CATEGORIAS*/
	@GetMapping("/{id}/videos/")
	public ResponseEntity<List<VideoVO>>  videosPorCategoria(@PathVariable Long id) {
		return	categoriaService.buscaVideos(id);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
