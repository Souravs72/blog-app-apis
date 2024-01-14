package com.sourav.blog.controllers;

import java.text.SimpleDateFormat; 
import java.util.Date;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sourav.blog.payloads.ApiResponse;
import com.sourav.blog.payloads.CommentDTO;
import com.sourav.blog.services.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {

	private final CommentService commentService;

	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@PostMapping("/post/{postId}/user/{userId}/comments")
	public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO, @PathVariable Integer postId,
			@PathVariable Integer userId) {

		CommentDTO createComment = this.commentService.createComment(commentDTO, userId, postId);
		return new ResponseEntity<CommentDTO>(createComment, HttpStatus.CREATED);
	}

	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId) {

		this.commentService.deleteComment(commentId);
		String pattern = "MM-dd-yyyy HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());

		return new ResponseEntity<ApiResponse>(new ApiResponse("Comment is deleted successfully", true, date),
				HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	@PutMapping("/{commentId}")
	public ResponseEntity<CommentDTO> updateComment(@Valid @RequestBody CommentDTO commentDTO,
			@PathVariable Integer commentId) {
		CommentDTO updatedDto = this.commentService.updateComment(commentDTO, commentId);

		return new ResponseEntity<CommentDTO>(updatedDto, HttpStatus.OK);
	}

	// get all
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	@GetMapping("/")
	public ResponseEntity<List<CommentDTO>> getAllComments() {
		List<CommentDTO> commentDTOs = this.commentService.getAllComments();

		return ResponseEntity.ok(commentDTOs);
	}

}
