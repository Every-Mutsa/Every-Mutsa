package com.example.everymutsa.web.comment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.everymutsa.common.exception.EntityNotFoundException;
import com.example.everymutsa.common.exception.ErrorCode;
import com.example.everymutsa.web.comment.domain.dto.CommentResponse;
import com.example.everymutsa.web.comment.domain.dto.CommentUpdateRequest;
import com.example.everymutsa.web.comment.domain.entity.Comment;
import com.example.everymutsa.web.comment.repository.CommentRepository;
import com.example.everymutsa.web.member.domain.Member;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {

	private final CommentRepository commentRepository;

	public CommentResponse save(String content) {
		Comment save = commentRepository.save(new Comment(content));
		return new CommentResponse(save);
	}

	public CommentResponse findById(Long id) {
		Comment findComment = commentRepository.findById(id).
			orElseThrow(()-> new EntityNotFoundException(ErrorCode.COMMENT_NOT_FOUND));

		return new CommentResponse(findComment);
	}

	public CommentResponse update(Long id, CommentUpdateRequest commentUpdateRequest) {
		Comment comment = commentRepository.findByIdOrThrow(id);
		comment.update(commentUpdateRequest.getContent());

		return new CommentResponse(comment);
	}

	public void delete(Long id) {
		commentRepository.deleteById(id);
	}
}
