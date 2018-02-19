package org.launchcode.controllers;

import org.launchcode.models.Cheese;
import org.launchcode.models.Menu;
import org.launchcode.models.data.CheeseDao;
import org.launchcode.models.data.MenuDao;
import org.launchcode.models.forms.AddMenuItemForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("menu")
public class MenuController {

    @Autowired
    private CheeseDao cheeseDao;

    @Autowired
    private MenuDao menuDao;

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("menus", menuDao.findAll());
        model.addAttribute("title", "Menus");

        return "menu/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute(new Menu());
        model.addAttribute("title", "Add Menu");

        return "menu/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @Valid Menu menu, Errors errors) {

        if(errors.hasErrors()) {
            model.addAttribute("title", "Add Menu");
            return "menu/add";
        }

        menuDao.save(menu);

        return "redirect:/menu/view/" + menu.getId();
    }

    @RequestMapping(value ="view/{id}", method = RequestMethod.GET)
    public String viewMenu(Model model, @PathVariable("id") int menuId) {

        model.addAttribute("menu", menuDao.findOne(menuId));

        return "menu/view";
    }

    @RequestMapping(value = "add-item/{id}", method = RequestMethod.GET)
    public String addItem(Model model, @PathVariable("id") int menuId) {

        Menu menu = menuDao.findOne(menuId);
        Iterable<Cheese> cheeses = cheeseDao.findAll();

        model.addAttribute("form", new AddMenuItemForm(menu, cheeses));
        model.addAttribute("title", menu.getName());

        return "menu/add-item";
    }

    @RequestMapping(value ="add-item", method = RequestMethod.POST)
    public String addItem(@Valid AddMenuItemForm itemForm, Errors errors) {

        if(errors.hasErrors()) {
            return "menu/add-item";
        }

        Menu menu = menuDao.findOne(itemForm.getMenuId());
        Cheese cheese = cheeseDao.findOne(itemForm.getCheeseId());
        menu.addItem(cheese);

        menuDao.save(menu);

        return "redirect:/menu/view/" + menu.getId();
    }
}
