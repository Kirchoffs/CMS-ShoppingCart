package com.syh.cmsshoppingcart.controllers;

import java.util.List;

import com.syh.cmsshoppingcart.models.CategoryRepository;
import com.syh.cmsshoppingcart.models.ProductRepository;
import com.syh.cmsshoppingcart.models.data.Category;
import com.syh.cmsshoppingcart.models.data.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/category")
public class CategoriesController {
    @Autowired
    private CategoryRepository categoryRepo;

    @Autowired
    private ProductRepository productRepo;

    @GetMapping("/{slug}")
    public String index(@PathVariable String slug, Model model,
            @RequestParam(value = "page", required = false) Integer p) {
        int perPage = 6;
        int page = (p != null) ? p : 0;
        Pageable pageable = PageRequest.of(page, perPage);
        long count = 0;

        if (slug.equals("all")) {
            Page<Product> products = productRepo.findAll(pageable);
            count = productRepo.count();
            model.addAttribute("products", products);
        } else {
            Category category = categoryRepo.findBySlug(slug);
            if (category == null) {
                return "redirect:/";
            }

            int categoryId = category.getId();
            String categoryName = category.getName();
            List<Product> products = productRepo.findAllByCategoryId(Integer.toString(categoryId), pageable);

            count = productRepo.countByCategoryId(Integer.toString(categoryId));
            model.addAttribute("products", products);
            model.addAttribute("categoryName", categoryName);
        }

        int pageCount = (int) Math.ceil((double) count / (double) perPage);

        model.addAttribute("pageCount", pageCount);
        model.addAttribute("perPage", perPage);
        model.addAttribute("count", count);
        model.addAttribute("page", page);

        return "products";
    }

    @GetMapping("/search/{keyWord}")
    public String searchResult(@PathVariable String keyWord, Model model,
            @RequestParam(value = "page", required = false) Integer p) {
        System.out.println("start");
        int perPage = 3;
        int page = (p != null) ? p : 0;
        Pageable pageable = PageRequest.of(page, perPage);

        Page<Product> products = productRepo.findByNameIgnoreCaseContaining(keyWord, pageable);
        model.addAttribute("products", products);
        model.addAttribute("keyWord", keyWord);

        long count = productRepo.countByNameIgnoreCaseContaining(keyWord);
        System.out.println(count);
        int pageCount = (int) Math.ceil((double) count / (double) perPage);

        model.addAttribute("pageCount", pageCount);
        model.addAttribute("perPage", perPage);
        model.addAttribute("count", count);
        model.addAttribute("page", page);

        return "search_result";
    }
}
