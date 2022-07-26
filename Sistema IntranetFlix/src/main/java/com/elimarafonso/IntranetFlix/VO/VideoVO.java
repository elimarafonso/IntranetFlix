package com.elimarafonso.IntranetFlix.VO;

import java.util.List;
import java.util.stream.Collectors;

import com.elimarafonso.IntranetFlix.Entity.DescricaoVideo;
import com.elimarafonso.IntranetFlix.Entity.Video;
import com.elimarafonso.IntranetFlix.Repository.CategoriaRepository;
import com.elimarafonso.IntranetFlix.Repository.VideoRepository;

public class VideoVO {




	public Long id;
	public String titulo;
	public String descricao;
	public String url;
	private Long idCategoria;

	public VideoVO() {
	}

	public Long getIdcategoria() {
		return idCategoria;
	}

	public void setIdcategoria(Long idcategoria) {
		this.idCategoria = idcategoria;
	}

	public VideoVO(Video videos) {
		this.id = videos.getId();
		this.titulo = videos.getTitulo();
		this.descricao = videos.getDescricao();
		this.url = videos.getUrl();
		this.idCategoria = videos.getCategoria().getId();
	}


	public VideoVO(Video videos, long idCategoria) {
		this.id = videos.getId();
		this.titulo = videos.getTitulo();
		this.descricao = videos.getDescricao();
		this.url = videos.getUrl();
		this.idCategoria =idCategoria;
	}

	public Long getId() {
		return id;
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

	public VideoVO converter(Video videos, long idCategoria) {
		
		return new VideoVO(videos, idCategoria);
	}



	public static List<VideoVO> converteListaDeVideos(List<Video> videos) {
		return videos.stream().map(VideoVO::new).collect(Collectors.toList());
	}

	public Video atualizaVideo(Long id, VideoRepository videoRepository) {

		Video video = videoRepository.getById(id);

		video.setDescricao(this.descricao);
		video.setTitulo(this.titulo);
		video.setUrl(this.url);
		videoRepository.save(video);
		return video;

	}

	public boolean verificaTamanhoCampos(String titulo, String descricao, String url) {

		// rever esta logica

		if ((DescricaoVideo.TITULO.validaCampo(titulo))
				|| (DescricaoVideo.DESCRICAO.validaCampo(descricao))
				|| (DescricaoVideo.URL.validaCampo(url))) {

			// retorna true se estivar algo de errado nos campos
			return true;
		}

		return false;
	}

	public void adicionaCategoria(int idcategoria, CategoriaRepository categoriaRepository) {

		categoriaRepository.findById(Long.valueOf(idcategoria));

	}

	@Override
	public String toString() {
		return "VideoVO [id=" + id + ", titulo=" + titulo + ", descricao=" + descricao + ", url=" + url
				+ ", idCategoria=" + idCategoria + "]";
	}



}
