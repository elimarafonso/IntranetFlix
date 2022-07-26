package com.elimarafonso.IntranetFlix.VO;

import java.util.List;
import java.util.stream.Collectors;

import com.elimarafonso.IntranetFlix.Entity.Categoria;
import com.elimarafonso.IntranetFlix.Entity.DescricaoCategoria;
import com.elimarafonso.IntranetFlix.Repository.CategoriaRepository;

public class CategoriaVO {

	private long id;
	private String titulo;
	private String cor;

	public CategoriaVO() {
	}

	public CategoriaVO(CategoriaVO categoriaVO) {
		this.id = categoriaVO.getId();
		this.titulo = categoriaVO.getTitulo();
		this.cor = categoriaVO.getCor();
	}

	public CategoriaVO(Long id,Categoria categoria) {
		this.id = id;
		this.titulo = categoria.getTitulo();
		this.cor = categoria.getCor();
	}

	
	public CategoriaVO(Categoria categoria) {
		this.id = categoria.getId();
		this.titulo = categoria.getTitulo();
		this.cor = categoria.getCor();
	}

	public long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getCor() {
		return cor;
	}

	public static List<CategoriaVO> converteListaDeCategorias(List<Categoria> categoria) {
		return categoria.stream().map(CategoriaVO::new).collect(Collectors.toList());
	}



	public boolean verificaCampos(String titulo, String cor) {

		if (DescricaoCategoria.TITULO.tamanhoDoCampo(titulo) || DescricaoCategoria.COR.tamanhoDoCampo(cor)) {

			return true;
			// retorna true algo esta errado
		}
		return false;
		// NAO tem nada errado, PODE SALVAR
	}


	public Categoria alteraCategoria(long id, CategoriaRepository categoriaRepository) {

		Categoria categoria = categoriaRepository.getById(id);

		categoria.setTitulo(this.titulo);
		categoria.setCor(this.cor);
	

		categoriaRepository.save(categoria);
		return categoria;
	}


	public CategoriaVO converteToCategoriaVO(Categoria categoria) {
		return new CategoriaVO(categoria);
	}

	@Override
	public String toString() {
		return "CategoriaVO [id=" + id + ", titulo=" + titulo + ", cor=" + cor + "]";
	}


	
}
