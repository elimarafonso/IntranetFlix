package com.elimarafonso.IntranetFlix.Entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.elimarafonso.IntranetFlix.VO.CategoriaVO;

@Entity
@Table(name = "categorias")
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "tituto", nullable = false, length = 30)
	private String titulo;

	@Column(name = "cor", nullable = false, length = 7)
	private String cor;

	public Categoria() {
	}

	public Categoria(String titulo, String cor) {
		this.titulo = titulo;
		this.cor = cor;
	}

	public Categoria(CategoriaVO categoriaVO) {
		this.id = categoriaVO.getId();
		this.titulo = categoriaVO.getTitulo();
		this.cor = categoriaVO.getCor();
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

	public static Categoria converte(CategoriaVO categoriaVO) {
		return new Categoria(categoriaVO);
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	@Override
	public String toString() {
		return "Categoria [id=" + id + ", titulo=" + titulo + ", cor=" + cor + "]";
	}

}
