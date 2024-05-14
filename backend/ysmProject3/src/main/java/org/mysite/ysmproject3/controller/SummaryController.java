package org.mysite.ysmproject3.controller;

import lombok.RequiredArgsConstructor;
import org.mysite.ysmproject3.service.CaptionSummaryService;
import org.mysite.ysmproject3.service.CommentSummaryService;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SummaryController {
    private final CaptionSummaryService captionSummaryService;
    private final CommentSummaryService commentSummaryService;



}
