package ru.otus.aivanov.home11.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
@RequiredArgsConstructor
public class BookController {

    @GetMapping("/")
    public String list(Model model) {
        return "book/list";
    }

    @GetMapping("/book/edit/{id}")
    public String edit(@PathVariable("id") long id, Model model) {
        model.addAttribute("referer", "/book/edit/" + id);
        model.addAttribute("id", id);
        return "book/edit";
    }

    @GetMapping("/book/new")
    public String edit(Model model) {
        model.addAttribute("referer", "/book/new");
        return "book/edit";
    }

}
