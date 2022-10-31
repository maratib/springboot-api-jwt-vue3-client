package com.jp.api.editor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jp.ApiApplication;

@RestController
@RequestMapping("api/editor")
public class EditorApi {

    @GetMapping
    public String home() {
        return "Editor API Version: " + ApiApplication.appVersion;

    }

}
