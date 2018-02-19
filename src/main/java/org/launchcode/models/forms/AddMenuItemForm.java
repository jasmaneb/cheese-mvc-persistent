package org.launchcode.models.forms;


import org.launchcode.models.Cheese;
import org.launchcode.models.Menu;

import javax.validation.constraints.NotNull;

public class AddMenuItemForm {

    @NotNull
    private int menuId;

    @NotNull
    private int cheeseId;

    private Menu menu;

    public AddMenuItemForm(Menu menu, Iterable<Cheese> cheeses) {
        this.menu = menu;
        this.cheeses = cheeses;
    }

    public AddMenuItemForm() {

    }

    private Iterable<Cheese> cheeses;

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public void setCheeseId(int cheeseId) {
        this.cheeseId = cheeseId;
    }

    public int getMenuId() {
        return menuId;
    }

    public int getCheeseId() {
        return cheeseId;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public void setCheeses(Iterable<Cheese> cheeses) {
        this.cheeses = cheeses;
    }

    public Menu getMenu() {

        return menu;
    }

    public Iterable<Cheese> getCheeses() {
        return cheeses;
    }
}
