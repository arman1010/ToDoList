package todolist.task.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import todolist.task.model.ToDo;
import todolist.task.security.SpringUser;
import todolist.task.services.ToDoService;

import javax.imageio.ImageIO;
import javax.validation.Valid;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/todo")
public class ToDoController {
    @Value("${image.upload.dir}")
    private String imageUploadDir;

    private ToDoService toDoService;

    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @PostMapping("/add")
    public String add(@Valid ToDo toDo, @RequestParam("img") MultipartFile file, @AuthenticationPrincipal SpringUser springUser) throws IOException {
        if (!file.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            File output = new File(imageUploadDir + File.separator + fileName);
            if (!output.exists()){
                output.mkdirs();
            }
            BufferedImage image = ImageIO.read(file.getInputStream());
            if (image.getHeight() > 320 && image.getWidth() > 240) {
                BufferedImage resized = resize(image, 320, 240);
                ImageIO.write(resized, "png", output);
            } else {
                file.transferTo(output);
            }
            toDo.setPicture(fileName);
        }
        toDoService.save(toDo);
        if (springUser != null) {
            return "redirect:/admin";
        }
        return "redirect:/";
    }

    private static BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }


}
