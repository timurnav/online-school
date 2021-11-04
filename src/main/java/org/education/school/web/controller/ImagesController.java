package org.education.school.web.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("uploaded/images")
public class ImagesController {

    private final ServletContext servletContext;

    public ImagesController(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @RequestMapping(value = "{imageName}", method = RequestMethod.GET)
    public void getImageAsByteArray(@PathVariable String imageName,
                                    HttpServletResponse response) throws IOException {
        InputStream in = servletContext.getResourceAsStream("WEB-INF/uploaded/" + imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        IOUtils.copy(in, response.getOutputStream());
    }
}
