package com.elimarafonso.IntranetFlix.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.elimarafonso.IntranetFlix.Entity.Video;
import com.elimarafonso.IntranetFlix.Repository.CategoriaRepository;
import com.elimarafonso.IntranetFlix.Repository.VideoRepository;
import com.elimarafonso.IntranetFlix.VO.VideoVO;

@Service
public class VideoService {

	private final VideoRepository videoRepository;
	private final CategoriaRepository categoriaRepository;

	/* CONSTRUTOR */
	@Autowired
	public VideoService(VideoRepository videoRepository, CategoriaRepository categoriaRepository) {
		this.videoRepository = videoRepository;
		this.categoriaRepository = categoriaRepository;
	}

	/* ENCONTRA TODOS */
	public List<VideoVO> findAll() {
		List<Video> videos = videoRepository.findAll();
		return VideoVO.converteListaDeVideos(videos);
	}

	/* ENCONTRA POR ID */
	public Optional<Video> findById(Long id) {
		return videoRepository.findById(id);
	}

	/* SALVA NOVO FILME */
	public ResponseEntity<VideoVO> salvaFilme(VideoVO videoVO) {
		// verifica se a string é maior ou esta vazia
		if (videoVO.verificaTamanhoCampos(videoVO.getTitulo(), videoVO.getDescricao(), videoVO.getUrl())) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		} // fim validaçoes

		Video video = Video.convertToVideo(videoVO, categoriaRepository);
		Video videoSalvo = videoRepository.save(video);
		VideoVO videoConverter = videoVO.converter(videoSalvo, videoSalvo.getCategoria().getId());
		return new ResponseEntity<>(videoConverter, HttpStatus.CREATED);
	}

	/* ATUALIZA DADOS DOS FILMES */
	public ResponseEntity<VideoVO> atualizaVideo(Long id, VideoVO dadosDoVideo) {
		Optional<Video> video = videoRepository.findById(id);
		if (video.isPresent()) {
			Video videoSalvo = dadosDoVideo.atualizaVideo(id, videoRepository, categoriaRepository);
			return ResponseEntity.ok(new VideoVO(videoSalvo));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	/* DELETA UM FILME DA BASE DE DADOS */
	public ResponseEntity<VideoVO> deletaVideoService(Long id) {
		Optional<Video> existeEsteVideo = videoRepository.findById(id);
		if (existeEsteVideo.isPresent()) {
			videoRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	public List<VideoVO> findByTituloLike(String titulo) {
		if (titulo == null) {
			List<Video> videos = videoRepository.findAll();
			return VideoVO.converteListaDeVideos(videos);
		}
		List<Video> videos = videoRepository.findByTituloContaining(titulo);
		return VideoVO.converteListaDeVideos(videos);
	}

}
