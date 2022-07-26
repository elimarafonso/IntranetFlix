package com.elimarafonso.IntranetFlix.Entity;

import java.io.Serializable;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.elimarafonso.IntranetFlix.Repository.CategoriaRepository;
import com.elimarafonso.IntranetFlix.VO.VideoVO;

@Entity
@Table(name = "videos")
public class Video implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;

	@Column(name = "titulo", nullable = false, length = 30)
	public String titulo;

	@Column(name = "descricao", nullable = false, length = 250)
	public String descricao;

	@Column(name = "url", nullable = false, length = 250)
	public String url;

	@ManyToOne
	private Categoria categoria;

	public Video() {
	}

	public Video(String titulo, String descricao, String url, Categoria Categoria) {
		this.titulo = titulo;
		this.descricao = descricao;
		this.url = url;
		this.categoria = Categoria;
	}

	public Video(VideoVO videoVO, Categoria categoria) {
		this.titulo = videoVO.getTitulo();
		this.descricao = videoVO.getDescricao();
		this.url = videoVO.url;
		this.categoria = categoria;
	}

	public Long getId() {
		return id;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getUrl() {
		return url;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public static Video convertToVideo(VideoVO videoVO, CategoriaRepository categoriaRepository) {

		
		if (videoVO.getIdcategoria() == null || videoVO.getIdcategoria() == 0 ){
			Long n = Long.valueOf(1);
			videoVO.setIdcategoria(n);
		}

		Optional<Categoria> categoria = categoriaRepository.findById(videoVO.getIdcategoria());

		return new Video(videoVO, categoria.get());
	}

	@Override
	public String toString() {
		return "Video [id=" + id + ", titulo=" + titulo + ", descricao=" + descricao + ", url=" + url + ", categoria="
				+ categoria + "]";
	}

}
