package todolist.task.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import todolist.task.model.ToDo;
import todolist.task.model.ToDoStatus;
import todolist.task.services.ToDoService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class AdminController {
    private ToDoService toDoService;

    public AdminController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @GetMapping("/admin")
    public String admin(ModelMap modelMap,
                        @RequestParam("page") Optional<Integer> page,
                        @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(12);
        Page<ToDo> all = toDoService.findAll(PageRequest.of(currentPage - 1, pageSize));
        modelMap.addAttribute("todos", all);
        int totalPages = all.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            modelMap.addAttribute("pageNumbers", pageNumbers);
        }
        return "admin";
    }

    @GetMapping("/edit")
    public String etid(@RequestParam("id") int id, ModelMap modelMap) {
        Optional<ToDo> byId = toDoService.findById(id);
        if (byId.isPresent()) {
            modelMap.addAttribute("toDoStatuses", ToDoStatus.values());
            modelMap.addAttribute("toDo", byId.get());
        }
        return "editPage";
    }
}