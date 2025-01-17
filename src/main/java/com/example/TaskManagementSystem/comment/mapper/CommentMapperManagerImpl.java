package com.example.TaskManagementSystem.comment.mapper;

import com.example.TaskManagementSystem.comment.dto.CommentCreateRequestDto;
import com.example.TaskManagementSystem.comment.dto.CommentResponseDto;
import com.example.TaskManagementSystem.comment.model.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;


@Component
@RequiredArgsConstructor
public class CommentMapperManagerImpl implements CommentMapperManager {

    private final CommentMapper commentMapper;
    private final CommentMapperList commentMapperList;

    @Override
    public List<CommentResponseDto> toDto(List<Comment> model) {
        return commentMapperList.toDto(model);
    }

    @Override
    public Comment toModel(CommentCreateRequestDto dto) {
        return commentMapper.toEntity(dto);
    }
}
