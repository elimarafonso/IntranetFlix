package com.elimarafonso.IntranetFlix.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elimarafonso.IntranetFlix.Entity.Video;
import com.elimarafonso.IntranetFlix.Service.VideoService;
import com.elimarafonso.IntranetFlix.VO.VideoVO;

@RestController
@RequestMapping("/videos")
public class VideoController {

	public final VideoService videoService;

	/* CONSTRUTOR */
	@Autowired
	public VideoController(VideoService videoService, PagedResourcesAssembler<VideoVO> assembler) {
		this.videoService = videoService;
	}

	/* BUSCA TODOS OS VIDEOS DO BANCO */
	@GetMapping
	public Page<VideoVO> listVideos(Pageable pageable) {
		Page<VideoVO> videos = videoService.findAll(pageable);
		return videos;
	}
	
	/* BUSCA TODOS OS VIDEOS DO BANCO */
	@GetMapping("/")
	public ResponseEntity<Page<VideoVO>> findVideos(@RequestParam(required = false) String titulo , Pageable pageable) {
		Page<VideoVO> videos = videoService.findByTituloLike(titulo,pageable);
		return new ResponseEntity<Page<VideoVO>>(videos, HttpStatus.OK);
	}
	

	/* BUSCA FILME POR IDENTIFICAÇÃO */
	@GetMapping("/{id}")
	public ResponseEntity<VideoVO> listVideo(@PathVariable Long id) {
		Optional<Video> video = videoService.findById(id);
		// moverpara CAMADA SERVICE ********
		if (video.isPresent()) {
			return ResponseEntity.ok(new VideoVO(video.get()));
		}
		return ResponseEntity.notFound().build();
	}

	/* CADASTRA FILME NO BANCO DE DADOS */
	@PostMapping
	public ResponseEntity<VideoVO> createVideos(@RequestBody VideoVO videoVO) {
		return videoService.salvaFilme(videoVO);
	}

	/* ALTERA INFORMAÇOES DO FILME */
	@PatchMapping("/{id}")
	private ResponseEntity<VideoVO> updateVideo(@PathVariable Long id,	@RequestBody @Validated VideoVO videoAtualizado) {
		ResponseEntity<VideoVO> dadosAtualizados = videoService.atualizaVideo(id, videoAtualizado);
		return dadosAtualizados;
	}

	/* DELTA UM FILME DO BANCO DE DADOS */
	@DeleteMapping("/{id}")
	public ResponseEntity<VideoVO> deleteVideo(@PathVariable Long id) {
		ResponseEntity<VideoVO> retornoService = videoService.deletaVideoService(id);
		return retornoService;
	}

}
